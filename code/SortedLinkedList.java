
/**
 * A sorted doubly linked list data structure that has a linked list properties plus the being always
 * sorted.  
 * 
 * @author yossef-elmahdy
 * 
 */
public class SortedLinkedList extends LinkedList {

    public void add(int value) {
        LinkedNode element = new LinkedNode(value);
        if (super.root.next == null) {
            super.root.next = element;
            element.prev = super.root;
        } else {
            LinkedNode current = super.root;
            boolean fin = true;
            while (current.next != null) {
                if (current.value > element.value) {
                    fin = false;
                    element.next = current;
                    element.prev = current.prev;
                    current.prev.next = element;
                    current.prev = element;
                }
                current = current.next;
            }
            if (fin) {
                current.next = element;
                element.prev = current;
            }
        }
    }

}