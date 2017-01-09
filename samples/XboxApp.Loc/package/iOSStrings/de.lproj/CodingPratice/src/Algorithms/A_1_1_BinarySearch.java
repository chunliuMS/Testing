package Algorithms;

import junit.framework.TestCase;

public class A_1_1_BinarySearch extends TestCase {
	
	int search(int v, int[] a){
		int low = 0;
		int high = a.length - 1;
		
		while( low <= high){
			int mid = low + (high - low) / 2;
			if(v == a[mid])
				return mid;
			else if ( v < a[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
	
		return -1;
	}
	
	public void testFun(){
		int a[] = {1, 3, 6, 8, 11, 23, 56, 122, 167, 245};
		assertTrue(search(23, a) == 5);
		assertTrue(search(3, a) == 1);
		assertTrue(search(233, a) == -1);
		assertTrue(search(167, a) == 8);
	}
}
