package EPI;

import junit.framework.TestCase;
/* 
 * Write a program that takes an array of integers where a[i] denotes the maximum you can advance from the index i,
 * and returns whether it's possible to reach the end. 
*/
				
public class Arrays6_DeleteKeysFromArray extends TestCase {
		
	int[] deleteKeys(int [] a, int key) {
		if (a == null || a.length == 0)
			return null;
		
		int nextIndex = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != key) {
				if (nextIndex != i){
					a[nextIndex] = a[i];
				}
				nextIndex++;
			}			
		}
		
		return a;
		
	}
	
	void print(int[] a) {
		
		if (a == null)
			return;
		
		for (int i : a){
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
	}
	

	public void testFun(){	
		print(deleteKeys(new int[] {3,3,1,0,3,2,0,1}, 3));		
	}		
}