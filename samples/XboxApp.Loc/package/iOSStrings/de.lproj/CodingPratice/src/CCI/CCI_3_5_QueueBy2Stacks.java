package CCI;

import java.util.Stack;

import junit.framework.TestCase;

//Implement MyQueue with 2 Stack class
public class CCI_3_5_QueueBy2Stacks extends TestCase {
		
	class MyQueue<T>{
		public MyQueue()
		{			
		}
		
		public void enque(T i)
		{
			newOne.push(i);
		}
		
		public T dequeue()
		{
			T v = null;
			if(old.isEmpty()){
				while(!newOne.isEmpty())
					old.push(newOne.pop());
			}
			if (!old.empty())
			  v = old.pop();
		   
		    return v;
		}
		
		private Stack<T> old = new Stack<T>();
		private Stack<T> newOne = new Stack<T>();
	}
	
	
	public void testFun(){			
		MyQueue<Integer> queue = new MyQueue<Integer>();
		for (int i = 0; i < 10; i++)
			queue.enque(i);
			
		Integer value = queue.dequeue();
		while (value != null){
			System.out.print(value);
			value= queue.dequeue();
		}				
	}
}
