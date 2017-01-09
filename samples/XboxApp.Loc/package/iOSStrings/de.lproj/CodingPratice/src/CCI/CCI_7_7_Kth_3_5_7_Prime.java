package CCI;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.TestCase;

//Design an algorithm to find kth number such that the only prime factors are 3, 5, 7.
public class CCI_7_7_Kth_3_5_7_Prime extends TestCase {
	
    int kthNumber(int k){
    	if (k < 0)
    		return 0;
    	
    	int v3 = 1;
    	Queue<Integer> q5 = new LinkedList<Integer>();
    	Queue<Integer> q7 = new LinkedList<Integer>();
    	
    	int min = 0;
    	for (int i = 0; i <= k; i++){
    		int v5 = q5.size() > 0 ? q5.peek() : Integer.MAX_VALUE;
    		int v7 = q7.size() > 0 ? q7.peek() : Integer.MAX_VALUE;
    		
    		min = Math.min(v3, Math.min(v5, v7));
    		if (v3 == min){
    			v3 = 3 * min;
    			q5.add(5 * min);
    		}
    		else if (v5 == min){
    			q5.remove();
    			q5.add(5 * min);
    		}
    		else{
    			q7.remove();
    		}
    		
    		q7.add(7 * min);
    	}
    	
    	return min;
    }
		
	public void testFun(){
		assertTrue(kthNumber(3) == 7);
		assertTrue(kthNumber(9) == 35);
		assertTrue(kthNumber(12) == 63);		
	}
}
