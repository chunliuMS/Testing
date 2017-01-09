package CCI;

import junit.framework.TestCase;

//Write code to partition a linked list around a value x where all nodes less than x come
//before nodes greater or equal to x
public class CCI_2_4_PartitionlistByGivenValue extends TestCase {
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
	
	Node partition(Node head, int x){
		Node smaller = null;
		Node scurr = null;
		Node bigger = null;
		Node bcurr = null;
		
		while (head != null)
		{
			if (head.data < x)
			{
				if (smaller == null)
					smaller = scurr = head;
				else
				{
					scurr.next = head;
					scurr = head;
				}
			}
			else {
				if (bigger == null)
					bigger = bcurr = head;	
				else {
					bcurr.next = head;
					bcurr = head;
				}
			}
			
			head = head.next;			
		}
		
		if (smaller != null){
			scurr.next = bigger;
			return smaller;
		}
		else
			return bigger;
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
		Node n7 = new Node(6);
		Node n6 = new Node(4, n7);
		Node n5 = new Node(5, n6);
		Node n4 = new Node(2, n5);
		Node n3 = new Node(1, n4);
		Node n2 = new Node(3, n3);
		Node n1 = new Node(8, n2);
		
		print(n1);
		System.out.println();
		Node n = partition(n1, 5);
		print(n);
		System.out.println();		
		print(partition(n, 3));
	}
}
