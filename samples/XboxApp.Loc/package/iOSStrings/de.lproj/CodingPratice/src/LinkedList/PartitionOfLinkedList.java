package LinkedList;

import junit.framework.TestCase;

//Partition a linked list to put all the elements with values less than a given value before.
public class PartitionOfLinkedList extends TestCase {
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
		
		Node partition(int value){
			Node lHead = null;
			Node lTail = null;
			Node rHead = null;
			Node rTail = null;
			
			Node current = this;
			while (current != null){
				Node next = current.next;
				current.next = null;
				if (current.value < value){
					if (lHead == null){						
						lHead = lTail = current;
					}
					else{
						lTail.next = current;
						lTail = lTail.next;
					}
				}
				else {
					if (rHead == null){
						rHead = rTail = current;
					}
					else{
						rTail.next = current;
						rTail = rTail.next;
					}
				}
					
				current = next;
			}
			
			if (lHead != null){
				lTail.next = rHead;
				return lHead;
			}
			else
				return rHead;
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
		
		Node ret = root.partition(0);
		print(ret);
		
		ret = ret.partition(3);
		print(ret);
		
		ret = ret.partition(4);
		print(ret);
		
		ret = ret.partition(6);
		print(ret);
	}
}
