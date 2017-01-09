package TreesAndGraphs;

import junit.framework.TestCase;

//Convert a binary search tree to a doubled link list.
public class BSTToLinkedList extends TestCase {
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
		
		TreeNode last = null;
		TreeNode head = null;
				
		void convert(TreeNode node){
			if(node == null)
				return;
			
			convert(node.left );			
			
			if(last != null){			
				last.right = node;
				node.left = last;
			}
			else
				head = node;
			
			last = node;
			
			convert(node.right);
		}
		
		TreeNode convertToDoubledLinkedList(TreeNode node){
			convert(node);
			return head;
		}
	}	
	
	void print(TreeNode node){
		if (node == null)
			return;
		print(node.left);
		
		System.out.print("\t" + Integer.toString(node.value));		
	
		print(node.right);
		
	}
	
	void printLinkedList(TreeNode head){
		System.out.println("\nFrom head");
		TreeNode tail = null;
		while(head != null){
			System.out.print("\t" + Integer.toString(head.value));
			if (head.right == null)
				tail = head;
			head = head.right;
		}
		
		System.out.println("\nFrom tail:");
		while(tail != null){
			System.out.print("\t" + Integer.toString(tail.value));			
			tail = tail.left;
		}
		
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
		TreeNode head = root.convertToDoubledLinkedList(root);
		printLinkedList(head);
	}
}
