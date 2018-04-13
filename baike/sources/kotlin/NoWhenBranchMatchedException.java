package kotlin;

public class NoWhenBranchMatchedException extends RuntimeException {
    public NoWhenBranchMatchedException(String str) {
        super(str);
    }

    public NoWhenBranchMatchedException(String str, Throwable th) {
        super(str, th);
    }

    public NoWhenBranchMatchedException(Throwable th) {
        super(th);
    }
}
