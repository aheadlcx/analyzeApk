package cz.msebera.android.httpclient;

public interface ExceptionLogger {
    public static final ExceptionLogger NO_OP = new a();
    public static final ExceptionLogger STD_ERR = new b();

    void log(Exception exception);
}
