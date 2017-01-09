package P100Solutions;

import junit.framework.TestCase;
import Util.*;

/* If there is a loop in a linked list, how to get the entry node of the loop? 
 * The entry node is the first node in the loop from head of list.  
*/
public class Get_loop_node_in_linkedlist extends TestCase {
		
	ListNode<Integer> getLoopNode(ListNode<Integer> head) {
		ListNode<Integer> fast = head;
		ListNode<Integer> slow = head;
		
		while (fast != null) {
			fast = fast.next;
			if (fast != null)
				fast = fast.next;
			else
				return null;
			
			slow = slow.next;
			
			if (slow == fast) {
				slow = head;
				while (slow != fast){
					slow = slow.next;
					fast = fast.next;
				}
				
				return slow;
			}
		}
		
		return null;
	}
	
	public void testFun(){	
		ListNode<Integer> n3 = new ListNode<Integer>(3);
		n3.add(4).add(5).add(6).next = n3;
		
		ListNode<Integer> head = new ListNode<Integer>(1);
		head.add(2).next = n3;		
		
		assertTrue(getLoopNode(head) == n3);
	}
	
}