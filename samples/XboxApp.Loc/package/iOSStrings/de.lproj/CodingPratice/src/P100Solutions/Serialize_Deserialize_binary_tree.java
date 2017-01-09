package P100Solutions;

import Util.*;
import junit.framework.TestCase;
import java.util.StringTokenizer;

// Serialize  and  Deserialize a Binary Tree  To  String 

public class Serialize_Deserialize_binary_tree extends TestCase {
	private static final String NULL = "#";
	private static final String DELIMITER = ",";
		
	public void testFun(){		
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		root.add(2, 3);
		root.left.add(4,  5);
		root.left.right.add(6,  null);
		root.right.add(7,  8);
		root.right.right.add(null,  9);
		root.print();
		
		StringBuffer str = new StringBuffer();
		serializeBTree(root, str);
		
		TreeNode<Integer> root2 = deserializeBTree(str.toString());
		root2.print();
	}
	
	private void serializeBTree(TreeNode<Integer> root, StringBuffer str) {
		if (root == null){
			str.append(NULL).append(DELIMITER);
		}
		else {
			str.append(root.value.toString()).append(DELIMITER);
			serializeBTree(root.left, str);
			serializeBTree(root.right, str);
		}
	}
	
	private TreeNode<Integer> deserializeBTree(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, DELIMITER);
		return deserize(tokenizer);
	}
	
	private TreeNode<Integer> deserize(StringTokenizer  elements){
		if (elements.hasMoreElements()) {
			String element = elements.nextElement().toString();
			if (element.equals(NULL))
				return null;
			else {
				TreeNode<Integer> node = new TreeNode<Integer>(Integer.parseInt(element));
				node.left = deserize(elements);
				node.right = deserize(elements);
				
				return node;
			}
		}
		return null;
	}
}
