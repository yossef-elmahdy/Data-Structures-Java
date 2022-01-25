
public class Edge {
	private int endPoint; 
	private Edge nextEdge; 
	
 	public Edge(int endPoint) {
		this.endPoint = endPoint;
		this.nextEdge = null; 
	}
	
	public Edge(int endPoint, Edge nextEdge) {
		this.endPoint = endPoint; 
		this.nextEdge = nextEdge; 
	}
	
	public int getEndPoint() {
		return this.endPoint; 
	}
	
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}
	
	public Edge getNextEdge() {
		return this.nextEdge; 
	}
	
	public void setNextEdge(Edge nextEdge) {
		this.nextEdge = nextEdge; 
	}
	
}
