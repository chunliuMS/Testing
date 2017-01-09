package TreesAndGraphs;

import junit.framework.TestCase;

//Implement a function to find common ancestor of 2 nodes.
public class FindCommonAncestor extends TestCase {
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
		
		int findAncestor(TreeNode node, int first, int second, Result result){
			if (node == null)
				return 0;			
			
			int ret = 0;
			if (result.node == null){				
				int retLeft = findAncestor(node.left, first, second, result);				
				int retRight =findAncestor(node.right, first, second, result);
				
				ret = retLeft | retRight;		
				
				if ( ret != 3){
					boolean findFirst = node.value == first;
					boolean findSecond = node.value == second;
					if (findFirst || findSecond){
						if (findFirst)
							ret |= 1;
						if (findSecond)
							ret |= 2;		
					}	
				}
				
				if( ret == 3 && result.node == null)
					result.node = node;	
			}	
			else
				ret = 3;
					
			return ret;
		}	
		
		public TreeNode findAncestor(int first, int second){
			Result result = new Result();
			findAncestor(this, first, second, result);
			
			return result.node;
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
		
		System.out.print("\n");
		print(root);
		
		TreeNode node = root.findAncestor(1, 2);
		assertTrue(node.value == 0);
		
		node = root.findAncestor(0, 1);
		assertTrue(node.value == 0);
		
		node = root.findAncestor(7, 8);
		assertTrue(node.value == 3);
		
		node = root.findAncestor(7, 4);
		assertTrue(node.value == 1);
		
		node = root.findAncestor(8, 10);
		assertTrue(node.value == 1);
		
		node = root.findAncestor(1, 14);
		assertTrue(node.value == 0);
		
		node = root.findAncestor(1, 7);
		assertTrue(node.value == 1);
	}
}
