package Algorithms;

import junit.framework.TestCase;

public class A_1_3_ReverseLinkedList extends TestCase {
	
	class Node<Item>{
		Node(Item item, Node<Item> next){
			this.item = item;
			this.next = next;
		}
		
		Node<Item> reverse(){
			Node<Item> prev = null;
			Node<Item> current = this;
			while(current != null){
				Node<Item> temp = current.next;
				current.next = prev;
				prev = current;
				current = temp;
			}
			return prev;
		}	
		
		Node<Item> reverse(Node<Item> curr, Node<Item> prev){
			if (curr == null)
				return null;
			
			Node<Item> last = null;
			if (curr.next == null)
				last = curr;
			else
				last = reverse(curr.next, curr);
			curr.next = prev;
			return last;
		}
		
		Node<Item> reverse(Node<Item> curr){
			if (curr == null)
				return null;
			
			if (curr.next == null)
				return curr;
			
			Node<Item> last = reverse(curr.next);
			curr.next.next = curr;
			curr.next = null;
			return last;
		}
	
		void print() {
			StringBuilder sb = new StringBuilder();
			Node<Item> curr = this;
			while(curr != null){
				sb.append(curr.item).append(" ");
				curr = curr.next;
			}
			System.out.println(sb.toString());
		}
		Item item;
		Node<Item> next;
	}
	
		
	public void testFun(){
		Node<Integer> prev = null;
		for (int i = 10; i >= 0; i--){
			prev = new Node<Integer>(i, prev);
		}
		prev.print();
		prev = prev.reverse();
		prev.print();
		
		prev = prev.reverse(prev, null);
		prev.print();
		
		prev = prev.reverse(prev);
		prev.print();
	}
}
