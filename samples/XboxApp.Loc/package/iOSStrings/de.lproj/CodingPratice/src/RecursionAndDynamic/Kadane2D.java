package RecursionAndDynamic;
import org.junit.Test;


public class Kadane2D {
	
	public void getMaxSub(int[][] a)
	{
		int S = Integer.MIN_VALUE;
		
		int x0 = 0;
		int x1 = 0;
		int y0 = 0;
		int y1 = 0;
		
		int n = a.length;
		for (int z = 0; z < n; z++){
			int[] pr = new int[n];
			for (int i = 0; i < n; i++)
				pr[i] = 0;
						
			for (int x = z; x < n; x++){
				int t = 0;
				int y_end = 0;
				int y_start = 0;
				int j = 0;				
				int s = Integer.MIN_VALUE;
				
				for (int i = 0; i < n; i++){
					pr[i] += a[x][i];
					t += pr[i];
					
					if (t > s){
						s = t;
						y_end = i;
						y_start = j;
					}
					
					if (t < 0){
						t = 0;
						j = i + 1;
					}
				}
				
				if(s > S){
					S = s;
					
					x0 = z;
					x1 = x;
					
					y0 = y_start;
					y1 = y_end;					
				}
			}
		}
		
		StringBuilder sb = new StringBuilder("Total value = ");
		sb.append(Integer.toString(S)).append("\n(").append(Integer.toString(x0)).append("-").append(Integer.toString(x1)).append(";").
													 append(Integer.toString(y0)).append("-").append(Integer.toString(y1)).append(")");
		
		System.out.println(sb.toString());
				
	}
	
	public void getMax(int[][] a, int M, int N){
		if (a == null || M < 1 || N < 1 )
			return;
		
		int max = Integer.MIN_VALUE;
		int r0 = 0, c0 = 0, r1= 0, c1= 0;
		
		for(int r = 0; r < M; r++){
			int[] ta = new int[N];		
			
			for (int rStart = r; rStart < M; rStart++){
				int xs = 0;
				int total = 0;
				for (int i = 0; i< N; i++){
					ta[i] += a[rStart][i];
					total += ta[i];
					if (total > max){
						max = total;
						r0 = r;
						r1 = rStart;
						c0 = xs;
						c1 = i;
					}
					if (total <= 0){
						xs = i + 1;
						total = 0;
					}
				}
			}			
		}
		
		System.out.println("Total = " + max);
		System.out.println(r0 + "," + c0 + " - " + r1 + "," + c1);
	}
	
		
	@Test
	public void test() {
		int[][] array = {	{0,		1,		-1,		-1,		1},
							{-1,	-1,		-1,		0,		1}, 
							{1, 	1,		1,		1,		0}, 
							{1,		1,		1,		0, 		1}, 
							{-1,	1,		-3,		1,		-1}};
		
		
		getMaxSub(array);
		getMax(array, 5, 5);
			
	}
}
