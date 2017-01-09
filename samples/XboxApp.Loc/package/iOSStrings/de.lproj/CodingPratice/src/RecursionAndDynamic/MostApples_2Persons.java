package RecursionAndDynamic;
import org.junit.Test;

//A table composed of N x M cells, each having a certain quantity of apples. Starting from the upper-left and at each step can go either down or right,
//Fin the the maximum number of apples you can collect.
public class MostApples_2Persons {				
		
	public int maxApples(int[][] t){					
		int y = t.length;
		int x = t[0].length;
		
		int[][] best = new int[x][x];		
		
		for (int s = 0; s <= x + y - 2; s++){
			int[][] temp = new int[x][x];
			
			int max_x = Math.min(s, x - 1);
			int min_x = Math.max(0, s - (y - 1));
			for(int a = min_x; a <= max_x; a++){
				for (int b = a; b <= max_x; b++){
					int aa = s - a;
					int bb = s - b;
					if (aa < 0 || bb < 0 || aa >= y || bb >= y)
						continue;
					
					int bestHere = 0;
					int delta = t[aa][a];
					if (a != b)
						delta += t[bb][b];
					
					if (a > 0 && b > 0)   bestHere = Math.max(bestHere, best[a-1][b-1]);
					if (a > 0 && bb > 0)  bestHere = Math.max(bestHere, best[a-1][b]);
					if (b > 0 && aa > 0)  bestHere = Math.max(bestHere, best[a][b-1]);
					if (aa > 0 && bb > 0) bestHere = Math.max(bestHere, best[a][b]);
					
					temp[a][b] = bestHere + delta;
				}
			}
			
			best = temp;			
		}
		
		return best[x-1][x-1];
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
		
		int[][] array1 = {	
				{3, 2, 1},
				{2, 1, 2},
				{2, 2, 1}
				};
		System.out.println("Max apples are: " + maxApples(array1));
		int[][] array2 = {	
				{1, 2, 1},
				{2, 4, 2},
				{2, 2, 1}
				};
		System.out.println("Max apples are: " + maxApples(array2));
	}
}
