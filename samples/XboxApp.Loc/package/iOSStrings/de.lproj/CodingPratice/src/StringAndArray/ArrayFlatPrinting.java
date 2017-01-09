package StringAndArray;

import org.junit.Test;


public class ArrayFlatPrinting {
	void printArray(int[][]array){
		int m = array.length;
		int n = array[0].length;
		
		for (int i = 0; i < m; i++){
			System.out.print("\r\n row " + Integer.toString(i) + "\t\t");
			for (int j = 0; j < n; j++)	{
				System.out.print("\t" + Integer.toString(array[i][j]));
			}
		}
		
		System.out.print("\r\n");
	}
	
	public void print(int[][] a)
	{
		int m = a.length;
		int n = a[0].length;
		
		int m_s = 0;
		int m_e = m -1;
		int n_s = 0;
		int n_e = n -1;
		
		while (m_s <= m_e && n_s <= n_e)	{
			//print top
			
			//System.out.print("\r\ntop \r\n");
			for (int i = n_s; i <= n_e; i++){
				System.out.print(Integer.toString(a[m_s][i]));
				System.out.print(" ");
			}			
			
			//print right
			//System.out.print("\r\nright \r\n");
			for (int i = m_s + 1; i <= m_e -1; i++){
				System.out.print(Integer.toString(a[i][n_e]));
				System.out.print(" ");
			}
			
			//print bottom
			//System.out.print("\r\nbottom \r\n");
			for (int i = n_e; i >= n_s; i--){
				System.out.print(Integer.toString(a[m_e][i]));
				System.out.print(" ");
			}
			
			//print left;
			//System.out.print("\r\nleft \r\n");
			for (int i = m_e - 1; i >= m_s + 1; i--){
				System.out.print(Integer.toString(a[i][n_s]));
				System.out.print(" ");
			}
			
			m_s++;
			m_e--;
			
			n_s++;
			n_e--;
			
			if (m_s == m_e && n_s == n_e){
				System.out.print(Integer.toString(a[m_s][n_s]));
			
				break;
			}
		
		}		
	}
	
		
	@Test
	public void test() {
		int[][] array = {	{0,1,2,3,4},
							{5,6,7,8,9,}, 
							{10, 11,12,13,14}, 
							{15,16,17,18, 19}, 
							{20,21,22,23,24}};
		
		printArray(array);
		
		print(array);
		
		int[][] array1 = {	{0,1,2,3},
				{5,6,7,8}, 
				{10, 11,12,13}, 
				{15,16,17,18}, 
				{20,21,22,23}};

		printArray(array1);

		print(array1);
	}
}
