package qsbk.app.utils.audit;

public class LoaderException extends Exception {
    public LoaderException(String str, Throwable th) {
        super(str, th);
    }

    public LoaderException(String str) {
        super(str);
    }

    public LoaderException(Throwable th) {
        super(th);
    }
}
