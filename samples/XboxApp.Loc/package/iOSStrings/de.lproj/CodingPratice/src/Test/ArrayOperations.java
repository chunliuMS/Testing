package Test;

import junit.framework.TestCase;

public class ArrayOperations extends TestCase {
	public int[][] rotate(int[][] a){
		int size = a.length;
		for (int layer = 0; layer < size / 2; layer++){
			int start = layer;
			int end = size - start - 1;
			
			for (int i = start; i < end; i++){
				int offset = i - start;
				
				int top = a[start][i];
				a[start][i] = a[end - offset][start];
				a[end - offset][start] = a[end][end -offset];
				a[end][end -offset] = a[i][end];
				a[i][end] = top;
			}			
		}	
		return a;
	}
	
	int[][] clone(int[][] a){
		int[][] aNew = new int[a.length][];
		for(int i = 0; i < a.length; i++)
			aNew[i] = new int[a[0].length];
		
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				aNew[i][j] = a[i][j];
		
		return aNew;
	}
	
	void sprialPrint(int[][] a){
		if (a == null || a.length == 0 || a[0].length == 0)
			return;
		
		int rows = a.length;
		int cols = a[0].length;
		
		int rs = 0;
		int re = rows - 1;
		int cs = 0;
		int ce = cols - 1;
		StringBuffer sb = new StringBuffer();
		while (rs <= re && cs <= ce){
			for (int c = cs; c <= ce; c++)
				sb.append(a[rs][c]).append(" ");
			for (int r = rs + 1; r <= re; r++)
				sb.append(a[r][ce]).append(" ");
			for (int c = ce - 1 ; c >= cs; c--)
				sb.append(a[re][c]).append(" ");
			for (int r = re - 1 ; r > rs; r--)
				sb.append(a[r][cs]).append(" ");
			
			rs++;
			cs++;
			re--;
			ce--;
		}	
		
		System.out.println(sb.toString());
	}
	
	int[][] assignValues(int size){
		if (size <= 0)
			return null;
		int[][] a = new int[size][];
		for (int i = 0; i < size; i++)
			a[i] = new int[size];
		
		int v = 0;
		for (int layer = 0; layer < size / 2 + size % 2; layer++){
			int start = layer;
			int end = size - layer - 1;
			if (start == end) //last one.
				a[start][end] = v++;
			else{
				for (int i = start; i < end; i++){
					int offset = i - start;
					a[start][i] = v + offset;
					a[i][end] = v + offset + end - start;
					a[end][end - offset] = v + offset + 2 * (end - start);
					a[end - offset][start] = v + offset + 3 *(end - start);					
				}
			}
			
			v += 4 * (end - start);
		}
		
		return a;
	}
	
	private void print(int[][] a){
		int m = a.length;
		System.out.print("\nArray : \n");
		for (int i = 0; i < m; i++){
			for (int j = 0; j < m; j++){
				System.out.print("\t" + Integer.toString(a[i][j]));
			}
			System.out.print("\n");	
		}
	}
	
	int[] mergeSortedArray(int[] a, int[] b, int sizeA){
		if (a == null || b == null || sizeA + b.length > a.length)
			return null;
		
		int pos = sizeA + b.length - 1;
		int pa = sizeA - 1;
		int pb = b.length - 1;
		while (pos >= 0){
			if (pb < 0)
				break;
			else if (pa < 0)
				a[pos] = b[pb--];
			else if (a[pa] > b[pb])
				a[pos] = a[pa--];
			else
				a[pos] = b[pb--];
			
			pos--;
		}
		
		return a;		
	}
	
	void print(int[] a){
		StringBuffer sb = new StringBuffer();
		for(int v : a){
			sb.append(v).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	void exch(int[]a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	void reverse(int[] a, int start, int end){
		while (start < end)
			exch(a, start++, end--);
	}
	
	int[] rotate(int[] a, int n){
		n %= a.length;
		
		reverse(a, 0, a.length - 1);
		reverse(a, 0, n - 1);
		reverse(a, n, a.length - 1);
		
		return a;
	}
	
	int searchInRotatedSortedArray(int[] a, int v){
		int low = 0; 
		int hi = a.length - 1;
		
		while (low <= hi){
			int mid = (low + hi) / 2;
			if (a[mid] == v)
				return mid;
			
			if (a[low] < a[mid]){
				if (a[low] <= v && a[mid] > v)
					hi = mid - 1;
				else
					low = mid + 1;
			}
			else {
				if ( a[mid] < v && v <= a[hi])
					low = mid + 1;
				else
					hi = mid - 1;
			}
		}
		
		return - 1;
	}
	
	class Result{
		public Result(int sum, int start, int end){
			this.sum = sum;
			this.start = start;
			this.end = end;
		}
		int sum;
		int start;
		int end;
	}
	
	Result findLargestSum(int[] a){
		if (a == null || a.length == 0)
			return null;
		
		Result res = new Result(Integer.MIN_VALUE, 0, 0);
		int start = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++){
			sum += a[i];
			if (sum > res.sum){
				res.sum = sum;
				res.start = start;
				res.end = i;
			}
			
			if (sum < 0){
				start = i + 1;
				sum = 0;
			}
		}
		return res;
	}
	
	public void testFun(){
		int[][] a = {  	{ 0,  1,  2,  3,  4},
						{ 5,  6,  7,  8,  9},
						{ 10, 11, 12, 13, 14},
						{ 15, 16, 17, 18, 19},
						{ 20, 21, 22, 23, 24}						
		};
		
		print(a);		
		print(rotate(clone(a)));
		sprialPrint(a);
		
		print(assignValues(4));
		print(assignValues(5));
		print(assignValues(6));
		
		int[] a1 = {1, 2, 4, 6, 8, 0, 0, 0, 0, 0, 0, 0};
		int[] b1 = {3, 4, 5,7, 9};
		print(mergeSortedArray(a1, b1, 5));
		
		int[] a2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		for (int i = 1; i < 10; i++)
			assertTrue(searchInRotatedSortedArray(rotate(a2, i + 5), i ) != -1);
		
		int[] a3 = { -1, 2, 3, -2, 4, -2, 5, -2, 6, -3, -12, 0, 2, -5, 4, 8, -15};
		Result res = findLargestSum(a3);
		assertTrue(res != null);
		System.out.println("Sum = " + res.sum + "(" + res.start + "," + res.end + ")");
	}
}
