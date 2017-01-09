using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.Design;
using System.IO;
using System.Linq;
using System.Resources;
using System.Text;
using System.Web;
using System.Windows.Forms;

namespace LocalizationToolUI
{
    public partial class StringLookup : Form
    {
        private readonly TfsHelper tfs;
        private readonly ResourceManager rm;
        private readonly BindingSource bindingSource;
        private List<KeyValuePair<string, ResourceMetadata>> searchResults;
        private const string SearchResultDisplayFormat = "{0}- {1}";
        private const string GenerateResourceCommandParmFormat = "{0} {1} {2} comment";
        private const string LocalizationToolExe = ".\\LocalizationTool.exe";
        
        public StringLookup(Uri tfsServerUrl)
        {
            this.rm = new ResourceManager();
            this.tfs = new TfsHelper(tfsServerUrl);
            InitializeComponent();

            bindingSource = new BindingSource { DataSource = SearchResultData };
            this.SearchResults.DataSource = bindingSource;
        }

        public BindingList<string> SearchResultData { get; set; }


        #region Event handlers
        private void LoadLocalizationXml_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrWhiteSpace(this.ResourceFilePathTextBox.Text))
            {
                if (File.Exists(this.ResourceFilePathTextBox.Text))
                {
                    try
                    {
                        this.tfs.CheckOutFile(this.ResourceFilePathTextBox.Text);                        
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Checkout failed - " + ex.Message);
                    }

                    try
                    {
                        this.rm.LoadResourceXml(this.ResourceFilePathTextBox.Text);
                        MessageBox.Show("Resource file successfully loaded!");
                    }
                    catch (Exception exception)
                    {
                        MessageBox.Show("Error creating ResourceManager - " + exception.Message);
                    }
                }
                else
                {
                    // invalid string.xml location
                    MessageBox.Show("Invalid location for string.xml");
                }                
            }
            else
            {
                // invalid string.xml location
                MessageBox.Show("Invalid location for string.xml");
            }
        }

        private void SearchStringButton_Click(object sender, EventArgs e)
        {
            try
            {
                this.DoSearchAndDisplay(this.StringTextbox.Text, false);

                // adjust platform check boxes
                if (this.SearchResultData != null && !this.SearchResultData.Any())
                {
                    this.SelectPlatformCheckbox.SetItemChecked((int) ResourcePlatform.iOS, false);
                    this.SelectPlatformCheckbox.SetItemChecked((int) ResourcePlatform.ANDROID, false);
                    this.SelectPlatformCheckbox.SetItemChecked((int) ResourcePlatform.WINDOWS, false);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        
        private void AddStringButton_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(this.ResourceIdTextbox.Text))
            {
                MessageBox.Show("Invalid Resource id");
                return;
            }

            if (string.IsNullOrWhiteSpace(this.ResourceValueTextbox.Text))
            {
                MessageBox.Show("Invalid resource value");
                return;
            }

            var selectedPlatforms = GetCheckedPlatforms();
            if (!selectedPlatforms.Any())
            {
                MessageBox.Show("Please select atleast one platform");
                return;
            }

            try
            {
                // check for duplicate resource id
                if (this.rm.IsDuplicateResourceId(this.ResourceIdTextbox.Text))
                {
                    MessageBox.Show(string.Format("{0} Already exists- please change resource id", this.ResourceIdTextbox.Text));
                    return;
                }

                // search duplicate string value
                this.DoSearch(this.ResourceValueTextbox.Text, true);
                
                // validation
                if (this.searchResults != null && this.searchResults.Any())
                {
                    if (!this.AddDuplicateStringCheckBox.Checked)
                    {
                        MessageBox.Show("Cannot add duplicate string - Please check \"Add duplicate string checkbox\" for adding duplicate string");
                        return;
                    }
                }
                
                this.rm.AddResourceData(this.ResourceIdTextbox.Text, this.ResourceValueTextbox.Text, null, selectedPlatforms);
                this.DoSearchAndDisplay(this.ResourceValueTextbox.Text, true);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void UpdateString_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(this.ResourceIdTextbox.Text))
            {
                MessageBox.Show("Invalid Resource id");
                return;
            }

            if (string.IsNullOrWhiteSpace(this.ResourceValueTextbox.Text))
            {
                MessageBox.Show("Invalid search string");
                return;
            }

            var selectedPlatforms = GetCheckedPlatforms();
            if (!selectedPlatforms.Any())
            {
                MessageBox.Show("Please select atleast one platform");
                return;
            }

            try
            {
                if (this.rm.IsDuplicateResourceId(this.ResourceIdTextbox.Text))
                {
                    this.rm.UpdateResourceData(this.ResourceIdTextbox.Text, this.ResourceValueTextbox.Text,
                                               selectedPlatforms);
                    this.DoSearchAndDisplay(this.ResourceValueTextbox.Text, true);
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void SaveXmlButton_Click(object sender, EventArgs e)
        {
            try 
            {
                // checkin file here
                if (!string.IsNullOrWhiteSpace(this.ResourceFilePathTextBox.Text) &&
                    File.Exists(this.ResourceFilePathTextBox.Text))
                {
                    this.tfs.CheckInFile(this.ResourceFilePathTextBox.Text);
                    MessageBox.Show("Checkin Successful!");
                }
                else
                {
                    // invalid string.xml location
                    MessageBox.Show("Invalid location for resource xml");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void SearchResults_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (this.SearchResults.SelectedIndex >= 0)
            {
                var result = this.searchResults[this.SearchResults.SelectedIndex];
                this.SelectPlatformCheckbox.SetItemChecked((int) ResourcePlatform.iOS, result.Value.ResourcePlatform.Contains(ResourcePlatform.iOS));
                this.SelectPlatformCheckbox.SetItemChecked((int)ResourcePlatform.ANDROID, result.Value.ResourcePlatform.Contains(ResourcePlatform.ANDROID));
                this.SelectPlatformCheckbox.SetItemChecked((int)ResourcePlatform.WINDOWS, result.Value.ResourcePlatform.Contains(ResourcePlatform.WINDOWS));

                // update text boxes with selection
                this.ResourceIdTextbox.Text = result.Key;
                this.ResourceValueTextbox.Text = result.Value.Value;
            }
        }

        private void StringLookup_FormClosing(object sender, FormClosingEventArgs e)
        {
            // not required
        }

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.RevertResourceFileOnClose();
        }

        private void GenerateResourceFiles_Click(object sender, EventArgs e)
        {
            var statusText = new StringBuilder("Resource file generation for ");
            string commandParamString = string.Empty;
            bool atleastOnePlatformChecked = false;
            
            // validations
            if (string.IsNullOrWhiteSpace(this.ResourceFilePathTextBox.Text) || !File.Exists(ResourceFilePathTextBox.Text))
            {
                MessageBox.Show("Resource file location doesn't exist");
                return;
            }

            if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.iOS))
            {
                atleastOnePlatformChecked = true;
                if (string.IsNullOrWhiteSpace(this.IOSResourceFileLocationTextBox.Text))
                {
                    MessageBox.Show("Enter valid drop location for all checked platforms");
                    return;
                }                
            }

            if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.ANDROID))
            {
                atleastOnePlatformChecked = true;
                if (string.IsNullOrWhiteSpace(this.AndroidResourceFileLocationTextBox.Text))
                {
                    MessageBox.Show("Enter valid drop location for all checked platforms");
                    return;
                }
            }

            if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.WINDOWS))
            {
                atleastOnePlatformChecked = true;
                if (string.IsNullOrWhiteSpace(this.WindowsResourceFileLocationTextBox.Text))
                {
                    MessageBox.Show("Enter valid drop location for all checked platforms");
                    return;
                }
            }

            if (!atleastOnePlatformChecked)
            {
                MessageBox.Show("Select platform before generating resources");
                return;
            }

            try
            {
                if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.iOS))
                {
                    commandParamString = string.Format(GenerateResourceCommandParmFormat, "-i",
                                                       this.ResourceFilePathTextBox.Text, this.IOSResourceFileLocationTextBox.Text);
                    statusText.Append(ResourcePlatform.iOS.ToString());
                    statusText.Append(" ");
                    RunLocalizationTool(commandParamString, statusText.ToString());
                }

                if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.ANDROID))
                {
                    commandParamString = string.Format(GenerateResourceCommandParmFormat, "-a",
                                                       this.ResourceFilePathTextBox.Text, this.AndroidResourceFileLocationTextBox.Text);
                    statusText.Append(ResourcePlatform.ANDROID.ToString());
                    statusText.Append(" ");
                    RunLocalizationTool(commandParamString, statusText.ToString());
                }

                if (this.ResourcePlatformCheckbox.GetItemChecked((int)ResourcePlatform.WINDOWS))
                {
                    commandParamString = string.Format(GenerateResourceCommandParmFormat, "-w",
                                                       this.ResourceFilePathTextBox.Text, this.WindowsResourceFileLocationTextBox.Text);
                    statusText.Append(ResourcePlatform.WINDOWS.ToString());
                    statusText.Append(" ");
                    RunLocalizationTool(commandParamString, statusText.ToString());
                }                
            }
            catch (Exception ex)
            {
                MessageBox.Show(string.Format("Error generating resource files - {0}", ex.Message));
            }
        }

        private void WindowsDropLocation_Click(object sender, EventArgs e)
        {
            var openFileDialog1 = new OpenFileDialog
                {
                    InitialDirectory = "c:\\",
                    Filter = "resx files (*.resx)|*.resx",
                    FilterIndex = 1,
                    RestoreDirectory = false
                };

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = openFileDialog1.FileName;
                this.WindowsResourceFileLocationTextBox.Text = filePath;
            }
        }

        private void AndroidDropLocation_Click(object sender, EventArgs e)
        {
            var openFileDialog1 = new OpenFileDialog
                {
                    InitialDirectory = "c:\\",
                    Filter = "xml files (*.xml)|*.xml",
                    FilterIndex = 1,
                    RestoreDirectory = true
                };

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = openFileDialog1.FileName;
                this.AndroidResourceFileLocationTextBox.Text = filePath;
            }
        }

        private void IOSDropLocation_Click(object sender, EventArgs e)
        {
            var openFileDialog1 = new OpenFileDialog
                {
                    InitialDirectory = "c:\\",
                    Filter = "strings files (*.strings)|*.strings",
                    FilterIndex = 1,
                    RestoreDirectory = true
                };

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = openFileDialog1.FileName;
                this.IOSResourceFileLocationTextBox.Text = filePath;
            }
        }

        private void SelectResourceFile_Click(object sender, EventArgs e)
        {
            var openFileDialog1 = new OpenFileDialog
            {
                InitialDirectory = "c:\\",
                Filter = "xml files (*.xml)|*.xml",
                FilterIndex = 1,
                RestoreDirectory = true
            };

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = openFileDialog1.FileName;
                this.ResourceFilePathTextBox.Text = filePath;
            }
        }



        #endregion

        #region Private methods

        private void RunLocalizationTool(string commandParamString, string statusText)
        {
            var procStartInfo =
                    new System.Diagnostics.ProcessStartInfo(
                        LocalizationToolExe, commandParamString)
                    {
                        RedirectStandardOutput = true,
                        UseShellExecute = false,
                        CreateNoWindow = true
                    };

            var proc = new System.Diagnostics.Process {StartInfo = procStartInfo};
            proc.Start();
            var result = proc.StandardOutput.ReadToEnd();

            if (string.IsNullOrWhiteSpace(result) || result.Trim().Equals("success", StringComparison.InvariantCultureIgnoreCase))
                MessageBox.Show("Resource files generated");
            else
            {
                throw new Exception(statusText + " failed");
            }
        }

        private List<ResourcePlatform> GetCheckedPlatforms()
        {
            var resourcePlatforms = new List<ResourcePlatform>();
            
            if (this.SelectPlatformCheckbox.GetItemChecked((int)ResourcePlatform.iOS))
            {
                resourcePlatforms.Add(ResourcePlatform.iOS);
            }
            if (this.SelectPlatformCheckbox.GetItemChecked((int)ResourcePlatform.ANDROID))
            {
                resourcePlatforms.Add(ResourcePlatform.ANDROID);
            }
            if (this.SelectPlatformCheckbox.GetItemChecked((int)ResourcePlatform.WINDOWS))
            {
                resourcePlatforms.Add(ResourcePlatform.WINDOWS);
            }

            return resourcePlatforms;
        }

        private void DoSearchAndDisplay(string searchStr, bool exact)
        {
            this.DoSearch(searchStr, exact);

            if (this.searchResults != null && this.searchResults.Any())
            {
                this.SearchResultData =
                    new BindingList<string>(
                        searchResults.Select(
                            item => string.Format(SearchResultDisplayFormat, item.Key.Length < 50 ? item.Key.PadRight(" ".Length*50-item.Key.Length) : item.Key, item.Value.Value)).ToList());

                this.SearchResults.DataSource = this.SearchResultData;

                var index = this.SearchResults.SelectedIndex;
                if (index >= 0)
                {
                    var result = this.searchResults[index];

                    this.SelectPlatformCheckbox.SetItemChecked((int)ResourcePlatform.iOS, result.Value.ResourcePlatform.Contains(ResourcePlatform.iOS));
                    this.SelectPlatformCheckbox.SetItemChecked((int)ResourcePlatform.ANDROID, result.Value.ResourcePlatform.Contains(ResourcePlatform.ANDROID));
                    this.SelectPlatformCheckbox.SetItemChecked((int)ResourcePlatform.WINDOWS, result.Value.ResourcePlatform.Contains(ResourcePlatform.WINDOWS));

                }
            }
            else
            {
                if (this.SearchResultData != null)
                    this.SearchResultData.Clear();                    
            }                        
        }

        private void DoSearch(string searchStr, bool exact)
        {
            if (!string.IsNullOrWhiteSpace(searchStr) && this.rm != null && this.rm.ResourceData != null)
            {
                this.searchResults = this.rm.SearchString(searchStr, exact);
            }
            else
            {
                if (this.rm == null || this.rm.ResourceData == null)
                {
                    throw new Exception("No resources loaded");
                }

                if (string.IsNullOrWhiteSpace(searchStr))
                {
                    throw new Exception("Empty search string");
                }                                
            }
        }

        private void RevertResourceFileOnClose()
        {
            try
            {
                if (!string.IsNullOrWhiteSpace(this.ResourceFilePathTextBox.Text))
                {
                    this.tfs.UndoCheckout(this.ResourceFilePathTextBox.Text);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        #endregion                                        

        private void AddToMaster_Click(object sender, EventArgs e)
        {
            ResXResourceReader rr = new ResXResourceReader(this.ResxFile.Text);

            rr.UseResXDataNodes = true;

            IDictionaryEnumerator dict = rr.GetEnumerator();
            while (dict.MoveNext())
            {
                var dataNode = dict.Value as ResXDataNode;
                this.rm.AddResourceData(dict.Key as string, HttpUtility.HtmlEncode(dataNode.GetValue((ITypeResolutionService)null) as string), dataNode.Comment, new List<ResourcePlatform>() { ResourcePlatform.WINDOWS, ResourcePlatform.iOS, ResourcePlatform.ANDROID });
            }                
        }

       
        
        
    }
}
