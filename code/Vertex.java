
public class Vertex {
	int order; 
	Object data; 
	Edge firstEdge; 
	
	public Vertex(int order, Object data) {
		this.order = order; 
		this.data = data; 
		this.firstEdge = null; 
	}
	
	public Vertex(int order, Object data, Edge firstEdge) {
		this.order = order;
		this.data = data; 
		this.firstEdge = firstEdge; 
	}
	
	public boolean addEdge(int withVertex) {
	    if (this.firstEdge == null) {
	        this.firstEdge = new Edge(withVertex);
	        return true;
	    }

	    Edge current = this.firstEdge;
	    // first edge
	    if (current.getEndPoint() > withVertex) {
	        this.firstEdge = new Edge(withVertex);
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
	
	// Pre-Condition: Edges are ordered 
	public void updateEdges(int deletedVertex) {
		if (this.firstEdge == null) {
			return; 
		} else {
			Edge current = this.firstEdge; 
			if (current.getEndPoint() == deletedVertex) {
				this.firstEdge = current.getNextEdge(); 
			} 	
			else {
				while (current != null) {
					if (current.getNextEdge() != null && current.getNextEdge().getEndPoint() == deletedVertex) {
						current.setNextEdge(current.getNextEdge().getNextEdge());
					}
					if (current.getEndPoint() > deletedVertex) {
						current.setEndPoint(current.getEndPoint()-1);
					}
					
					current = current.getNextEdge();
				}
			}
		}
	}
	
	

}
