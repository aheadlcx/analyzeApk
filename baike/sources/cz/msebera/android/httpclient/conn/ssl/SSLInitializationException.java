package cz.msebera.android.httpclient.conn.ssl;

public class SSLInitializationException extends IllegalStateException {
    public SSLInitializationException(String str, Throwable th) {
        super(str, th);
    }
}
