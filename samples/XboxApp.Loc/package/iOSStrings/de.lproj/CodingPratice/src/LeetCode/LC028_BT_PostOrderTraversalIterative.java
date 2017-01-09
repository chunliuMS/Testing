package LeetCode;

import java.util.Stack;

import junit.framework.TestCase;

//Given a binary tree, print the elements in post order without using recursion

public class LC028_BT_PostOrderTraversalIterative extends TestCase {
	
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
		Stack<TNode> visit = new Stack<TNode>();
		Stack<TNode> output = new Stack<TNode>();
		visit.push(head);
		
		while(!visit.empty()){
			TNode node = visit.pop();
			output.push(node);
			if(node.left != null)
				visit.push(node.left);
			if(node.right != null)
				visit.push(node.right);
		}
		
		while(!output.empty())
			System.out.print(output.pop().data + "\t");		
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
