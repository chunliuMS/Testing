package Test;

import junit.framework.TestCase;

public class Trees extends TestCase {
	public class Node {
		int data;
		Node left;
		Node right;
		int leftSize;
		
		public Node(int data){
			this.data = data;
		}
		
		public void add(int v){
			if (v <= data){
				leftSize++;
				if (left == null)
					left = new Node(v);
				else
					left.add(v);						
			}
			else{
				if (right == null)
					right = new Node(v);
				else
					right.add(v);
			}
		}
		
		public int getRank(int k){
			if (k == this.data)
				return this.leftSize;
			else if (k < this.data){
				if (this.left != null)
					return this.left.getRank(k);
				else
					return -1;
			}
			else {
				 if(right == null)
					 return -1;
				 else {
					 int rank = right.getRank(k);
					 return rank == -1 ? -1 : rank + leftSize + 1; 
				 }
			}				
		}
	}		
	public void testFun(){
		
	}
}
