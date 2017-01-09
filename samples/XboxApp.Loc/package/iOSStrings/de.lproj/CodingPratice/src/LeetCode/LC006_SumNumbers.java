package LeetCode;

import java.util.Vector;

import junit.framework.TestCase;

//Print all combinations of a number as a sum of candidate numbers.
public class LC006_SumNumbers extends TestCase {
	
	void SumNumbers(int[]c, int index, int sum, Vector<Integer> l){	
		if (index > c.length - 1)
			return;
		int maxCount  = sum / c[index];
		if (sum % c[index] > 0)
			maxCount++;
		for (int i = 0; i <= maxCount; i++, l.add(c[index]), sum-= c[index]){
			if (sum == 0){
				print(l);	
				return;
			}
			else if (sum > 0){
				SumNumbers(c, index + 1, sum, new Vector<Integer>(l));
			}
			else
				return;				
		}
	}	
	
	void SumNumbers(int[]c, int sum){
		SumNumbers(c, 0, sum, new Vector<Integer>());
	}
	
	void print(Vector<Integer> l){
		StringBuilder sb = new StringBuilder(" ( ");
		for(int i: l){
			sb.append(i).append(" ");
		}
		sb.append(") ");
		
		System.out.println(sb.toString());
	}
	
	void solve(int target, int sum, int[] c, int[] index, int n){
		if (sum > target)
			return;
		if (sum == target)
			print(c, index, n);
		
		for (int i = index[n]; i < c.length; i++){
			index[n+1] = i;
			solve(target, sum + c[i], c, index, n+1);
		}
	}
	
	void solve(int target, int[]c){
		int[] index = new int[1000];
		solve(target, 0, c, index, 0);
	}
	
	void print(int[]c, int[] index, int n){
		StringBuilder sb = new StringBuilder("( ");
		for (int i = 1; i <= n; i++)
			sb.append(c[index[i]]).append(" ");
		
		sb.append( ")" );
		System.out.println(sb.toString());
	}
	
	void Sum(int[]a, int[]c, int index, int sum){
		if (index > a.length - 1)
			return;
		int count = sum / a[index];
		if (sum % a[index] != 0)
			count++;
		for (int i = 0; i <= count; i++){
			int s = sum - i * a[index];
			c[index] = i;
			if (s == 0)
				print(index, a, c);
			else if (s < 0)
				return;
			else
				Sum(a, c, index + 1, s);
		}		
	}
	
	void print(int index, int[] a, int[]c){
		StringBuilder sb = new StringBuilder(" ( ");
		for(int i = 0; i <= index; i++){
			for (int j = 0; j < c[i]; j++){
				sb.append(a[i]).append(',');
			}
		}
		sb.append(')');
		
		System.out.println(sb.toString());
	}
	
	public void testFun(){
		int[]c = {2, 3, 6, 7};
		SumNumbers(c, 17);
		solve(17, c);
		
		int[]c2 = {10, 1, 2, 7, 6, 5};
		
		System.out.println("The second array:");
		SumNumbers(c2, 8);
		
		System.out.println("The third array:");
		int[] a = {2, 3, 6, 7};
		int[] c3 = {0, 0, 0, 0};
		Sum(a, c3, 0, 17);
		
	}	
}
