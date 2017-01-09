package CCI;

import java.util.Stack;

import junit.framework.TestCase;

//Sort a stack in ascending order
public class CCI_3_6_StackSort extends TestCase {
		
	public void sort(Stack<Integer> numbers){
		Stack<Integer> back = new Stack<Integer>();
		while(!numbers.empty()){
			Integer temp = numbers.pop();
			while (!back.empty()&& temp > back.peek()){
				numbers.push(back.pop());
			}
			back.push(temp);
		}
		
		numbers.clear();
		while(!back.empty())
			numbers.push(back.pop());
	}
	
	public void testFun(){			
		
		Stack<Integer> numbers = new Stack<Integer>();
		numbers.push(4);
		numbers.push(6);
		numbers.push(2);
		numbers.push(8);
			
		sort(numbers);
		
		while (!numbers.empty()){
			System.out.println(numbers.pop());			
		}				
	}
}
