package P100Solutions;
import java.util.Stack;

import Util.*;
import junit.framework.TestCase;;

//Paths with Specified Sum in Binary Tree 
//Question: All nodes along children pointers from root to leaf nodes form a path in a binary tree. 
//Given a binary tree and a number, please print out all of paths where the sum of all nodes value is same as the given number

public class Path_sum_finder extends TestCase {
		
	public void testFun(){		
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(5, 12);
		root.left.add(4, 7);
		
		Stack<TreeNode<Integer>> st = new Stack<TreeNode<Integer>>();
		printPath(root, st, 0, 22);
	}
	
	void print(Stack<TreeNode<Integer>> st) {
		if (st == null)
			return;
		System.out.print("\nPath --  ");
		for (int i = 0; i < st.size(); i++) {
			System.out.print(st.get(i).value);
			System.out.print("\t");
		}
	}
	
	private void printPath(TreeNode<Integer> root, Stack<TreeNode<Integer>> st, int currentTotal, int sum) {
		if (root == null)
			return;
		
		currentTotal += root.value;
		st.push(root);
		
		if (currentTotal == sum)
			print(st);
		
		printPath(root.left, st, currentTotal, sum);
		printPath(root.right, st, currentTotal, sum);
		
		st.pop();
		currentTotal -= root.value;		
	}
	
}
