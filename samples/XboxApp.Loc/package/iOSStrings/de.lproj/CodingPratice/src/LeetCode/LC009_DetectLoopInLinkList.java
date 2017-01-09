package LeetCode;

import junit.framework.TestCase;

//Detect if a single linked list has the loop.
public class LC009_DetectLoopInLinkList extends TestCase {
	class Node{
		int value;
		Node next;
		Node last;
				
		Node(int value){
			this.value = value;
			this.next = null;
			this.last = this;
		}
		
		Node append(int value){
			Node n = new Node(value);
			return append(n);			
		}
		
		Node append(Node n){
			this.last.next = n;
			this.last = n;
			return this;
		}
	}	
	
	void print(Node n){
		StringBuffer sb = new StringBuffer();
		while(n != null){
			sb.append(n.value).append(" ");
			n = n.next;
		}
		System.out.println(sb.toString());
	}
	
	boolean hasLoop(Node n){
		Node slow = n;
		Node fast = n;		
		while(slow != null && fast != null && fast.next != null){			
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast)
				return true;
		}
		return false;
	}
	
	public void testFun(){
		Node n = new Node(0);
		n.append(1).append(2).append(3).append(4).append(5).append(6);
		print(n);
		assertFalse(hasLoop(n));
		n.append(n);
		assertTrue(hasLoop(n));
	}	
}
