package EPI;

import junit.framework.TestCase;
/* 
 * Give integer k > 0, print out each line of pascal's triangle. 
*/
				
public class Arrays6_ComputePascalTriangle extends TestCase {
	void print(int k) {
		if (k <= 0)
			return;
		
		int[] a = new int[k];
		a[0] = 1;
		for (int i = 1; i <= k; i++){
			a[i-1] = 1;
			for (int j = i - 2; j >= 1; j--){
				a[j] = a[j] + a[j-1];
			}
			
			for (int j = 0; j < (k - i) ; j++)
				System.out.print("\t");
			
			for (int j = 0; j < i; j++){
				System.out.print(a[j]);
				System.out.print("\t\t");
			}
			
			System.out.println();
				
		}
	}
		
	public void testFun(){	
		print(10);
	}		
}