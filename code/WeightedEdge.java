
public class WeightedEdge extends Edge {
	private int weight = 0; 
	
	public WeightedEdge(int endPoint, int weight) {
		super(endPoint);
		if (weight >= 0) {
			this.weight = weight;
		} 
	}
	
	public WeightedEdge(int endPoint, int weight, Edge nextEdge) {
		super(endPoint, nextEdge);
		if (weight >= 0) {
			this.weight = weight;
		}
	}
	
	public int getWeight() {
		return this.weight; 
	}
	
	// Pre-Condition: zero & positive weights are allowed only  
	public void setWeight(int weight) {
		if (weight >= 0) {
			this.weight = weight;
		}
	}
}
