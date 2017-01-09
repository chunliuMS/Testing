package LeetCode;

import junit.framework.TestCase;

//Given a singly linked list where elements are sorted in ascending order,
//convert it to a height balanced Binary Search Tree.

public class LC027_SortedListToBST extends TestCase {
	class LNode{
		int data;
		LNode next;
		
		public LNode append(int v){
			LNode node = new LNode();
			node.data = v;
			this.next = node;
			return node;
		}
	}
	
	class TNode{
		int data;
		TNode left;
		TNode right;
	}
	
	TNode convert(LNode[] node, int start, int end){
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TNode left = convert(node, start, mid - 1);
		TNode curr = new TNode();
		curr.data = node[0].data;
		node[0] = node[0].next;
		curr.left = left;
		curr.right = convert(node, mid + 1, end);
		
		return curr;
	}
	
	TNode convert(LNode head){
		LNode curr = head;
		int count = 0;
		while (curr != null){
			count++;
			curr = curr.next;
		}
		
		if (count > 0){
			return  convert(new LNode[]{head}, 0, count - 1);
		}
		else
			return null;
	}
	
	void print(TNode head){
		if (head == null)
			return;
		print(head.left);
		System.out.print("\t" + head.data);
		print(head.right);
	}
	
	public void testFun(){
		LNode head = new LNode();
		head.data = 1;
		head.append(2).append(3).append(4).append(5);
		TNode t = convert(head);
		print(t);
	}	
}
