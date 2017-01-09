package LeetCode;

import java.util.Stack;

import junit.framework.TestCase;

//Given a binary tree, print the elements in post order without using recursion

public class LC029_BT_InOrderTraversalIterative extends TestCase {
	
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
	
		
	void print(TNode head){
		if (head == null)
			return;
		Stack<TNode> s = new Stack<TNode>();
		TNode current = head;
		
		while(!s.empty() || current != null){
			if (current != null){
				s.push(current);
				current = current.left;
			}
			else {				
				current = s.pop();
				System.out.print(current.data + "\t");
				current = current.right;				
			}
		}		
	}
	
	public void testFun(){
		TNode head = new TNode(1, 2, 3);
		head.left.left = new TNode(4, 5, 6);
		head.left.right = new TNode(7);
		head.right.left = new TNode(8);
		head.right.right = new TNode(9);
		
		print(head);
	}	
}
