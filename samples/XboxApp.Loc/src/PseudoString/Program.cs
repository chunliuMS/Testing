using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.IO;

namespace PseudoString
{
    class Program
    {
        private static Dictionary<char, char[]> charDictionary;
        private static bool isAndroid = false;        
        private static Dictionary<char, char[]> CharMap
        {
            get
            {
                if (charDictionary == null)
                {
                    charDictionary = new Dictionary<char, char[]>();
                    charDictionary.Add('a', new char[] { '\u03B1', '\u212B', '\u0040', '\u00AA', '\u00C0', '\u00C1', '\u00C2', '\u00C3', '\u00C4', '\u00C5', '\u00E0', '\u00E1', '\u00E2', '\u00E3', '\u00E4', '\u00E5' });
                    charDictionary.Add('b', new char[] { '\u00DF', '\u025E', '\u026E', '\u03B2', '\u042A', '\u1D03', '\u1E9E', '\u212C' });
                    charDictionary.Add('c', new char[] { '\u03C2', '\u20A1', '\u20B5', '\u2102', '\u00A2', '\u00A9', '\u00C7', '\u00E7' });
                    charDictionary.Add('d', new char[] { '\u03B4', '\u1D06', '\u20AB', '\u00D0', '\u00D3' });
                    charDictionary.Add('e', new char[] { '\u20AC', '\u0258', '\u0259', '\u025B', '\u03B5', '\u2130', '\u00C8', '\u00C9', '\u00CA', '\u00CB', '\u00E8', '\u00E9', '\u00EA', '\u00EB' });
                    charDictionary.Add('f', new char[] { '\u20A3', '\u2131' });
                    charDictionary.Add('g', new char[] { '\u20B2', '\u0261', '\u0262', '\u210A' });
                    charDictionary.Add('h', new char[] { '\u0389', '\u210B', '\u210D', '\u210E', '\u210F' });
                    charDictionary.Add('i', new char[] { '\u0268', '\u00A1', '\u00CC', '\u00CD', '\u00CE', '\u00CF', '\u00EC', '\u00ED', '\u00EE', '\u00EF' });
                    charDictionary.Add('j', new char[] { '\u025F', '\u2110' });
                    charDictionary.Add('k', new char[] { '\u20AD', '\u212A' });
                    charDictionary.Add('l', new char[] { '\u013E', '\u20A4', '\u2112', '\u2113' });
                    charDictionary.Add('m', new char[] { '\u20A5', '\u2133' });
                    charDictionary.Add('n', new char[] { '\u0274', '\u03B7', '\u0419', '\u0439', '\u00D1', '\u00F1', '\u0143', '\u0144', '\u0145', '\u0146', '\u0147', '\u0148', '\u014A', '\u014B' });
                    charDictionary.Add('o', new char[] { '\u014E', '\u014F', '\u014C', '\u014D', '\u0150', '\u0151', '\u03C3', '\u00F0', '\u00F3', '\u00D2', '\u00F2', '\u00D4', '\u00F4', '\u00D6', '\u00F6', '\u00D5', '\u00F5', '\u00D8' });
                    charDictionary.Add('p', new char[] { '\u20B1', '\u2119', '\u2118', '\u03C1', '\u00DE' });
                    charDictionary.Add('q', new char[] { '\u211A', '\u0051' });
                    charDictionary.Add('r', new char[] { '\u0393', '\u211D', '\u211B', '\u211C', '\u0154', '\u0155', '\u0158', '\u0159', '\u0156', '\u0157', '\u211E' });
                    charDictionary.Add('s', new char[] { '\u015A', '\u015B', '\u015C', '\u015D', '\u0160', '\u0161', '\u015E', '\u015F', '\u0024' });
                    charDictionary.Add('t', new char[] { '\u03C4','\u20AE', '\u0164', '\u0165', '\u0162', '\u0163', '\u0166', '\u0167', '\u03C4' });
                    charDictionary.Add('u', new char[] { '\u00B5', '\u0265', '\u00DA', '\u00FA', '\u00D9', '\u00F9', '\u00DB', '\u00FB', '\u00DC', '\u00FC', '\u016C', '\u016D', '\u016A', '\u016B', '\u0168', '\u0169', '\u016E', '\u016F', '\u0170', '\u0171', '\u03BC' });
                    charDictionary.Add('v', new char[] { '\u0263', '\u0264' });
                    charDictionary.Add('w', new char[] { '\u019C', '\u026F', '\u0270', '\u0174', '\u0175', '\u03C9', '\u0449' });
                    charDictionary.Add('x', new char[] { '\u00D7', '\u0078' });
                    charDictionary.Add('y', new char[] { '\u00A5', '\u00DD', '\u00FD', '\u1EF2', '\u1EF3', '\u0176', '\u0177', '\u00FF', '\u0178', '\u1EF8', '\u1EF9', '\u1EF4', '\u1EF5', '\u03B3' });
                    charDictionary.Add('z', new char[] { '\u2124', '\u0179', '\u017A', '\u017B', '\u017C', '\u017D', '\u017E' });
                }

                return charDictionary;
            }
        }

