package Test;

import junit.framework.TestCase;

public class Sortings extends TestCase {

	void exch(int[]a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	void print(int[] a) {
		StringBuffer sb = new StringBuffer();
		for(int v : a)
			sb.append(v).append(" ");
		System.out.println(sb.toString());
	}
	
	int[] bubbleSort(int[] a){		
		int n = a.length;
		for (int i = 0; i < n - 1; i++){
			for (int j = 0; j < n - i - 1; j++){
				if (a[j] > a[j + 1])
					exch(a, j, j + 1);
			}
		}
		return a;
	}
	
	int[] selectionSort(int[] a){
		for (int i = 0; i < a.length; i++){
			int mi = i;
			for (int j = i + 1; j < a.length; j++){
				if (a[j] < a[mi])
					mi = j;
			}
			if (mi != i)
				exch(a, mi, i);
		}
		return a;
	}
	
	int[] insertionSort(int[] a){
		for (int i = 1; i < a.length; i++){
			for (int j = i; j > 0; j--){
				if (a[j] < a[j - 1])
					exch(a, j , j - 1);
			}				
		}
		
		return a;
	}
	
	void mergeSort(int[]a, int[] b, int start, int end){
		if (start >= end)
			return;
		int mid = (start + end) / 2;
		mergeSort(a, b, start, mid);
		mergeSort(a, b, mid + 1, end);
		merge(a, b, start, mid, end);
	}
	
	void merge(int[] a, int[]b, int start, int mid, int end){
		for(int i = start; i <= end; i++)
			b[i] = a[i];
		
		int firstIndex = start;
		int secondIndex = mid + 1;
		for (int k = start; k <= end; k++){
			if (firstIndex > mid)
				a[k] = b[secondIndex++];
			else if (secondIndex > end)
				a[k] = b[firstIndex++];
			else if (b[firstIndex] < b[secondIndex])
				a[k] = b[firstIndex++];
			else
				a[k] = b[secondIndex++];
		}
	}
	
	int[] mergeSort(int[] a){
		int[] b = new int[a.length];
		mergeSort(a, b, 0, a.length - 1);
		
		return a;
	}
	
	void sink(int[] a, int i, int end){
		int lc = 2 * i + 1;
		int rc = 2 * i + 2;
		
		int largest = i;
		
		if (lc <= end && a[lc] > a[i])
			largest = lc;
		if (rc <= end && a[rc] > a[largest])
			largest = rc;
		
		if (largest != i){
			exch(a, i, largest);
			sink(a, largest, end);
		}		
	}
	
	void heapfy(int[] a){
		int lp = (a.length - 1 - 1) / 2;
		for (int i = lp; i >= 0; i--)
			sink(a, i, a.length - 1);
		
	}
	
	int[] heapSort(int[] a){
		heapfy(a);
		
		for (int i = a.length - 1; i > 0; i--){
			exch(a, i, 0);
			sink(a, 0, i - 1);
		}
		
		return a;
	}
	
	void quickSort(int[] a, int start, int end){
		if (start >= end)
			return;
		
		int pv = a[start];
		int lt = start;
		int gt = end;
		
		int i = lt + 1;
		
		while (i <= gt){
			if (a[i] > pv)
				exch(a, i, gt--);
			else if (a[i] == pv)
				i++;
			else
				exch(a, i++, lt++);
		}
		
		quickSort(a, start, lt - 1);
		quickSort(a, gt + 1, end);		
	}
	
	int[] quickSort(int[] a){
		quickSort(a, 0, a.length - 1);		
		return a;		
	}
	
	int getChar(String s, int p){
		if (p < s.length())
			return s.charAt(p);
		else 
			return -1;
	}
	
	void exch(String[] a, int i, int j){
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	void quickSort(String[] a, int low, int hi, int p){
		if (low >= hi)
			return;
		
		int pv = getChar(a[low], p);
		int lt = low;
		int gt = hi;
		int i = low + 1;
		
		while (i <= gt){
			int v = getChar(a[i], p);
			if (v < pv)
				exch(a, i++, lt++);
			else if (v == pv)
				i++;
			else
				exch(a, i, gt--);				
		}
		
		quickSort(a, low, lt - 1, p);
		quickSort(a, gt+ 1, hi, p);
		
		if (pv > 0)
			quickSort(a, lt, gt, ++p);		
	}
	
	void quickSort(String[] a){
		quickSort(a, 0, a.length - 1, 0);
		
		for (String s: a){
			System.out.println(s);
		}
	}
	
	public void testFun(){
		int[] test1 = {2, 4, 7, 9, 3, 5, 8, 1, 6};
		print(test1);
		print(bubbleSort(test1.clone()));
		print(selectionSort(test1.clone()));	
		print(insertionSort(test1.clone()));
		print(mergeSort(test1.clone()));
		print(heapSort(test1.clone()));
		print(quickSort(test1.clone()));
		
		String[] a = {"abc", "a", "i", "edf", "acb", "it", "xy", "zxxxx", "itt"};
		quickSort(a);
	}
}
