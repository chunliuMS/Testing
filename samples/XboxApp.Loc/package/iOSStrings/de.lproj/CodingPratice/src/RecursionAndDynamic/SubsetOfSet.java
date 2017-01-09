package RecursionAndDynamic;

import java.util.Vector;

import junit.framework.TestCase;

//Get all the subset of the string.
public class SubsetOfSet extends TestCase {
	private void getSubset(String s, int start, Vector<String>list){
		if (start == s.length() - 1){
			list.add(s.substring(start));
		}	
		else{
			getSubset(s, start + 1, list);
			int size = list.size();
			String c = s.substring(start, start + 1);
			list.add(c);
			for (int i = 0; i < size; i++){	
				StringBuffer buffer = new StringBuffer();
				buffer.append(c).append(list.get(i));
				list.add(buffer.toString());			
			}
		}
	}
	
	public Vector<String> getSubset(String s){
		
		Vector<String> vec = new Vector<String>();
		if (s != null && s.length() > 0)
			getSubset(s, 0, vec);
		return vec;
	}
	
	void print(Vector<String> list){
		System.out.print("\n\nOut put string set = ");
		System.out.print(Integer.toString(list.size()));
		System.out.print(" items \n");
		int i = 0;
		for(String str : list){
			System.out.print(str);
			System.out.print("\t");
			i++;
			if (i == 10){
				i = 0;
				System.out.print("\n");
			}
		}
	}
	
	public void testFun(){	
		print(getSubset("0"));
		print(getSubset("01"));
		print(getSubset("012"));
		print(getSubset("0123"));
		print(getSubset("01234"));
		print(getSubset("543210"));
		print(getSubset("0123456"));
		print(getSubset("01234567"));
		print(getSubset("012345678"));
		print(getSubset("0123456789"));		
	}
}
