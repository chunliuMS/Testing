package Algorithms;

import junit.framework.TestCase;

public class A_5_1_3WayStringQuickSort extends TestCase {
	public void sort(String[] a){
		sort(a, 0, a.length - 1, 0);
	}
	
	private int getChar(String s, int d){
		if (d < s.length())
			return s.charAt(d);
		else
			return -1;
	}
	
	private void exch(String[] a, int i, int j){
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private void sort(String[] a, int lo, int hi, int d){
		if (hi <= lo)
			return;
		
		int lt = lo; 
		int gt = hi;
		int i = lt + 1;
		int v = getChar(a[lo], d);
		while (i <= gt){
			int t = getChar(a[i], d);
			if (t < v){
				exch(a, i++, lt++);
			}
			else if (t > v)
				exch(a, i, gt--);
			else
				i++;
		}
		
		sort(a, lo, lt-1, d);
		sort(a, gt + 1, hi, d);
		
		if (v > 0)
			sort(a, lt, gt, d + 1);
	}
		
	public void testFun(){
		String[] a = {"abc", "a", "i", "edf", "acb", "it", "xy", "zxxxx", "itt"};
		sort(a);
		
		for(String s : a){
			System.out.println(s);
		}
	}
}
