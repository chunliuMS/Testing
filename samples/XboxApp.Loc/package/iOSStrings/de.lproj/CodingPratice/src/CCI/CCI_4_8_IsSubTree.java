package CCI;

import junit.framework.TestCase;

//Implement a function to check if a tree is a subset of another very big tree.
public class CCI_4_8_IsSubTree extends TestCase {
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
	
	boolean isSubset(Node t1, Node t2){
		if(t2 == null)
			return true;
		
		if (match(t1, t2))
			return true;
		if (t1 == null)
			return false;
		return isSubset(t1.left, t2) || isSubset(t1.right, t2);
	}
	
	boolean match(Node first, Node second){
		if (first == null && second == null)
			return true;
		if (first == null || second == null)
			return false;
		if (first.value == second.value)
			return match(first.left, second.left) && match(first.right, second.right);
		return false;			
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
		{
			Node n1 = root.append(1, true);		
			Node n2 = root.append(2, false);
			Node n3 = n1.append(3, true);
			n1.append(4, false);
			n2.append(5, true);
			n2.append(6, false);
			n3.append(7, true);		
			Node n8 = n3.append(8, false);
			n8.append(9, false);
		}
		
		Node n3 = new Node(3);
		{		
			n3.append(7, true);		
			Node n8 = n3.append(8, false);
			n8.append(9, false);
		}		
		assertTrue(isSubset(root, n3));
		
		n3 = new Node(3);
		{		
			n3.append(7, true);		
			n3.append(8, false);
		}		
		assertFalse(isSubset(root, n3));
		
		n3 = new Node(3);
		{		
			n3.append(7, true);		
			Node n8 = n3.append(8, false);
			n8.append(9, false);
			n8.append(10, true);
		}		
		assertFalse(isSubset(root, n3));
	}
}
