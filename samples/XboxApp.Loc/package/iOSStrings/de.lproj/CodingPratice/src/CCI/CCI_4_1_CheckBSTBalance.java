package CCI;

import junit.framework.TestCase;

//Implement a function to check if a binary tree is balanced.
//A balanced tree is defined that the heights of the 2 subtrees of any node never differ by more than one.
public class CCI_4_1_CheckBSTBalance extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
		
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		Node append(int value, boolean left){
			Node node = this;			
			Node child = new Node(value);
			if (left)
				node.left = child;
			else
				node.right = child;			
			return child;
		}	
	}	
	
	public int getHeight(Node node)
	{
		if(node == null)
			return 0;
		int left = getHeight(node.left);
		if (left < 0)
			return left;
		int right = getHeight(node.right);
		if (right < 0)
			return right;
		
		if (Math.abs(left-right) > 1)
			return -1;
		else
			return Math.max(left, right) + 1;
	}

	public boolean isBalanced(Node root)
	{
		return getHeight(root) > 0;
	}
	
	void print(Node node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);
		
	}
	
	public void testFun(){
		Node root = new Node(0);
		Node n1 = root.append(1, true);
		Node n2 = root.append(2, false);
		Node n3 = n1.append(3, true);
		n1.append(4, false);
		n2.append(5, true);
		n2.append(6, false);
		Node n7 = n3.append(7, true);		
		n3.append(8, false);
		
		System.out.print("\n");
		print(root);
		boolean balanced = isBalanced(root);
		assertTrue(balanced);
		
		System.out.print("\n");
		n7.append(9, true);		
		print(root);
		
		balanced = isBalanced(root);
		assertFalse(balanced);
	}
}
