package TreesAndGraphs;

import junit.framework.TestCase;

//Implement a function to check if a binary tree is balanced.
//A balanced tree is defined that the heights of the 2 subtrees of any node never differ by more than one.
public class CheckTreeBalanced extends TestCase {
	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		TreeNode append(int value, boolean left){
			TreeNode node = this;			
			TreeNode child = new TreeNode(value);
			if (left)
				node.left = child;
			else
				node.right = child;			
			return child;
		}	
		
		int checkHeight(TreeNode node){
			if (node == null)
				return 0;
			int lHeight = checkHeight(node.left);
			if (lHeight < 0)
				return lHeight;
			
			int rHeight = checkHeight(node.right);
			if (rHeight < 0)
				return rHeight;
			
			if (Math.abs(lHeight - rHeight) > 1)
				return -1;
			
			return Math.max(lHeight, rHeight) + 1;
		}
		
		boolean isBalanced(){
			return checkHeight(this) > 0;
		}
	}	
	
	void print(TreeNode node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);
		
	}
	
	public void testFun(){
		TreeNode root = new TreeNode(0);
		TreeNode n1 = root.append(1, true);
		TreeNode n2 = root.append(2, false);
		TreeNode n3 = n1.append(3, true);
		n1.append(4, false);
		n2.append(5, true);
		n2.append(6, false);
		TreeNode n7 = n3.append(7, true);		
		n3.append(8, false);
		
		System.out.print("\n");
		print(root);
		boolean balanced = root.isBalanced();
		assertTrue(balanced);
		
		System.out.print("\n");
		n7.append(9, true);		
		print(root);
		
		balanced = root.isBalanced();
		assertFalse(balanced);
	}
}
