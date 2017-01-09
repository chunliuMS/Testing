package TreesAndGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class SSSP {	
	
	public class Vertex {
		final private String id;
		final private String name;
		
		
		public Vertex(String id, String name) {
			this.id = id;
			this.name = name;
		}
		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return name;
		}
		
	}
	
	public class Edge  {
		private final String id; 
		private final Vertex source;
		private final Vertex destination;
		private final int weight; 
		
		public Edge(String id, Vertex source, Vertex destination, int weight) {
			this.id = id;
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
		
		public String getId() {
			return id;
		}
		public Vertex getDestination() {
			return destination;
		}

		public Vertex getSource() {
			return source;
		}
		public int getWeight() {
			return weight;
		}
		
		@Override
		public String toString() {
			return source + " " + destination;
		}		
	}
	
	public class Graph {
		private final List<Vertex> vertexes;
		private final List<Edge> edges;

		public Graph(List<Vertex> vertexes, List<Edge> edges) {
			this.vertexes = vertexes;
			this.edges = edges;
		}

		public List<Vertex> getVertexes() {
			return vertexes;
		}

		public List<Edge> getEdges() {
			return edges;
		}
	}

	
	public class DijkstraAlgorithm {

		private final List<Vertex> nodes;
		private final List<Edge> edges;
		private Set<Vertex> settledNodes;
		private Set<Vertex> unSettledNodes;
		private Map<Vertex, Vertex> predecessors;
		private Map<Vertex, Integer> distance;

		public DijkstraAlgorithm(Graph graph) {
			// Create a copy of the array so that we can operate on this array
			this.nodes = new ArrayList<Vertex>(graph.getVertexes());
			this.edges = new ArrayList<Edge>(graph.getEdges());
		}

		public void execute(Vertex source) {
			settledNodes = new HashSet<Vertex>();
			unSettledNodes = new HashSet<Vertex>();
			distance = new HashMap<Vertex, Integer>();
			predecessors = new HashMap<Vertex, Vertex>();
			distance.put(source, 0);
			unSettledNodes.add(source);
			while (unSettledNodes.size() > 0) {
				Vertex node = getMinimum(unSettledNodes);
				settledNodes.add(node);
				unSettledNodes.remove(node);
				findMinimalDistances(node);
			}
		}

		private void findMinimalDistances(Vertex node) {
			List<Vertex> adjacentNodes = getNeighbors(node);
			for (Vertex target : adjacentNodes) {
				if (getShortestDistance(target) > getShortestDistance(node)
						+ getDistance(node, target)) {
					distance.put(target, getShortestDistance(node)
							+ getDistance(node, target));
					predecessors.put(target, node);
					unSettledNodes.add(target);
				}
			}

		}

		private int getDistance(Vertex node, Vertex target) {
			for (Edge edge : edges) {
				if (edge.getSource().equals(node)
						&& edge.getDestination().equals(target)) {
					return edge.getWeight();
				}
			}
			throw new RuntimeException("Should not happen");
		}

		private List<Vertex> getNeighbors(Vertex node) {
			List<Vertex> neighbors = new ArrayList<Vertex>();
			for (Edge edge : edges) {
				if (edge.getSource().equals(node)
						&& !isSettled(edge.getDestination())) {
					neighbors.add(edge.getDestination());
				}
			}
			return neighbors;
		}

		private Vertex getMinimum(Set<Vertex> vertexes) {
			Vertex minimum = null;
			for (Vertex vertex : vertexes) {
				if (minimum == null) {
					minimum = vertex;
				} else {
					if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
						minimum = vertex;
					}
				}
			}
			return minimum;
		}

		private boolean isSettled(Vertex vertex) {
			return settledNodes.contains(vertex);
		}

		private int getShortestDistance(Vertex destination) {
			Integer d = distance.get(destination);
			if (d == null) {
				return Integer.MAX_VALUE;
			} else {
				return d;
			}
		}

		/*
		 * This method returns the path from the source to the selected target and
		 * NULL if no path exists
		 */
		public LinkedList<Vertex> getPath(Vertex target) {
			LinkedList<Vertex> path = new LinkedList<Vertex>();
			Vertex step = target;
			// Check if a path exists
			if (predecessors.get(step) == null) {
				return null;
			}
			path.add(step);
			while (predecessors.get(step) != null) {
				step = predecessors.get(step);
				path.add(step);
			}
			// Put it into the correct order
			Collections.reverse(path);
			return path;
		}
		
		void printPath(int index){
			LinkedList<Vertex> path = getPath(nodes.get(index));
			
			if (path != null && path.size() > 0){
				System.out.println("Path to " + Integer.toString(index) + " are:");
				for (Vertex vertex : path) {
					System.out.println(vertex);
				}
			}
			else
				System.out.println("Failed to find path to " + Integer.toString(index));
		}

	}

	
	private List<Vertex> nodes;
	private List<Edge> edges;
	
	private void addLane(int sourceLocNo, int destLocNo, int duration) {
		{
			String laneId = Integer.toString(sourceLocNo) + "-" + Integer.toString(destLocNo);
			Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
			edges.add(lane);
		}
		{
			String laneId = Integer.toString(destLocNo) + "-" + Integer.toString(sourceLocNo);
			Edge lane = new Edge(laneId,nodes.get(destLocNo), nodes.get(sourceLocNo), duration );
			edges.add(lane);
		}
	}

	
	public class Node implements Comparable<Node> {
		   
		   final int name;
		   boolean visited = false;   // used for Kosaraju's algorithm and Edmonds's algorithm
		   int lowlink = -1;          // used for Tarjan's algorithm
		   int index = -1;            // used for Tarjan's algorithm
		   
		   public Node(final int argName) {
		       name = argName;
		   }
		   
		   public int compareTo(final Node argNode) {
		       return argNode == this ? 0 : -1;
		   }
		}

	public class Edge1 implements Comparable<Edge> {
		   
		   final Node from, to; 
		   final int weight;
		   
		   public Edge1(final Node argFrom, final Node argTo, final int argWeight){
		       from = argFrom;
		       to = argTo;
		       weight = argWeight;
		   }
		   
		   public int compareTo(final Edge argEdge){
		       return weight - argEdge.weight;
		   }
		}


	
	public class Dijkstra {
		   // assumes Nodes are numbered 0, 1, ... n and that the source Node is 0
		   ArrayList<Node> findShortestPath(Node[] nodes, Edge1[] edges, Node target) {
		       int[][] Weight = initializeWeight(nodes, edges);
		       int[] D = new int[nodes.length];
		       Node[] P = new Node[nodes.length];
		       ArrayList<Node> C = new ArrayList<Node>();
		       // initialize:
		       // (C)andidate set,
		       // (D)yjkstra special path length, and
		       // (P)revious Node along shortest path
		       for(int i=0; i<nodes.length; i++){
		           C.add(nodes[i]);
		           D[i] = Weight[0][i];
		           if(D[i] != Integer.MAX_VALUE){
		               P[i] = nodes[0];
		           }
		       }
		       // crawl the graph
		       for(int i=0; i<nodes.length; i++){
		           // find the lightest Edge among the candidates
		           int l = Integer.MAX_VALUE;
		           Node n = nodes[0];
		           for(Node j : C){
		               if(D[j.name] < l){
		                   n = j;
		                   l = D[j.name];
		               }
		           }
		           C.remove(n);
		           // see if any Edges from this Node yield a shorter path than from source->that Node
		           for(int j=0; j<nodes.length; j++){
		               if(D[n.name] != Integer.MAX_VALUE && Weight[n.name][j] != Integer.MAX_VALUE && D[n.name]+Weight[n.name][j] < D[j]){
		                   // found one, update the path
		                   D[j] = D[n.name] + Weight[n.name][j];
		                   P[j] = n;
		               }
		           }
		       }
		       // we have our path. reuse C as the result list
		       C.clear();
		       int loc = target.name;
		       C.add(target);
		       // backtrack from the target by P(revious), adding to the result list
		       while(P[loc] != nodes[0]){
		           if(P[loc] == null){
		               // looks like there's no path from source to target
		               return null;
		           }
		           C.add(0, P[loc]);
		           loc = P[loc].name;
		       }
		       C.add(0, nodes[0]);
		       
		       StringBuilder sb = new StringBuilder("The path to " + Integer.toString(target.name) + " are:\n" );
		      for (Node n : C){
		    	  sb.append("\t").append(Integer.toString(n.name));
		      }
		      System.out.println(sb.toString());
		       return C;
		   }
		   private int[][] initializeWeight(Node[] nodes, Edge1[] edges){
		       int[][] Weight = new int[nodes.length][nodes.length];
		       for(int i=0; i<nodes.length; i++){
		           Arrays.fill(Weight[i], Integer.MAX_VALUE);
		       }
		       for(Edge1 e : edges){
		           Weight[e.from.name][e.to.name] = e.weight;
		       }
		       return Weight;
		   }
		}

	
		
	@Test
	public void test() {		
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 10; i++) {
			Vertex location = new Vertex("N" + i, "Node_" + i);
			nodes.add(location);
		}

		addLane(0, 1, 1);
		addLane(0, 7, 6);
		addLane(0, 8, 3);
		addLane(1, 2, 3);
		addLane(1, 9, 4);
		addLane(2, 3, 7);
		addLane(3, 9, 2);
		addLane(4, 9, 4);
		addLane(5, 8, 3);
		addLane(5, 6, 5);
		addLane(6, 8, 1);
		addLane(8, 9, 6);
		
		
		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(0));
		
		dijkstra.printPath(2);
		dijkstra.printPath(3);
		dijkstra.printPath(4);
		dijkstra.printPath(5);
		dijkstra.printPath(6);
		dijkstra.printPath(7);
		dijkstra.printPath(8);
		dijkstra.printPath(9);		
		
		
		Node[] nodes = new Node[10];
		for (int i = 0; i < nodes.length; i++){
			nodes[i] = new Node(i);
		}
		Edge1[] edge1s =  new Edge1[24];
		edge1s[0] = new Edge1(nodes[0], nodes[1], 1);
		edge1s[1] = new Edge1(nodes[0], nodes[7], 6);		
		edge1s[2] = new Edge1(nodes[0], nodes[8], 3);
		edge1s[3] = new Edge1(nodes[1], nodes[2], 3);		
		edge1s[4] = new Edge1(nodes[1], nodes[9], 4);
		edge1s[5] = new Edge1(nodes[2], nodes[3], 7);		
		edge1s[6] = new Edge1(nodes[3], nodes[9], 2);
		edge1s[7] = new Edge1(nodes[4], nodes[9], 4);		
		edge1s[8] = new Edge1(nodes[5], nodes[8], 3);
		edge1s[9] = new Edge1(nodes[5], nodes[6], 5);
		edge1s[10] = new Edge1(nodes[6], nodes[8], 1);
		edge1s[11] = new Edge1(nodes[8], nodes[9], 6);
		
		edge1s[12] = new Edge1(nodes[1], nodes[0], 1);
		edge1s[13] = new Edge1(nodes[7], nodes[0], 6);		
		edge1s[14] = new Edge1(nodes[8], nodes[0], 3);
		edge1s[15] = new Edge1(nodes[2], nodes[1], 3);		
		edge1s[16] = new Edge1(nodes[9], nodes[1], 4);
		edge1s[17] = new Edge1(nodes[3], nodes[2], 7);		
		edge1s[18] = new Edge1(nodes[9], nodes[3], 2);
		edge1s[19] = new Edge1(nodes[9], nodes[4], 4);		
		edge1s[20] = new Edge1(nodes[8], nodes[5], 3);
		edge1s[21] = new Edge1(nodes[6], nodes[5], 5);
		edge1s[22] = new Edge1(nodes[8], nodes[6], 1);
		edge1s[23] = new Edge1(nodes[9], nodes[8], 6);
		
		Dijkstra di = new Dijkstra();
		di.findShortestPath(nodes, edge1s, nodes[2]);
		di.findShortestPath(nodes, edge1s, nodes[3]);
		di.findShortestPath(nodes, edge1s, nodes[4]);
		di.findShortestPath(nodes, edge1s, nodes[5]);
		di.findShortestPath(nodes, edge1s, nodes[6]);
		di.findShortestPath(nodes, edge1s, nodes[7]);
		di.findShortestPath(nodes, edge1s, nodes[8]);
		di.findShortestPath(nodes, edge1s, nodes[9]);
				
	}
}
