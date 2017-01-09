package RecursionAndDynamic;

import junit.framework.TestCase;

//A person can go left and right for each step. Calculate the chance to go back to the initial place after n steps. 
public class ChancesToStay extends TestCase {
	int N = 0;
	int move(int dist, int n, int[][]t){
		if (n == 0)
			return dist == 0 ? 1 : 0;
		if ((dist % 2 == 0 && n % 2 != 0) || (dist % 2 != 0 && n % 2 == 0 ))
			return 0;		
		if (t[N + dist][n] >= 0)
			return t[N + dist][n];
		int count = move(dist + 1, n - 1, t) + move(dist - 1, n - 1, t);
		t[N + dist][n] = count;
		return count;
	}
		
	public void testFun(){
	
		for (int i = 1; i <= 30; i++ ){
			int[][] t = new int[i * 2 + 1][i + 1];
			for (int m = 0; m < t.length; m++)
				for (int n = 0; n < t[0].length; n++)
					t[m][n] = - 1;
			
			N = i;
			System.out.println("n = " + Integer.toString(i) + " total chances = " + move(0, i, t));
		}
	}
}
