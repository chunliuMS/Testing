package CCI;

import junit.framework.TestCase;

//Implement a function to check if a binary tree is a binary search tree
public class CCI_4_5_CheckBST extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
		
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}		
	}	
	
	public Node createBST(int[] values, int start, int end){
		if (start > end )
			return null;
		
		int mid = (start + end) / 2;
		Node n = new Node(values[mid]);
		n.left = createBST(values, start, mid - 1);
		n.right = createBST(values, mid + 1, end);
		return n;
	}
	
	public boolean isBST(Node node, Integer pre){
		if (node == null)
			return true;
		if (!isBST(node.left, pre))
			return false;
		if (node.value <  pre)
			return false;
		pre = node.value;
		
		return isBST(node.right, pre);
	}
	
	void print(Node node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);
		
	}
	
	public void testFun(){
		int[] v = {1, 2, 3, 4, 5, 6, 7, 8 ,9};
		Node n = createBST(v, 0, v.length - 1);
		assertTrue(isBST(n, Integer.MIN_VALUE));	
		int[] v1 = {1, 8, 3, 5, 4, 6, 7, 8 ,9};
		n = createBST(v1, 0, v1.length - 1);
		assertFalse(isBST(n, Integer.MIN_VALUE));
	}
}