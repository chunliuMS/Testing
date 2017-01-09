
package CCI;

import junit.framework.TestCase;

//You're given 2 sorted arrays, A and B, where A has enough buffer at the end to hold B.
//Write a method to merger B into A in sorted order.
public class CCI_11_1_MergeSortedArrays extends TestCase {	
    void merge(int[] a, int size_a, int[] b, int size_b){
    	if (a.length < size_a + size_b)
    		return;
    	if (b.length < size_b)
    		return;
    	
    	int pa = size_a - 1;
    	int pb = size_b - 1;
    	
    	int size = size_a + size_b - 1;
    	
    	while(size >= 0)
    	{
    		if (pa >= 0 && pb >= 0)	{
    			if (a[pa] > b[pb]){
    				a[size--] = a[pa--];
    			}
    			else
    				a[size--] = b[pb--];
    		}
    		else if (pb >= 0)
    			a[size--] = b[pb--];
    		else
    			break;
    	}
    }    
        
    public void testFun(){
    
    	int a[]= {1, 3, 5, 7, 9, 11, 0, 0, 0, 0, 0, 0};
    	int b[] = {2, 4, 6, 8};
    	merge(a, 6, b, 4);
    	for (int  i = 0; i < 10; i++)
    		System.out.print(a[i]+ " ");
	}
}