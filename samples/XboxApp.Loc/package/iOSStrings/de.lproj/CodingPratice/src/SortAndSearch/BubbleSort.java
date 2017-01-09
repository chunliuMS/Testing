package SortAndSearch;
import org.junit.Test;


public class BubbleSort {	
	
		
	public class Algorithm {
		
		public Algorithm(){			
		}
		
		public void sort(int[] a){
			int n = a.length;
			for (int i = 0; i < n - 1; i++){
				for (int j = 0; j < n - i - 1; j ++){
					if(a[j + 1] < a[j]){
						int temp = a[j];
						a[j] = a[j+1];
						a[j+1] = temp;
					}
				}
			}
		}			
	}	
		
	@Test
	public void test() {		
		int[] a = { 9, 1, 3, 5, 6, 4, 2, 0, 8,7};
		Algorithm algorithm = new Algorithm();
		algorithm.sort(a);
		
		StringBuffer sb = new StringBuffer("Array are: ");
		for(int i: a){
			sb.append(i).append("\t");
		}
		
		System.out.println(sb.toString());
	}
}
