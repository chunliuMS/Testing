
package CCI;

import java.util.ArrayList;

import junit.framework.TestCase;

//Write an algorithm to print all ways of arranging eight queens on an 8 x 8 chess board so that none of them
//share the same row, column or diagonal.
public class CCI_9_9_ChessBoard extends TestCase {	
    void placeQueens(int row, int[] columns, ArrayList<int[]> result){
    	if (row == 8){
    		result.add(columns.clone());
    	}
    	
    	for (int i = 0; i < 8; i++)
    	{
    		if (valid(row, i, columns)){
    			columns[row] = i;
    			placeQueens(row + 1, columns, result);
    		}
    	}
    }
    
    boolean valid(int row, int column, int[] columns){
    	for (int i = 0; i < row; i++)
    	{
    		if(column == columns[i])
    			return false;
    		
    		int dist = Math.abs(column - columns[i]);
    		if (dist == row - i)
    			return false;    		
    	}
    	
    	return true;
    }
    
    public void testFun(){
    	ArrayList<int[]> result = new ArrayList<int[]>();
    	int[] columns = new int[8];
    	placeQueens(0, columns, result);
    	
    	for (int[] a: result){
    		System.out.println();
    		for (int i = 0; i < a.length; i++)
    			System.out.print(" " + a[i]);    			
    	}
	}
}

