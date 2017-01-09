package LeetCode;

import java.util.Vector;

import junit.framework.TestCase;

//Find the intersection of two sorted array.
public class LC003_FindArrayIntersection extends TestCase {
	
	Vector<Integer> find(int[]a, int[] b){
		int m = a.length;
		int n = b.length;
		
		int i = 0; 
		int j = 0;
		
		Vector<Integer> v = new Vector<Integer> ();
		while (i < m && j < n){
			if (a[i] == b[j]){
				v.add(a[i]);
				i++;
				j++;
			}
			else if (a[i] < b[j])
				i++;
			else
				j++;				
		}
		return v;		
	}
	
	public void testFun(){
		int[] a = {1, 3, 4, 5, 7, 8, 11, 15};
		int[] b = {4, 5, 8, 11, 15, 20, 23};
		Vector<Integer> v = find(a, b);
		for(Integer i : v){
			System.out.print(i);
			System.out.print(" ");
		}
	}	
}
