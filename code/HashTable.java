import java.util.ArrayList;

public class HashTable {
	private ArrayList<LinkedList> hTable; 
	private int size; 
	private static final int TABLE_SIZE = 100; 
	private static final int HASH_CONST = 12345; 
	
	public HashTable() {
		hTable = new ArrayList<LinkedList>(TABLE_SIZE);
		this.size = 0; 
		for (int i = 0; i < TABLE_SIZE; ++i) {  
			this.hTable.add(new LinkedList()); 
		}
	}
	
	public int size() {
		return this.size; 
	}
	
	public boolean isEmpty() {
		return this.size == 0; 
	}
	
	private int hash(int key) {
	    if (key < 0) {
	    	return 0; 
	    }
		return key*HASH_CONST % TABLE_SIZE;
	}
	
	public int effectiveHash(Object key) { 
		return key.hashCode(); 
	}
	
	public void insert(int key, int value) { 
		int hashedKey = this.hash(key); 
		this.hTable.get(hashedKey).addOne(value); 
		++this.size; 
	}
	
	public boolean lookup(int key, int value) { 
		int hashedKey = this.hash(key); 
		if (this.hTable.get(hashedKey) == null) {
			return false; 
		}
		return this.hTable.get(hashedKey).search(value);  
	}
	
	public void remove(int key, int value) { 
		int hashedKey = this.hash(key); 
		if (this.hTable.get(hashedKey) == null) {
			return; 
		}
		this.hTable.get(hashedKey).remove(value); 
		--this.size; 
	}
}
