package Maths;

import junit.framework.TestCase;

//implement an algorithm to get sample array(r) from very big array[s].
public class ReservoirSampling extends TestCase {
	
	public int[] Sampling(int[] s, int k){
		if (s.length < k)
			return null;
		
		int[] r = new int[k];
		int i = 0;
		while(i < k)
			r[i] = s[i++];
		
		while (i < s.length){
			int index = (int)(i * Math.random());
			if (index < k)
				r[index] = s[i];
			i++;
		}
		
		return r;
	}
	
		
	public void testFun(){
	
	}
}
