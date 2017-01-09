package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import junit.framework.TestCase;

//Clone a graph. Input is a Node pointer. Return the Node pointer of the cloned graph.
//struct Node {
//               Vector<Node> neighbors;
//}
public class LC024_CloneGraph extends TestCase {
	class Node{
		public Node(int data){
			this.data = data;
		}
		int data;
		Vector<Node> neighbors = new Vector<Node>();
	}
	
	Node clone(Node graph){
		if (graph == null)
			return null;
		
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node copyGraph = new Node(graph.data * 10);
		map.put(graph, copyGraph);
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(graph);
		
		while(!queue.isEmpty()){
			Node node = queue.remove();
			Node copyNode = map.get(node);
			for(Node neighbor : node.neighbors){
				Node copyNeighbor;
				if (map.containsKey(neighbor))
					copyNeighbor = map.get(neighbor);
				else{
					copyNeighbor = new Node(neighbor.data * 10);
					map.put(neighbor, copyNeighbor);
					queue.add(neighbor);
				}
				copyNode.neighbors.add(copyNeighbor);
			}
		}
		
		return copyGraph;
	}	
	
	Node clone(Node node, HashMap<Node, Node> hm){
		if (hm.containsKey(node))
			return hm.get(node);
		
		Node cloned = new Node(node.data * 10);
		hm.put(node, cloned);
		
		for(Node n : node.neighbors)
			cloned.neighbors.add(clone(n, hm));
			
		return cloned;
	}
	
	Node cloneDFS(Node node){
		HashMap<Node, Node> hm = new HashMap<Node, Node>();
		return clone(node, hm);
	}
	
	void print(Node graph){
		if (graph == null)
			return;
		System.out.println("Graph for " + graph.data + " :");
		
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> visited = new HashSet<Node>();
		queue.add(graph);
		while(!queue.isEmpty()){
			Node node = queue.remove();
			if (!visited.contains(node)){
				visited.add(node);
				StringBuffer sb = new StringBuffer();
				sb.append(node.data).append("-->");
				for(Node neighbor : node.neighbors){
					sb.append(neighbor.data).append(" ");
					if (!visited.contains(neighbor))
						queue.add(neighbor);
				}
				System.out.println(sb.toString());
			}
		}
	}
	
	public void testFun(){
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		n1.neighbors.add(n2);
		n1.neighbors.add(n5);
		n1.neighbors.add(n3);
		n2.neighbors.add(n4);
		n2.neighbors.add(n1);
		n3.neighbors.add(n1);
		n3.neighbors.add(n4);
		n3.neighbors.add(n5);
		n4.neighbors.add(n2);
		n4.neighbors.add(n3);
		n4.neighbors.add(n5);
		n5.neighbors.add(n1);
		n5.neighbors.add(n3);
		n5.neighbors.add(n4);
		
		print(n1);
		print(clone(n1));
		print(cloneDFS(n1));
	}	
}
