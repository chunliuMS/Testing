
package CCI;

import java.util.ArrayList;
import java.util.Hashtable;

import junit.framework.TestCase;

//Given a list of words, find the longest word made by other words in the list.
public class CCI_18_8_SuffixTree extends TestCase {
	
	class SuffixTree{
		public SuffixTree(String str){
			root = new TreeNode();
			for (int i = 0; i < str.length(); i++)
				root.insert(i, str.substring(i));
		}
		
		public ArrayList<Integer> search(String str){
			return root.find(str);
		}
		
		private TreeNode root;
	}
	
	class TreeNode{
		private Hashtable<Character, TreeNode> children = new Hashtable<Character, TreeNode>();
		private ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		void insert(int index, String str){
			if (str != null && str.length() > 0){				
				char v = str.charAt(0);
				if (children.containsKey(v)){
					children.get(v).insert(index, str.substring(1));
				}
				else{
					TreeNode node = new TreeNode();
					node.insert(index, str.substring(1));
					children.put(v, node);
				}				
				indexes.add(index);
			}
		}
		
		ArrayList<Integer> find(String str){
			if (str == null || str.length() == 0)
				return indexes;
			char c = str.charAt(0);
			if (children.containsKey(c))
				return children.get(c).find(str.substring(1));
			return null;
		}
	}
	
    public void testFun(){       	
    	SuffixTree tree = new SuffixTree("this is an issue");
    	ArrayList<Integer> indexes = tree.search("is");
    	assertTrue(indexes.size() == 3);
	}
}