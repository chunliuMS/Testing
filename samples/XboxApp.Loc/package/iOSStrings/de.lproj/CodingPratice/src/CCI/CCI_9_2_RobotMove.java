package CCI;

import junit.framework.TestCase;

//Image a robot sitting on the upper left corner of an X by Y grid. The robot can only move in 2 directions: right and down.
//How may paths are for the robot to go from (0, 0) to (X, Y)?
public class CCI_9_2_RobotMove extends TestCase {
	
    int countPath(int x, int y, int xi, int yi, int[][] cache){  
    	if (x < 0 || y < 0)
    		return 0;
    	if (x == 0 || y == 0)
    		return 1;
    	if (cache[xi][yi] > 0)
    		return cache[xi][yi];
    	else if (xi == x || yi == y)
    		return 1;
    	else
    	{
    		cache[xi][yi] = countPath(x, y, xi + 1, yi, cache) + countPath(x, y, xi, yi +1, cache);
    		return cache[xi][yi];
    	}    	
    }
    
    int countPath(int x, int y, int[][] cache){
    	if (x == 0 || y == 0 )
    		return 1;
    	if (cache[x][y] > 0)
    		return cache[x][y];
    	else {
    		cache[x][y] = countPath(x - 1, y, cache) + countPath(x, y -1, cache);
    		return cache[x][y];
    	}    		
    }
    
    public int getPaths(int x, int y){
		if (x < 1 || y < 1)
			return 0;
		
		int [][] cache = new int[x+1][];
		for (int i = 0; i <=  x; i++)
			cache[i] = new int[y+1];		
		
		int paths =  countPath(x, y, 0, 0, cache);	
		System.out.println(x + "x" + y + " -- " + paths);
		
		cache = new int[x+1][];
		for (int i = 0; i <=  x; i++)
			cache[i] = new int[y+1];	
		
		int paths2 =  countPath(x, y, cache);	
		System.out.println(x + "x" + y + " -- " + paths2);
		
		assertTrue(paths == paths2);
		
		return paths2;		
	}
    		
	public void testFun(){
		getPaths(1,1);
		getPaths(2, 2);
		getPaths(3, 3);
		getPaths(4, 4);
		getPaths(14, 11);		
	}
}
