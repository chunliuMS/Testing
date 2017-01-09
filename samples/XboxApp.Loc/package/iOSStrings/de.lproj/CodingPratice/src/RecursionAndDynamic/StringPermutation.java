package RecursionAndDynamic;

import java.util.Vector;

import junit.framework.TestCase;

//Write a method to print all permutations of a string.
public class StringPermutation extends TestCase {
	private Vector<String> permutation(char[] a, int start){
		if (start == a.length - 1){
			Vector<String> s = new Vector<String>();
			s.add(String.valueOf(a[start]));
			return s;
		}	
		else{
			Vector<String> org_v = permutation(a, start + 1);
			Vector<String> new_v = new Vector<String>();
			for (String org: org_v){
				for (int j = 0; j <= org.length(); j++){
					StringBuffer buffer = new StringBuffer(org);
					new_v.add(buffer.insert(j, a[start]).toString());					
				}
			}			
			return new_v;
		}
	}
	
	public Vector<String> permutation(String s){		
		if (s.length() > 0){
			char[] a = s.toCharArray();
			return permutation(a, 0);
		}
		else{
			return null;
		}
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
		print(permutation("a"));
		print(permutation("ab"));
		print(permutation("abc"));
		print(permutation("abcd"));
		print(permutation("abcde"));
		print(permutation("abcdef"));
		print(permutation("0123456"));
		/*print(permutation("01234567"));
		print(permutation("012345678"));
		print(permutation("0123456789"));*/		
	}
}
