package P100Solutions;

import junit.framework.TestCase;

/*  Problem: Given a matrix with 1s and 0s, please find the number of groups of 1s. 
 * A group is defined by horizontally or vertically adjacent 1s. For example, 
 * there are four groups of 1s in Figure 1 which are drawn with different colors. 
*/

public class Group_of_1s_in_matrix extends TestCase {
		
	 int getTotalGroupOf1s(int[][] ar) {
		 if (ar == null || ar.length == 0)
			 return 0;
		 
		 int total = 0;
		 
		 for (int r = 0; r < ar.length; r++) {
			 for (int c = 0; c < ar[0].length; c++) {
				 if (ar[r][c] == 1){
					 total++;
					resetAjacent(ar, r, c); 
				 }
			 }
		 }
		 
		 return total;
	}
	 
	void resetAjacent(int[][] ar, int r, int c){
		if (r >= 0 && r < ar.length && c >= 0 && c < ar[0].length && ar[r][c] == 1) {
			ar[r][c] = 0;
			resetAjacent(ar, r - 1, c);
			resetAjacent(ar, r, c - 1);
			resetAjacent(ar, r, c + 1);
			resetAjacent(ar, r + 1, c);
		}		
	}
	
	
	public void testFun(){	
		int [][] arr = { {1, 1, 0, 0, 1},
						 {1, 0, 0, 1, 0},
						 {1, 1, 0, 1, 0},
						 {0, 0, 1, 0, 0}
					    };
		
		assertTrue(getTotalGroupOf1s(arr) == 4);
		
	}
	
}