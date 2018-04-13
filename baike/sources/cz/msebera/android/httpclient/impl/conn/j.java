package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.IOException;
import java.io.OutputStream;

@NotThreadSafe
class j extends OutputStream {
    private final OutputStream a;
    private final Wire b;

    public j(OutputStream outputStream, Wire wire) {
        this.a = outputStream;
        this.b = wire;
    }

    public void write(int i) throws IOException {
        try {
            this.b.output(i);
        } catch (IOException e) {
            this.b.output("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void write(byte[] bArr) throws IOException {
        try {
            this.b.output(bArr);
            this.a.write(bArr);
        } catch (IOException e) {
            this.b.output("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.b.output(bArr, i, i2);
            this.a.write(bArr, i, i2);
        } catch (IOException e) {
            this.b.output("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void flush() throws IOException {
        try {
            this.a.flush();
        } catch (IOException e) {
            this.b.output("[flush] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            this.a.close();
        } catch (IOException e) {
            this.b.output("[close] I/O error: " + e.getMessage());
            throw e;
        }
    }
}
