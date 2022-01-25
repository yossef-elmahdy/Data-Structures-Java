import java.util.EmptyStackException;

/**
 * A stack data structure that has a Last-In-First-Out (LIFO) order. 
 * 
 * @author yossef-elmahdy
 * 
 */
public class Stack {
    public Object[] items;
    private int sp;
    private static final int INITSIZE = 3;

    public Stack() {
        this.items = new Object[INITSIZE];
        this.sp = 0;
    }

    public boolean isEmpty() {
        return this.sp == 0;
    }

    public boolean isFull() {
        return this.sp == this.items.length;
    }

    public int size() {
        return sp;
    }

    private void expandArray() {
        Object tmp[] = new Object[this.items.length * 2];
        System.arraycopy(this.items, 0, tmp, 0, this.items.length);
        this.items = tmp;
    }

    private void shrinkArray() {
        if ((this.items.length - this.sp) > this.INITSIZE) {
            Object tmp[] = new Object[this.items.length - this.INITSIZE];
            System.arraycopy(this.items, 0, tmp, 0, this.items.length - this.INITSIZE);
            this.items = tmp;
        }
    }

    public void push(Object num) {
        if (this.isFull())
            this.expandArray();
        
        this.items[this.sp] = num;
        ++this.sp;
    }

    public Object pop() {
        if (!this.isEmpty()) {
            --this.sp;
            Object ret = this.items[sp];
            this.items[sp] = null;
            this.shrinkArray();
            return ret;
        } else {
            throw new EmptyStackException();
        }
    }

    public Object top() {
        if (!this.isEmpty()) {
            return this.items[sp - 1];
        } else {
            throw new EmptyStackException();
        }
    }

}