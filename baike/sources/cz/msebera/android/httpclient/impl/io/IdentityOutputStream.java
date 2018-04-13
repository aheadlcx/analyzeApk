package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.OutputStream;

@NotThreadSafe
public class IdentityOutputStream extends OutputStream {
    private final SessionOutputBuffer a;
    private boolean b = false;

    public IdentityOutputStream(SessionOutputBuffer sessionOutputBuffer) {
        this.a = (SessionOutputBuffer) Args.notNull(sessionOutputBuffer, "Session output buffer");
    }

    public void close() throws IOException {
        if (!this.b) {
            this.b = true;
            this.a.flush();
        }
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.a.write(bArr, i, i2);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(int i) throws IOException {
        if (this.b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.a.write(i);
    }
}
