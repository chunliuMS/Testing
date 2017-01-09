package LeetCode;

import java.util.Vector;

import junit.framework.TestCase;

//Binary tree serialization.
public class LC007_SerializeBinaryTree extends TestCase {
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
		
	void print(Node node){		
		if (node == null)
			return;
		System.out.print(" " + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);		
	}
	
	class Index {
		int v = 0;	
		Index inc(){
			v++;
			return this;
		}
	}
	
	Node Deserialize(int[] a, Index index){
		if (a[index.v]  < 0) 
			return null;
		Node root = new Node(a[index.v]);
		root.left = Deserialize(a, index.inc());
		root.right = Deserialize(a, index.inc());
		
		return root;
	}
	
	void Serialize(Node root, Vector<Integer> v){
		if (root == null)
			v.add(-1);
		else{
			v.add(root.value);
			Serialize(root.left, v);
			Serialize(root.right, v);
		}
			
	}
	int[] Serialize(Node root){		
		Vector<Integer> v = new Vector<Integer>();
		Serialize(root, v);
		int[] a = new int[v.size()];
		for (int i = 0; i < a.length; i++)
			a[i] = v.get(i);
		
		return a;
	}
	
	public void testFun(){
		int[] a = {30, 10, 50, -1, -1, -1, 20, 45, -1, -1, 35, -1, -1};
		Node root = Deserialize(a, new Index());
		print(root);
		int[] a1 = Serialize(root);
		root = Deserialize(a1, new Index());
		System.out.println();
		print(root); 
	}	
}
