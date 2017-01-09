package TreesAndGraphs;

import java.util.Stack;

import junit.framework.TestCase;

//You're given a binary tree in which each node contains a value. 
//Design an algorithm to print all paths which sum to a given value.
public class PrintPathWithSum extends TestCase {
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
				
		void findPath(TreeNode node, int sum, Stack<Integer> path){
			if (node == null)
				return;
			
			if (path == null){
				path = new Stack<Integer>();
			}
			path.push(node.value);
			int t = 0;
			for (int i = path.size() -1; i >= 0; i--){
				t += path.get(i);
				if (t == sum){
					System.out.print("\n ");
					for (int j = i; j < path.size(); j++){
						System.out.print( "\t" + Integer.toString(path.get(j)));
					}
				}
			}
			
			findPath(node.left, sum, path);
			findPath(node.right, sum, path);
			
			path.pop();
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
		n3.append(13, true);		
		n3.append(14, false);
		n4.append(11, true);
		n4.append(12, false);
		n5.append(9, true);
		n5.append(10, false);
		n6.append(7, true);
		n6.append(8, false);	
		
		System.out.print("\nPaths for sum = 17: \n");
		root.findPath(root, 17, null);
		
		System.out.print("\nPaths for sum = 16: \n");
		root.findPath(root, 16, null);
		
		System.out.print("\nPaths for sum = 15: \n");
		root.findPath(root, 15, null);
		
		System.out.print("\nPaths for sum = 14: \n");
		root.findPath(root, 14, null);
	}
}
