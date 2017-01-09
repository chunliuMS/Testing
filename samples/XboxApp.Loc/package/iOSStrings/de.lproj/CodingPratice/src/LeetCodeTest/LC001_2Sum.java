package LeetCodeTest;
import java.util.HashMap;

import junit.framework.TestCase;

//Given a [sorted] array of integers, return indices of 2 numbers such that they add up to a specific target.
//You may assume that each input would have exactly one solution. 
public class LC001_2Sum extends TestCase {
	private class Indices {
		Indices(int first, int second) {
			this.first = first;
			this.second = second; 
		}
		int first;
		int second;
	}
	
	//sorted array.
	Indices getIndices(int[] a, int s){
		if (a == null || a.length < 2)
			return null;
		
		int i = 0;
		int j = a.length - 1;
		
		while (i < j){
			int t = a[i] + a[j];
			if (t < s)
				i++;
			else if (t == s){
				return new Indices(i, j);	
			}
			else
				j--;
		}
		
		return null;
	}
	
	//not sorted
	Indices getIndices2(int[] a, int s){
		if (a == null || a.length < 2) return null;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer> (a.length);
		for (int i = 0; i < a.length; i++) {
			map.put(a[i], i);
		}
		
		for (int i = 0; i < a.length; i++) {
	
			int v = s - a[i];
			if (map.containsKey(v)) {
				int j = map.get(v);
				if (i != j)
					return new Indices(i, map.get(v));
			}
		}
		
		return null;
	}
	
	public void testFun(){
		int[] a = { 3, 2, 4}; 
		Indices  indices = getIndices2(a, 6);
		
		if (indices == null) {
			System.out.println("No found!");
		} else {
			System.out.print("(" + String.valueOf(indices.first) + "," + String.valueOf(indices.second) + ")");
		}
	}	
}
