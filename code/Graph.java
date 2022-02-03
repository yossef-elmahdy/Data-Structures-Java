import java.util.ArrayList;


public class Graph {
	private int numVertices; 
	private ArrayList<Vertex> vertices; 
	
	public Graph() {
		this.numVertices = 0; 
		this.vertices = new ArrayList<Vertex>();  
	}
	
	public int numVertices() {
		return this.numVertices; 
	}
	
	// Post-Condition: add ordered vertices (i.e. 0, 1, 2, ...).
	// Return the index of the vertex (zero-based).  
	public void addVertex(Object data) {
		Vertex vertex = new Vertex(this.numVertices, data); 
		this.vertices.add(vertex);
		++this.numVertices;  
	}
	
	// Pre-Condition: assume ordered vertices (i.e. 0, 1, 2, ...).
	public boolean addDirectedEdge(int fromVertex, int toVertex) {
		if ((fromVertex >= 0 && fromVertex < this.numVertices) 
				&& (toVertex < this.numVertices && toVertex >= 0)) {
			return this.vertices.get(fromVertex).addEdge(toVertex); 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	// Pre-Condition: assume ordered vertices (i.e. 0, 1, 2, ...).
	public boolean addUndirectedEdge(int vertex1, int vertex2) {
		if ((vertex1 >= 0 && vertex1 < this.numVertices) && 
				(vertex2 >= 0 && vertex2 < this.numVertices)) {
			return ( this.vertices.get(vertex1).addEdge(vertex2)) 
					&& (this.vertices.get(vertex2).addEdge(vertex1) ); 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	public boolean removeVertex(int vertexNum) {
		if (vertexNum >= 0 && vertexNum < this.numVertices) {
			this.vertices.remove(vertexNum); 
			--this.numVertices; 
			for (int i = 0; i < this.numVertices; ++i) {
				this.vertices.get(i).updateEdges(vertexNum);
			}
			return true;
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	private void DFS(int order, boolean visited[]) {
		System.out.println("("+ order + ": " + 
				this.vertices.get(order).data + ")");
		visited[order] = true;
		
		Edge current = this.vertices.get(order).firstEdge; 
		int endPoint = -1;
		while (current != null) {
			endPoint = current.getEndPoint();
			if (!visited[endPoint]) {
				this.DFS(endPoint, visited);
			}
			current = current.getNextEdge(); 
		}
	}
	
	public void traverseDepthFirst(int startVertex) {
		boolean[] visited = new boolean[this.numVertices]; 
		this.DFS(startVertex, visited);
		
		// for isolated vertices 
		for (int i = 0; i < this.numVertices; ++i) {
			if (!visited[i]) {
				this.DFS(i, visited);
			}
		}
	}
	
	private void BFS(int order, boolean visited[]) {
		if (!visited[order]) {
			System.out.println("("+ order + ": " + 
					this.vertices.get(order).data + ")");
			visited[order] = true;
		}
		
		Edge current = this.vertices.get(order).firstEdge; 
		Queue q = new Queue(); 
		int endPoint = -1;
		while (current != null) {
			endPoint = current.getEndPoint(); 
			if (!visited[endPoint]) {
				System.out.println("("+ endPoint + ": " + 
						this.vertices.get(endPoint).data + ")"); 
				visited[endPoint] = true; 
				try {
					q.enqueue(endPoint);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			current = current.getNextEdge(); 
		}
		
		if (q.isEmpty()) {
			return; 
		}
		try {
			this.BFS((int) q.dequeue(), visited);
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
	
	public void traverseBredthFirst(int startVertex) {
		boolean[] visited = new boolean[this.numVertices]; 
		this.BFS(startVertex, visited);
		
		// for isolated vertices 
		for (int i = 0; i < this.numVertices; ++i) {
			if (!visited[i]) {
				this.BFS(i, visited);
			}
		}
	}
	
	private void DFSPath(int order, boolean visited[]) {
		visited[order] = true;
		Edge current = this.vertices.get(order).firstEdge; 
		int endPoint = -1;
		while (current != null) {
			endPoint = current.getEndPoint();
			if (!visited[endPoint]) {
				this.DFSPath(endPoint, visited);
			}
			current = current.getNextEdge(); 
		}
	}
	
	public boolean findPath(int fromVertex, int toVertex) {
		if ((fromVertex >= 0 && fromVertex < this.numVertices) 
				&& (toVertex >= 0 && toVertex < this.numVertices)) {
			boolean[] visited = new boolean[this.numVertices];
			this.DFSPath(fromVertex, visited);
			return visited[toVertex]; 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	public int numComponents() {
		boolean[] visited = new boolean[this.numVertices]; 
		int cnt = 0;
		for (int vertex = 0; vertex < this.numVertices; ++vertex) {
			if (!visited[vertex]) {
				++cnt; 
				this.DFSPath(vertex, visited);
			}
		}
		return cnt; 
	}
	
	public void Components() {
		boolean[] visited = new boolean[this.numVertices]; 
		int cnt = 0;
		for (int vertex = 0; vertex < this.numVertices; ++vertex) {
			if (!visited[vertex]) {
				System.out.println("Connection #" + (++cnt) + " starting from vertex " + vertex + ": ");
				this.DFS(vertex, visited);
			}
		}
	}
	
	public boolean isCyclic(int vertex) {
		if (vertex >= 0 && vertex < this.numVertices) {
			boolean[] visited = new boolean[this.numVertices];
			Edge start = this.vertices.get(vertex).firstEdge; 
			if (start != null) {
				this.DFSPath(start.getEndPoint(), visited);
			}
			return visited[vertex]; 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	
	public boolean containCycle() {
		for (int vertex = 0; vertex < this.numVertices; ++vertex) {
			if (this.isCyclic(vertex)) {
				return true; 
			}
		}
		return false;
	}
	
	public int degree(int vertex) {
		if (vertex >= 0 && vertex < this.numVertices) {
			int cnt = 0; 
			Edge current = this.vertices.get(vertex).firstEdge; 
			while (current != null) {
				++cnt; 
				current = current.getNextEdge(); 
			} 
			return cnt; 
		} else {
			throw new ArrayIndexOutOfBoundsException(); 
		}
	}
	


}
