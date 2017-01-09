package CCI;

import java.util.ArrayList;

import junit.framework.TestCase;

//An array contains all the integers from 0 to n except for one missing number, 
//the only operation to access the number is fetch the j bit of a[i].
//Write a function to find the mission one. 
public class CCI_5_7_FindMissingByBits extends TestCase {
	
    int findMissing(ArrayList<Integer> a, int column)
	{	
    	if (column > 31)
    		return 0;
    	
		ArrayList<Integer> zeros = new ArrayList<Integer>(a.size() / 2);
		ArrayList<Integer> ones = new ArrayList<Integer>(a.size() / 2);
		
		for(Integer t : a){
			if (getBit(t, column) == 0)
				zeros.add(t);
			else
				ones.add(t);
		}
	
		if (zeros.size() <= ones.size()){
			int v = findMissing(zeros, column + 1);
			return v << 1;       
		}
		else
		{
			int v = findMissing(ones, column + 1);
			return (v << 1) | 1;
		}
	}
    
    int getBit(int v, int column){
    	return v & (1 << column);
    }
		
	public void testFun(){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i <= 66; i++)
			a.add(i);
		for (int i = 68; i < 100; i++)
			a.add(i);
		
		assertTrue(findMissing(a, 0) == 67);
	}
}
