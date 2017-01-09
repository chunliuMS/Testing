package StringAndArray;

import java.util.Vector;

import org.junit.Test;


public class AllSubsetOfArray {
		
	public void print(Vector<int[]> a, int[]org)
	{
		StringBuffer sb = new StringBuffer("\nThe sub set of {");
		for (int i: org){
			sb.append(Integer.toString(i)).append(",");
		}
		sb.append("}");
		System.out.println(sb.toString());
		
		for(int[] element : a){
			sb = new StringBuffer("{");
			for(int v : element){
				sb.append(Integer.toString(v)).append(",");		
			}
			sb.replace(sb.length() -1, sb.length(), "}");
			sb.append(" ");
			System.out.print(sb.toString());			
		}
	}
	
	public void getSubset(int[] a, int start, Vector<int[]> v){
		if (start == a.length -1){
			int[] sub = new int[1];
			sub[0] = a[start];
			v.add(sub);
			return;
		}
		getSubset(a, start+1, v);
		
		int size = v.size();
		int [] sub = new int[1];
		sub[0] = a[start];
		v.add(sub);
		
		for(int i = 0; i < size; i++){
			int[] org = v.elementAt(i);
			int[] new_sub = new int[org.length + 1];
			System.arraycopy(org, 0, new_sub, 0, org.length);
			new_sub[org.length] = a[start];
			v.add(new_sub);			
		}
	}
	
	
	public Vector<int[]> getSubSet(int[] a){
		if ( a == null || a.length == 0)
			return null;
		
		Vector<int[]> v = new Vector<int[]>();
		getSubset(a, 0, v);
		return v;
		
	}
	
	boolean add(int[]v, int index){
		if (index >= v.length)
			return false;
		if (v[index] == 0){
			v[index] = 1;
			return true;
		}
		else {
			v[index] = 0;
			return add(v, index + 1);
		}
			
	}
	
	public Vector<int[]> SubSet(int[] a){
		if (a == null || a.length == 0)
			return null;
		
		Vector<int[]> subsets = new Vector<int[]>();
		int[] v = new int[a.length];
		while(add(v, 0)){
			int count = 0;
			for (int i : v){
				if (i == 1)
					count++;				
			}
			
			int[] set = new int[count];		
			int index = 0;
			for (int j = 0; j < v.length; j++){
				if (v[j] == 1)
					set[index++] = a[j];
			}
			
			subsets.add(set);			
		}
		
		return subsets;
	}
	
		
	@Test
	public void test() {
		int[] array1 = {0};							
		print(getSubSet(array1), array1);
		
		int[] array2 = {0,1};							
		print(getSubSet(array2), array2);
			
		int[] array3 = {0,1,2};							
		print(getSubSet(array3), array3);
		
		int[] array4 = {0,1,2,3};							
		print(getSubSet(array4), array4);
		print(SubSet(array4), array4);
		
		int[] array5 = {0,1,2,3,4};							
		print(getSubSet(array5), array5);
		
		print(SubSet(array5), array5);
		
	}
}
