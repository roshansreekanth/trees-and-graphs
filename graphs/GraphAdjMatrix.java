// Roshan Sreekanth - R00170592
import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

 
public class GraphAdjMatrix implements Graph{

    // ATTRIBUTES: 
    int vertices;
    int[][] matrix;
    boolean isDirected;
    //TO-DO
 
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) 
    {
        this.vertices = V;
        this.matrix = new int[vertices][vertices];
        for(int row = 0; row < vertices; row++)
        {
            for(int column = 0 ; column < vertices; column++)
            {
                matrix[row][column] = -1;
            }
        }

        this.isDirected = directed;
    }


    // 1. IMPLEMENTATION METHOD numVerts: 
    public int numVerts() 
    { 
        //TO-DO
        return vertices;
    }
    
   
    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() 
    { 
        int counter = 0;
        for(int row = 0; row < vertices; row++)
        {
            for(int column = 0; column < vertices; column++)
            {
                if(matrix[row][column] > -1)
                {
                   counter++;
                }
            }
        }
        
        if(this.isDirected)
        {
            return counter;
        }
        int selfLoopCounter = 0;
        for(int row = 0; row < vertices; row++)
        {
            for(int column = 0; column < vertices; column++)
            {
                if(row == column && matrix[row][column] > -1)
                {
                   selfLoopCounter++;
                }
            }
        }
        return (counter/2) + selfLoopCounter; // Self loops are only counted once
    }


   //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) 
    {
        if(v1 <= vertices - 1 && v2 <= vertices- 1)
        {
            String arrow = " ---> ";
          
            matrix[v1][v2] = w;
            if(!isDirected)
            {
                arrow = " ---- ";
                matrix[v2][v1] = w; // This also automatically replaces the weights if the relationship already exists
            }
            System.out.println("Added "+ v1 + arrow + v2  + " Weight: " + w);
        }
        else
        {
            System.out.println("Error! Cannot add edge with " + v1 + " and " + v2);
        }
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2)
   {
      if(v1 <= vertices - 1 && v2 <= vertices - 1)
      {
        matrix[v1][v2] = -1;
        if(!isDirected)
        {
            matrix[v2][v1] = -1;
            System.out.println("Removed edge "+ v2 + " and " + v1);
        }
        System.out.println("Removed edge "+ v1 + " and " + v2);
    }
      else
      {
          System.out.println("Error! Cannot remove edge with " + v1 +  " and " + v2);
      }
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        return matrix[v1][v2] > -1;
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
        return matrix[v1][v2];
	}

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v)
	{
        LinkedList<Integer> neighbors = new LinkedList<Integer>();
        for(int column = 0; column < vertices; column++)
        {
            if(matrix[v][column] > -1)
                neighbors.add(column);
        }
        return neighbors;
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) 
	{
       //TO-DO
       if(this.isDirected)
       {
        int inDegree = 0;
        int outDegree = 0;
        for(int vertice = 0; vertice < vertices; vertice++)
        {
            if(matrix[v][vertice] > -1)
            {
                outDegree++;
            }
            if(matrix[vertice][v] > -1)
            {
                inDegree++;
            }
        }
        return inDegree + outDegree;
       }
       return this.getNeighbors(v).size(); // if it is an undirected graph the direction need not be considered
	}
	

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString()
    {
        String graphString = "";
        String arrow = " ---- ";
        if(this.isDirected)
        {
            arrow = " ---> ";
        }

        for(int row = 0; row < vertices; row++)
        {
            for(int column = 0; column < vertices; column++)
            {
                if(matrix[row][column] > -1)
                {
                     graphString += row + arrow + column + " Weight: " + matrix[row][column] + "\n";
                }
            }
        }
        return graphString;
    }    
}