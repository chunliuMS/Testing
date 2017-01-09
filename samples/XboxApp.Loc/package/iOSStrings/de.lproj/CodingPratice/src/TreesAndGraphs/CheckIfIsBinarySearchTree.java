package TreesAndGraphs;

import junit.framework.TestCase;

//Implement a function to check if a binary tree is a binary search tree.
public class CheckIfIsBinarySearchTree extends TestCase {
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
		
		int last = Integer.MIN_VALUE;
		boolean isBinarySearchTree(TreeNode node){
			if(node == null)
				return true;
			
			boolean ret = isBinarySearchTree(node.left );
			if (!ret)
				return false;
			
			if (node.value < last)
				return false;
			else
				last = node.value;
			
			return  isBinarySearchTree(node.right);
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
		{
			TreeNode root = new TreeNode(0);
		
			TreeNode n1 = root.append(1, true);
			TreeNode n2 = root.append(2, false);
			TreeNode n3 = n1.append(3, true);
			n1.append(4, false);
			n2.append(5, true);
			n2.append(6, false);
			n3.append(8, false);
			
			System.out.print("\n");
			print(root);
			boolean ret = root.isBinarySearchTree(root);
			assertFalse(ret);
		}
		
		TreeNode root = new TreeNode(4);
		TreeNode n2 = root.append(2, true);
		TreeNode n6 = root.append(6, false);
		n2.append(1, true);
		n2.append(3, false);
		n6.append(5, true);
		n6.append(7, false);
		
		System.out.print("\n");
		print(root);
		boolean ret = root.isBinarySearchTree(root);
		assertTrue(ret);
	}
}
