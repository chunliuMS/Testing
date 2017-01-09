package P100Solutions;

import junit.framework.TestCase;


/* Question: A sub-array has one number of some continuous numbers. 
 * Given an integer array with positive numbers and negative numbers, get the maximum sum of all sub-arrays. Time complexity should be O(n).  
 *  For example, in the array {1, -2, 3, 10, -4, 7, 2, -5}, its sub-array {3, 10, -4, 7, 2} has the maximum sum 18.      
*/
public class Max_subsequence_in_array extends TestCase {
	int maxstart, maxend;

		
	int maxSequence(int[] array) {
		
		assertFalse (array == null);
		
		int max = Integer.MIN_VALUE;
		int st = 0;
		int total = 0;
		
		for (int i = 0; i < array.length; i++) {
			if (total <= 0 ) {
				total = array[i];
				st = i;
			}
			else
				total += array[i];
				
			if (total > max) {
				max = total;
				maxstart = st;
				maxend = i;
			}
		}
		
		return max;
	}
	
	
	public void testFun(){	
		int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
		assertTrue(maxSequence(array) == 18);
	}
	
}