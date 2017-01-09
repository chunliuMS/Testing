package P100Solutions;

import junit.framework.TestCase;
import Util.*;

/*  Nodes in a list represent a number.
 *  Please implement a function/method to add numbers in two lists, and store the sum into a new list. */
public class Addition_of_2_linkedlist extends TestCase {
		
	ListNode<Integer> add(ListNode<Integer> l1, ListNode<Integer> l2) {
		if (l1 == null)
			return l2;
		else if (l2 == null)
			return l1;
		
		l1 = reverse(l1);
		l2 = reverse(l2);
		
		int carry = 0;
		ListNode<Integer> nl = null;
		ListNode<Integer> n = null;
		while (l1 != null || l2 != null) {
			int value = carry + (l1 != null ? l1.value : 0) + (l2 != null ? l2.value : 0);
			if (value >= 10){
				carry = 1;
				value -= 10;
			}
			else
				carry = 0;
			
			if (nl == null) {
				nl = new ListNode<Integer>(value);
				n = nl;
			}
			else
				nl = nl.add(value);
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		
		if (carry > 0)
			n.add(carry);
		
		return reverse(n);
	}
	
	ListNode<Integer> reverse(ListNode<Integer> list) {
		ListNode<Integer> pre = null;
		ListNode<Integer> cur = list;
		
		while (cur != null) {
			ListNode<Integer> next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	
	public void testFun(){	
		ListNode<Integer> n1 = new ListNode<Integer>(9);
		n1.add(2).add(4);
		ListNode<Integer> n2 = new ListNode<Integer>(1);
		n2.add(2).add(7).add(9);
		add(n1, n2).print();
	}
	
}