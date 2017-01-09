package RecursionAndDynamic;
import org.junit.Test;

//A table composed of N x M cells, each having a certain quantity of apples. Starting from the upper-left and at each step can go either down or right,
//Fin the the maximum number of apples you can collect.
public class MostApples {	
	
		
	public class Algorithm {
		
		public Algorithm(){			
		}
		
		public int getSequence(int[][] a){					
			int m = a.length;
			int n = a[0].length;
			
			int[][] sum = new int[m][n];
						
			for(int i = 0; i < m; i++ ){
				for (int j= 0; j < n; j++){
					int prev = 0;
					if (i > 0 && j > 0){
						prev = (sum[i-1][j] > sum[i][j-1]) ? sum[i-1][j] : sum[i][j-1];
					}
					else if (i > 0)
						prev = sum[i-1][j];
					else if (j > 0)
						prev = sum[i][j-1];
						
					sum[i][j] = prev + a[i][j];					
				}
			}
			print(a);
			
			System.out.println();
			print(sum);
			
			
			return sum[m-1][n-1];
		}				
	}	
	
	void print(int[][]array){
		int size = array.length;
		for (int i = 0; i < size; i++){
			System.out.print("\r\n row " + Integer.toString(i) + "\t\t");
			for (int j = 0; j < size; j++)	{
				System.out.print("\t" + Integer.toString(array[i][j]));
			}
		}			
	}
		
	@Test
	public void test() {	
		
		int[][] array = {	
				{0, 1, 2, 3, 4},
				{5, 6, 7, 8, 9}, 
				{10,11,12,13,14}, 
				{15,16,17,18,19}, 
				{20,21,22,23,24}};
		Algorithm al = new Algorithm();
		al.getSequence(array);		
	}
}
