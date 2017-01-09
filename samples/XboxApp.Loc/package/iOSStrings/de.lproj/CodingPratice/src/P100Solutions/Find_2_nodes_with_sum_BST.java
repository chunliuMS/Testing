package P100Solutions;
import junit.framework.TestCase;

import java.util.ArrayList;

import Util.TreeNode;

////Nodes with Sum in a Binary Search Tree 
//Problem: Given a binary search tree, please check whether there are two nodes in it whose sum equals a given value. 

public class Find_2_nodes_with_sum_BST extends TestCase {
		
	public void testFun(){	
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(6, 14);
		root.left.add(4,  9);
		root.right.add(12,  16);
		root.print();	
		
		assertTrue(has2NodesWithSum(root, 15));
		assertTrue(has2NodesWithSum(root, 18));
		assertFalse(has2NodesWithSum(root, 17));
		
	}	
	
	private boolean has2NodesWithSum(TreeNode<Integer> root, int sum) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		convertToArrayList(root, al);
		
		int start = 0;
		int end = al.size() - 1;
		while (start < end){
			int startValue = al.get(start);
			int endValue = al.get(end);
			
			int total = startValue + endValue;
			if (total == sum)
				return true;
			else if (total > sum)
				end --;
			else
				start++;
		}
		
		return false;
	}
	
	private void convertToArrayList(TreeNode<Integer> root, ArrayList<Integer> al) {
		if (root == null)
			return;
		
		convertToArrayList(root.left, al);
		al.add(root.value);
		convertToArrayList(root.right, al);
	}
	
}
