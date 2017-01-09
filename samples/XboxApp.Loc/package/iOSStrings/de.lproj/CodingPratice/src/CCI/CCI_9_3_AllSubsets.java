package CCI;

import java.util.ArrayList;

import junit.framework.TestCase;

//Write a method to return all the subsets of a set.
public class CCI_9_3_AllSubsets extends TestCase {
	
    void getAllSubset(int[]a, int index, ArrayList<ArrayList<Integer>> subset){  
    	if (index != a.length - 1)
    	{
    		getAllSubset(a, index + 1, subset);
    		
    		int size = subset.size();
    		for (int i = 0; i < size; i++){
    			ArrayList<Integer> sub = new ArrayList<Integer>(subset.get(i));
    			sub.add(a[index]);
    			subset.add(sub);
    		}
    		
    	}
    	ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(a[index]);
		subset.add(al);
    }
    
    ArrayList<ArrayList<Integer>> getSubsets(int a[]){
    	ArrayList<ArrayList<Integer>> subset  = new ArrayList<ArrayList<Integer>>();
    	getAllSubset(a, 0, subset);
    	
    	for (int i = 0; i < subset.size(); i++){
    		StringBuffer sb = new StringBuffer();
    		ArrayList<Integer> al = subset.get(i);
    		for (int j = 0; j < al.size(); j++)
    			sb.append(al.get(j)).append(" ");
    		
    		System.out.println(sb.toString());
    	}
    	
    	return subset;
    }
    
    
    public void testFun(){
    	int[] a = {0, 1, 2, 3, 4, 5};
    	getSubsets(a);
	}
}
