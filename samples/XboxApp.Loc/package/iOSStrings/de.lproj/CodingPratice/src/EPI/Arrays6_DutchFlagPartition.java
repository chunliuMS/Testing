package EPI;
import junit.framework.TestCase;
/* 
 * Give and array and an index i, Write a function partition the array so that values small than A[i] to the left, equals are in the middle, and the larger are on the right.  
*/
				
public class Arrays6_DutchFlagPartition extends TestCase {
		
	void partition(int[] a, int pi) {
		if (a == null || a.length < 2 || pi >= a.length)
			return;
		
		int lt = 0; 
		int gt = a.length - 1;
		int v = a[pi];
		int i = 0;
		
		while (i <= gt) {
			if (a[i] < v) {
				if (i != lt)
					exch(a, i, lt);
				i++;
				lt++;
			}
			
			else if (a[i] == v)
				i++;
			else {
				exch(a, i, gt--);
			}
		}
		
	}
	
	void partition(int a[]) {
		if (a == null || a.length < 2)
			return;
		
		int lt = 0; 
		int gt = a.length - 1;
		
		while (lt < gt) {
			if (a[lt] > 0)
				exch(a, lt, gt--);
			else
				lt++;
		}
	}
	
	
	void exch(int[]a , int i, int j) {
		int v = a[i];
		a[i] = a[j];
		a[j] = v;
	}
	
	void print(int[] a) {
		for (int i : a){
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
	}
	
	
	public void testFun(){	
		int[] a = { 1, 3, 5, 4, 2, 7, 3, 4, 9};
		partition(a, 3);		
		print(a);
		
		
		int[] b = {0, 1, 1, 0, 1, 0, 0};
		partition(b);
		print(b);
	}
		
}