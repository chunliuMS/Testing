package CCI;

import junit.framework.TestCase;

//Implement a function to check if a binary tree is balanced.
//A balanced tree is defined that the heights of the 2 subtrees of any node never differ by more than one.
public class CCI_4_3_CreateBST extends TestCase {
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
		print(n);		
	}
}