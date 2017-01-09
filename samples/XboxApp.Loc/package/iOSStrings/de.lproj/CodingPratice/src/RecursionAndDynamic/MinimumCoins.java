package RecursionAndDynamic;
import java.util.Vector;

import org.junit.Test;

//Given N unique size of coins (v1, v2, ...vn) find the minimum number of coins used to get the total sum of S.
public class MinimumCoins {	
	
		
	public class Algorithm {
		
		public Algorithm(){			
		}
		
		@SuppressWarnings("unchecked")
		public int[] getMinimumCoins(int[] a, int sum){		
			int[] min = new int[sum + 1];
			Object[] coins = new Object [sum + 1];			
			
			for(int k = 0; k <= sum; k++){
				coins[k] = new Vector<Integer>();
			}
			
			for (int i = 1; i <= sum; i++)
				min[i] = Integer.MAX_VALUE;
			min[0] = 0;
			
			for (int s = 1; s <= sum; s++){
				for (int j = 0; j < a.length; j++){
					if (a[j] <= s && min[s - a[j]] + 1 < min[s]){
						min[s] = min[s - a[j]] + 1;
						Vector<Integer> cur = (Vector<Integer>)coins[s];
						Vector<Integer> pre = (Vector<Integer>)coins[s-a[j]];
						
						cur.clear();
						cur.addAll(pre);
						cur.add(a[j]);
					}
				}
			}			
			
			for (int i = 0; i < min.length; i++){
				StringBuilder sb = new StringBuilder("Sum=" + Integer.toString(i) + "\t no=" + Integer.toString(min[i]) + "\t");
				Vector<Integer> cur = (Vector<Integer>)coins[i];
				for(int j = 0; j < cur.size(); j ++)
				{
					sb.append("  " + Integer.toString(cur.get(j)));
				}
				System.out.println(sb.toString());
			}
			
			return min;
		}				
	}
	
	int count = Integer.MAX_VALUE;
	int[]minCount;
	void get(int coins[], int sum, int index, int counts[]){		
		int maxCount = sum / coins[index];
		if (maxCount == 0){
			counts[index] = 0;
			if (index < coins.length - 1)
				get(coins, sum, index + 1, counts);
		}
		else {	
			for (int i = maxCount; i > 0; i--){
				counts[index] = i;
				int s = sum - i * coins[index];
				if (s == 0){
					int total = 0;
					for(int t = 0; t <= index; t++)
						total += counts[t];
					if (total < count){
						count = total;
						minCount = new int[index + 1];
						for(int j = 0; j <= index; j++)
							minCount[j] = counts[j];
					}			
				}
				else if (index < coins.length - 1)
					get(coins, s, index + 1, counts);
			}
		}		
	}
		
	@Test
	public void test() {	
		//int[] a = {1, 2, 5, 10, 25};
		int[] a = {100, 25, 10, 5, 2, 1};
		Algorithm al = new Algorithm();
		al.getMinimumCoins(a,  50);		
		
		int[] counts = new int[a.length];
		get(a, 150, 0, counts);
		System.out.println("The result of 48 is:");
		for (int v: minCount){
			System.out.print(v + " ");
		}	
	}
}
