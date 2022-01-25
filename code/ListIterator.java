import java.util.*;

public class ListIterator implements Iterator {
    private List myList;
    private int myPos;

    public ListIterator(List L) {
        this.myList = L;
        this.myPos = 0;
    }

    public boolean hasNext() {
        return (myPos < myList.size());
    }

    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        ++myPos;
        return myList.get(myPos - 1);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}