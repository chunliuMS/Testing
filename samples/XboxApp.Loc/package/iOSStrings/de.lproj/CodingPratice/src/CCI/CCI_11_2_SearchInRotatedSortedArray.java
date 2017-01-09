
package CCI;

import junit.framework.TestCase;

//Given a sorted array of n integers that has been rotated an unknown number of times, 
//write code to find an element in the array. You may assume that the array was originally
//sorted in increasing order.
public class CCI_11_2_SearchInRotatedSortedArray extends TestCase {	
    int search(int[] a, int start, int end, int v) {
    	if (start > end)
    		return -1;    	
    	int mid = (start + end) / 2;
    	if (a[mid] == v)
    		return mid;
    	if (a[start] == v)
    		return start;
    	if (a[end] == v)
    		return end;
    	
    	if (a[start] < a[mid]){
    		if (v > a[start] && v < a[mid])
    			return search(a, start, mid - 1, v);
    		else
    			return search(a, mid + 1, end, v);
    	}
    	else {
    		if (v > a[mid] && v < a[end])    	
    			return search(a, mid + 1, end, v);
    		else
    			return search(a, start, mid - 1, v);
    	}    	
    }    
        
    public void testFun(){    
    	int a[]= {8, 9, 10, 1, 2, 3, 4, 5, 6, 7};
    	assertTrue(search(a, 0, 9, 1) == 3);
    	assertTrue(search(a, 0, 9, 9) == 1);
    	assertTrue(search(a, 0, 9, 8) == 0);
    	assertTrue(search(a, 0, 9, 7) == 9);
	}
}