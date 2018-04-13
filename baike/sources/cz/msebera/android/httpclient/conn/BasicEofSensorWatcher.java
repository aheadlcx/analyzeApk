package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
@Deprecated
public class BasicEofSensorWatcher implements EofSensorWatcher {
    protected final ManagedClientConnection a;
    protected final boolean b;

    public BasicEofSensorWatcher(ManagedClientConnection managedClientConnection, boolean z) {
        Args.notNull(managedClientConnection, "Connection");
        this.a = managedClientConnection;
        this.b = z;
    }

    public boolean eofDetected(InputStream inputStream) throws IOException {
        try {
            if (this.b) {
                inputStream.close();
                this.a.markReusable();
            }
            this.a.releaseConnection();
            return false;
        } catch (Throwable th) {
            this.a.releaseConnection();
        }
    }

    public boolean streamClosed(InputStream inputStream) throws IOException {
        try {
            if (this.b) {
                inputStream.close();
                this.a.markReusable();
            }
            this.a.releaseConnection();
            return false;
        } catch (Throwable th) {
            this.a.releaseConnection();
        }
    }

    public boolean streamAbort(InputStream inputStream) throws IOException {
        this.a.abortConnection();
        return false;
    }
}
