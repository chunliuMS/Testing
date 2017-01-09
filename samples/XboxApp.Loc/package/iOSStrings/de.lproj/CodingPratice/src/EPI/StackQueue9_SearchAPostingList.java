package EPI;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import junit.framework.TestCase;
/* 
 * Given a BST node, return all the node in sorted order without using recursion. 
*/
				
public class StackQueue9_SearchAPostingList extends TestCase {
		
	public class PostingListNode {
		PostingListNode next;
		PostingListNode jump;
		int value;
	}
		
	void print(PostingListNode n, Set<PostingListNode> passed) {
		Queue<PostingListNode> queue = new LinkedList<PostingListNode>();
		if (n == null)
			return;
		
		queue.add(n);;
		while (!queue.isEmpty()) {
			PostingListNode node = queue.poll();
			if (node != null && !passed.contains(node)) {
				passed.add(node);
				System.out.print(" " + node.value);
				PostingListNode n1 = node.next;
				if (n1 != null ) {
					queue.add(n1);;
				}
				PostingListNode n2 = node.jump;
				if (n2 != null) {
					queue.add(n2);
				}
			}			
		}
		
	}
	

	void print2(PostingListNode n) {
		
	}
		
	public void testFun(){	
		
	}		
}