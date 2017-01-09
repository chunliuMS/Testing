package LeetCode;

import junit.framework.TestCase;

//Populating the next right pointers in each node. You may assume this is a full binary tree.
public class LC002_BinaryTreePopulateNextright extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
		Node nextRight;
		
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
			this.nextRight = null;
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
		System.out.print(" " + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);		
	}
	
	void connect(Node root){
		if (root == null)
			return;
		if (root.left != null)
			root.left.nextRight = root.right;
		
		if (root.right != null && root.nextRight != null)
			root.right.nextRight = root.nextRight.left;
		
		connect(root.left);
		connect(root.right);
	}
	
	void printRight(Node node){
		if (node == null)
			return;
		Node temp = node;
		System.out.println();
		while(temp != null){
			System.out.print(" " + Integer.toString(temp.value));
			temp = temp.nextRight;
		}
		
		printRight(node.left);				
	}
	
	public void testFun(){
		int[] v = {8,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
		Node n = createBST(v, 0, v.length - 1);
		print(n);
		
		connect(n);
		printRight(n);
	}	
}
