package RecursionAndDynamic;
import java.util.Vector;

import org.junit.Test;

//Given a sequence of n numbers - a[0], a[1] ... an[n-1], find the length of the longest non-decreasing sequence.
public class LargestSequence {	
	
		
	public class Algorithm {
		
		public Algorithm(){			
		}
		
		@SuppressWarnings("unchecked")
		public int[] getSequence(int[] a){		
			int size = a.length;
			int[] max = new int[size];
			Object[] nums = new Object [size];			
			
			for(int k = 0; k < size; k++){
				nums[k] = new Vector<Integer>();
			}
			
			for (int i = 0; i < size; i++){
				max[i] = 1;
				Vector<Integer> cur = (Vector<Integer>)nums[i];
				cur.add(a[i]);
			}
			
			
			for (int s = 1; s < size; s++){
				for (int j = 0; j < s; j++){
					if (a[s] >= a[j] && max[s] < max[j] + 1){
						max[s] = max[j] + 1;
						
						Vector<Integer> cur = (Vector<Integer>)nums[s];
						Vector<Integer> js = (Vector<Integer>)nums[j];
						
						cur.clear();
						cur.addAll(js);
						cur.add(a[s]);
					}
				}
			}
			
			
			for (int i = 0; i < max.length; i++){
				StringBuilder sb = new StringBuilder("Index =" + Integer.toString(i) + "\t size=" + Integer.toString(max[i]) + "\t");
				Vector<Integer> cur = (Vector<Integer>)nums[i];
				for(int j = 0; j < cur.size(); j ++)
				{
					sb.append("  " + Integer.toString(cur.get(j)));
				}
				System.out.println(sb.toString());
			}
			
			return max;
		}				
	}	
		
	@Test
	public void test() {		
		int[] a = { 5, 3, 4, 8, 6, 7};
		Algorithm al = new Algorithm();
		al.getSequence(a);		
	}
}
