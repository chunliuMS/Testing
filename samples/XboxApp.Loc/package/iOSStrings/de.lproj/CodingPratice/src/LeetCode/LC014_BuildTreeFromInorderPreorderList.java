package LeetCode;

import junit.framework.TestCase;

//Build a tree from pre-order and in order list.
public class LC014_BuildTreeFromInorderPreorderList extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
				
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
		
	void print(Node node){		
		if (node == null)
			return;
		System.out.print(" " + Integer.toString(node.value));
		
		print(node.left);
		print(node.right);		
	}
	
	class Index {
		int v = 0;	
		Index inc(){
			v++;
			return this;
		}
	}
	
	Node build(int[] pre, int[] in){
		return build(pre, new Index(), in, 0, pre.length - 1);
	}
	
	Node build(int[] pre, Index curr, int[] in, int start, int end){
		if (curr.v > pre.length)
			return null;		
		if (start > end)
			return null;
		
		int value = pre[curr.v];
		curr.inc();
		Node root = new Node(value);
		int div = -1;
		for (int i = start; i <= end; i++){
			if (value == in[i])
				div = i;
		}
		
		if (div >= 0){
			root.left = build(pre, curr, in, start, div - 1);
			root.right = build(pre, curr, in, div + 1, end);
		}
		
		return root;
	}
	
	int index = 0;
	Node Build2(int[]pre, int[]in, int start, int end){		
		if (index >= in.length)
			return null;
		
		if (start > end)
			return null;
		
		int v = in[index++];
		Node n = new Node(v);
		int div = -1;
		for (int i = end; i <= start; i--){
			if (pre[i] == v){
				div = i;
				break;
			}		
		}
		
		n.left = Build2(pre, in, start, div - 1);
		n.right = Build2(pre, in, div + 1, end);
		
		return n;
	}
	
	Node build2(int[] pre, int[] in){
		return Build2(pre, in, 0, pre.length - 1);
	}
		
	public void testFun(){
		int[] pre = {7, 10, 4, 3, 1, 2, 8, 11};
		int[] in = {4, 10, 3, 1, 7, 11, 8, 2};
		print(build2(pre, in));
	}	
}
