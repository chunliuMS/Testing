package SortAndSearch;
import org.junit.Test;

//Given an array of integer which has both negative and positive values, sort it to have all the positive values are sitting after negative values while keeping
//the sequence of both positive and negative values the same as before.
public class IntegerSort {
	void printArray(int[]array){
		int m = array.length;
				
		for (int i = 0; i < m; i++){
			System.out.print(Integer.toString(array[i]) + "\t");			
		}
		
		System.out.print("\r\n");
	}
	
	void sort(int[] a){
		int pp = -1;
		int np = -1;
		
		for(int i = 0; i < a.length; i++){
			if (a[i] >= 0){
				if (pp < 0)					
					pp = i;
			}
			else{
				np = i;
			}			
			
			if (pp >= 0 && np >= 0 && np > pp){
				int t = a[np];
				for( int j = np; j > pp; j--){
					a[j] = a[j - 1];
				}
				a[pp] = t;
					
				pp += 1;
				np = -1;
			}
		}
	}
	
		
	@Test
	public void test() {
		int[] a = { 1, -2, -3, 4, 5, -6, -7, -8, 9, -10};
		printArray(a);
		sort(a);
		printArray(a);
		
		int[] a2 = {10, 9, 7, -1, -2, -3, 4, 5, 6, 8, -11};
		printArray(a2);
		sort(a2);
		printArray(a2);
		
		int[] a3 = {-10, -9, 7, -1, 2, -3, 4, 5, 6, 8, -11};
		printArray(a3);
		sort(a3);
		printArray(a3);
	}
}
