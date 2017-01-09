package P100Solutions;

import Util.*;
import junit.framework.TestCase;;


//Convert a binary search tree to a sorted double-linked list. 
//We can only change the target of pointers, but cannot create any new nodes. 

public class BST_to_sorted_linkedlist extends TestCase {
		
	public void testFun(){		
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(6, 14);
		root.left.add(4,  8);
		root.right.add(12,  16);
		root.print();	
		
		convert(root);
		
		TreeNode<Integer> h = head;
		TreeNode<Integer> hp = null;
		System.out.println("Linked list: \n");
		while (h != null) {
			System.out.print(h.value);
			System.out.print("\t");
			hp = h;
			h = h.right;
		}
		
		System.out.println("\nLinked list reverse: \n");
		while (hp != null) {
			System.out.print(hp.value);
			System.out.print("\t");
			hp = hp.left;
		}
		
	}
	
	private TreeNode<Integer> head = null;
	private TreeNode<Integer> prev = null;
	
	private void convert(TreeNode<Integer> root) {
		if (root != null)
			convert(root.left);
		else
			return;
		
		if (root.left == null && prev == null) {
			head = root;
			prev = root;
			return;
		}
		
		root.left = prev;
		prev.right = root;
		
		prev = root;
		
		convert(root.right);		
	}
	
}
