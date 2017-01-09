package P100Solutions;

import junit.framework.TestCase;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


/*  Question: How to implement a function to check whether there is a path for a string in a matrix of characters?  
 * It moves to left, right, up and down in a matrix, and a cell for a movement. 
 * The path can start from any entry in a matrix. 
 * If a cell is occupied by a character of a string on the path, it cannot be occupied by another character again.  
For example, the matrix below with three rows and four columns has a path for the string “BCCED” (as highlighted in the matrix). 
It does not have a path for the string “ABCB”, because the first “B” in the string occupies the “B” cell in the matrix, 
and the second “B” in the string cannot enter into the same cell again. 
A B C E 
S F C S 
A D E E     
*/
public class String_path_in_matrix extends TestCase {
	 class Pos {
		 public Pos(int row, int col, int index) {
			 this.row = row;
			 this.col = col;
			 this.index = index;
		 }
		 int row;
		 int col;
		 int index;
	 }
		
	 ArrayList<Pos> findPath(char[][] ar, char[] chars) {	
		 ArrayList<Pos> path = new ArrayList<Pos> ();
		 Queue<Pos> q = new LinkedList<Pos> ();
		 
		 if (ar == null || chars == null || ar.length == 0 || chars.length == 0)
			 return null;
		 
		 for (int r = 0; r < ar.length; r++) {
			 for (int c = 0; c < ar[0].length; c++) {
				 if (ar[r][c] == chars[0]) {
					 q.add(new Pos(r, c, 0));
					 
					 while (!q.isEmpty()){
						 Pos pos = q.poll();
						 if (path.size() > pos.index)
							 path.remove(pos.index);
						 path.add(pos.index, pos);
						 if (path.size() == chars.length)
							 return path;
						 
						 if (pos.row - 1 > 0 && ar[pos.row -1][pos.col] == chars[pos.index + 1])  //top
							 q.add(new Pos(pos.row - 1, pos.col, pos.index + 1));
						 if (pos.row +1 < ar.length && ar[pos.row +1][pos.col] == chars[pos.index + 1])  //bottom
							 q.add(new Pos(pos.row + 1, pos.col, pos.index + 1));
						 if (pos.col - 1 > 0 && ar[pos.row][pos.col -1] == chars[pos.index + 1])  //left
							 q.add(new Pos(pos.row, pos.col - 1, pos.index + 1));
						 if (pos.col + 1 > 0 && ar[pos.row][pos.col + 1] == chars[pos.index + 1])  //right
							 q.add(new Pos(pos.row, pos.col + 1, pos.index + 1));
							 
					 }
				 }
			 }
			 
		 }
		 
		 return null;
	}
	
	
	public void testFun(){	
		char[][] arr = { {'a', 'b', 'c', 'e'},
						 {'s', 'f', 'c', 's'},
						 {'a', 'd', 'e', 'f'} };
		char [] chars = {'b', 'c', 'c', 'e', 'd'};
		ArrayList<Pos> path = findPath(arr, chars);
		
		assertTrue(path != null);
		
	}
	
}