package cz.msebera.android.httpclient;

import java.io.IOException;

public class ConnectionClosedException extends IOException {
    public ConnectionClosedException(String str) {
        super(str);
    }
}
