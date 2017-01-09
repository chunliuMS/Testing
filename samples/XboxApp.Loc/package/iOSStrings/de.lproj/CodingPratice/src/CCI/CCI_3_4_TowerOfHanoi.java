package CCI;

import java.util.Stack;

import junit.framework.TestCase;

//Towers of Hanoi
public class CCI_3_4_TowerOfHanoi extends TestCase {
	
	void move(Stack<Integer> src, int n,  Stack<Integer> desc, Stack<Integer> buffer)
	{				
		if (n > 0)
		{
			move(src, n - 1, buffer, desc);
			desc.push(src.pop());
			move(buffer, n - 1, desc, src);
		}			
	}
	
	void move(Stack<Integer> src, Stack<Integer> desc){
		if (src == null || desc == null)
			return;
		Stack<Integer> buffer = new Stack<Integer>();
		move(src, src.size(), desc, buffer);
	}
	
	public void print(Stack<Integer> stack)
	{
		for ( int i = 0; i < stack.size(); i++){
			System.out.print(stack.elementAt(i));
			System.out.print("\t");
		}
	}
	
	public void testFun(){
		Stack<Integer> src = new Stack<Integer>();
		
		for (int i = 10; i > 0; i--)
			src.push(i);
			
		Stack<Integer> dest = new Stack<Integer>();
				
		System.out.println("SRC:");
		print(src);			
		
		move(src,  dest);
		
		System.out.println("SRC:");
		print(src);
		
		System.out.println("dest:");
		print(dest);				
	}
}
