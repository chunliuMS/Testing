package Util;
import java.util.*;

public class TreeNode <T> {
	public T value;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode (T v) {
		this.value = v;
	}
	
	public void add(T left, T right) {
		if (left != null) {
			this.left = new TreeNode <T> (left);
		}
		
		if (right != null) {
			this.right = new TreeNode <T> (right);
		}
	}
	
	public void print() {
		Queue <TreeNode<T>> elements = new LinkedList<TreeNode<T>>();
		elements.add(this);
		System.out.println("*******Print the tree******\n");
		
		while (!elements.isEmpty()) {
			TreeNode<T> node = elements.poll();
			System.out.print(node.value.toString());
			System.out.print("\t");
			if (node.left != null) {
				elements.add(node.left);
			}
			
			if (node.right != null) {
				elements.add(node.right);
			}
		}
		
		System.out.println("\n*******end******");
	}
}
