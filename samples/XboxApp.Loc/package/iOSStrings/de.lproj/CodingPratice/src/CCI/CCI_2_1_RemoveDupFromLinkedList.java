package CCI;

import junit.framework.TestCase;

//Remove duplicates from an unsorted linked list
public class CCI_2_1_RemoveDupFromLinkedList extends TestCase {
	class LinkedListNode
	{
		public int data;
		public LinkedListNode next;
		
		public LinkedListNode(int data, LinkedListNode next) {
			this.data = data;
			this.next = next;
		}
		
		public LinkedListNode(int data)	{
			this(data, null);
		}
	}
	void removeDuplicates(LinkedListNode head){
		
		LinkedListNode h = head;		
		while (h != null)
		{
			LinkedListNode cur = h;
						
			while (cur.next != null)
			{
				if (h.data == cur.next.data)
				{
					cur.next = cur.next.next;				
				}
				else
					cur = cur.next;
			}
			h = h.next;
		}		
	}
	
	public void print(LinkedListNode head)
	{
		while (head != null)
		{
			System.out.print(head.data);
			System.out.print("\t");
			head = head.next;
		}
	}
	
	public void testFun(){
		LinkedListNode n5 = new LinkedListNode(5);
		LinkedListNode n4 = new LinkedListNode(4, n5);
		LinkedListNode n3 = new LinkedListNode(3, n4);
		LinkedListNode n2 = new LinkedListNode(2, n3);
		LinkedListNode n1 = new LinkedListNode(1, n2);
		LinkedListNode n04 = new LinkedListNode(4, n1);
		LinkedListNode n05 = new LinkedListNode(5, n04);
		
		print(n05);
		System.out.println();
		removeDuplicates(n05);
		print(n05);
	}
}
