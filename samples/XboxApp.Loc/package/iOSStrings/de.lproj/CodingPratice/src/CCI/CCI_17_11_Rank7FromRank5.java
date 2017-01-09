
package CCI;

import junit.framework.TestCase;

//Implement method rand7() given method rand5().
public class CCI_17_11_Rank7FromRank5 extends TestCase {
		
	int rand5(){
		return ((int)(Math.random() * 100000)) % 5;
	}
	
	int rand7(){
		while( true)
		{
			int v = rand5() + 5 * rand5();
			if (v < 21)
				return v % 7;
		}		
	}
		
    public void testFun(){    
    	for (int i = 0; i < 40; i++){
    		StringBuffer sb = new StringBuffer();
    		for (int j= 0; j < 7; j++){
    			sb.append(rand7()).append(" ");
    		}
    		System.out.println(sb.toString());
    	}
	}
}