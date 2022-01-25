
public class WeightedVertex {
	Object data; 
	WeightedEdge firstEdge; 
	
	public WeightedVertex(Object data) {
		this.data = data; 
		this.firstEdge = null; 
	}
	
	public WeightedVertex(Object data, WeightedEdge firstEdge) {
		this.data = data; 
		this.firstEdge = firstEdge; 
	}
	
	public boolean addEdge(int withVertex, int weight) {
	    if (this.firstEdge == null) {
	        this.firstEdge = new WeightedEdge(withVertex, weight);
	        return true;
	    }

	    Edge current = this.firstEdge;
	    // first edge
	    if (current.getEndPoint() > withVertex) {
	        this.firstEdge = new WeightedEdge(withVertex, weight);
	        this.firstEdge.setNextEdge(current);
	    } else {
	        while (current.getNextEdge() != null) {
	            if (current.getNextEdge().getEndPoint() > withVertex) {
	                break;
	            }
	            current = current.getNextEdge();
	        }
	        if (current.getEndPoint() == withVertex) {
	            return false;
	        }
	        Edge e = new Edge(withVertex, current.getNextEdge());
	        current.setNextEdge(e);
	    }
	    return true;
	}
	
	// TODO updateEdges 
}
