package Util;

public class ListNode <T> {
	public T value;
	public ListNode<T> next;
	
	public ListNode (T v) {
		this.value = v;
	}
	
	public ListNode<T> add(T next) {
		if (next != null) {
			this.next = new ListNode <T> (next);
		}
		return this.next;		
	}
	
	public void print() {
		System.out.println("*******Print the list******\n");
		
		ListNode<T> head = this;
		while (head != null) {
			System.out.print(head.value.toString());
			System.out.print("\t");
			
			head = head.next;
		}
		
		System.out.println("\n*******end******");
	}
}
