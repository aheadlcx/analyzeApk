package cz.msebera.android.httpclient.client.cache;

public class HttpCacheUpdateException extends Exception {
    public HttpCacheUpdateException(String str) {
        super(str);
    }

    public HttpCacheUpdateException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
