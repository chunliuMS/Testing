package P100Solutions;

import java.util.Arrays;

import junit.framework.TestCase;

/*  Problem: Reorder the digits of a number, in order to get the next number which is the least one that is greater than the input number. 
 * For example, the number 34724126 is the next number of 34722641 when reordering digits  
*/

public class Next_number_with_same_set_of_data extends TestCase {
		
	 int getNextNUmberwithSameSetOfDigits(int number) {
		 if (number <= 0)
			 return number;
		 
		 int[] a = new int [20];
		 int length = 0;
		 while (number > 0) {
			 a[length++] = number % 10;
			 number /= 10;
		 }
		 
		 int i;
		 for (i = 1; i < length; i++) {
			 if (a[i] < a[i - 1])
				 break;
		 }
		 
		 if (i  <= length - 1) { // found the turning point
			 for (int j = 0; j < i; j++){
				 if (a[j] > a[i]){
					 int temp = a[j];
					 a[j] = a[i];
					 a[i] = temp;
					 break;
				 }
			 }
			 
			 Arrays.sort(a, 0, i);			 
		 }
		 
		 int num = 0; 
		 for (int ii = length - 1; ii >= i; ii--){
			 num *= 10;
			 num += a[ii];
		 }
		 
		 for (int ii= 0; ii < i ; ii++){
			 num *= 10;
			 num += a[ii];
		 }	 
		 
		 
		 return num;
	 }
	 
	public void testFun(){	
		
		assertTrue(getNextNUmberwithSameSetOfDigits(34722641) == 34724126);
		
	}
	
}