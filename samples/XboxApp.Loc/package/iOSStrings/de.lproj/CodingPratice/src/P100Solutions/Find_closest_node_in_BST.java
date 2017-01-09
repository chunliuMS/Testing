package P100Solutions;
import junit.framework.TestCase;
import Util.TreeNode;

//Closest Node in a Binary Search Tree 
//Problem: Given a binary search tree and a value k, please find a node in the binary search tree whose value is closest to k. 

public class Find_closest_node_in_BST extends TestCase {
		
	public void testFun(){	
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(6, 14);
		root.left.add(4,  9);
		root.right.add(12,  16);
		root.print();	
		
		assertTrue(findclosedNode(root, 17).value == 16 );
		assertTrue(findclosedNode(root, 8).value == 9 );
		assertTrue(findclosedNode(root, 1).value == 4 );
		
	}	
	
	private TreeNode<Integer> findclosedNode(TreeNode<Integer> root, int val) {
		TreeNode<Integer> closest = null;
		int deviation = Integer.MAX_VALUE;
		
		while (root != null) {
			int delta = Math.abs(root.value - val);
			if (delta < deviation) {
				deviation = delta;
				closest = root;
			}
			
			if (root.value > val)
				root = root.left;
			else if (root.value == val)
				break;
			else 
				root = root.right;
		}
		
		return closest;
	}
	
}
