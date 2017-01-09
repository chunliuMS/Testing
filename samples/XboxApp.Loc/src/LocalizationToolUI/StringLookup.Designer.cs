namespace LocalizationToolUI
{
    partial class StringLookup
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.SearchStringButton = new System.Windows.Forms.Button();
            this.StringTextbox = new System.Windows.Forms.TextBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.SearchResults = new System.Windows.Forms.ListBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.SelectResourceFile = new System.Windows.Forms.Button();
            this.LoadXmlButton = new System.Windows.Forms.Button();
            this.ResourceFilePathTextBox = new System.Windows.Forms.TextBox();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.ResourceValueTextbox = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.UpdateString = new System.Windows.Forms.Button();
            this.SelectPlatformCheckbox = new System.Windows.Forms.CheckedListBox();
            this.AddDuplicateStringCheckBox = new System.Windows.Forms.CheckBox();
            this.ResourceIdTextbox = new System.Windows.Forms.TextBox();
            this.AddStringButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox6 = new System.Windows.Forms.GroupBox();
            this.IOSDropLocation = new System.Windows.Forms.Button();
            this.AndroidDropLocation = new System.Windows.Forms.Button();
            this.WindowsDropLocation = new System.Windows.Forms.Button();
            this.IOSResourceFileLocationTextBox = new System.Windows.Forms.TextBox();
            this.AndroidResourceFileLocationTextBox = new System.Windows.Forms.TextBox();
            this.WindowsResourceFileLocationTextBox = new System.Windows.Forms.TextBox();
            this.ResourcePlatformCheckbox = new System.Windows.Forms.CheckedListBox();
            this.GenerateResourceFiles = new System.Windows.Forms.Button();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.CloseButton = new System.Windows.Forms.Button();
            this.SaveXmlButton = new System.Windows.Forms.Button();
            this.ResxFile = new System.Windows.Forms.TextBox();
            this.AddToMaster = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.groupBox6.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.groupBox2);
            this.splitContainer1.Panel1.Controls.Add(this.groupBox3);
            this.splitContainer1.Panel1.Controls.Add(this.groupBox1);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.AddToMaster);
            this.splitContainer1.Panel2.Controls.Add(this.ResxFile);
            this.splitContainer1.Panel2.Controls.Add(this.groupBox5);
            this.splitContainer1.Panel2.Controls.Add(this.groupBox6);
            this.splitContainer1.Panel2.Controls.Add(this.groupBox4);
            this.splitContainer1.Size = new System.Drawing.Size(590, 762);
            this.splitContainer1.SplitterDistance = 324;
            this.splitContainer1.TabIndex = 0;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.SearchStringButton);
            this.groupBox2.Controls.Add(this.StringTextbox);
            this.groupBox2.Location = new System.Drawing.Point(12, 94);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(564, 66);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "2. Search for string (case insensitive)";
            // 
            // SearchStringButton
            // 
            this.SearchStringButton.Location = new System.Drawing.Point(414, 17);
            this.SearchStringButton.Name = "SearchStringButton";
            this.SearchStringButton.Size = new System.Drawing.Size(134, 23);
            this.SearchStringButton.TabIndex = 1;
            this.SearchStringButton.Text = "Search String";
            this.SearchStringButton.UseVisualStyleBackColor = true;
            this.SearchStringButton.Click += new System.EventHandler(this.SearchStringButton_Click);
            // 
            // StringTextbox
            // 
            this.StringTextbox.Location = new System.Drawing.Point(20, 20);
            this.StringTextbox.Name = "StringTextbox";
            this.StringTextbox.Size = new System.Drawing.Size(388, 20);
            this.StringTextbox.TabIndex = 0;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.SearchResults);
            this.groupBox3.Location = new System.Drawing.Point(13, 166);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(566, 186);
            this.groupBox3.TabIndex = 0;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "3. Search Result";
            // 
            // SearchResults
            // 
            this.SearchResults.FormattingEnabled = true;
            this.SearchResults.Location = new System.Drawing.Point(20, 19);
            this.SearchResults.Name = "SearchResults";
            this.SearchResults.Size = new System.Drawing.Size(533, 160);
            this.SearchResults.TabIndex = 0;
            this.SearchResults.SelectedIndexChanged += new System.EventHandler(this.SearchResults_SelectedIndexChanged);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.SelectResourceFile);
            this.groupBox1.Controls.Add(this.LoadXmlButton);
            this.groupBox1.Controls.Add(this.ResourceFilePathTextBox);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(564, 76);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "1. Check out resource file (Required)";
            // 
            // SelectResourceFile
            // 
            this.SelectResourceFile.Location = new System.Drawing.Point(416, 16);
            this.SelectResourceFile.Name = "SelectResourceFile";
            this.SelectResourceFile.Size = new System.Drawing.Size(132, 23);
            this.SelectResourceFile.TabIndex = 2;
            this.SelectResourceFile.Text = "Browse Resource File";
            this.SelectResourceFile.UseVisualStyleBackColor = true;
            this.SelectResourceFile.Click += new System.EventHandler(this.SelectResourceFile_Click);
            // 
            // LoadXmlButton
            // 
            this.LoadXmlButton.AllowDrop = true;
            this.LoadXmlButton.Location = new System.Drawing.Point(416, 47);
            this.LoadXmlButton.Name = "LoadXmlButton";
            this.LoadXmlButton.Size = new System.Drawing.Size(132, 23);
            this.LoadXmlButton.TabIndex = 1;
            this.LoadXmlButton.Text = "Checkout Resource File";
            this.LoadXmlButton.UseVisualStyleBackColor = true;
            this.LoadXmlButton.Click += new System.EventHandler(this.LoadLocalizationXml_Click);
            // 
            // ResourceFilePathTextBox
            // 
            this.ResourceFilePathTextBox.Location = new System.Drawing.Point(21, 20);
            this.ResourceFilePathTextBox.Name = "ResourceFilePathTextBox";
            this.ResourceFilePathTextBox.Size = new System.Drawing.Size(388, 20);
            this.ResourceFilePathTextBox.TabIndex = 0;
            this.ResourceFilePathTextBox.Text = "Enter location of string.xml on your enlistment";
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.ResourceValueTextbox);
            this.groupBox5.Controls.Add(this.label2);
            this.groupBox5.Controls.Add(this.UpdateString);
            this.groupBox5.Controls.Add(this.SelectPlatformCheckbox);
            this.groupBox5.Controls.Add(this.AddDuplicateStringCheckBox);
            this.groupBox5.Controls.Add(this.ResourceIdTextbox);
            this.groupBox5.Controls.Add(this.AddStringButton);
            this.groupBox5.Controls.Add(this.label1);
            this.groupBox5.Location = new System.Drawing.Point(15, 3);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(554, 150);
            this.groupBox5.TabIndex = 0;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "4. Add String";
            // 
            // ResourceValueTextbox
            // 
            this.ResourceValueTextbox.Location = new System.Drawing.Point(210, 76);
            this.ResourceValueTextbox.Name = "ResourceValueTextbox";
            this.ResourceValueTextbox.Size = new System.Drawing.Size(311, 20);
            this.ResourceValueTextbox.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(207, 59);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(82, 13);
            this.label2.TabIndex = 9;
            this.label2.Text = "Resource value";
            // 
            // UpdateString
            // 
            this.UpdateString.Location = new System.Drawing.Point(412, 118);
            this.UpdateString.Name = "UpdateString";
            this.UpdateString.Size = new System.Drawing.Size(109, 23);
            this.UpdateString.TabIndex = 5;
            this.UpdateString.Text = "Update String";
            this.UpdateString.UseVisualStyleBackColor = true;
            this.UpdateString.Click += new System.EventHandler(this.UpdateString_Click);
            // 
            // SelectPlatformCheckbox
            // 
            this.SelectPlatformCheckbox.FormattingEnabled = true;
            this.SelectPlatformCheckbox.Items.AddRange(new object[] {
            "iOS",
            "Android",
            "Windows"});
            this.SelectPlatformCheckbox.Location = new System.Drawing.Point(18, 19);
            this.SelectPlatformCheckbox.Name = "SelectPlatformCheckbox";
            this.SelectPlatformCheckbox.Size = new System.Drawing.Size(162, 64);
            this.SelectPlatformCheckbox.TabIndex = 0;
            // 
            // AddDuplicateStringCheckBox
            // 
            this.AddDuplicateStringCheckBox.AutoSize = true;
            this.AddDuplicateStringCheckBox.Location = new System.Drawing.Point(20, 118);
            this.AddDuplicateStringCheckBox.Name = "AddDuplicateStringCheckBox";
            this.AddDuplicateStringCheckBox.Size = new System.Drawing.Size(119, 17);
            this.AddDuplicateStringCheckBox.TabIndex = 3;
            this.AddDuplicateStringCheckBox.Text = "Add duplicate string";
            this.AddDuplicateStringCheckBox.UseVisualStyleBackColor = true;
            // 
            // ResourceIdTextbox
            // 
            this.ResourceIdTextbox.Location = new System.Drawing.Point(209, 32);
            this.ResourceIdTextbox.Name = "ResourceIdTextbox";
            this.ResourceIdTextbox.Size = new System.Drawing.Size(312, 20);
            this.ResourceIdTextbox.TabIndex = 1;
            // 
            // AddStringButton
            // 
            this.AddStringButton.Location = new System.Drawing.Point(209, 119);
            this.AddStringButton.Name = "AddStringButton";
            this.AddStringButton.Size = new System.Drawing.Size(125, 22);
            this.AddStringButton.TabIndex = 4;
            this.AddStringButton.Text = "Add String";
            this.AddStringButton.UseVisualStyleBackColor = true;
            this.AddStringButton.Click += new System.EventHandler(this.AddStringButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(206, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(65, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Resource Id";
            // 
            // groupBox6
            // 
            this.groupBox6.Controls.Add(this.IOSDropLocation);
            this.groupBox6.Controls.Add(this.AndroidDropLocation);
            this.groupBox6.Controls.Add(this.WindowsDropLocation);
            this.groupBox6.Controls.Add(this.IOSResourceFileLocationTextBox);
            this.groupBox6.Controls.Add(this.AndroidResourceFileLocationTextBox);
            this.groupBox6.Controls.Add(this.WindowsResourceFileLocationTextBox);
            this.groupBox6.Controls.Add(this.ResourcePlatformCheckbox);
            this.groupBox6.Controls.Add(this.GenerateResourceFiles);
            this.groupBox6.Location = new System.Drawing.Point(17, 225);
            this.groupBox6.Name = "groupBox6";
            this.groupBox6.Size = new System.Drawing.Size(563, 124);
            this.groupBox6.TabIndex = 2;
            this.groupBox6.TabStop = false;
            this.groupBox6.Text = "6. Generate platform specific resource files";
            // 
            // IOSDropLocation
            // 
            this.IOSDropLocation.Location = new System.Drawing.Point(426, 70);
            this.IOSDropLocation.Name = "IOSDropLocation";
            this.IOSDropLocation.Size = new System.Drawing.Size(126, 23);
            this.IOSDropLocation.TabIndex = 6;
            this.IOSDropLocation.Text = "Browse to strings file";
            this.IOSDropLocation.UseVisualStyleBackColor = true;
            this.IOSDropLocation.Click += new System.EventHandler(this.IOSDropLocation_Click);
            // 
            // AndroidDropLocation
            // 
            this.AndroidDropLocation.Location = new System.Drawing.Point(426, 44);
            this.AndroidDropLocation.Name = "AndroidDropLocation";
            this.AndroidDropLocation.Size = new System.Drawing.Size(126, 23);
            this.AndroidDropLocation.TabIndex = 4;
            this.AndroidDropLocation.Text = "Browse to strings xml";
            this.AndroidDropLocation.UseVisualStyleBackColor = true;
            this.AndroidDropLocation.Click += new System.EventHandler(this.AndroidDropLocation_Click);
            // 
            // WindowsDropLocation
            // 
            this.WindowsDropLocation.Location = new System.Drawing.Point(426, 18);
            this.WindowsDropLocation.Name = "WindowsDropLocation";
            this.WindowsDropLocation.Size = new System.Drawing.Size(128, 23);
            this.WindowsDropLocation.TabIndex = 2;
            this.WindowsDropLocation.Text = "Browse to RESX file";
            this.WindowsDropLocation.UseVisualStyleBackColor = true;
            this.WindowsDropLocation.Click += new System.EventHandler(this.WindowsDropLocation_Click);
            // 
            // IOSResourceFileLocationTextBox
            // 
            this.IOSResourceFileLocationTextBox.Location = new System.Drawing.Point(145, 71);
            this.IOSResourceFileLocationTextBox.Name = "IOSResourceFileLocationTextBox";
            this.IOSResourceFileLocationTextBox.Size = new System.Drawing.Size(275, 20);
            this.IOSResourceFileLocationTextBox.TabIndex = 5;
            this.IOSResourceFileLocationTextBox.Text = "Enter path including filename";
            // 
            // AndroidResourceFileLocationTextBox
            // 
            this.AndroidResourceFileLocationTextBox.Location = new System.Drawing.Point(145, 45);
            this.AndroidResourceFileLocationTextBox.Name = "AndroidResourceFileLocationTextBox";
            this.AndroidResourceFileLocationTextBox.Size = new System.Drawing.Size(275, 20);
            this.AndroidResourceFileLocationTextBox.TabIndex = 3;
            this.AndroidResourceFileLocationTextBox.Text = "Enter path including filename";
            // 
            // WindowsResourceFileLocationTextBox
            // 
            this.WindowsResourceFileLocationTextBox.Location = new System.Drawing.Point(145, 19);
            this.WindowsResourceFileLocationTextBox.Name = "WindowsResourceFileLocationTextBox";
            this.WindowsResourceFileLocationTextBox.Size = new System.Drawing.Size(275, 20);
            this.WindowsResourceFileLocationTextBox.TabIndex = 1;
            this.WindowsResourceFileLocationTextBox.Text = "Enter path including filename";
            // 
            // ResourcePlatformCheckbox
            // 
            this.ResourcePlatformCheckbox.FormattingEnabled = true;
            this.ResourcePlatformCheckbox.Items.AddRange(new object[] {
            "iOS",
            "Android",
            "Windows"});
            this.ResourcePlatformCheckbox.Location = new System.Drawing.Point(19, 17);
            this.ResourcePlatformCheckbox.Name = "ResourcePlatformCheckbox";
            this.ResourcePlatformCheckbox.Size = new System.Drawing.Size(120, 64);
            this.ResourcePlatformCheckbox.TabIndex = 0;
            // 
            // GenerateResourceFiles
            // 
            this.GenerateResourceFiles.Location = new System.Drawing.Point(426, 99);
            this.GenerateResourceFiles.Name = "GenerateResourceFiles";
            this.GenerateResourceFiles.Size = new System.Drawing.Size(126, 23);
            this.GenerateResourceFiles.TabIndex = 7;
            this.GenerateResourceFiles.Text = "Generate";
            this.GenerateResourceFiles.UseVisualStyleBackColor = true;
            this.GenerateResourceFiles.Click += new System.EventHandler(this.GenerateResourceFiles_Click);
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.CloseButton);
            this.groupBox4.Controls.Add(this.SaveXmlButton);
            this.groupBox4.Location = new System.Drawing.Point(15, 168);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(565, 51);
            this.groupBox4.TabIndex = 1;
            this.groupBox4.TabStop = false;
            this.groupBox4.Tag = "";
            this.groupBox4.Text = "5. Checkin or Undo resource file";
            // 
            // CloseButton
            // 
            this.CloseButton.Location = new System.Drawing.Point(350, 19);
            this.CloseButton.Name = "CloseButton";
            this.CloseButton.Size = new System.Drawing.Size(171, 23);
            this.CloseButton.TabIndex = 1;
            this.CloseButton.Text = "Undo Checkout on resource xml";
            this.CloseButton.UseVisualStyleBackColor = true;
            this.CloseButton.Click += new System.EventHandler(this.CloseButton_Click);
            // 
            // SaveXmlButton
            // 
            this.SaveXmlButton.Location = new System.Drawing.Point(30, 19);
            this.SaveXmlButton.Name = "SaveXmlButton";
            this.SaveXmlButton.Size = new System.Drawing.Size(169, 23);
            this.SaveXmlButton.TabIndex = 0;
            this.SaveXmlButton.Text = "Check-in Resource xml";
            this.SaveXmlButton.UseVisualStyleBackColor = true;
            this.SaveXmlButton.Click += new System.EventHandler(this.SaveXmlButton_Click);
            // 
            // ResxFile
            // 
            this.ResxFile.Location = new System.Drawing.Point(36, 374);
            this.ResxFile.Name = "ResxFile";
            this.ResxFile.Size = new System.Drawing.Size(384, 20);
            this.ResxFile.TabIndex = 3;
            // 
            // AddToMaster
            // 
            this.AddToMaster.Location = new System.Drawing.Point(443, 370);
            this.AddToMaster.Name = "AddToMaster";
            this.AddToMaster.Size = new System.Drawing.Size(117, 23);
            this.AddToMaster.TabIndex = 4;
            this.AddToMaster.Text = "AddToMaster";
            this.AddToMaster.UseVisualStyleBackColor = true;
            this.AddToMaster.Click += new System.EventHandler(this.AddToMaster_Click);
            // 
            // StringLookup
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(590, 762);
            this.Controls.Add(this.splitContainer1);
            this.Name = "StringLookup";
            this.Text = "Localization Resource Tool";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.StringLookup_FormClosing);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            this.splitContainer1.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox5.ResumeLayout(false);
            this.groupBox5.PerformLayout();
            this.groupBox6.ResumeLayout(false);
            this.groupBox6.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.CheckedListBox SelectPlatformCheckbox;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button SearchStringButton;
        private System.Windows.Forms.TextBox StringTextbox;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button LoadXmlButton;
        private System.Windows.Forms.TextBox ResourceFilePathTextBox;
        private System.Windows.Forms.Button AddStringButton;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.Button CloseButton;
        private System.Windows.Forms.Button SaveXmlButton;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.ListBox SearchResults;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox ResourceIdTextbox;
        private System.Windows.Forms.CheckBox AddDuplicateStringCheckBox;
        private System.Windows.Forms.Button UpdateString;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.GroupBox groupBox6;
        private System.Windows.Forms.CheckedListBox ResourcePlatformCheckbox;
        private System.Windows.Forms.Button GenerateResourceFiles;
        private System.Windows.Forms.TextBox ResourceValueTextbox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button IOSDropLocation;
        private System.Windows.Forms.Button AndroidDropLocation;
        private System.Windows.Forms.Button WindowsDropLocation;
        private System.Windows.Forms.TextBox IOSResourceFileLocationTextBox;
        private System.Windows.Forms.TextBox AndroidResourceFileLocationTextBox;
        private System.Windows.Forms.TextBox WindowsResourceFileLocationTextBox;
        private System.Windows.Forms.Button SelectResourceFile;
        private System.Windows.Forms.Button AddToMaster;
        private System.Windows.Forms.TextBox ResxFile;

    }
}

