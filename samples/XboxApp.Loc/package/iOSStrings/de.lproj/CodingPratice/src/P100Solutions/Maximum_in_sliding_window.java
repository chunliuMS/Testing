package P100Solutions;

import junit.framework.TestCase;
import java.util.*;

/* Question: Given an array of numbers and a sliding window size, how to get the maximal numbers in all sliding windows?      
*/

public class Maximum_in_sliding_window extends TestCase {
	
	int[] maxArray(int[] arr, int size) {
		if (arr == null || arr.length < size || size  <= 0)
			return null;
		if (size == 1)
			return arr;
		
		int[] m = new int[arr.length - size + 1];
		
		Stack<Integer> ll = new Stack<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			int v = arr[i];
			while (!ll.isEmpty() && arr[ll.peek()] <= v)
				ll.pop();
			
			ll.push(i);
			
			if (i > ll.firstElement() + size - 1)
				ll.remove(0);
			
			int j = i - size + 1;
			if (j >= 0)
				m[j] = arr[ll.firstElement()];
				
		}
		
		return m;
	}
	
	public void testFun(){	
		int[] arr = {1, 2, 3, 4, 5, 6, 3, 5 , 9, 4, 6};
		arr = maxArray(arr, 3);
		
		for (int v : arr) {
			System.out.print("\t");
			System.out.print(v);
		}
	}
	
}