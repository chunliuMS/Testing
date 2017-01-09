package LinkedList;

import junit.framework.TestCase;

//Write code to reverse double-linked list.
public class ReverseDoubledLinkedList extends TestCase {
	class Node{
		int value;
		Node next;
		Node prev;
		Node(int value){
			this.value = value;
			this.next = null;
			this.prev = null;
		}
		
		
		Node append(int value){
			Node n = this;
			while(n.next != null){
				n = n.next;
			}
			n.next = new Node(value);
			n.next.prev = this;
			return n.next;
		}
		
		Node reverse(Node node){
			if (node == null)
				return null;
			
			Node temp = node.next;
			node.next = node.prev;
			node.prev = temp;
			
			if ( temp == null)
				return node;
			else
				return reverse(temp);
			
		}
		
		Node reverse(){
			Node curr = this;
			while(true){
				Node next = curr.next;
				curr.next = curr.prev;
				curr.prev = next;
				
				if (next != null)
					curr = next;
				else
					break;
			}
			
			return curr;
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
		Node root = new Node(0);
		root.append(1).append(2).append(3).append(4).append(5).append(6).append(7).append(8).append(9);
		print(root);
		
		Node root1 = root.reverse(root);
		print(root1);	
		
		root1 = root1.reverse(root1);
		print(root1);
		
		root1 = root1.reverse();
		print(root1);
		
	}
	
}