        static void Main(string[] args)
        {
            if (args.Length < 2)
            {
                System.Console.WriteLine("usage: PseudoString <source>.xml <dest>.xml [android]");
                return;
            }

            XmlDocument doc = new XmlDocument();
            doc.PreserveWhitespace = true;
            
            doc.Load(args[0]);

            if (args.Count() == 3 && !String.IsNullOrEmpty(args[2]) && args[2].ToLowerInvariant() == "android")
            {
                isAndroid = true;
            }

            int elementCount = doc.DocumentElement.ChildNodes.Count;

            for (int c = 0; c < elementCount; ++c)
            {
                XmlNode element = doc.DocumentElement.ChildNodes[c];

                if (element is XmlElement)
                {
                    for (int d = 0; d < element.ChildNodes.Count; ++d)
                    {
                        XmlNode subElement = element.ChildNodes[d];

                        if (subElement is XmlText)
                        {
                            string innerText = subElement.InnerText;

                            if (innerText.Trim().Length > 0)
                            {
                                uint numberToPad = Convert.ToUInt32((double)innerText.Trim().Length * 0.15); // 15% each side for 30% total

                                subElement.InnerText = String.Format("[{0} {1} {2}]", GetPaddingText(numberToPad, '!'), GetExtendedText(innerText), GetPaddingText(numberToPad, '!'));
                            }
                        }
                    }
                }
            }
            doc.Save(args[1]);
        }

        static string GetPaddingText(uint len, char padChar)
        {
            StringBuilder builder = new StringBuilder();
            len = Math.Max(1, len);

            for (int c = 0; c < len; ++c)
            {
                builder.Append(padChar);
            }

            return builder.ToString();
        }

        private static string GetExtendedText(string text)
        {
            StringBuilder builder = new StringBuilder();
            string lowerText = text.ToLowerInvariant();

            for (int i = 0; i < text.Length; i++)
            {
                char character = lowerText[i];
                if (CharMap.ContainsKey(character))
                {
                    int index = 0;
                    if (isAndroid)
                    {                        
                        index = 0;
                    }
                    else
                    {
                        Random rand = new Random((int)DateTime.Now.Ticks);
                        index = rand.Next(0, CharMap[character].Length);
                    }                    
                    builder.Append(CharMap[character][index]);
                }
                else
                {
                    if (character == '{')
                    {
                        do 
                        {
                            builder.Append(character);
                            i++;
                            if (i >= text.Length)
                            {
                                throw new IndexOutOfRangeException(
                                    string.Format(
                                    "The string '{0}' has an open curly parenthesis but no closing parenthesis.",
                                    text));
                            }

                            character = text[i];
                        } while (character != '}' && i < text.Length);
                    }
                    else if (character == '%' && isAndroid)
                    {
                        do
                        {
                            builder.Append(character);
                            i++;
                            if (i >= text.Length)
                            {
                                break;
                            }
                            character = text[i];
                        } while (character != ' ' && i < text.Length);
                    }

                    if (i < text.Length)
                    {
                        builder.Append(text[i]);
                    }
                }
            }

            return builder.ToString();
        }
    }
}
