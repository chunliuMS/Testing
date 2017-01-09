package LeetCode;

import junit.framework.TestCase;

//Given a function which generates a random integer in the range 1 to 7, 
//write a function which generates a random integer in the range 1 to 10 uniformly. 
public class LC021_RejectionSampling extends TestCase {
	
	int Rand7(){
		return (int)(Math.random() * 1000) % 7 + 1;
	}
			
	int Rand10(){
		while (true){
			int r = Rand7();
			int c = Rand7();
			
			int t = r + (c - 1) * 7;
			if (t <= 40)
				return t % 10 + 1;
		}
	}
	
	public void testFun(){
		
	}	
}
