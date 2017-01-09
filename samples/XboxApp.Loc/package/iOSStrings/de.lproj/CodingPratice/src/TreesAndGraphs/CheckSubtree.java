package TreesAndGraphs;

import junit.framework.TestCase;

//You have binary tree t1 with millions of nodes and t2 with hundreds of node. 
//Create an algorithm to decide t2 is the subtree of t1.
public class CheckSubtree extends TestCase {
	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		class Result{		
			TreeNode node;
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
				
		boolean checkSubTree(TreeNode t1, TreeNode t2){
			if(t1 == null && t2 == null)
				return true;
			else if (t2 == null)
				return true;
			else if (t1 == null && t2 != null)
				return false;
			
			if ( t1.value == t2.value)
				if (match(t1, t2))
					return true;
			
			return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
		}		
	}	
	
	boolean match(TreeNode t1, TreeNode t2){
		if (t1 == null && t2 == null)
			return true;
		else if (t1 == null && t2 != null)
			return false;
		else if (t2 == null && t1 != null)
			return true;
		
		if (t1.value == t2.value)
			return match(t1.left, t2.left) && match(t1.right, t2.right);
		else
			return false;		
	}
	
	void print(TreeNode node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);
		
	}
	
	public void testFun(){
		TreeNode root = new TreeNode(0);
		TreeNode n1 = root.append(1, true);
		TreeNode n2 = root.append(2, false);
		TreeNode n3 = n1.append(3, true);
		TreeNode n4 = n1.append(4, false);
		TreeNode n5 = n2.append(5, true);
		TreeNode n6 = n2.append(6, false);
		n3.append(7, true);		
		n3.append(8, false);
		n4.append(9, true);
		n4.append(10, false);
		n5.append(11, true);
		n5.append(12, false);
		n6.append(13, true);
		n6.append(14, false);	
		
		TreeNode sn3 = new TreeNode(3);
		sn3.append(7, true);		
		sn3.append(8, false);		
		assertTrue(root.checkSubTree(root, sn3));
		
		TreeNode sn1 = new TreeNode(1);
		sn1.append(3, true);
		sn1.append(4, false);
		assertTrue(root.checkSubTree(root, sn1));
		
		TreeNode nn2 = new TreeNode(2);
		TreeNode nn5 = nn2.append(5, true);
		assertTrue(root.checkSubTree(root, nn2));
		
		TreeNode nn6 = nn2.append(6, false);
		assertTrue(root.checkSubTree(root, nn2));
		
		nn5.append(11, true);
		nn5.append(12, false);		
		assertTrue(root.checkSubTree(root, nn2));
		
		nn6.append(13, true);
		assertTrue(root.checkSubTree(root, nn2));
		
		n6.append(14, false);
		assertTrue(root.checkSubTree(root, nn2));
		
		System.out.print("\n");
		print(root);		
	}
}
