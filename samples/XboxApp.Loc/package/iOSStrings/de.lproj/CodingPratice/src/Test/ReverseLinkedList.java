package Test;

import junit.framework.TestCase;

public class ReverseLinkedList extends TestCase {
	class Node<Item>{
		Node(Item data, Node<Item> next){
			this.data = data;
		}
		Item data;
		Node<Item> next;
		
		public Node<Item> add(Item data){
			Node<Item> n = new Node<Item>(data, null);
			this.next = n;
			return n;
		}		
	}
	
	public void printAll(Node<Integer> head){
		System.out.println();
		while(head != null){
			System.out.print(head.data + " ");
			head = head.next;
		}		
	}
	
	Node<Integer> reverse(Node<Integer> head){
		Node<Integer> prev = null;
		while(head != null){
			Node<Integer> next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;			
	}
	
	Node<Integer> reverse2(Node<Integer> node){
		if (node.next == null)
			return node;
		
		Node<Integer> ret = reverse2(node.next);
		node.next.next = node;
		node.next = null;
		
		return ret;
	}
	
	class DNode<Item>{
		DNode(Item data, DNode<Item> prev, DNode<Item> next ){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		Item data;
		DNode<Item> prev;
		DNode<Item> next;
		
		public DNode<Item> add(Item data){
			DNode<Item> n = new DNode<Item>(data, this, null);
			this.next = n;
			return n;
		}		
	}
	
	DNode<Integer> reverse(DNode<Integer> head){
		DNode<Integer> prev = null;
		DNode<Integer> curr = head;
		while (curr != null){
			DNode<Integer> next = curr.next;
			curr.next = curr.prev;
			curr.prev = next;
			
			prev = curr;
			curr = next;
		}
		
		return prev;
	}
	
	public void printAll(DNode<Integer> head){
		System.out.println();
		while(head != null){
			System.out.print(head.data + " ");
			head = head.next;
		}		
	}
	
	public void testFun(){
		Node<Integer> head = new Node<Integer>(0, null);
		head.add(1).add(2).add(3).add(4).add(5);
		printAll(head);
		head = reverse(head);
		printAll(head);
		
		printAll(reverse2(head));
		
		DNode<Integer> dn = new DNode<Integer>(10, null, null);
		dn.add(11).add(12).add(13).add(14).add(15);
		
		printAll(dn);
		printAll(reverse(dn));
		
	}
}
