package StringAndArray;

import java.util.Vector;

import org.junit.Test;


public class GetMElementsFromArray {
		
	public void print(Vector<int[]> v)
	{
		StringBuffer sb = new StringBuffer("\n\n{");
		for(int[] a :v){
			sb.append("{");
			for(int e : a){
				sb.append(Integer.toString(e)).append(",");		
			}
			sb.replace(sb.length() -1, sb.length(), "}\n");
			sb.append(" ");			
		}
		sb.append("}\n\n");
		
		System.out.println(sb.toString());
	}
	
	public Vector<int[]> getSubset(int[] a, int start, int count, int size){
		if (count == 1){
			Vector<int[]> v = new Vector<int[]>();
			for (int i = start; i < a.length; i++ ){				
				int[] sub = new int[size];
				sub[count -1] = a[i];
				v.add(sub);
			}
			return v;
		}
		
		
		Vector<int[]> v = new Vector<int[]> ();
		
		for(int i = start; i < a.length; i++){
			if (i != start){
				int temp = a[i];
				a[i] = a[start];
				a[start] = temp;
			}
			Vector<int[]> s = getSubset(a, start + 1, count - 1, size);
			if (i != start){
				int temp = a[i];
				a[i] = a[start];
				a[start] = temp;
			}
			
			for (int[] sub : s){
				sub[count-1] = a[i];
			}
			v.addAll(s);				
		}
		return v;
	}
	
	
	public Vector<int[]> getSubSet(int[] a, int size){
		if ( a == null || a.length == 0)
			return null;
		
		Vector<int[]> v = getSubset(a, 0, size, size);
		return v;
		
	}
	
		
	@Test
	public void test() {
		int[] array = {0,1,2, 3, 4, 5};							
		print(getSubSet(array, 1));
		print(getSubSet(array, 2));
		print(getSubSet(array, 3));
		print(getSubSet(array, 4));
		print(getSubSet(array, 5));
		
	}
}
