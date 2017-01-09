package LeetCode;

import junit.framework.TestCase;

//Given a node from a cyclic linked list which has been sorted, write a function to insert a new node
//to the list such that it remains sorted.
public class LC012_InsertIntoCyclicSortedList extends TestCase {
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
		Node m = n;
		StringBuffer sb = new StringBuffer();
		while(n != null ){
			sb.append(n.value).append(" ");
			n = n.next;
			if (m == n)
				break;
		}
		System.out.println(sb.toString());
	}
	
	boolean insert(Node root, int v){
		Node n = new Node(v);
		Node prev = root;
		Node curr = prev.next;
		while(prev != null && curr != null){
			if ((prev.value < curr.value && v >= prev.value && v < curr.value) ||
				(prev.value > curr.value && (v >= prev.value || v <= curr.value)))	{
				prev.next = n;
				n.next = curr;
				
				return true;
			}
			prev = curr;
			curr = curr.next;			
		}
		
		return false;
	}
	
	public void testFun(){
		Node n = new Node(15);
		n.append(3).append(7);
		Node n10 = new Node(10);
		n10.next = n;
		n.append(n10);
		print(n);
		assertTrue(insert(n, 4));
		assertTrue(insert(n, 1));
		assertTrue(insert(n, 10));
		assertTrue(insert(n, 20));
		print(n);
	}	
}
