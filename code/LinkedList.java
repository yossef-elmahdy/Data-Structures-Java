
/**
 * A doubly linked list data structure that has a list properties. 
 * 
 * @author yossef-elmahdy
 * 
 */
public class LinkedList {
    protected LinkedNode root;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public int size() {
        return this.size;
    }

    public void addLinear(int value) {
        LinkedNode element = new LinkedNode(value);
        if (this.root == null) {
            this.root = element;
        } else {
            LinkedNode current = this.root;
            while (current.next != null) {
                current = current.next;
            }
            current.next = element;
            element.prev = current;
        }
        ++this.size;
    }

    public void addOne(int value) {
        LinkedNode element = new LinkedNode(value);
        if (this.root == null) {
            this.root = element;
        } else {
            element.next = this.root;
            this.root.prev = element;
            this.root = element;
        }
        ++this.size;
    }

    public boolean remove(int value) {
        if (!this.isEmpty()) {
            LinkedNode current = this.root;
            while (current != null) {
                if (current.value == value) {
                    if (current.prev == null) // first element 
                    {
                        this.root = current.next;
                        //this.root.prev = null;
                    } else if (current.next == null) // last element
                    {
                        current.prev.next = null;
                        current.prev = null;
                    } else // middle element 
                    {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    --this.size;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
        return false;
    }

    public boolean search(int value) {
        LinkedNode current = this.root;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void print() {
        if (!this.isEmpty()) {
            LinkedNode current = this.root;
            int i = 0;
            while (current != null) {
                System.out.println("Item " + i++ + ": " + current.value);
                current = current.next;
            }
        } else {
            System.out.println("Empty Linked List!");
        }
    }

}