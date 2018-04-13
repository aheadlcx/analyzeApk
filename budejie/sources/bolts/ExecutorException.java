package bolts;

public class ExecutorException extends RuntimeException {
    public ExecutorException(Exception exception) {
        super("An exception was thrown by an Executor", exception);
    }
}
