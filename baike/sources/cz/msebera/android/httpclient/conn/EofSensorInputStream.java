package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
public class EofSensorInputStream extends InputStream implements ConnectionReleaseTrigger {
    protected InputStream a;
    private boolean b = false;
    private final EofSensorWatcher c;

    public EofSensorInputStream(InputStream inputStream, EofSensorWatcher eofSensorWatcher) {
        Args.notNull(inputStream, "Wrapped stream");
        this.a = inputStream;
        this.c = eofSensorWatcher;
    }

    protected boolean a() throws IOException {
        if (!this.b) {
            return this.a != null;
        } else {
            throw new IOException("Attempted read on closed stream.");
        }
    }

    public int read() throws IOException {
        if (!a()) {
            return -1;
        }
        try {
            int read = this.a.read();
            a(read);
            return read;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!a()) {
            return -1;
        }
        try {
            int read = this.a.read(bArr, i, i2);
            a(read);
            return read;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int available() throws IOException {
        int i = 0;
        if (a()) {
            try {
                i = this.a.available();
            } catch (IOException e) {
                c();
                throw e;
            }
        }
        return i;
    }

    public void close() throws IOException {
        this.b = true;
        b();
    }

    protected void a(int i) throws IOException {
        if (this.a != null && i < 0) {
            boolean z = true;
            try {
                if (this.c != null) {
                    z = this.c.eofDetected(this.a);
                }
                if (z) {
                    this.a.close();
                }
                this.a = null;
            } catch (Throwable th) {
                this.a = null;
            }
        }
    }

    protected void b() throws IOException {
        if (this.a != null) {
            boolean z = true;
            try {
                if (this.c != null) {
                    z = this.c.streamClosed(this.a);
                }
                if (z) {
                    this.a.close();
                }
                this.a = null;
            } catch (Throwable th) {
                this.a = null;
            }
        }
    }

    protected void c() throws IOException {
        if (this.a != null) {
            boolean z = true;
            try {
                if (this.c != null) {
                    z = this.c.streamAbort(this.a);
                }
                if (z) {
                    this.a.close();
                }
                this.a = null;
            } catch (Throwable th) {
                this.a = null;
            }
        }
    }

    public void releaseConnection() throws IOException {
        close();
    }

    public void abortConnection() throws IOException {
        this.b = true;
        c();
    }
}
