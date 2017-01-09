package CCI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import junit.framework.TestCase;

//Given a directed graph, design an algorithm to find out if there is a route between two nodes.
public class CCI_4_2_FindRoute extends TestCase {
	class Node{
		int value;
		public boolean visited = false;
		
		Node(int value){
			this.value = value;
		}			
		Node prev = null;
	}	
	
	class Graph{
		private Vector<Node> nodes = new Vector<Node>();
		private int[][] adjMatrix;
				
		public Node addNode(int n){
			return this.addNode(n, false);
		}
		
		public Node addNode(int n, boolean lastOne){
			Node node = new Node(n);
			nodes.add(node);
			int size = nodes.size();
			adjMatrix = new int[size][size];
			return node;
		}
		
		public void connect(Node begin, Node end){
			int bIndex = nodes.indexOf(begin);
			int eIndex = nodes.indexOf(end);
			
			if (bIndex >= 0 && eIndex >=0){
				adjMatrix[bIndex][eIndex] = 1;
				adjMatrix[eIndex][bIndex] = 1;
			}
		}
		
		private Node getUnvisitedChild(Node n){
			int index = nodes.indexOf(n);
			if (index >=0 && index < adjMatrix.length){
				for (int j = 0; j < adjMatrix.length; j++){
					if (adjMatrix[index][j] > 0){
						Node node = nodes.get(j);
						if (!node.visited)
							return node;
					}
				}
			}
			return null;
		}
		
		private ArrayList<Node> getUnvisitedChilds(Node n){
			ArrayList<Node> children = new ArrayList<Node>();
			int index = nodes.indexOf(n);
			if (index >=0 && index < adjMatrix.length){
				for (int j = 0; j < adjMatrix.length; j++){
					if (adjMatrix[index][j] > 0){
						Node node = nodes.get(j);
						if (!node.visited)
							children.add(node);
					}
				}
			}
			return children;
		}
		
		public boolean hasRouteBFS(Node first, Node second){
			Queue<Node> queue = new LinkedList<Node>();
			first.visited = true;
			queue.add(first);
			
			while(!queue.isEmpty()){
				Node node = queue.remove();
				Node child = null;
				while ((child = getUnvisitedChild(node)) != null){
					if (child == second)
						return true;
					else{
						child.visited = true;
						queue.add(child);
					}
				}
			}			
			return false;
		}
		
		public Node searchRouteBFS(Node first, Node second){
			Queue<Node> queue = new LinkedList<Node>();
			first.visited = true;
			queue.add(first);
			
			while(!queue.isEmpty()){
				Node node = queue.remove();
				Node child = null;
				while ((child = getUnvisitedChild(node)) != null){
					if (child == second){
						child.prev = node;
						return child;
					}
					else{
						child.visited = true;
						child.prev = node;
						queue.add(child);
					}
				}
			}			
			return null;
		}
		
		public Stack<Node> searchRouteDFS(Node first, Node second){
			Stack<Node> s = new Stack<Node>();
			first.visited = true;			
			s.push(first);
			
			while (!s.empty()){
				Node n = s.peek();
				if (n == second) {
					s.push(second);
					return s;
				}
				else {
					Node child = getUnvisitedChild(n);
					if (child != null){
						if (child == second){
							s.push(second);
							return s;
						}
						child.visited = true;
						s.push(child);
					}
					else
						s.pop();					
				}
			}			
			return null;
		}
		
		boolean dfs(Node curr, Node dest){
			curr.visited = true;
			if (curr == dest)
				return true;
			ArrayList<Node> children = getUnvisitedChilds(curr);
			for (Node n: children){
				n.prev = curr;
				if (dfs(n, dest))
					return true;
			}			
			return false;
		}
		
		void clearAll(){
			for(int i = 0; i < nodes.size(); i++){
				nodes.get(i).visited = false;
				nodes.get(i).prev = null;
			}			
		}
	}		
	
	void print(Stack<Node> route){
		if (route == null || route.size() == 0)
			System.out.println("No route exists!");
		else
			System.out.println("\nFound the route: ");
		for(int i = 0; i < route.size(); i++){
			System.out.print(route.get(i).value);
			System.out.print("  ");
		}
	}
	
	void printRoute(Node end){
		System.out.println("\nBFS Found the route: ");
		while (end != null){
			System.out.print(end.value);
			end = end.prev;
		}
	}
		
	public void testFun(){
		Graph g = new Graph();
		Node n1 = g.addNode(1);
		Node n2 = g.addNode(2);
		Node n3 = g.addNode(3);
		Node n4 = g.addNode(4);
		Node n5 = g.addNode(5);
		Node n6 = g.addNode(6);
		Node n7 = g.addNode(7);
		Node n8 = g.addNode(8);
		Node n9 = g.addNode(9, true);
		
		g.connect(n1, n2);
		g.connect(n1, n4);
		g.connect(n2, n4);
		g.connect(n4, n5);
		g.connect(n5, n3);
		g.connect(n6, n7);		
		g.connect(n8, n9);
		
		assertTrue(g.hasRouteBFS(n1, n5));
		g.clearAll();
		
		Node r= g.searchRouteBFS(n1, n3);
		assertTrue(r != null);
		printRoute(r);
		g.clearAll();
		
		assertTrue(g.hasRouteBFS(n1, n5));
		g.clearAll();	
		
		assertTrue(g.hasRouteBFS(n6, n7));
		g.clearAll();
		assertTrue(g.hasRouteBFS(n8, n9));
		g.clearAll();
		
		assertFalse(g.hasRouteBFS(n6, n1));
		g.clearAll();		
		assertFalse(g.hasRouteBFS(n1, n6));
		g.clearAll();
		
		Stack<Node> route = g.searchRouteDFS(n1, n5);
		assertTrue(route != null);
		print(route);
		g.clearAll();
		
		r= g.searchRouteBFS(n1, n5);
		assertTrue(r != null);
		printRoute(r);
		
		g.clearAll();
		route = g.searchRouteDFS(n2, n3);
		assertTrue(route != null);
		print(route);
		
		g.clearAll();
		r= g.searchRouteBFS(n2, n3);
		assertTrue(r != null);
		printRoute(r);
		
		g.clearAll();
		if(g.dfs(n2, n3))
			printRoute(n3);
	}
	
	
}
