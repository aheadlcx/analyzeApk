package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@NotThreadSafe
@Deprecated
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected ManagedClientConnection a;
    protected final boolean b;

    public BasicManagedEntity(HttpEntity httpEntity, ManagedClientConnection managedClientConnection, boolean z) {
        super(httpEntity);
        Args.notNull(managedClientConnection, "Connection");
        this.a = managedClientConnection;
        this.b = z;
    }

    public boolean isRepeatable() {
        return false;
    }

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.c.getContent(), this);
    }

    private void b() throws IOException {
        if (this.a != null) {
            try {
                if (this.b) {
                    EntityUtils.consume(this.c);
                    this.a.markReusable();
                } else {
                    this.a.unmarkReusable();
                }
                a();
            } catch (Throwable th) {
                a();
            }
        }
    }

    @Deprecated
    public void consumeContent() throws IOException {
        b();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
        b();
    }

    public void releaseConnection() throws IOException {
        b();
    }

    public void abortConnection() throws IOException {
        if (this.a != null) {
            try {
                this.a.abortConnection();
            } finally {
                this.a = null;
            }
        }
    }

    public boolean eofDetected(InputStream inputStream) throws IOException {
        try {
            if (this.a != null) {
                if (this.b) {
                    inputStream.close();
                    this.a.markReusable();
                } else {
                    this.a.unmarkReusable();
                }
            }
            a();
            return false;
        } catch (Throwable th) {
            a();
        }
    }

    public boolean streamClosed(InputStream inputStream) throws IOException {
        boolean isOpen;
        try {
            if (this.a != null) {
                if (this.b) {
                    isOpen = this.a.isOpen();
                    inputStream.close();
                    this.a.markReusable();
                } else {
                    this.a.unmarkReusable();
                }
            }
        } catch (SocketException e) {
            if (isOpen) {
                throw e;
            }
        } catch (Throwable th) {
            a();
        }
        a();
        return false;
    }

    public boolean streamAbort(InputStream inputStream) throws IOException {
        if (this.a != null) {
            this.a.abortConnection();
        }
        return false;
    }

    protected void a() throws IOException {
        if (this.a != null) {
            try {
                this.a.releaseConnection();
            } finally {
                this.a = null;
            }
        }
    }
}
