package Algorithms;

import junit.framework.TestCase;

public class A_2_2_MergeSort extends TestCase {
	
	void MergeSort(int[] a, int[] b, int low, int hi){
		if (low >= hi)
			return;
		
		int mid = low + (hi - low) / 2;
		MergeSort(a, b,  low, mid);
		MergeSort(a, b, mid + 1, hi);
		Merge(a, b, low, mid, hi);		
	}
	
	void Merge(int[] a, int[] b, int low, int mid, int hi){
		for (int i = low; i <= hi; i++){
			b[i] = a[i];
		}	
		
		int i = low;
		int j = mid + 1;
		for (int k = low; k <= hi; k++){
			if (i > mid)
				a[k] = b[j++];
			else if (j > hi)
				a[k] = b[i++];
			else if (b[i] > b[j])
				a[k] = b[j++];
			else
				a[k] = b[i++];			
		}
	}
	
	void print(int[] a) {
		StringBuilder sb = new StringBuilder("Array: ");
		for(int v : a){
			sb.append(v).append(" ");
		}
		System.out.println(sb.toString());
	}	
		
	public void testFun(){
		int[] a = {2, 4, 1, 8, 3, 9, 7, 5, 0, 6};
		int[] b = new int[a.length];
		MergeSort(a, b, 0, a.length - 1);
		print(a);
	}
}
