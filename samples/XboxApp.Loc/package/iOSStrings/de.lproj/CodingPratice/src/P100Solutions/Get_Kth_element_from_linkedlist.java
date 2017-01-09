package P100Solutions;

import junit.framework.TestCase;
import Util.*;

/*  Problem: Get the Kth node from end of a linked list. It counts from 1 here, so the 1st node from end is the tail of list.   
For instance, given a linked list with 6 nodes, whose value are 1, 2, 3, 4, 5, 6, its 3rd node from end is the node with value 4.  
A node in the list is defined as:  
struct ListNode {     int       m_nValue;     ListNode* m_pNext; }; 
*/
public class Get_Kth_element_from_linkedlist extends TestCase {
		
	ListNode<Integer> getKthFromEnd(ListNode<Integer> root, int k) {
		if (k <= 0)
			return null;
		
		ListNode<Integer> first = root;
		while (k-- > 0 && first != null) {
			first = first.next;
		}
		
		if (k > 0)
			return null;
		
		ListNode<Integer> second = root;		
		while (first != null){
			first = first.next;
			second = second.next;
		}
		
		return second;
	}
	
	public void testFun(){		
		ListNode<Integer> root = new ListNode<Integer>(1);
		root.add(2).add(3).add(4);
		
		assertTrue(getKthFromEnd(root, 1).value == 4);
		assertTrue(getKthFromEnd(root, 4).value == 1);
		assertTrue(getKthFromEnd(root, 6) == null);
		assertTrue(getKthFromEnd(null, 3) == null);
	}
	
}
