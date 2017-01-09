package LinkedList;

import junit.framework.TestCase;

//Write code to remove duplicates from unsorted linked list.
public class RemoveDupFromList extends TestCase {
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
		
		void removeDup(){
			Node curr = this;
			while (curr != null){
				Node ptr = curr;
				while (ptr.next != null){
					if (curr.value == ptr.next.value)
						ptr.next = ptr.next.next;
					else
						ptr = ptr.next;
				}
				curr = curr.next;
			}
		}
	}
	
	
	
	private void print(Node root){
		Node n = root;
		System.out.print("\r\nValues: \n");
		while (n!= null){
			System.out.print("\t" + Integer.toString(n.value));
			n = n.next;
		}
	}
	
	public void testFun(){
		Node root = new Node(5);
		root.append(0).append(0).append(6).append(5).append(4).append(3).append(2).append(1).append(1);
		print(root);
		
		root.removeDup();
		print(root);
		
	}
}
