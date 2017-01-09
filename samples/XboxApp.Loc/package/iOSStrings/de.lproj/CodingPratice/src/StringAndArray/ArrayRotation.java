package StringAndArray;

import junit.framework.TestCase;

//Implement an algorithm to determine if a string has all unique characters. 
//What if you can not use additional data structure.
public class ArrayRotation extends TestCase {
	public void rotate(int[][] a){
		int n = a.length;
		if (n >= 2){
			
			for (int layer = 0; layer < n/2; layer++){
				int start = layer;
				int end = n - start - 1;
				
				for (int i = start; i < end; i++){
					int offset = i - start;
					int top = a[layer][i];
					
					a[layer][i] = a[end-offset][layer];
					a[end-offset][layer] = a[end][end-offset];
					a[end][end - offset] = a[i][end];
					a[i][end] = top;
				}
			}
		}
	}
	
	private void print(int[][] a){
		int m = a.length;
		System.out.print("\nArray : \n");
		for (int i = 0; i < m; i++){
			for (int j = 0; j < m; j++){
				System.out.print("\t" + Integer.toString(a[i][j]));
			}
			System.out.print("\n");	
		}
	}
	
	public void testFun(){
		int[][] a = {  	{ 0,  1,  2,  3,  4},
						{ 5,  6,  7,  8,  9},
						{ 10, 11, 12, 13, 14},
						{ 15, 16, 17, 18, 19},
						{ 20, 21, 22, 23, 24}						
		};
		
		print(a);
		rotate(a);
		print (a);
	}
}
