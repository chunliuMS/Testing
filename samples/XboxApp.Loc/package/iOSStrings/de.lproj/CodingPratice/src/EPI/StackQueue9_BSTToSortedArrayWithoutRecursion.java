package EPI;
import java.util.ArrayList;
import java.util.Stack;

import Util.TreeNode;
import junit.framework.TestCase;
/* 
 * A posting list is a singly linked list with an additional "jump" field at each node. The jump field points to any other node.
 * Write recursive and iterative routines the take a posting list and compute the jump first order. 
*/
				
public class StackQueue9_BSTToSortedArrayWithoutRecursion extends TestCase {
	ArrayList<TreeNode<Integer>> compute(TreeNode<Integer> root) {
		ArrayList<TreeNode<Integer>> al = new ArrayList<TreeNode<Integer>> ();
		Stack<TreeNode<Integer>> s = new Stack<TreeNode<Integer>> ();
		TreeNode<Integer> curr = root;
		
		while (curr != null || !s.empty()) {
			if (curr != null) {
				s.push(curr);
				curr = curr.left;
			} else {
				curr = s.pop();
				al.add(curr);
				curr = curr.right;
			}
		}
		
		
		return al;
		
	}	
		
	public void testFun(){	
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		root.add(6, 14);
		root.left.add(4, 8);
		root.left.right.add(7, 9);
		root.right.add(12,  16);
		
		ArrayList<TreeNode<Integer>> al = compute(root);
		
		for (TreeNode<Integer> n : al) {
			System.out.print(n.value);
			System.out.print("\t");
		}
	}		

	
}