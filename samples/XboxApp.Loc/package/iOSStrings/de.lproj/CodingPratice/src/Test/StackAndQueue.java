package Test;

import junit.framework.TestCase;

public class StackAndQueue extends TestCase {
	private class Node<Item>{
		Node(Item data, Node<Item> next){
			this.data = data;
			this.next = next;
		}	
		
		Node(Item data){
			this(data, null);
		}
		
		Item data;
		Node<Item> next;
	}	
	
	public class Stack<Item>{		
		public Stack<Item> push(Item data){
			top = new Node<Item>(data, top);
			return this;
		}
		
		Node<Item> top;
		
		public Item pop(){
			Item ret = null;
			if (top != null){
				ret = top.data;
				top = top.next;
			}
			return ret;	
		}
	}
	
	public class Queue<Item>{
		private Node<Item> first;
		private Node<Item> last;
		
		public Queue<Item> enqueue(Item data){
			if (last == null)
				first = last = new Node<Item>(data);
			else{
				last.next = new Node<Item>(data);
				last = last.next;
			}
			
			return this;
		}
		
		public Item dequeue(){
			Item ret = null;
			if (first != null){
				ret = first.data;
				first = first.next;
				if (first == null)
					last = null;
			}
			
			return ret;
		}		
	}
	
	public void testFun(){
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0).push(1).push(2);
		assertTrue(stack.pop() == 2);
		stack.push(3);
		assertTrue(stack.pop() == 3);
		assertTrue(stack.pop() == 1);
		assertTrue(stack.pop() == 0);
		assertTrue(stack.pop() == null);
		assertTrue(stack.push(4).push(5).pop()== 5);
		assertTrue(stack.pop() == 4);
		assertTrue(stack.pop() == null);
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(0).enqueue(1);
		assertTrue(queue.dequeue() == 0);
		assertTrue(queue.dequeue() == 1);
		assertTrue(queue.dequeue() == null);
		queue.enqueue(2).enqueue(3);
		assertTrue(queue.dequeue() == 2);
		queue.enqueue(4);
		assertTrue(queue.dequeue() == 3);
		assertTrue(queue.dequeue() == 4);		
	}
}
