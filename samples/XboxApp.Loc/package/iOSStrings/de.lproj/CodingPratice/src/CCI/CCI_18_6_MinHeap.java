
package CCI;

import junit.framework.TestCase;

//Describe an algorithm to find the smallest one million numbers in one billion numbers. 
//Assume that the computer memory can hold all one billion numbers.
public class CCI_18_6_MinHeap extends TestCase {
	
	class MaxHeap{
		public MaxHeap(int[] array){
			this.size = array.length;
			a = array;
			buildHeap();
		}
		
		private void buildHeap() {
			int lastParent = size / 2 - 1;
			for (int i = lastParent; i >= 0; i--)
				heapify(i);
		}
		
		private void heapify(int i){
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int largest = i;
			
			if (left < size && a[largest] < a[left] )
				largest = left;
			
			if (right < size && a[largest] < a[right] )
				largest = right;
			
			if (i != largest){
				int temp = a[i];
				a[i] = a[largest];
				a[largest] = temp;
				heapify(largest);
			}
		}
		
		void append(int v){
			if (a[0] > v){
				a[0] = v;
				heapify(0);
			}
		}
		
		private int size;
		private int[] a;
	}
	class SelectionRank{
		SelectionRank(int[] a){
			this.a = a;			
		}
		
		private int partition(int left, int right, int pivotValue){
			while(true){
				while (left <= right && a[left] <= pivotValue)
					left++;
				while(left <= right && a[right] > pivotValue)
					right--;
				if (left > right)
					return left - 1;
				int temp = a[left];
				a[left] = a[right];
				a[right] = temp;
			}
		}		
		
		public int rank(int left, int right, int k){
			int rand = left + (int)Math.round((right - left) * Math.random());
			int pivotValue = a[rand];
			int leftend = partition(left, right, pivotValue);
			int leftsize = leftend - left + 1;
			if (leftsize == k)
				return leftend;
			else if (leftsize < k)
				return rank(leftend + 1, right, k - leftsize);
			else
				return rank(left, leftend, k);
		}		
		
		private int[] a;
	}
	void print(int[] a){
		StringBuffer sb = new StringBuffer("The values in the array: ");
		for(int i : a){
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
		
		int v = Math.max(12, 12);
	}	
	
	void shuffle(int[] a){
		for (int i = 0; i < a.length; i++)
		{
			int index = (int) (a.length * Math.random());
			int temp = a[index];
			a[index] = a[i];
			a[i] = temp;
		}
	}
		
    public void testFun(){       	
    	int a[] = {1, 3, 2, 9, 8, 5, 4, 7, 6, 0};
    	print(a);
    	MaxHeap maxHeap = new MaxHeap(a);
    	maxHeap.append(4);
    	maxHeap.append(14);
    	maxHeap.append(0);
    	maxHeap.append(1234);
    	maxHeap.append(7);
    	maxHeap.append(2);
    	print(a);
    	
    	a = new int[100];
    	for (int i = 0; i < 100; i++)
    		a[i] = i;
    	print(a);
    	shuffle(a);
    	print(a);
    	SelectionRank rank = new SelectionRank(a);
    	int k = rank.rank(0, a.length - 1, 30);
    	assertTrue(k == 29);
    	print(a);   
   
	}
}