package LeetCodeTest;

import junit.framework.TestCase;

//You're given 2 linked lists representing 2 non-negative numbers. The digits are stored in reverse order and each of their nodes contains a single digit.
//Add the 2 numbers and return it as a linked list.


public class LC002_AddTwoNumbers extends TestCase {
	private class Node {
		Node(int value) {
			this.value = value;
		}
		
		int value;
		Node next;
	}
	
	Node addNumbers(Node a, Node b){
		if (a == null)
			return b;
		else if (b == null)
			return a;
		Node head = new Node(0);
		Node prev = head;
		int carry = 0;
		while (a != null || b != null) {
			int total = (a != null ? a.value : 0) + (b != null ? b.value : 0) + carry;
			if (total >= 10) {
				carry = total / 10;
				total %= 10;
			} else 
				carry = 0;
			
			Node n = new Node(total);
			
			prev.next = n;
			prev = n;
			
			if (a != null) a = a.next;
			if (b != null) b = b.next;
		}
		
		if (carry > 0){
			prev.next = new Node(carry);
		}
		
		return head.next;
	}
	

	public void testFun(){
		Node a = new Node(2);
		a.next = new Node(4);
		a.next.next = new Node(3);
		
		Node b = new Node(5);
		b.next = new Node(6);
		b.next.next = new Node(4);
		
		Node s = addNumbers(a, b);
		
		while (s != null) {
			System.out.print(String.valueOf(s.value));
			s = s.next;
		}
	}	
}
