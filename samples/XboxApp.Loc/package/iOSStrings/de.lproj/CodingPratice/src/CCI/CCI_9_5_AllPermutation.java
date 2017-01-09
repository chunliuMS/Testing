package CCI;

import java.util.ArrayList;

import junit.framework.TestCase;

//Write a method to compute all the permutations of a string.
public class CCI_9_5_AllPermutation extends TestCase {
	
    void getPermutation(String s, int index, ArrayList<String> a){ 
    	if (index == s.length() - 1) //last char
    		a.add(s.substring(index));
    	else {
    		getPermutation(s, index + 1, a);
    		char c = s.charAt(index);
    		int size = a.size();
    		for(int i = 0; i < size; i++){
    			String str = a.get(i);
    			a.set(i, str + c);
    			for (int j = 0; j < str.length(); j++){
    				a.add(str.substring(0, j)+ c + str.substring(j));
    			}
    		}    		
    	}    		
    }       
    void exch(char[] a, int i, int j){
    	char tem = a[i];
    	a[i] = a[j];
    	a[j] = tem;
    }
    void getPermutation(char[] a, int index){
    	if (index == a.length - 1){
    		StringBuffer sb = new StringBuffer();
    		for (char c : a){
    			sb.append(c);
    		}
    		System.out.println(sb.toString());
    	}
    	else
    	{
    		for (int i = index; i < a.length; i++){
    			if (i != index){
    				exch(a, i, index);
    			}
    			getPermutation(a, index + 1);
    			if (i != index){
    				exch(a, i, index);
    			}
    		}
    	}
    }
    
    
    
    public void testFun(){
    	
    }
}
