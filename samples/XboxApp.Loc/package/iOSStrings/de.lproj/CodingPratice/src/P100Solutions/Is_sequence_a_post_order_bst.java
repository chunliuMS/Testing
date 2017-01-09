package P100Solutions;
import junit.framework.TestCase;;

//Post-order Traversal Sequences of Binary Search Trees 
//Problem: Determine whether an input array is a post-order traversal sequence of a binary tree or not. 
//If it is, return true; otherwise return false. Assume all numbers in an input array are unique. 

public class Is_sequence_a_post_order_bst extends TestCase {
		
	public void testFun(){		
		int [] al = {5, 7, 6, 9, 11, 10, 8};
		assertTrue(isBST(al, 0, al.length - 1));
		int[]a2 = {7, 4, 6, 5};
		assertFalse(isBST(a2, 0, a2.length - 1));
	}	
	
	private boolean isBST(int[] seq, int begin, int end) {
		if (end - begin <= 2)
			return true;
		
		int rootValue = seq[end];
		int right = 0;
		for (int i = begin; i < end; i++)
		{
			if (seq[i] == rootValue )
				return false;
			else if (seq[i] > rootValue) {
				right = i;
				break;
			}
		}
		
		for (int i = right; i < end; i++) {
			if (seq[i] <= rootValue)
				return false;
		}
			
		
		return isBST(seq, begin, right -1) && isBST(seq, right, end -1);
	}
	
}
