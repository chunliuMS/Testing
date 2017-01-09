package P100Solutions;

import Util.*;
import junit.framework.TestCase;;


// Binary Search Tree Verification  Question: How to verify whether a binary tree is a binary search tree? 

public class BST_verification extends TestCase {
		
	public void testFun(){		
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(6, 14);
		root.left.add(4,  8);
		root.right.add(12,  16);
		root.print();	
		
		assertTrue(verify(root, Integer.MIN_VALUE, Integer.MAX_VALUE));	
		
		root.right.right.add(13, null);
		root.print();
		assertFalse(verify(root, Integer.MIN_VALUE, Integer.MAX_VALUE));	
	}
	
	private boolean verify(TreeNode<Integer> root,  int min, int max) {
		if (root == null)
			return true;
		
		if (root.value <= min || root.value >= max)	
			return false;
		
		return verify(root.left, min, root.value) && verify(root.right, root.value, max);
	}
	
}
