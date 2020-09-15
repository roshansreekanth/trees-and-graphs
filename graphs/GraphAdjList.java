// Roshan Sreekanth - R00170592
import java.util.LinkedList;
import java.util.Iterator;

	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList  implements Graph {

	// ATTRIBUTES: 
	    
	 private int vertices;
	 private boolean isDirected;
	 private LinkedList<LinkedList<Edge>> graph;
	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */
	    
	 public GraphAdjList(int V, boolean directed) {
		this.vertices = V;
		this.isDirected = directed;
		this.graph = new LinkedList<LinkedList<Edge>>();
	 }

	 
	  // 1. IMPLEMENTATION METHOD numVerts: 
	  public int numVerts() { 
		return vertices;
     }

	  // 2. IMPLEMENTATION METHOD numEdges:
	  public int numEdges() { 
		
		if(this.isDirected)
		{
			return graph.size();
		}
		
		int selfLoopCounter = 0;
		for(int i = 0; i < graph.size(); i++)
		{
			LinkedList<Edge> collection = graph.get(i);
			if(collection.get(0).getVertex() == collection.get(1).getVertex())
			{
				selfLoopCounter++;
			}
		}
		return (graph.size()/2) + selfLoopCounter; // Self loops are only counted once
	}

	    
	  //  3. IMPLEMENTATION METHOD addEdge:
	  public void addEdge(int v1, int v2, int w) {
		//TO-DO
		if(v1 <= vertices - 1 && v2 <= vertices - 1)
		{
			removeEdge(v1, v2);	// Removes  the edge if it already exists so it is replaced by the new one
			LinkedList<Edge> connection = new LinkedList<Edge>();
			Edge edge1 = new Edge(v1, w);
			Edge edge2 = new Edge(v2, w);
			String arrow = " ---> ";

			connection.add(edge1);
			connection.add(edge2);
			graph.add(connection);
			
			if(v1 != v2 && !isDirected)
			{
				arrow = " ----";
				LinkedList<Edge> undirectedConnection = new LinkedList<Edge>();
				undirectedConnection.add(edge2);
				undirectedConnection.add(edge1);
				graph.add(undirectedConnection);
			}
			System.out.println("Added "+ v1 + arrow + v2  + " Weight: " + w);
		}
		
		else
		{
			System.out.println("Error! Cannot add edge with " + v1 + " and " + v2);
		}		
    }
	  
	 // 4. IMPLEMENTATION METHOD removeEdge: 
	 public void removeEdge(int v1, int v2) {
		
		for(int i = 0; i < graph.size(); i++)
		{
			LinkedList<Edge> collection = graph.get(i);
			if(collection.get(0).getVertex() == v1 && collection.get(1).getVertex() == v2)
			{
				graph.remove(i);
				System.out.println("Removed "+ v1 + " and " + v2 + " with weight " + collection.get(0).getWeight());
			}
			
		}

		if(!isDirected)
		{
			for(int i = 0; i < graph.size(); i++)
			{
				LinkedList<Edge> collection = graph.get(i);
				if(collection.get(1).getVertex() == v1 && collection.get(0).getVertex() == v2)
				{
					graph.remove(i);
					System.out.println("Removed "+ v2 + " and " + v1 + " with weight " + collection.get(0).getWeight());
				}
			}
		}
		
	 }
	 
	 // 5. IMPLEMENTATION METHOD hasEdge:
	 public boolean hasEdge(int v1, int v2) {
		 for(int i = 0; i < graph.size(); i++)
		{
			LinkedList<Edge> collection = graph.get(i);
			if(collection.get(0).getVertex() == v1 && collection.get(1).getVertex() == v2)
			{
				return true;
			}
		}
		 return false;
	 }

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	 public int getWeightEdge(int v1, int v2) {
		for(int i = 0; i < graph.size(); i++)
		{
			LinkedList<Edge> collection = graph.get(i);
			if(collection.get(0).getVertex() == v1 && collection.get(1).getVertex() == v2)
			{
				return collection.get(0).getWeight();
			}
		}
		return -1;
	 }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	 public LinkedList getNeighbors(int v) {
		 LinkedList<Integer> neighbors = new LinkedList<Integer>();

		 for(int i = 0; i < graph.size(); i++)
		 {
			 LinkedList<Edge> collection = graph.get(i);
			 if(collection.get(0).getVertex() == v)
			 {
				 neighbors.add(collection.get(1).getVertex());
			 }
			
		 }
		 return neighbors;
	 }

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		if(this.isDirected)
		{
			int inDegree = 0;
			int outDegree = 0;
			for(int i = 0; i < graph.size(); i++)
			{
				LinkedList<Edge> collection = graph.get(i);
				if(collection.get(0).getVertex() == v)
				{
					outDegree++;
				}
				if(collection.get(1).getVertex() == v)
				{
					inDegree++;
				}
			}
			return inDegree + outDegree;
		}
		return this.getNeighbors(v).size(); // For undirected graphs the direction of the relationship need not be considered
	}

	// 9. IMPLEMENTATION METHOD toString:
	 public String toString() {
		String graphString = "";
		String arrow = " ---- ";
		if(this.isDirected)
		{
			arrow = " ---> ";
		}

		for(LinkedList<Edge> collection : graph)
		{
			graphString += collection.get(0) + arrow + collection.get(1) + " Weight : " + collection.get(0).getWeight() + "\n";
		}
		return graphString;
	 }
}


