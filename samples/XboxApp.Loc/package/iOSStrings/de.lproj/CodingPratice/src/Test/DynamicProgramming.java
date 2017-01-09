package Test;

import junit.framework.TestCase;

public class DynamicProgramming extends TestCase {
	int fib(int n){
		if (n == 0)
			return 0;
				
		int n1 = 0;
		int n2 = 1;
		
		for (int i = 2; i < n; i++){
			n2 = n1 + n2;
			n1 = n2 - n1;
		}
		
		return n1 + n2;
	}
	
	int count = 0;
	
	int knapsack(int[] w, int[] v, int c, int i){
		if (i >= w.length || c == 0)
			return 0;		
		
		count++;
		
		if(w[i] > c)
			return knapsack(w, v, c, i + 1);
		else
			return Math.max(knapsack(w, v, c, i + 1), v[i] + knapsack(w, v, c - w[i], i + 1));
		
	}
	
	int knapsack(int[] w, int[] v, int c, int i, int[][] t){
		if (i >= w.length || c == 0)
			return 0;		
		if (t[i][c] >= 0)
			return t[i][c];
		
		count++;
		
		if(w[i] > c)
			t[i][c] = knapsack(w, v, c, i + 1, t);
		else
			t[i][c] = Math.max(knapsack(w, v, c, i + 1, t), v[i] + knapsack(w, v, c - w[i], i + 1, t));
		
		return t[i][c];
		
	}
	
	void knapsack(int[]w, int[]v, int c){
		boolean[][] sol = new boolean[w.length][c + 1];
		int[][] opt = new int[w.length + 1][c + 1];
		for(int i = 0; i < w.length; i++){
			for(int j = 1; j <= c; j++){
				int op1 = i > 0 ? opt[i-1][j] : 0;
				int op2 = 0;
				if (w[i] <= j)
					op2 = v[i] + (i > 0  ? opt[i - 1][j- w[i]] : 0);
				
				opt[i][j] = Math.max(op1, op2);
				sol[i][j] = (op1 < op2);
			}
		}
		
		boolean[] taken = new boolean[w.length];
		for(int i  = w.length -1, j = c; i >= 0; i--){
			if(sol[i][j]){
				taken[i] = true; 
				j = j - w[i];
			}
			else
				taken[i] = false;
		}
		
		for(int i = 0; i < w.length; i++)
			System.out.println( i + "\t" + v[i] + "\t" + taken[i]);
	}
	
	int maxMoney(int v[]){
		int[][] p = new int[v.length][v.length];
		int a, b, c;
		for(int i = 0; i < v.length; i++){
			for(int m = 0, n = i; n < v.length; m++, n++){
				a = (m + 2 < v.length) ? p[m + 2][n] : 0;
				b = (m + 1 < v.length && n - 1 >= 0) ? p[m + 1][n - 1] : 0;
				c = (n - 2 >= 0) ? p[m][n -2] : 0;
				
				p[m][n] = Math.max(v[m] + Math.min(a,b), v[n] + Math.min(b, c)); 
			}
		}
		return p[0][v.length - 1];		
	}
	
	int maxMoney2(int v[]){
		int[][] p = new int[v.length][v.length];
		for (int i = 0; i < v.length; i++)
			p[i][i] = v[i];
		for (int i  = 0; i < v.length - 1; i++)
			p[i][i + 1] = Math.max(v[i], v[i + 1]);
		
		for(int s = 2; s < v.length; s++){
			for(int i = 0; i < v.length - s; i++){
				int j = i + s;						
				p[i][j] = Math.max(v[i] + Math.min(p[i + 2][j], p[i + 1][j - 1]), v[j] + Math.min( p[i][j -2], p[i + 1][j -1])); 
			}
		}
		return p[0][v.length - 1];		
	}
	
	int maxMoney(int v[], int m, int n){
		if(m > n)
			return 0;
		
		int op1 = v[m] + Math.min(maxMoney(v, m + 2, n), maxMoney(v, m + 1, n - 1));
		int op2 = v[n] + Math.min(maxMoney(v, m + 1, n - 1), maxMoney(v, m, n - 2));
		
		return Math.max(op1, op2);		
	}
	
	public void testFun(){
		for (int i = 0; i < 40; i++){
			System.out.println(Integer.toString(i) + "\t -- " + fib(i));
		}
		
		int[] w = {2,4,6,3,7,2,8};
		int[] v = {3,6,2,4,9,3,4};
				
		int sum = knapsack(w, v, 11, 0);
		System.out.println("Maximum value = " + Integer.toString(sum));
		System.out.println("Count = "  + Integer.toString(count));
		
		count = 0;
		int c = 11;
		int[][] t = new int[w.length][];
		for(int j = 0; j < t.length; j++){
			t[j] = new int[c + 1];
			for (int k = 0; k < c + 1; k++)
				t[j][k] = -1;
		}			
		
		sum = knapsack(w, v, c, 0, t);
		System.out.println("Maximum value = " + Integer.toString(sum));
		System.out.println("Count = "  + Integer.toString(count));
		knapsack(w, v, c);
		
		int m[] = {3, 2, 2, 3, 1, 2};
		System.out.println("Maximum money to choose: " + maxMoney(m) );
		System.out.println("Maximum money to choose 2: " + maxMoney2(m) );
		
		System.out.println("Maximum money is: " + maxMoney(m, 0, m.length - 1));
	}
}
