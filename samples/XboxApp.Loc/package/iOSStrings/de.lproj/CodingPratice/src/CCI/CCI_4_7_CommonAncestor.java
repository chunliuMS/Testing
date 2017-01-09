package CCI;

import junit.framework.TestCase;

//Find the first common ancestor of 2 nodes
public class CCI_4_7_CommonAncestor extends TestCase {
	class Node{
		int value;
		Node left;
		Node right;
		
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
	
	static final int none = 0;
	static final int hasP = 1;
	static final int hasQ = 2;
	static final int hasAll = 3;		
		
	class RetNode
	{
		public Node ancestor = null;
	}
	
	public int findCommonAncestor(Node head, Node p, Node q, RetNode ret){
		if (head == null)
			return none;
		int lr = findCommonAncestor(head.left, p, q, ret);		
		int rr = findCommonAncestor(head.right, p, q, ret);
		
		if (ret.ancestor != null)
			return hasAll;
		
		int result = lr | rr;
		if (result == hasAll){
			ret.ancestor = head;
			return result;
		}
		
		if (head == p)
			result |= hasP;
		if (head == q)
			result |= hasQ;
		
		return result;		
	}
	
	public void testFun(){
		Node root = new Node(0);
		Node n1 = root.append(1, true);
		Node n2 = root.append(2, false);
		Node n3 = n1.append(3, true);
		Node n4 = n1.append(4, false);
		Node n5 = n2.append(5, true);
		Node n6 = n2.append(6, false);
		Node n7 = n3.append(7, true);	
		Node n8 = n5.append(8, true);
		
		RetNode res = new RetNode();;
		findCommonAncestor(root, n3, n4, res);
		assertTrue(res.ancestor == n1);
		
		res.ancestor = null;
		findCommonAncestor(root, n3, n5, res);
		assertTrue(res.ancestor == root);
		
		res.ancestor = null;
		findCommonAncestor(root, n7, n8, res);
		assertTrue(res.ancestor == root);
		
		res.ancestor = null;
		findCommonAncestor(root, n6, n8, res);
		assertTrue(res.ancestor == n2);
		
		res.ancestor = null;
		findCommonAncestor(root, n7, n3, res);
		assertTrue(res.ancestor == n1);
		
		res.ancestor = null;
		findCommonAncestor(root, n2, n8, res);
		assertTrue(res.ancestor == root);
	}
}