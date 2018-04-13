package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.annotation.Immutable;

@Immutable
public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    public ConnectionPoolTimeoutException(String str) {
        super(str);
    }
}
