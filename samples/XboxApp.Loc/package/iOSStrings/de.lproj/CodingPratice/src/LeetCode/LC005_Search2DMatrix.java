package LeetCode;

import junit.framework.TestCase;

//Find the the value in a 2D array, each row and column are sorted.
public class LC005_Search2DMatrix extends TestCase {
	class Pos
	{
		public Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
		int r;
		int c;
	}
	
	void print(Pos p){
		if (p == null)
			System.out.println("not found");
		else
			System.out.println(Integer.toString(p.r) + " " + Integer.toString(p.c));
	}
	
	Pos search(int[][]a, int k){
		int m = a.length;
		int n = m > 0 ? a[0].length : 0;
		
		int r = 0;
		int c = n - 1;
		
		while(r < m && c >= 0){
			if (a[r][c] > k)
				c--;
			else if (a[r][c] < k)
				r++;
			else 
				return new Pos(r, c);
		}
		
		return null;
	}
	
	void search1(int[][]a, int k){
		Pos pos = new Pos( -1, -1);
		int m = a.length;
		int n = m > 0 ? a[0].length : 0;
		if (search(a, k, 0, 0, m - 1, n - 1, pos))
			print(pos);
		else print(null);
	}
	
	boolean search(int[][]a, int k, int m1, int n1, int m2, int n2, Pos pos){
		if (m2 < m1 || n2 < n1)
			return false;		
		if (a[m1][n1] > k || a[m2][n2] < k)
			return false;
					
		int row = m1 + (m2-m1)/2;
		int col = n1 + (n2-n1)/2;
		if (a[row][col] == k){
			pos.r = row;
			pos.c = col;
			return true;
		}
		
		if (a[row][col] > k){
			return search(a, k, m1, n1, row -1, n2, pos) ||
				   search(a, k, row, n1, m2, col - 1, pos);
		}
		else{
			return search(a, k, m1, col + 1, m2 , n2, pos) ||
			       search(a, k, row + 1, n1, m2, col, pos);
		}			
	}
	
	public void testFun(){
		int[][] a = {	{1,  4,   7, 11, 16},
					  	{2,  5,   8, 12, 19},
					  	{3,  6,   9, 16, 22},
					  	{10, 13, 14, 17, 24},
					  	{18, 21, 23, 26, 30}	};
		
		print(search(a, 5));
		print(search(a, 9));
		print(search(a, 13));
		print(search(a, 26));
		print(search(a, 15));
		
		search1(a, 5);
		search1(a, 9);
		search1(a, 13);
		search1(a, 26);
		search1(a, 15);
	}	
}
