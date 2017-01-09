package StringAndArray;

import java.util.Arrays;

import junit.framework.TestCase;

//Given an array of integers. Find the contiguous sequence with the largest sum. 
//Print out the sub-array and the sum.
public class FindPairEqualsSumValue extends TestCase {
	
	public void findLargestSum(int[]a, int v){
		if (a == null || a.length < 2){
			return;	
		}
		
		Arrays.sort(a);
		int min = 0;
		int max = a.length - 1;
		
		while(min < max){
			int t = a[min] + a[max];
			if (t == v){
				System.out.print("(" + Integer.toString(a[min]) + " " +
						Integer.toString(a[max]) + ")");
				min++;
				max--;
			}
			else if (t < v){
				min++;
			}
			else{
				max--;
			}
		}
		
		
	}		
	public void testFun(){		
		int[] a = { -3, 5, 4, 1, 0, 4, 6, 0, 3, 7, -5, 2, 14, 12};
		System.out.println("\nv = 9");
		findLargestSum(a, 9);			
		System.out.println("\nv = 5");
		findLargestSum(a, 5);
	}
}
