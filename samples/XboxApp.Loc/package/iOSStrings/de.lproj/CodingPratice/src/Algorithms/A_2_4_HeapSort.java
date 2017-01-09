package Algorithms;

import junit.framework.TestCase;

public class A_2_4_HeapSort extends TestCase {
	
	void HeapSort(int[] a){
		heapify(a);
		int hi = a.length - 1;
		
		while (hi > 0){
			exch(a, 0, hi);			
			sink(a, 0, hi--);
		}		
	}
	
	void sink(int[] a, int i, int size){
		int lc = 2 * i + 1;
		int rc = 2 * i + 2;
		
		int biggest  = i;
		if (lc < size && a[lc] > a[i])
			biggest  = lc;
		if (rc < size && a[rc] > a[biggest])
			biggest = rc;
		
		if (biggest != i){
			exch(a, i, biggest);
			sink(a, biggest, size);
		}
	}
	
	void swim(int[] a, int i){
		while (i > 0){
			int pi = (i - 1) / 2;
			if (a[pi] > a[i]){
				exch(a, pi, i);
			}			
			i = pi;
		}
	}

	void heapify(int[] a){
		int lp = (a.length - 2) / 2;
		for (int i = lp; i >= 0; i--)
			sink(a, i, a.length);
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
		HeapSort(a);
		print(a);
	}
}
