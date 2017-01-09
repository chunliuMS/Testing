package P100Solutions;

import Util.ListNode;
import junit.framework.TestCase;

/*  Question: A board has n*m cells, and there is a gift with some value (value is greater than 0) in every cell. 
 * You can get gifts starting from the top-left cell, and move right or down in each step, and finally reach the cell at the bottom-right cell. 
 * What’s the maximal value of gifts you can get from the board?  
*/

public class Stack_with_min_function extends TestCase {
		
	class Stack 
	{
		public Integer pop() {
			if (top == null)
				return null;
			int val;
			if (top.value >= min)
				val = top.value;
			else {
				val = min;
				min = 2 * min - top.value;
			}
			
			top = top.next;
			return val;	
		}
		
		public Stack push(int value) {
			int nodeValue = value;
			if (top == null)
				min = value;
			if (value < min) {
				nodeValue = 2 * value - min;
				min = value;
			}
				
			ListNode<Integer> n = new ListNode<Integer>(nodeValue);
			n.next = top;
			top = n;
			
			return this;
		}
		
		public Integer min() {
			if (top == null)
				return null;
			if (top.value > min)
				return min;
			else
				return 2 * min - top.value;
		}
		
		private ListNode<Integer> top;
		private int min;
	}
	
	public void testFun(){	
		Stack s = new Stack();
		s.push(8).push(6).push(2).push(4).push(1).push(9);
		for (int i = 0; i < 6; i++) {
			System.out.println("min -- " + s.min + " val -- " + s.pop());
		}
	}
	
}