package LeetCode;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import junit.framework.TestCase;

//Print binary tree level by level.
public class LC008_PrintBinaryTreeInLevelOrder extends TestCase {
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
		System.out.print(" " + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);		
	}
	
	void print(Vector<Node> v){
		StringBuilder sb = new StringBuilder();		
		for (Node n : v)
			sb.append(n.value).append(" ");
		System.out.println(sb.toString());
	}
	
	void print(Queue<Node> v){
		StringBuilder sb = new StringBuilder();		
		for (Node n : v)
			sb.append(n.value).append(" ");
		System.out.println(sb.toString());
	}
	
	void printLevel(Node root){	
		System.out.println();		
		Vector<Node> v = new Vector<Node>();
		v.add(root);
		while(v.size() > 0){
			print(v);
			int size = v.size();			
			for (int i = 0; i < size; i++){
				Node n = v.get(i);
				if (n.left != null)
					v.add(n.left);
				if (n.right != null)
					v.add(n.right);				
			}
			
			for (int i = 0; i < size; i++)
				v.remove(0);
		}		
	}
	
	void printLevel2(Node root){	
		System.out.println("Output from printLevel2");
		if (root == null)
			return;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()){
			print(q);
			int size = q.size();
			while(size > 0){
				Node n = q.poll();				
				if (n.left != null) q.add(n.left);
				if (n.right != null) q.add(n.right);
				size--;
			}
		}		
	}
	
	public void testFun(){
		int[] v = {8,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
		Node root = createBST(v, 0, v.length - 1);
		print(root);
		printLevel(root);
		printLevel2(root);
	}	
}
