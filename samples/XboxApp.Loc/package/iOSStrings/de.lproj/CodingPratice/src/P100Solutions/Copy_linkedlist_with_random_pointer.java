package P100Solutions;

import junit.framework.TestCase;
import java.util.Hashtable;


/* If there is a loop in a linked list, how to get the entry node of the loop? 
 * The entry node is the first node in the loop from head of list.  
*/
public class Copy_linkedlist_with_random_pointer extends TestCase {
	class Node{
		Node next;
		int value;
		Node rand;
	}	
	
	Node copylinkedList(Node head) {
		Node node = head;
		Hashtable<Node, Node> ht = new Hashtable<Node, Node>();
		while (node != null) {
			Node n2 = copy(node, ht);
			n2.value = node.value;
			
			if (node.next != null) 
				n2.next = copy(node.next, ht);
			
			if (node.rand != null)
				n2.rand = copy(node.rand, ht);
			
			node = node.next;				
		}
		
		return ht.get(head);		
	}
	
	Node copy(Node n, Hashtable<Node, Node> ht) {
		if (ht.containsKey(n))
			return ht.get(n);
		else {
			Node n1 = new Node();
			ht.put(n, n1);
			return n1;
		}
	}

	
	public void testFun(){	
		Node n1 = new Node();
		Node n2 = new Node();
		Node n3 = new Node();
		Node n4 = new Node();
		
		n1.value = 1;
		n1.next = n2;
		n1.rand = n3;
		
		n2.value = 2;
		n2.next = n3;
		n3.rand = n4;
		
		n3.value = 3;
		n3.next = n4;
		
		n4.value = 4;
		
		Node n = n1;
		
		while (n != null) {
			System.out.print(" \nvalue - " + n.value );
			System.out.print(" next - " + (n.next == null ? "null" : n.next.value));
			System.out.print(" rand - " + (n.rand == null ? "null" : n.rand.value));
			n = n.next;
		}
		
		Node nn = copylinkedList(n1);
		
		System.out.print("\n");
		while (nn != null) {
			System.out.print("\nvalue - " + nn.value );
			System.out.print(" next - " + (nn.next == null ? "null" : nn.next.value));
			System.out.print(" rand - " + (nn.rand == null ? "null" : nn.rand.value));
			nn = nn.next;
		}
		
	}
	
}