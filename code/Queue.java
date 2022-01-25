import java.util.EmptyStackException;

/**
 * A queue data structure that has a First-In-First-Out (FIFO) order. 
 * 
 * @author yossef-elmahdy
 * 
 */
public class Queue {
    private Object items[];
    private int numItems;
    private static final int INITSIZE = 3;
    private int frontIndex;
    private int rearIndex;

    public Queue() {
        this.items = new Object[INITSIZE];
        this.numItems = 0;
        this.frontIndex = 0;
        this.rearIndex = 0;
    }

    public boolean isEmpty() {
        return this.numItems == 0;
    }

    public boolean isFull() {
        return this.numItems == this.items.length;
    }

    public int size() {
        return this.numItems;
    }

    private void extendArray() {
        Object tmp[] = new Object[this.items.length * 2];
        int copyNum = this.numItems;
        int i = this.frontIndex;
        int j = this.frontIndex;
        while (copyNum != 0) {
            tmp[i] = this.items[j];
            ++i;
            ++j;
            --copyNum;

            if (j == this.items.length) {
                j = 0;
            }
        }

        this.rearIndex = i;
        this.items = tmp;
    }

    public void enqueue(Object item) throws EmptyQueueException {
        if (this.isFull()) {
            this.extendArray();
        }

        this.items[this.rearIndex] = item;
        ++this.numItems;
        ++this.rearIndex;

        if (this.rearIndex >= this.items.length) {
            this.rearIndex = 0;
        }
    }

    public Object dequeue() throws EmptyQueueException {
        if (!this.isEmpty()) {
            Object ret = this.items[this.frontIndex];
            --this.numItems;
            ++this.frontIndex;

            if (this.frontIndex >= this.items.length) {
                this.frontIndex = 0;
            }
            return ret;
        } else {
            throw new EmptyQueueException();
        }
    }
    
    public Object front() throws EmptyQueueException {
        if (!this.isEmpty()) {
            return this.items[this.frontIndex];
        } else {
            throw new EmptyQueueException();
        }
    }

}