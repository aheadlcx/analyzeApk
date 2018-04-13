package cz.msebera.android.httpclient;

public class MethodNotSupportedException extends HttpException {
    public MethodNotSupportedException(String str) {
        super(str);
    }

    public MethodNotSupportedException(String str, Throwable th) {
        super(str, th);
    }
}
