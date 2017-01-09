package StringAndArray;

import junit.framework.TestCase;

//Implement an algorithm to find the array 
//row 0:  			1
//row 1   		   1  1
//row 2			  1  2  1
//row 3          1  3  3  1
//row 4         1  4  6  4  1
//row 5        1 5 10 10  5  1
//row 6       1 6 15 20 15  6  1
public class GetNewArray extends TestCase {
	int[] getRow(int n){
		int [] v = new int[n+1];
		v[0] = 1;
		
		for (int i = 1; i <= n; i++){
			int save = v[0];
			for (int j = 1; j <= i; j++){
				int temp = v[j];
				v[j] = save  + v[j]; 
				save = temp;			
			}
			
		}
				
		return v;
	}
	
	
	int[] getRow2(int n){
		int [] v = new int[n+1];
		v[0] = 1;
		
		for (int i = 1; i <= n; i++){
			//v[i] = 1;
			for (int j = i ; j > 0; j--){
				v[j] = v[j] + v[j-1];
			}
		}
				
		return v;
	}
	
	void getRow3(int n, int[] a){
		if (n == 0){
			a[0] = 1;
			return;
		}
		
		getRow3(n - 1, a);
		for(int i = n; i > 0; i--){
			a[i] += a[i-1];					
		}			
	}
	
	int[] getRow3(int n){
		int[] a = new int[n+1];
		getRow3(n, a);
		return a;
	}
	
	private void print(int[]a){
		int m = a.length;
		System.out.print("\nArray of " + Integer.toString(a.length - 1));
		for (int i = 0; i < m; i++){
			System.out.print("\t");
			System.out.print(Integer.toString(a[i]));
		}
	}
	
	public void testFun(){
		print(getRow(0));
		print(getRow(1));
		print(getRow(2));
		print(getRow(3));
		print(getRow(4));
		print(getRow(5));
		print(getRow(6));		
		print(getRow(7));
		print(getRow(8));
		print(getRow(9));
		
		System.out.println();
		print(getRow2(0));
		print(getRow2(1));
		print(getRow2(2));
		print(getRow2(3));
		print(getRow2(4));
		print(getRow2(5));
		print(getRow2(6));		
		print(getRow2(7));
		print(getRow2(8));
		print(getRow2(9));		
		
		System.out.println("\n\nRecursive:");
		print(getRow3(0));
		print(getRow3(1));
		print(getRow3(2));
		print(getRow3(3));
		print(getRow3(4));
		print(getRow3(5));
		print(getRow3(6));		
		print(getRow3(7));
		print(getRow3(8));
		print(getRow3(9));
	}
}
