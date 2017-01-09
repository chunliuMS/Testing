package LeetCode;

import junit.framework.TestCase;

//Find the k-th smallest element in the union of 2 sorted arrays, assume there is no duplicate elements in 2 arrays.
public class LC023_KthSmallestInSortedArrays extends TestCase {
	
	Integer find(int[] a, int[] b, int k) {
		if (k <= 0 || (a == null && b== null)) 
			return null;
		
		if (a == null || a.length == 0 || b == null || b.length == 0){
			int[] temp = (a == null || a.length == 0) ? b : a;
			if (temp.length < k)
				return null;
			else
				return temp[k -1];
		}
		
		if (a.length + b.length < k)
			return null;
		
		int left = (b.length < k) ? (k - b.length - 1) : 0; 
		int right = Math.min(a.length - 1, k - 1);
		while (left <= right){
			int i = (left + right) / 2;
			int j = k - i - 1;
			
			if (a[i] < b[j]){
				if (j == 0 || a[i] > b[j-1])
					return a[i];
				else 
					left = i + 1;
			}
			else {
				if(i == 0 || a[i - 1] < b[j])
					return b[j];
				else
				{
					right = i - 1;
				}
			}
		}
		
		return null;
 	}	
	
	public void testFun(){
		int a[] = {1, 3, 5, 7, 9, 11};
		int b[] = {0, 2, 4, 6, 8, 10, 12};
		
		assertTrue(find(a, b, 5) == 4);
		assertTrue(find(a, b, 9) == 8);
		assertTrue(find(a, b, 8) == 7);	
		
		int a1[] = null;
		assertTrue(find(a1, b, 5) == 8);
		assertTrue(find(a1, b, 1) == 0);
		assertTrue(find(a1, b, 7) == 12);
		assertTrue(find(a1, a,  7) == null);
	}	
}
