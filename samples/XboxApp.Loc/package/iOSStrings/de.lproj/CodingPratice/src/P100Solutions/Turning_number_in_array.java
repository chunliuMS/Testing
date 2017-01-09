package P100Solutions;

import junit.framework.TestCase;


/*  Problem: Turning number is the maximum number in an array which increases and then decreases. 
 *  This kind of array is also named unimodal array.
 *  Please write a function which gets the index of the turning number in such an array.  
 *  For example, the turning number in array {1, 2, 3, 4, 5, 10, 9, 8, 7, 6} is 10, so its index 5 is the expected output.   
*/
public class Turning_number_in_array extends TestCase {
		
	int getTurningNumber(int[] array) {
		
		if (array == null || array.length < 3)
			return -1;
		
		int left = 0;
		int right = array.length - 1;
		
		if (array[0] > array[1] || array[right] >= array[right -1])
			return -1;
		
		while(left < right - 1) {
			int mid = (left + right) / 2;
			if (array[mid] > array[mid -1] && array[mid] > array[mid + 1])
				return mid;
			else if (array[mid] > array[mid -1] && array[mid] < array[mid + 1])
				left = mid;
			else 
				right = mid;
		}
		
		return -1;
	}
	
	
	public void testFun(){	
		int[] array = {1, 2, 3, 4, 5, 10, 9, 8, 7, 6};
		assertTrue(getTurningNumber(array) == 5);
	}
	
}