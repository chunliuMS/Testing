
package CCI;

import junit.framework.TestCase;

//Imagine you're reading a stream integer. Implement a method to track(int x) and a method to 
//getRank(int x) which returns the number of values less than x, 
//write method to find an element.
public class CCI_11_8_GetRank extends TestCase {
		
	private class Node
	{
		public Node(int data){
			this.data = data;
		}
		int leftSize = 0;
		int data;
		Node left;
		Node right;
		
		public void insert(int d){
			if (d <= data){
				if (left != null)
					left.insert(d);
				else
					left = new Node(d);
				leftSize++;
			}
			else {
				if (right != null)
					right.insert(d);
				else
					right = new Node(d);
			}				
		}
		
		public int getRank(int d){
			if (d == data)
				return leftSize;
			else if (d < data){
				if (left != null)
					return left.getRank(d);
				else
					return -1;
			}
			else {
				int rightRank = (right == null) ? -1 : right.getRank(d);
				if (rightRank == -1)
					return -1;
				else
					return rightRank + 1 + leftSize;
			}
		}
	}
	
	public class Question{
		private Node root = null;
		public void track(int d){
			if(root == null)
				root = new Node(d);
			else
				root.insert(d);			
		}
		
		public int rank(int d)
		{
			if (root == null)
				return -1;
			else
				return root.getRank(d);
		}
	}	
	
    public void testFun(){    
    	
    	Question q = new Question();
    	q.track(2);
    	q.track(7);
    	q.track(4);
    	q.track(3);
    	q.track(5);
    	q.track(6);
    	q.track(1);
    	q.track(3);
    	
    	assertTrue(q.rank(7) == 7);
    	assertTrue(q.rank(1) == 0);
    	assertTrue(q.rank(2) == 1);
    	assertTrue(q.rank(3) == 3);
    	assertTrue(q.rank(4) == 4);
    	assertTrue(q.rank(9) == -1);
	}
}