package EPI;

import java.util.HashMap;
import java.util.Stack;

import junit.framework.TestCase;
/* 
 * Test a string over "{}()[]" for well formedness. 
*/
				
public class StackQueue9_ValidFormedBrackets extends TestCase {
		
	boolean wellFormed(String str) {
		if (str == null || str.length() < 2)
			return false;
		
		Stack<Character> stack = new Stack<Character>();
		HashMap<Character, Character> map = new HashMap<Character, Character> ();
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(' || c== '[' || c== '{') {
				stack.push(c);
			} else if (c == ')' || c == ']' || c == '}') {
				if (stack.isEmpty() || map.get(stack.peek()) != c){
					return false;
				} else {
					stack.pop();
				}
			}
		}
		
		return stack.isEmpty();
	}
	
		
	public void testFun(){	
		assertTrue(wellFormed("({[1]}{2}){}{}"));
		assertFalse(wellFormed("({[]]}{}){}{}"));
	}		
}