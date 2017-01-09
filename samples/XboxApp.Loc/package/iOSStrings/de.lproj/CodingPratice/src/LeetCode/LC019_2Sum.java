package LeetCode;

import junit.framework.TestCase;

//Given a sorted array of integers, find all pair of integers that the sum of them is s. 
public class LC019_2Sum extends TestCase {
			
	void print(int[] a, int s){
		int i = 0;
		int j = a.length - 1;
		
		while (i < j){
			int t = a[i] + a[j];
			if (t < s)
				i++;
			else if (t == s){
				System.out.print("(" + Integer.toString(a[i]) + "," + Integer.toString(a[j]) + ")");				
				i++;
			}
			else
				j--;
		}
	}
	
	public void testFun(){
		int[] a = { -2, -1, 1, 3, 4,  6, 7, 8, 9, 10}; 
		print(a, 7);
	}	
}
