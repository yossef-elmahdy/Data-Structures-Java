
public class FullStackException extends Exception {
    public FullStackException() {
        super("The stack is full");
    }

    public FullStackException(String message) {
        super(message);
    }

}