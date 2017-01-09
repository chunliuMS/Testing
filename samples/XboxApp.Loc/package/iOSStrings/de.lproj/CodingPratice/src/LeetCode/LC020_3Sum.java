package LeetCode;

import junit.framework.TestCase;

//Given a shorted array of integers, find all triplets with the sum  equals s. 
public class LC020_3Sum extends TestCase {
			
	void print(int[] a, int s){
		for (int k = 0; k <= a.length - 3; k++){
			int i = k + 1;
			int j = a.length - 1;
			
			while (i < j){
				int t = a[i] + a[j] + a[k];
				if (t < s)
					i++;
				else if (t == s){
					System.out.print("(" + Integer.toString(a[k]) + 
							         "," + Integer.toString(a[i]) + 
							         "," + Integer.toString(a[j]) + ")");				
					i++;
				}
				else
					j--;
			}
		}
	}
	
	public void testFun(){
		int[] a = { -2, -1, 0, 1, 3, 4,  6, 7, 8, 9, 10}; 
		print(a, 7);
	}	
}
