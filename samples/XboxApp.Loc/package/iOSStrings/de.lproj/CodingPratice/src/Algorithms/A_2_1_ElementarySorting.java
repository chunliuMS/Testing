package Algorithms;

import junit.framework.TestCase;

public class A_2_1_ElementarySorting extends TestCase {
	
	void SelectionSort(int[] a){
		int n = a.length;
		for (int i = 0; i < n; i++){
			int mi = i;
			for (int j = i+ 1; j < n; j++){
				if (a[j] < a[mi])
					mi = j;
			}
			if (mi != i){
				int temp = a[i];
				a[i] = a[mi];
				a[mi] = temp;
			}
		}
	}
	
	void InsertionSort(int[] a){
		int n = a.length;
		for (int i = 1; i < n; i++){
			for (int j = i; j > 0; j--){
				if (a[j] < a[j - 1]){
					int tem = a[j];
					a[j] = a[j -1];
					a[j - 1] = tem;
				}
			}			
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
		SelectionSort(a);
		print(a);
		
		int[] a1 = {2, 4, 1, 8, 3, 9, 7, 5, 0, 6};
		InsertionSort(a1);
		print(a1);
	}
}
