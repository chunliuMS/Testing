package SortAndSearch;

import junit.framework.TestCase;

//Given 2 sorted array a & b, where a has enough space to hold all the value,
//write a method to merge b into a.
public class MergeSortedArray extends TestCase {
	private boolean merge(int[] a, int size_a, int[] b, int size_b){
		if (a.length < size_a + size_b)
			return false;
		int pos_a = size_a - 1;
		int pos_b = size_b - 1;
		
		int merge_index = size_a + size_b -1;
		while (pos_a >= 0 && pos_b >= 0){
			if (a[pos_a] >= b[pos_b]){
				a[merge_index] = a[pos_a];
				pos_a--;
			}
			else{
				a[merge_index] = b[pos_b];
				pos_b--;
			}
			
			merge_index--;
		}
		
		while(pos_b >= 0)
		{
			a[merge_index--] = b[pos_b--];
		}
		
		print(a, size_a + size_b);
		
		return true;
	}	
	
	void print(int[] a, int size){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++){
			sb.append(Integer.toString(a[i])).append(",");
		}
		System.out.println(sb.toString());
	}
		
	public void testFun(){	
		int[] a = {1, 3, 5, 7, 9, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] b= {2, 4, 6, 8, 10, 11, 12, 13};
		
		merge(a, 6, b, b.length);
	}
}
