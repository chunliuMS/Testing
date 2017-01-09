package P100Solutions;

import junit.framework.TestCase;

/*  Question: A board has n*m cells, and there is a gift with some value (value is greater than 0) in every cell. 
 * You can get gifts starting from the top-left cell, and move right or down in each step, and finally reach the cell at the bottom-right cell. 
 * What’s the maximal value of gifts you can get from the board?  
*/

public class Maximum_value_of_gifts extends TestCase {
		
	 int getMaximumValueOfGifts(int[][] ar) {
		 if (ar == null || ar.length == 0)
			 return 0;
		 
		 for (int r = 0; r < ar.length; r++) {
			 for(int c = 0; c < ar[0].length; c++) {
				 if (r > 0 && c > 0)
					 ar[r][c] += ar[r][c - 1] > ar[r -1][c] ? ar[r][c -1] : ar[r -1][c];
				 else if (r > 0)
					 ar[r][c] += ar[r-1][c];
				 else if (c > 0)
					 ar[r][c] += ar[r][c - 1];
			 }
		 }
		 
		 return ar[ar.length - 1][ar[0].length - 1];
	}
	 
	public void testFun(){	
		int [][] arr = { {1, 10, 3, 8},
						 {12, 2, 9, 6},
						 {5, 7, 4, 11},
						 {3, 7, 16, 5}
					    };
		
		assertTrue(getMaximumValueOfGifts(arr) == 53);
		
	}
	
}