package LeetCode;

import junit.framework.TestCase;

//Print the 2D array in spiral. 
public class LC018_Print2DArrayInSpiralOrder extends TestCase {			
		
	void print2(int[][]a, int start, int m, int n){
		if (start > m || start > n)
			return;
		
		for(int i = start; i <= n; i++)
			System.out.print(a[start][i] + " ");
		for (int i = start + 1; i <= m; i++)
			System.out.print(a[i][n] + " ");
		for (int i = n - 1; i >= start; i--)
			System.out.print(a[m][i] + " ");
		for(int i = m - 1; i > start; i--)
			System.out.print(a[i][start] + " ");
		
		print2(a, ++start, --m, --n);
	}
	
	public void testFun(){
		int[][] a = { {1, 2, 3, 4, 5},
					  {6, 7, 8, 9, 10},
					  {11,12,13,14,15},
					  {16,17,18,19,20},
					  {21,22,23,24,25}
					};
		print2(a, 0, 4, 4);
	}	
}
