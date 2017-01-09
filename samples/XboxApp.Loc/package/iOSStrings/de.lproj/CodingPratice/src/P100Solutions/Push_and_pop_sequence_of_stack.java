package P100Solutions;

import java.util.Stack;

import junit.framework.TestCase;

/* Problem: Given two integer sequences, one of which is the push sequence of a stack, 
 * please check whether the other sequence is a corresponding pop sequence or not.   
 * For example, if 1, 2, 3, 4, 5 is a push sequence, 4, 5, 3, 2, 1 is a corresponding pop sequence, but the sequence 4, 3, 5, 1, 2 is not.    
*/

public class Push_and_pop_sequence_of_stack extends TestCase {
	
	boolean isPopSequence(int[] push, int[] pop) {
		if (push == null || pop == null || push.length == 0 || push.length != pop.length)
			return false;
		Stack<Integer> pStack = new Stack<Integer> ();
		int pushIndex = 0;
		for (int i = 0; i < pop.length; i++) {
			int v = pop[i];
			if (pStack.isEmpty()) {
				for (int j = 0; j < push.length; j++){
					if (push[j] != v)
						pStack.push(push[j]);
					else {
						pushIndex = j + 1;
						break;
					}						
				}					
			}
			else {
				if (pStack.peek() == v)
					pStack.pop();
				else if (pushIndex < push.length && push[pushIndex] == v) {
					pushIndex++;
				}
				else
					return false;
			}
		}
		
		return true;
	}
	
	public void testFun(){	
		int[] push = {1, 2, 3, 4, 5};
		int[] pop1 = {4, 5, 3, 2, 1};
		int[] pop2 = {4, 3, 5, 1, 2};
		
		assertTrue(isPopSequence(push, pop1));
		assertFalse(isPopSequence(push, pop2));
	}
	
}