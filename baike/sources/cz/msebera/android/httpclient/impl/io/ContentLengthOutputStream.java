package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.OutputStream;

@NotThreadSafe
public class ContentLengthOutputStream extends OutputStream {
    private final SessionOutputBuffer a;
    private final long b;
    private long c = 0;
    private boolean d = false;

    public ContentLengthOutputStream(SessionOutputBuffer sessionOutputBuffer, long j) {
        this.a = (SessionOutputBuffer) Args.notNull(sessionOutputBuffer, "Session output buffer");
        this.b = Args.notNegative(j, "Content length");
    }

    public void close() throws IOException {
        if (!this.d) {
            this.d = true;
            this.a.flush();
        }
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.c < this.b) {
            long j = this.b - this.c;
            if (((long) i2) > j) {
                i2 = (int) j;
            }
            this.a.write(bArr, i, i2);
            this.c += (long) i2;
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(int i) throws IOException {
        if (this.d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.c < this.b) {
            this.a.write(i);
            this.c++;
        }
    }
}
