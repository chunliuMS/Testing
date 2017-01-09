
package CCI;

import junit.framework.TestCase;

//Numbers are randomly generated and passed to a method. 
//Write a program to find and maintain the median value as the new values are generated
public class CCI_18_9_MedianValue extends TestCase {
	enum HeapType
	{
		Min,
		Max
	}
	
	class Heap{		
		Heap(int maxSize, HeapType type){
			this.maxSize = maxSize;
			this.heapType = type;
			a = new int[maxSize];
			this.size = 0;
		}
		
		private void heapify(int i){
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int p = i;
			
			if (left < size  ){
				if ((heapType == HeapType.Max && a[p] < a[left]) ||
					(heapType == HeapType.Min && a[p] > a[left]))
				p = left;
			}
			
			if (right < size  ){
				if ((heapType == HeapType.Max && a[p] < a[right]) ||
					(heapType == HeapType.Min && a[p] > a[right]))
				p = right;
			}
			
			if (i != p){
				int temp = a[i];
				a[i] = a[p];
				a[p] = temp;
				heapify(p);
			}
		}
		
		void shiftUp(int i){
			int p = (i - 1) / 2;
			if (heapType == HeapType.Max && a[p] < a[i] ||
				heapType == HeapType.Min && a[p] > a[i]){
				int t = a[p];
				a[p] = a[i];
				a[i] = t;
				
				if (p > 0)
					shiftUp(p);
			}
				
		}
		
		void add(int v){	
			if (size < maxSize){
				a[size++] = v;
				if (size > 1)
					shiftUp(size - 1);
			}
		}
		
		int getRoot(){
			return a[0];
		}
		
		void deleteRoot(){
			a[0] = a[size - 1];
			size--;
			heapify(0);
		}
		
		int getSize(){
			return size;
		}
		
		void print(){
			StringBuffer sb = new StringBuffer("The values in the array: ");
			for(int i = 0; i < size; i++){
				sb.append(a[i]).append(" ");
			}
			System.out.println(sb.toString());
		}	
		
		private int maxSize;
		private HeapType heapType;
		private int size;
		private int[] a;
	}
	
	class MedianFinder{
		
		void add(int v){
			if (min.getSize() > 0){
				if(v > min.getRoot()){
					if (min.getSize() < max.getSize()){
						min.add(v);
					}
					else {
						max.add(min.getRoot());
						min.deleteRoot();
						min.add(v);
					}
				}
				else{
					if(max.getSize() == min.getSize())
						max.add(v);
					else
					{
						if (v > max.getRoot())
							max.add(v);
						else {
							min.add(max.getRoot());
							max.deleteRoot();
							max.add(v);							
						}
					}
				}
			}
			else{
				if (max.getSize() == 0)
					max.add(v);
				else {
					if (v <  max.getRoot() ){
						min.add(max.getRoot());
						max.deleteRoot();
						max.add(v);
					}
					else
						min.add(v);
				}
			}
		}
		
		int getMedian(){
			if (min.getSize() > 0){
				if (max.getSize() > min.getSize())
					return max.getRoot();
				else
					return (min.getRoot() + max.getRoot()) / 2;
			}
			else
				return max.getRoot();
		}
		
		
		private Heap max = new Heap(100, HeapType.Max);
    	private Heap min = new Heap(100, HeapType.Min);
	}
	
    public void testFun(){       	
    	/*Heap max = new Heap(100, HeapType.Max);
    	Heap min = new Heap(100, HeapType.Min);
    	
    	for (int i = 0; i < 20; i++){
    		max.add(i);
    		min.add(i);
    	}
    	
    	max.print();
    	min.print();
    	
    	max.deleteRoot();
    	max.print();
    	
    	min.deleteRoot();
    	min.print();*/
    	
    	MedianFinder finder = new MedianFinder();
    	finder.add(1);
    	finder.add(2);
    	finder.add(8);
    	finder.add(6);
    	finder.add(5);
    	assertTrue(finder.getMedian() == 5);
    	
    	finder.add(10);
    	finder.add(11);
    	assertTrue(finder.getMedian() == 6);
	}
}