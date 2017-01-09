package CCI;

import java.util.Stack;

import junit.framework.TestCase;

//Implement a function to check if a linked list is a palindrome.
public class CCI_2_7_PalindromeList extends TestCase {

	class Node
	{
		public int data;
		public Node next;
		
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		public Node(int data)	{
			this(data, null);
		}
	}
	
	boolean idPalindrome(Node head){
		if (head == null || head.next == null)
			return false;
		
		Node slow = head;
		Node fast = head;
		Stack<Node> stack = new Stack<Node>();
 		
		while (fast != null && fast.next != null){
			stack.push(slow);
			slow = slow.next;			
			fast = fast.next.next;			
		}
		
		if (fast != null){
			slow = slow.next;
		}
		
		while (slow != null) {
			if ( stack.pop().data != slow.data)
				return false;
			slow = slow.next;
		}
		
		return true;
		
	}
	
	public void print(Node head)
	{
		while (head != null)
		{
			System.out.print(head.data);
			System.out.print("\t");
			head = head.next;
		}
	}
	
	public void testFun(){
		Node n7 = new Node(1);
		Node n6 = new Node(2, n7);
		Node n5 = new Node(3, n6);
		Node n4 = new Node(4, n5);
		Node n3 = new Node(3, n4);
		Node n2 = new Node(2, n3);
		Node n1 = new Node(1, n2);
		
		print(n1);		
		assertTrue(idPalindrome(n1));
		
		System.out.println();
		print(n2);
		assertFalse(idPalindrome(n2));
	}
}
