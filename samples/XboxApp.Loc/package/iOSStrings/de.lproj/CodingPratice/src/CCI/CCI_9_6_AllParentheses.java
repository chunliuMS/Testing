package CCI;

import java.util.ArrayList;

import junit.framework.TestCase;

//Print all the valid combinations of n-pair of parentheses.
public class CCI_9_6_AllParentheses extends TestCase {
	
    void getParentheses(ArrayList<String> list, int leftRemain, int rightRemain, char[] str, int count){ 
    	if (leftRemain == 0 && rightRemain == 0){
    		list.add(String.valueOf(str));
    	}
    	else {
    		if (leftRemain > 0){
    			str[count] = '(';
    			getParentheses(list, leftRemain - 1, rightRemain, str, count + 1);
    		}
    		if (rightRemain > leftRemain){
    			str[count] = ')';
    			getParentheses(list, leftRemain, rightRemain - 1, str, count + 1);
    		}
    	}    		
    }  
    
    ArrayList<String> getParentheses(int count){
    	char[] str = new char[2 * count];
    	ArrayList<String> list = new ArrayList<String>();
    	getParentheses(list, count, count, str, 0);
    	System.out.println("All the valid (" + list.size() + "):");
    	for (String valid: list){
    		System.out.println(valid);
    	}
    	return list;
    }
    
    public void testFun(){
    	getParentheses(3);
    	getParentheses(6);
	}
}
