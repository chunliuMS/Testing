using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace LocalizationToolUI
{
    public class ResourceManager
    {
        #region Private members
        
        private const string StringElementTag = "String";
        private const string ResourceIdAttributeTag = "ResID";
        private const string ResourceElementTag = "Resource";
        private const string LocCommentTag = "_locComment";
        private const string StringXmlNodeFormat = "\r\n    <String ResID=\"{0}\" >\r\n        <!--_locID_text=\"{0}\" _locComment=\"{1}, {2}\"-->{3}</String>\r\n";
        
        private Dictionary<string, ResourceMetadata> resourceData;
        private string resourceFile;

        #endregion

        #region Public properties

        public Dictionary<string, ResourceMetadata> ResourceData
        {
            get { return this.resourceData; }
        }

        #endregion

        #region Public methods

        public void LoadResourceXml(string resourceFile)
        {
            this.resourceFile = resourceFile;
            using (var reader = new XmlTextReader(resourceFile))
            {
                this.resourceData = new Dictionary<string, ResourceMetadata>();

                var resId = string.Empty;
                var resValue = string.Empty;
                var isResourceString = false;
                List<ResourcePlatform> stringPlatforms = null;

                while (reader.Read())
                {
                    if (reader.NodeType == XmlNodeType.Element &&
                        reader.Name.Equals(StringElementTag, StringComparison.InvariantCultureIgnoreCase))
                    {
                        isResourceString = true;
                        resId = reader.GetAttribute(ResourceIdAttributeTag);
                    }
                    else if (reader.NodeType == XmlNodeType.Text && isResourceString)
                    {
                        resValue = reader.Value;
                    }
                    else if (reader.NodeType == XmlNodeType.Comment)
                    {
                        stringPlatforms = GetSupportedPlatforms(reader.Value);
                    }
                    else if (reader.NodeType == XmlNodeType.EndElement &&
                             reader.Name.Equals(StringElementTag, StringComparison.InvariantCultureIgnoreCase))
                    {
                        isResourceString = false;
                        if (resId != null && !resourceData.ContainsKey(resId))
                        {
                            resourceData.Add(resId, new ResourceMetadata()
                                {
                                    ResourcePlatform = stringPlatforms,
                                    Value = resValue
                                });
                        }
                        else
                        {
                            throw new Exception("Invalid or duplicate resource ID found in resource file");
                        }
                    }
                }
            }
        }

        public void UpdateResourceData(string resourceId, string value, List<ResourcePlatform> platforms)
        {
            resourceId = resourceId.Trim();

            var xmlData = new XmlDocument {PreserveWhitespace = true};
            xmlData.Load(this.resourceFile);

            var selectedNode = xmlData.SelectSingleNode(string.Format("descendant::{0}[@{1}='{2}']", StringElementTag, ResourceIdAttributeTag, resourceId)) as XmlElement;
            if (selectedNode != null)
            {
                selectedNode.LastChild.Value = value;

                XmlNode locCommentElement = selectedNode;

                for (int i = 0; i < selectedNode.ChildNodes.Count; i++)
                {
                    if (selectedNode.ChildNodes[i] == null || selectedNode.ChildNodes[i].Value == null)
                        continue;

                    if (selectedNode.ChildNodes[i].Value.Contains(LocCommentTag))
                    {
                        locCommentElement = selectedNode.ChildNodes[i];
                        break;
                    }
                }
                
                // replaces node with new platforms
                UpdateLocCommentValue(locCommentElement, platforms);
                
                // update resource file
                using (var writer = new XmlTextWriter(this.resourceFile, Encoding.UTF8))
                {
                    xmlData.WriteTo(writer);
                }

                this.resourceData[resourceId] = new ResourceMetadata()
                                                                {
                                                                    ResourcePlatform = platforms,
                                                                    Value = value
                                                                };                
            }            
        }

        private void UpdateLocCommentValue(XmlNode locCommentElement, List<ResourcePlatform> platforms)
        {
            if (locCommentElement == null)
                return;

            var position = locCommentElement.Value.IndexOf(LocCommentTag, StringComparison.InvariantCultureIgnoreCase);

            var outputLocCommentValue = new StringBuilder();

            outputLocCommentValue.Append(locCommentElement.Value.Substring(0, position + LocCommentTag.Length + 2));

            var value = locCommentElement.Value.Substring(position + LocCommentTag.Length + 2);
            var data = value.Split(new[] { ' ' }, StringSplitOptions.None);

            if (data.Any())
            {
                data[0] = string.Join(",", platforms.ToArray());
                data[0] = string.Format("{0},", data[0]);
            }

            locCommentElement.Value = outputLocCommentValue.Append(string.Join(" ", data)).ToString();            
        }

        public void AddResourceData(string resourceId, string value, string comment, List<ResourcePlatform> platforms)
        {
            if (this.resourceData.ContainsKey(resourceId))
                return;

            resourceId = resourceId.Trim();

            // node creation
            var stringXmlNode = CreateStringXmlNode(resourceId, value, comment, platforms.ToArray());
            
            // add node to string xml
            AppendStringToResourceFile(stringXmlNode);

            // update in memory copy of data
            this.resourceData.Add(resourceId, new ResourceMetadata()
                {
                    ResourcePlatform = platforms,
                    Value = value
                });            
        }

        private void AppendStringToResourceFile(string stringXmlNode)
        {
            if (!string.IsNullOrWhiteSpace(stringXmlNode))
            {
                var xmlData = new XmlDocument {PreserveWhitespace = true};
                xmlData.Load(resourceFile);

                var resourceElement = xmlData.GetElementsByTagName(ResourceElementTag);

                if (resourceElement.Count == 1)
                {
                    var fragment = xmlData.CreateDocumentFragment();
                    fragment.InnerXml = stringXmlNode;
                    resourceElement[0].AppendChild(fragment);
                    
                    using (var writer = new XmlTextWriter(this.resourceFile, Encoding.UTF8))
                    {                        
                        xmlData.WriteTo(writer);                    
                    }                    
                }
                else
                {
                    throw new Exception("More than one \"Resource\" elements found in resource file");
                } 
            }
        }

        private string CreateStringXmlNode(string resourceId, string value, string comment, ResourcePlatform[] platforms)
        {
            var stringPlatforms = string.Join(",", platforms.Select(x => x.ToString()).ToArray());
            return string.Format(StringXmlNodeFormat, resourceId, stringPlatforms, comment, value);
        }

        private List<ResourcePlatform> GetSupportedPlatforms(string value)
        {
            var stringPlatforms = new List<ResourcePlatform>();
            if (!string.IsNullOrWhiteSpace(value))
            {
                // TODO- add more robust parsing. For now this code just parses enough to get "_locComment" from string node
                var commentData = value.Split(new [] {' '}, StringSplitOptions.RemoveEmptyEntries);
                
                string locComment = commentData.FirstOrDefault(x => x.ToLower().Contains("_locComment".ToLower()));
                string locCommentValue = string.Empty;
                if (locComment != null)
                {
                    locCommentValue = locComment.Split(new char[] {'='}, StringSplitOptions.RemoveEmptyEntries).LastOrDefault();
                }
               
                if (!string.IsNullOrWhiteSpace(locCommentValue))
                {                    
                    if (locCommentValue.ToLower().Contains(ResourcePlatform.iOS.ToString().ToLower()))
                        stringPlatforms.Add(ResourcePlatform.iOS);
                    if (locCommentValue.ToLower().Contains(ResourcePlatform.ANDROID.ToString().ToLower()))
                        stringPlatforms.Add(ResourcePlatform.ANDROID);
                    if (locCommentValue.ToLower().Contains(ResourcePlatform.WINDOWS.ToString().ToLower()))
                        stringPlatforms.Add(ResourcePlatform.WINDOWS);                    
                }
            }

            return stringPlatforms;
        }

        public List<KeyValuePair<string, ResourceMetadata>> SearchString(string searchStr, bool exact)
        {
            if (this.resourceData == null)
            {
                throw new Exception("Resource data null");
            }

            return exact
                       ? resourceData.Where(
                           item => item.Value.Value.Equals(searchStr, StringComparison.InvariantCultureIgnoreCase))
                                     .ToList()
                       : resourceData.Where(item => item.Value.Value.ToLower().Contains(searchStr.ToLower())).ToList();                
        }

        public List<KeyValuePair<string, ResourceMetadata>> SearchResourceIdString(string resourceId, bool exact)
        {
            if (this.resourceData == null)
            {
                throw new Exception("Resource data null");
            }

            return exact
                       ? resourceData.Where(
                           item => item.Value.Value.Equals(resourceId, StringComparison.InvariantCultureIgnoreCase))
                                     .ToList()
                       : resourceData.Where(item => item.Value.Value.ToLower().Contains(resourceId.ToLower())).ToList();
        }

        public bool IsDuplicateResourceId(string resourceId)
        {
            if (this.resourceData == null)
            {
                throw new Exception("Resource data null");
            }

            return resourceData.ContainsKey(resourceId);

        }
        #endregion

    }
}
