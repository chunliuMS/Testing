package Algorithms;

import java.util.Iterator;

import junit.framework.TestCase;

public class A_1_3_BagStackQueue extends TestCase {
	
	class Bag<Item> implements Iterable<Item>{
		private class Node{
			Node(Item item, Node next){
				this.item = item;
				this.next = next;
			}
			Item item;
			Node next;
		}
		
		Bag(){			
		}
		
		void add(Item item){
			top = new Node(item, top);
			N++;
		}
		
		int size(){
			return N;
		}
		
		boolean isEmpty(){
			return N == 0;
		}
		
		private int N;
		private Node top;
		@Override
		public Iterator<Item> iterator() {
			return new BagIterator();
		}
		
		private class BagIterator implements Iterator<Item>
		{
			private Node current = top;
			public boolean hasNext(){ 
				return current != null; }
			public void remove() {}
			public Item next(){
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}
	
	class Queue<Item> implements Iterable<Item>{
		private class Node{
			Node(Item item, Node next){
				this.item = item;
				this.next = next;
			}
			Item item;
			Node next;
		}
		
		Queue(){			
		}
		
		void enqueue(Item item){
			if (isEmpty()){
				first = last = new Node(item, null);
			}
			else{
				last.next = new Node(item, null);
				last = last.next;
			}
			
			N++;
		}
		
		Item dequeue(){
			if (isEmpty())
				return null;
			
			Node ret = first;
			first = first.next;
			N--;
			
			if (N == 0)
				last = null;
			
			return ret.item;
		}
		
		int size(){
			return N;
		}
		
		boolean isEmpty(){
			return N == 0;
		}
		
		private int N;
		private Node first;
		private Node last;
		
		@Override
		public Iterator<Item> iterator() {
			return new QueueIterator();
		}
		
		private class QueueIterator implements Iterator<Item>
		{
			private Node current = first;
			public boolean hasNext(){ 
				return current != null; }
			public void remove() {}
			public Item next(){
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}
	
	class Stack<Item> implements Iterable<Item>{
		private class Node{
			Node(Item item, Node next){
				this.item = item;
				this.next = next;
			}
			Item item;
			Node next;
		}
		
		Stack(){			
		}
		
		void push(Item item){
			top = new Node(item, top);			
			N++;
		}
		
		Item pop(){
			if (isEmpty())
				return null;
			
			Node ret = top;
			top = top.next;
			N--;
			
			return ret.item;
		}
		
		int size(){
			return N;
		}
		
		boolean isEmpty(){
			return N == 0;
		}
		
		private int N;
		private Node top;
	
		@Override
		public Iterator<Item> iterator() {
			return new QueueIterator();
		}
		
		private class QueueIterator implements Iterator<Item>
		{
			private Node current = top;
			public boolean hasNext(){ 
				return current != null; }
			public void remove() {}
			public Item next(){
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}
	
	public void testFun(){
		Bag<Integer> bag = new Bag<Integer>();
		for (int i = 0; i < 10; i++){
			bag.add(i);
		}
		StringBuilder sb = new StringBuilder("Bags: ");
		for (int i : bag){
			sb.append(i).append(" ");
		}		
		System.out.println(sb.toString());
		
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < 10; i++)
			queue.enqueue(i);
		
	    queue.enqueue(queue.dequeue());
	    queue.enqueue(queue.dequeue());	    
	    sb = new StringBuilder("Queues: ");
		for (int i : queue){
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
		
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < 10; i++)
			stack.push(i);
		
		stack.push(stack.pop());
	    stack.push(stack.pop());
	    stack.pop();
	    sb = new StringBuilder("Stack: ");
		for (int i : stack){
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}
}
