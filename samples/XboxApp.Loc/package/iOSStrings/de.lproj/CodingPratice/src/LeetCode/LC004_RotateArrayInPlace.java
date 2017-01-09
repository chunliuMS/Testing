package LeetCode;

import junit.framework.TestCase;

//Rotate an array kth times.
public class LC004_RotateArrayInPlace extends TestCase {
	
	void rotate(int[]a, int k){
		int n = a.length;
		if (k > n)
			k = k % n;
		
		reverse(a, 0, n-1);
		reverse(a, 0, k-1);
		reverse(a, k, n-1);
	}
	
	void reverse(int[] a, int i, int j){
		while(i < j){
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
			i++;
			j--;
		}			
	}
	
	void print(int[] a){
		StringBuffer sb = new StringBuffer();
		for (int i : a)
			sb.append(i).append(" ");
		System.out.println(sb.toString());
	}
	
	public void testFun(){
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		rotate(a, 3);
		print(a);
		rotate(a, 12);
		print(a);
	}	
}
