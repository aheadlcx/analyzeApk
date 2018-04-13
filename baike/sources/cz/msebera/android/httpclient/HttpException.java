package cz.msebera.android.httpclient;

public class HttpException extends Exception {
    public HttpException(String str) {
        super(str);
    }

    public HttpException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
