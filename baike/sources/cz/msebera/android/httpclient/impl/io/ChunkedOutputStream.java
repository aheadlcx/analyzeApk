package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import java.io.IOException;
import java.io.OutputStream;

@NotThreadSafe
public class ChunkedOutputStream extends OutputStream {
    private final SessionOutputBuffer a;
    private final byte[] b;
    private int c;
    private boolean d;
    private boolean e;

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer, int i) throws IOException {
        this(i, sessionOutputBuffer);
    }

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer) throws IOException {
        this(2048, sessionOutputBuffer);
    }

    public ChunkedOutputStream(int i, SessionOutputBuffer sessionOutputBuffer) {
        this.c = 0;
        this.d = false;
        this.e = false;
        this.b = new byte[i];
        this.a = sessionOutputBuffer;
    }

    protected void a() throws IOException {
        if (this.c > 0) {
            this.a.writeLine(Integer.toHexString(this.c));
            this.a.write(this.b, 0, this.c);
            this.a.writeLine("");
            this.c = 0;
        }
    }

    protected void a(byte[] bArr, int i, int i2) throws IOException {
        this.a.writeLine(Integer.toHexString(this.c + i2));
        this.a.write(this.b, 0, this.c);
        this.a.write(bArr, i, i2);
        this.a.writeLine("");
        this.c = 0;
    }

    protected void b() throws IOException {
        this.a.writeLine("0");
        this.a.writeLine("");
    }

    public void finish() throws IOException {
        if (!this.d) {
            a();
            b();
            this.d = true;
        }
    }

    public void write(int i) throws IOException {
        if (this.e) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.b[this.c] = (byte) i;
        this.c++;
        if (this.c == this.b.length) {
            a();
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.e) {
            throw new IOException("Attempted write to closed stream.");
        } else if (i2 >= this.b.length - this.c) {
            a(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, this.b, this.c, i2);
            this.c += i2;
        }
    }

    public void flush() throws IOException {
        a();
        this.a.flush();
    }

    public void close() throws IOException {
        if (!this.e) {
            this.e = true;
            finish();
            this.a.flush();
        }
    }
}
