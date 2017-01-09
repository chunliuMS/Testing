package LinkedList;

import junit.framework.TestCase;

//implement an algorithm to find the kth to last element of a single linked list.
public class kthElementInLinkedList extends TestCase {
	class Node{
		int value;
		Node next;
		Node(int value){
			this.value = value;
			this.next = null;
		}
		
		Node append(int value){
			Node n = this;
			while(n.next != null){
				n = n.next;
			}
			n.next = new Node(value);
			return n.next;
		}
		
		int printKthFromLast(Node curr, int k)	{
			if (curr == null)
				return 0;
			int n = printKthFromLast(curr.next, k) + 1;
			if (n == k)
				System.out.print("\n The " + Integer.toString(k) + "th element is :" + Integer.toString(curr.value));
			return n;
		}
		
		int indexFromLast = 0;
		Node findFromLast(Node root, int k){
			if (root == null){
				indexFromLast = 0;
				return null;
			}
			else
			{
				Node ret = findFromLast(root.next, k);
				indexFromLast++;
				if (indexFromLast == k)
					return root;
				else
					return ret;
			}	
		}		
	}	
	
	private void print(Node root){
		Node n = root;
		System.out.print("\r\nValues: \n");
		while (n!= null){
			System.out.print("\t" + Integer.toString(n.value));
			n = n.next;
		}
	}
	
	public void testFun(){
		Node root = new Node(5);
		root.append(0).append(0).append(6).append(5).append(4).append(3).append(2).append(1).append(1);
		print(root);
		
		root.printKthFromLast(root, 1);
		root.printKthFromLast(root, 3);
		root.printKthFromLast(root, 5);
		root.printKthFromLast(root, 10);
		root.printKthFromLast(root, 12);	
		
		Node ret = root.findFromLast(root, 1);
		if (ret != null)
			System.out.print("\n The 1th element from end is :" + Integer.toString(ret.value));
		
		ret = root.findFromLast(root, 3);
		if (ret != null)
			System.out.print("\n The 3rd element from end is :" + Integer.toString(ret.value));
		
		ret = root.findFromLast(root, 5);
		if (ret != null)
			System.out.print("\n The 5th element from end is :" + Integer.toString(ret.value));
		
		ret = root.findFromLast(root, 10);
		if (ret != null)
			System.out.print("\n The 10th element from end is :" + Integer.toString(ret.value));
		
		ret = root.findFromLast(root, 20);
		if (ret != null)
			System.out.print("\n The 20th element from end is :" + Integer.toString(ret.value));		
	}
}
