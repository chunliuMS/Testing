package TreesAndGraphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import org.junit.Test;


public class TopologicalSort {	
	
	public class Vertex {
		final private int id;
		
		public Vertex(int id) {
			this.id = id;			
		}
		public int getId() {
			return id;
		}
		
		@Override
		public int hashCode() {
			return id;
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
			return this.id == other.id;
		}

		@Override
		public String toString() {
			return Integer.toString(id);
		}		
	}
	
	public class Edge  {
		private final int from;
		private final int to;
				
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}		
		
		public int getFrom() {
			return from;
		}

		public int getTo() {
			return to;
		}
		
		@Override
		public int hashCode() {
			return from ^ to ;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			
			Edge other = (Edge)obj;			
			return this.from == other.from && this.to == other.to;
		}
		/*public int getWeight() {
			return weight;
		}*/
		
		@Override
		public String toString() {
			return Integer.toString(from) + "-" + Integer.toString(to);
		}		
	}
	
	public class Graph {
		private final HashSet<Vertex> vertexes = new HashSet<Vertex>();
		private final HashSet<Edge> edges = new HashSet<Edge>();

		public Graph() {			
		}
		
		public void add(int v) {
			vertexes.add(new Vertex(v));			
		}
		
		public void add(int from, int to){
			edges.add(new Edge(from, to));
		}
		
		public Vector<Vertex> getNodesWithoutIncomingEdges(){
			Vector<Vertex> vertices = new Vector<Vertex>();
			for(Vertex vertex : vertexes){
				boolean noIncomingEdges = true;
				for(Edge edge : edges){
					if (edge.to == vertex.id){
						noIncomingEdges = false;
						break;
					}
				}
				if (noIncomingEdges){
					vertices.add(vertex);
				}
			}
			return vertices;
		}
		
		public void Remove(Edge edge){
			edges.remove(edge);
		}
		
		public boolean noIncomingEdges(Vertex vertex){
			boolean noIncomingEdge = true;
			for(Edge edge : edges){
				if (edge.to == vertex.id){
					noIncomingEdge = false;
					break;
				}
			}
			
			return noIncomingEdge;
		}
		
		public List<Vertex> Sort(){
			List<Vertex> L = new ArrayList<Vertex>();
			
			Vector<Vertex> S = getNodesWithoutIncomingEdges();
			while(S.size() != 0){
				Vertex n = S.get(0);
				S.remove(0);
				vertexes.remove(n);
				L.add(n);		
				
				for(Vertex m : vertexes){		
					Vector<Edge> removed = new Vector<Edge>();					
					for (Edge e : edges){
						if (e.from == n.id && e.to == m.id){
							removed.add(e);							
						}
					}
					boolean hasRemoved = removed.size() > 0;
					if (hasRemoved){
						for (Edge e : removed){
							edges.remove(e);
						}
						
						if (noIncomingEdges(m)){
							S.add(m);
						}
					}
				}
			}			
			
			if (edges.size() > 0)
				return null;
			else	
				return L;
		}
		
		public Vertex getNodeWithoutIncomingEdges(){
			
			for(Vertex vertex : vertexes){
				boolean noIncomingEdges = true;
				for(Edge edge : edges){
					if (edge.to == vertex.id){
						noIncomingEdges = false;
						break;
					}
				}
				if (noIncomingEdges){
					return vertex;
				}
			}
			return null;
		}		
	}	
		
	@Test
	public void test() {		
		Graph graph = new Graph();
		for (int i = 0; i < 10; i++)
			graph.add(i);
		
		graph.add(0, 1);
		graph.add(0, 2);
		graph.add(1, 3);
		graph.add(1, 2);
		graph.add(3, 5);
		graph.add(4, 5);
		graph.add(5, 6);
		graph.add(2, 3);
		graph.add(3, 4);
		
		//List<Vertex> orders =  graph.Sort();
		List<Vertex> orders =  graph.Sort();
		for(Vertex v : orders)
		{
			System.out.print("\t" + Integer.toString(v.id));
		}		
	}
}
