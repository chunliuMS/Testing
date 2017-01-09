package Algorithms;

import junit.framework.TestCase;

public class A_2_3_QuickSort extends TestCase {
	
	void QuickSort(int[] a, int low, int hi){
		if (low >= hi)
			return;
		
		int i = low + 1;
		int lt = low;
		int gt = hi;
		
		int v = a[lt];
		
		while (i <= gt){
			if (a[i] < v)
				exch(a, i++, lt++);
			else if (a[i] > v)
				exch(a, i, gt--);
			else
				i++;
		}
		
		QuickSort(a, low, lt - 1);
		QuickSort(a, gt + 1, hi);
	}
	
	void exch(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
		QuickSort(a, 0, a.length - 1);
		print(a);
	}
}
