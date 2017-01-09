package LinkedList;

import junit.framework.TestCase;

//implement an algorithm to find the kth to last element of a single linked list.
public class FindBeginnigOfLoopLinkedList extends TestCase {
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
		
		Node findLoopBeginning(){
			Node slow = this;
			Node fast = this;
			do {
				slow = slow.next;
				fast = fast.next;
				if (fast != null)
					fast = fast.next;
				else
					break;
			} while (slow != fast);
			
			if (fast == null)
				return null;
			else {
				slow = this;
				while(slow != fast)
				{
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}		
	}
	
	public void testFun(){
		Node root = new Node(0);
		Node loopBegin = root.append(1).append(2).append(3);
		Node tail = loopBegin.append(4).append(5).append(6).append(7).append(8).append(9);
		tail.next = loopBegin;		
		
		Node lb = root.findLoopBeginning();
		if (lb != null){
			System.out.print("\n Loop Beginning node value: " + Integer.toString(lb.value));
		}
		assertTrue (loopBegin == lb);
	}		
}
