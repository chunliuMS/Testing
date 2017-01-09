package TreesAndGraphs;

import junit.framework.TestCase;

//Implement a function to find kth largest value in a BST.
public class FindKthLargestInBST extends TestCase {
	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		TreeNode append(int value, boolean left){
			TreeNode node = this;			
			TreeNode child = new TreeNode(value);
			if (left)
				node.left = child;
			else
				node.right = child;			
			return child;
		}	
		
		TreeNode FindLargest(TreeNode root, int k){
			this.k = k;		
			return FindLargest(root);
		}
		int k = 0;
		TreeNode FindLargest(TreeNode node){
			if(node == null)
				return null;			
			
			TreeNode ret = FindLargest(node.right );
			if ( ret == null){
				k--;
				if (k == 0)
					ret = node;
				else
					ret =  FindLargest(node.left);
			}
			return ret;
		}
	}	
	
	void print(TreeNode node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);
		
	}
	
	public void testFun(){
				
		TreeNode root = new TreeNode(4);
		TreeNode n2 = root.append(2, true);
		TreeNode n6 = root.append(6, false);
		n2.append(1, true);
		n2.append(3, false);
		n6.append(5, true);
		n6.append(7, false);
		
		System.out.print("\n");
		print(root);
		TreeNode ret = root.FindLargest(root, 1);
		assertTrue(ret.value == 7);
		
		ret = root.FindLargest(root, 3);
		assertTrue(ret.value == 5);
	}
}
