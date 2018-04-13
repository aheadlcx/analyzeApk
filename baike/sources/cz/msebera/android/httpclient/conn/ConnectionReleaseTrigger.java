package cz.msebera.android.httpclient.conn;

import java.io.IOException;

public interface ConnectionReleaseTrigger {
    void abortConnection() throws IOException;

    void releaseConnection() throws IOException;
}
