
public class EmptyQueueException extends Exception {
    public EmptyQueueException() {
        super("The Queue is Empty");
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}