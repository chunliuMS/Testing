package Algorithms;

import java.util.LinkedList;
import java.util.Queue;

import junit.framework.TestCase;

public class A_3_3_BinarySearchTree extends TestCase {
	
	public class BST<Key extends Comparable<Key>, Value>{
		private Node root; // root of BST
		
		private class Node{
			private Key key; // key
			private Value val; // associated value
			private Node left, right; // links to subtrees
			private int N; // # nodes in subtree rooted here
			
			public Node(Key key, Value val, int N){ 
				this.key = key; 
				this.val = val; 
				this.N = N; }
		}
		
		public int size(){ 
			return size(root); 
		}
		
		private int size(Node x){
			if (x == null) 
				return 0;
			else 
				return x.N;
		}
		
		public Value get(Key key){
			return get(root, key);
		}
		
		private Value get(Node x, Key key){
			if (x == null)
				return null;
			int comp = key.compareTo(x.key);
			if (comp == 0)
				return x.val;
			else if (comp < 0)
				return get(x.left, key);
			else 
				return get(x.right, key);
		}
		
		public void put(Key key, Value val){
			root = put(root, key, val);			
		}
		
		private Node put(Node x, Key key, Value val){
			if (x == null) 
				return new Node(key, val, 1);
			int comp = key.compareTo(x.key);
			if (comp == 0)
				x.val = val;
			else if (comp < 0)
				x.left = put(x.left, key, val);
			else 
				x.right = put(x.right, key, val);
			
			x.N = size(x.left) + size(x.right) + 1;	
			return x;
		}
		
		public Key min(){
			if (root == null)
				return null;
			return min(root).key;
		}
		
		private Node min(Node x){
			if (x.left == null)
				return x;
			else
				return min(x.left);
		}
		
		public Key max(){
			if (root == null)
				return null;
			return min(root).key;
		}
		
		
		public Key floor(Key key){
			Node x = floor(root, key);
			if (x == null) 
				return null;
			return x.key;
		}
		
		private Node floor(Node x, Key key)	{
			if (x == null) 
				return null;
			int cmp = key.compareTo(x.key);
			if (cmp == 0) 
				return x;
			if (cmp < 0) 
				return floor(x.left, key);
			Node t = floor(x.right, key);
			if (t != null) 
				return t;
			else 
				return x;
		}
		
		public Key select(int k){
			Node n =  select(root, k);
			return n == null ? null : n.key;
		}
		
		public Node select(Node x, int k){
			if (x == null)
				return null;
			if (k == x.N)
				return x;
			else if (k < x.N)
				return select(x.left, k);
			else
				return select(x.right, k);
		}
		
		public int rank(Key key){
			return rank(root, key);
		}
		
		public int rank(Node x, Key key){
			if (x == null)
				return 0;
			int comp = key.compareTo(x.key);
			if (comp == 0)
				return x.N;
			else if (comp < 0)
				return rank(x.left, key);
			else
				return rank(x.right, key);
		}
		
		public void deleteMin(){
			root = deleteMin(root);				
		}
		
		private Node deleteMin(Node x){
			if (x.left == null) 
				return x.right;
			x.left = deleteMin(x.left);
			x.N = size(x.left) + size(x.right) + 1;
			return x;
		}
		
		public void delete(Key key){
			root = delete(root, key);				
		}
		
		private Node delete(Node x, Key key){
			if (x == null)
				return null;
			
			int comp = key.compareTo(x.key);
			if (comp < 0)
				x.left = delete(x.left, key);
			else if (comp > 0)
				x.right = delete(x.right, key);
			else{
				if (x.left == null)
					return x.right;
				if (x.right == null)
					return x.left;
				Node t = x;
				x = min(x.right);
				x.right = deleteMin(t.right);
				x.left = t.left;
			}
			
			x.N = size(x.left) + size(x.right) + 1;
			return x;
		}
		
		public Iterable<Key> keys()	{ 
			return keys(min(), max()); 
		}
		public Iterable<Key> keys(Key lo, Key hi)
		{
			Queue<Key> queue = new LinkedList<Key>();
			keys(root, queue, lo, hi);
			return queue;
		}
		private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
		{
		if (x == null) return;
			int cmplo = lo.compareTo(x.key);
			int cmphi = hi.compareTo(x.key);
			if (cmplo < 0) 
				keys(x.left, queue, lo, hi);
			if (cmplo <= 0 && cmphi >= 0) 
				queue.add(x.key);
			if (cmphi > 0) 
				keys(x.right, queue, lo, hi);
		}
		
		public void print(){
			StringBuilder sb = new StringBuilder("Tree: ");
			print(root, sb);
			
			System.out.println(sb.toString());
		}
		
		private void print(Node x, StringBuilder sb){
			if (x == null)
				return;
			print(x.left, sb);
			sb.append(x.key).append("-").append(x.val).append(" ");
			print(x.right, sb);
		}
	}
		
	public void testFun(){
		BST<Integer, String> bst = new BST<Integer, String>();
		bst.put(1, "v1");
		bst.put(9, "v9");
		bst.put(0, "v0");
		bst.put(5, "v5");
		bst.put(8, "v8");
		bst.put(2, "v2");
		bst.put(7, "v7");
		bst.put(6, "v6");
		bst.put(3, "v3");
		bst.put(4, "v4");		
		bst.print();
		
		bst.delete(5);
		bst.delete(7);
		bst.print();
		
		bst.put(4, "v4-2");
		bst.put(5, "v5-2");
		bst.print();
	}
}
