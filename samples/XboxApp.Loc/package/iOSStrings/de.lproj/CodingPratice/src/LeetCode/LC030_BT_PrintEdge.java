package LeetCode;

import junit.framework.TestCase;

//Print edge nodes (boundary) of a binary tree anti-clockwise.

public class LC030_BT_PrintEdge extends TestCase {
	
	class TNode{
		public TNode(int data, Integer left, Integer right){
			this.data = data;
			if (left != null)
				this.left = new TNode(left);
			if (right != null)
				this.right = new TNode(right);
		}
		public TNode(int data){
			this.data = data;
		}
		int data;
		TNode left;
		TNode right;		
	}
	
	void printLeftEdges(TNode n, boolean print){
		if(n == null)
			return;
		if(print || (n.left == null && n.right == null))
			System.out.print(n.data + " ");
		printLeftEdges(n.left, print);
		printLeftEdges(n.right, false);
	}
	
	void printRightEdges(TNode n, boolean print){
		if(n == null)
			return;
		
		printRightEdges(n.left, false);
		printRightEdges(n.right, true);
		
		if(print || (n.left == null && n.right == null))
			System.out.print(n.data + " ");
	}
	
	
		
	void print(TNode head){
		if (head == null)
			return;
		System.out.print(head.data + " ");
		
		printLeftEdges(head.left, true);
		printRightEdges(head.right, true);
	}
	
	public void testFun(){
		TNode head = new TNode(1);
				
		head.left = new TNode(2, 4, 5);
		head.right = new TNode(3, 6, 7);
		
		print(head);
	}	
}
