import java.util.ArrayList;

public class WeightedGraph extends Graph {
	private int numVertices; 
	private ArrayList<WeightedVertex> vertices; 
	
	public WeightedGraph() {
		this.numVertices = 0; 
		this.vertices = new ArrayList<WeightedVertex>();  
	}
	
	// Pre-Condition: assume ordered vertices (i.e. 0, 1, 2, ...).
	public boolean addDirectedEdge(int fromVertex, int toVertex, int weight) {
		if ((fromVertex >= 0 && fromVertex < this.numVertices) 
				&& (toVertex < this.numVertices && toVertex >= 0)) {
			return this.vertices.get(fromVertex).addEdge(toVertex, weight); 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	// Pre-Condition: assume ordered vertices (i.e. 0, 1, 2, ...).
	public boolean addUndirectedEdge(int vertex1, int vertex2, int weight) {
		if ((vertex1 >= 0 && vertex1 < this.numVertices) && 
				(vertex2 >= 0 && vertex2 < this.numVertices)) {
			return ( this.vertices.get(vertex1).addEdge(vertex2, weight)) 
					&& (this.vertices.get(vertex2).addEdge(vertex1, weight) ); 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
}
