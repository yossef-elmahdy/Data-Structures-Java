import java.util.*;

/**
 * A list data structure that has properties of the vector (open-end array). 
 * 
 * @author yossef-elmahdy
 * 
 */
public class List {
    private Object[] items = null;
    private int numItems = -1;
    private int listCurrentSize = 5; //change

    public List() {
        this.items = new Object[this.listCurrentSize];
        this.numItems = 0;
        System.out.println("A new list is created!");
    }

    public List(int init) {
        this.listCurrentSize = init;
        this.items = new Object[listCurrentSize];
        this.numItems = 0;
        System.out.println("A new list with inital allocation = " + init + " is created!");
    }

    public boolean isEmpty() {
        return this.numItems == 0;
    }

    public boolean isFull() {
        return this.numItems == this.listCurrentSize;
    }

    public int size() {
        return this.numItems;
    }

    private void extendArray() {
        this.listCurrentSize *= 2;
        Object[] tmp = new Object[this.listCurrentSize];
        for (int i = 0; i < this.numItems; ++i) {
            tmp[i] = this.items[i];
        }

        this.items = tmp.clone();
    }

    public void add(Object item) {
        if (this.isFull()) {
            this.extendArray();
        }
        this.items[this.numItems] = item;
        ++this.numItems;
    }

    public void addOrdered(Object item) {
        if (this.isFull()) {
            this.extendArray();
        }
        if (this.numItems == 0) {
            this.items[this.numItems] = item;
            ++this.numItems;
            return;
        }
        int i;
        for (i = 0; i < this.numItems; ++i) {
            if (this.compareTo(this.items[i], item) > 0)
                break;
        }
        this.add(item, i);
    }

    public boolean add(Object item, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > this.numItems) {
            throw new IndexOutOfBoundsException();
        } else {
            if (pos == this.numItems) {
                this.items[pos] = item;
                ++this.numItems;
            } else {
                // Copy the items 
                Object[] tmp = new Object[this.numItems];
                for (int i = pos, j = 0; i < this.numItems; ++i) {
                    tmp[j++] = this.items[i];
                }

                this.items[pos] = item;
                ++this.numItems;

                for (int i = pos + 1, j = 0; i < this.numItems; ++i) {
                    this.items[i] = tmp[j++];
                }

            }
            return true;
        }
    }
    
    public Object elementAt(int pos) {
    	if (!this.isEmpty() && pos < numItems) {
    		return this.items[pos]; 
    	}
    	throw new IndexOutOfBoundsException(); 
    }
    
    private void shrinkArray() {
        if ((this.listCurrentSize - this.numItems) > 10) {
            this.listCurrentSize -= 10;
        }
    }

    public boolean removeAt(int pos) {
        if (pos < 0 || pos >= this.numItems) {
            throw new IndexOutOfBoundsException();
        } else {
            if (pos == this.numItems - 1) {
                this.items[pos] = null;
            } else {
                for (int i = pos + 1; i < this.numItems; ++i) {
                    this.items[i - 1] = this.items[i];
                }
            }
            --this.numItems;
            this.shrinkArray();
            return true;
        }
    }
    
    public boolean remove(Object val) {
        for (int i = 0; i < this.numItems; ++i) {
            if (this.items[i].equals(val)) {
            	--this.numItems; 
                return this.removeAt(i);
            }
        }
        return false;
    }
    
    public void print() {
        if (!this.isEmpty()) {
            for (int i = 0; i < this.numItems; ++i) {
                System.out.println("Item " + i + ": " + this.items[i]);
            }
        } else {
            System.out.println("Empty List!");
        }
    }

    public Object get(int pos) {
        if (pos >= 0 && pos < this.numItems) {
            return this.items[pos];
        }
        return null;
    }
    
    public Object pop() {
        if (!this.isEmpty()) {
            Object ret = this.items[this.numItems - 1];
            this.items[this.numItems - 1] = null;
            --this.numItems;
            this.shrinkArray();
            return ret;
        }
        return null;
    }

    public void printType() {
        if (!this.isEmpty()) {
            if (this.items[0].getClass() == Integer.class) {
                System.out.println("Integer");
            } else if (this.items[0].getClass() == String.class) {
                System.out.println("String");
            } else if (this.items[0].getClass() == Float.class) {
                System.out.println("Float");
            } else if (this.items[0].getClass() == Boolean.class) {
                System.out.println("Boolean");
            }
        } else {
            System.out.println("Null");
        }
    }
    
    public Class getType() {
    	if (!this.isEmpty()) {
    		return this.items[0].getClass(); 
    	}
    	return null; 
    }

    public boolean contains(Object val) {
        for (int i = 0; i < this.numItems; ++i) {
            if (this.items[i] == val)
                return true;
        }
        return false;
    }

    public Iterator iterator() {
        return new ListIterator(this);
    }

    private int compareTo(Object a, Object b) {
        if (a.getClass() == String.class || a.getClass() == Character.class) {
            return a.toString().compareTo(b.toString());
        } else if (a.getClass() == Integer.class) {
            if ((int) a > (int) b)
                return 1;
            else if ((int) a < (int) b)
                return -1;
            else
                return 0;

        } else if (a.getClass() == Double.class) {
            if ((double) a > (double) b)
                return 1;
            else if ((double) a < (double) b)
                return -1;
            else
                return 0;
        }
        System.out.println("Can't compare");
        return 0;
    }

    public int searchBinary(Object val) {
        int left = 0;
        int right = this.numItems - 1;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (this.compareTo(this.items[middle], val) == 0) {
                return middle;
            } else if (this.compareTo(this.items[middle], val) > 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
    
    public int index(Object val) {
        for (int i = 0; i < this.numItems; ++i) {
            if (this.items[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    public int count(Object val) {
        int cnt = 0;
        for (int i = 0; i < this.numItems; ++i) {
            if (this.items[i].equals(val)) {
                ++cnt;
            }
        }
        return cnt;
    }
    
    public double sum() throws Exception {
    	if (this.getType() == Integer.class) {
        	double sum = 0;
        	for (int i = 0; i < this.numItems; ++i) {
        		sum += (int) this.items[i]; 
        	} 
        	return sum; 
    	} else if (this.getType() == Double.class)
    	{
        	double sum = 0;
        	for (int i = 0; i < this.numItems; ++i) {
        		sum += (double) this.items[i]; 
        	} 
        	return sum; 
    	}
    	throw new Exception("Invalid data type for arithmetic operations"); 
    }
    
    public Object max() throws Exception {
    	if (this.getType() == Integer.class || this.getType() == Double.class || this.getType() == Float.class) {
    		Object maxElement = Integer.MIN_VALUE;
    		for (int i = 0; i < this.numItems; ++i) {
    			if (this.compareTo(this.items[i], maxElement) > 0) {
    				maxElement = this.items[i]; 
    			}
    		}
    		return maxElement; 
    	}
    	throw new Exception("Invalid data type for arithmetic operations"); 
    }
    
    public Object min() throws Exception {
    	if (this.getType() == Integer.class || this.getType() == Double.class || this.getType() == Float.class) {
    		Object minElement = Integer.MAX_VALUE;
    		for (int i = 0; i < this.numItems; ++i) {
    			if (this.compareTo(this.items[i], minElement) < 0) {
    				minElement = this.items[i]; 
    			}
    		}
    		return minElement; 
    	}
    	throw new Exception("Invalid data type for arithmetic operations"); 
    }
}