import java.util.ArrayList;

public class PriorityQueue {
	public ArrayList<Comparable> pQueue; 
	
	public PriorityQueue() {
		pQueue = new ArrayList<Comparable>(); 
	}
	
	public int size() {
		return this.pQueue.size(); 
	}
	
	public boolean isEmpty() {
		return this.size() == 0; 
	}
	
	public boolean isFull() {
		return false; 
	}
	
	public int parent(int node) {
		if (!this.isEmpty() && node < this.size()) {
			return (node-1)/2; 
		} else {
			return -1; 
		}
	}
	
	public int leftChild(int node) {
		if (!this.isEmpty() && node < this.size()) {
			return node*2+1; 
		} else {
			return -1; 
		}
	}
	
	public int rightChild(int node) {
		if (!this.isEmpty() && node < this.size()) {
			return node*2+2; 
		} else {
			return -1; 
		}
	}
	
	public void print() {
		if (!this.isEmpty()) {
			for (int i = 0; i < this.size(); ++i) {
				System.out.println(this.pQueue.get(i));
			}
		}
	}
	
	public Comparable top() throws EmptyQueueException {
		if (!this.isEmpty()) {
			return this.pQueue.get(0); 
		} else {
			throw new EmptyQueueException();
		}
	}
	
	public void push(Comparable item) {
		this.pQueue.add(item); 
		//this.heapifyUp(this.size()-1);
	}
	
	public void pop() {
		if (!this.isEmpty()) {
			this.pQueue.set(0, this.pQueue.get(this.size()-1));
			this.pQueue.remove(this.size()-1); 
			//this.heapifyDown(0); 
		}
	}
	
	
	private void heapifyUp(int node) {
		if (node == 0 || this.pQueue.get(parent(node)).compareTo(this.pQueue.get(node)) < 0) {
			return; 
		}
		
		Comparable tmp = this.pQueue.get(node); 
		this.pQueue.set(node, this.pQueue.get(this.parent(node))); 
		this.pQueue.set(this.parent(node), tmp); 
		
		this.heapifyUp(this.parent(node));
	}
	
	private void heapifyDown(int node) {
		int left = this.leftChild(node); 
		if (left == -1) {
			return; 
		}
		
		int right = this.rightChild(node); 
		if (right != -1 && (this.pQueue.get(right).compareTo(this.pQueue.get(left)) < 0)) {
			left = right; 
		}
		
		if (this.pQueue.get(node).compareTo(this.pQueue.get(left)) > 0) {
			Comparable tmp = this.pQueue.get(node); 
			this.pQueue.set(node, this.pQueue.get(left)); 
			this.pQueue.set(left, tmp); 
			
			this.heapifyDown(left);
		}
	}
}
