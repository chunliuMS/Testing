package LeetCode;

import java.util.ArrayList;

import junit.framework.TestCase;

//A long array A[] is given. There is a sliding window of size w which is moving from
//left to right, one step per movement.
//Write a method to calculate Array which contains the maximum value of the sliding widnow.
public class LC015_SlidingWindowMaximum extends TestCase {
	
	int[] getMaximum(int[] a, int w)
	{
		if (w > a.length)
			return null;
		
		int b[] = new int[a.length - w + 1];
		ArrayList<Integer> sw = new ArrayList<Integer>();		
				
		for (int i = 0; i < a.length; i++){
			if (!sw.isEmpty()){
				if (i - sw.get(0) > w -1)
					sw.remove(0);
			
				while(!sw.isEmpty() && a[i] >= a[sw.get(sw.size() - 1)])
					sw.remove(sw.size() - 1);
			}
			sw.add(i);
			
			if (i >= w - 1)
				b[i - w + 1] = a[sw.get(0)];	
		}
				
		return b;		
	}
	
	
	public void testFun(){
		int a[] = {1,3,-1,-3,5,3,6,7,3,4};
		int b[] = getMaximum(a, 3);
		if (b != null){
			for (int v : b)
				System.out.print(Integer.toString(v) + " ");							
		}
	}	
}
