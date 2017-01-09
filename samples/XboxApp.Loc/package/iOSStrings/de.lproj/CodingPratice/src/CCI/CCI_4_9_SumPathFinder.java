package CCI;
import java.util.Stack;
import java.util.Vector;

import junit.framework.TestCase;

//You're given a binary tree, each node has a value. Design an algorithm to print all the 
//paths which sum is a given value.
public class CCI_4_9_SumPathFinder extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
		boolean visited = false;
		
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		
		Node append(int value, boolean left){
			Node node = this;			
			Node child = new Node(value);
			if (left)
				node.left = child;
			else
				node.right = child;			
			return child;
		}	
	}	
	
	void getPath(Stack<Node> nodes, int sum , Vector<Stack<Node>> paths){
		int total = 0;
		for (int i = nodes.size() - 1; i >= 0; i--){
			total += nodes.get(i).value;
			if (total == sum){
				Stack<Node> path = new Stack<Node>();
				for (int j = nodes.size() - 1; j >= i; j--)
					path.add(nodes.get(j));
				paths.add(path);
			}
		}
	}
		
	Vector<Stack<Node>> findPaths(Node root, int sum)
	{
		if (root == null)
			return null;
		
		Vector<Stack<Node>> paths = new Vector<Stack<Node>>();
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		root.visited = true;
		
		while(!s.empty()){			
			Node n = s.peek();			
			if (n.left != null && !n.left.visited){
				s.push(n.left);
				n.left.visited = true;
				getPath(s, sum, paths);
			}
			else if (n.right != null && !n.right.visited ){
				s.push(n.right);
				n.right.visited = true;
				getPath(s, sum, paths);
			}
			else 
				s.pop();			
		}
		
		return paths;
	}
	
	
			
	void print(Node node){
		if (node == null)
			return;
		System.out.print("\t" + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);		
	}
	
	public void testFun(){
		Node root = new Node(2);
		Node n1 = root.append(1, true);		
		Node n2 = root.append(2, false);
		Node n3 = n1.append(3, true);
		n1.append(4, false);
		Node n5 = n2.append(5, true);
		n5.append(4, true);
		n2.append(6, false);
		n3.append(7, true);		
		Node n8 = n3.append(8, false);
		n8.append(1, false);
		
		Vector<Stack<Node>> paths =  findPaths(root, 13);
		assertTrue(paths.size() == 3);
	}
}
