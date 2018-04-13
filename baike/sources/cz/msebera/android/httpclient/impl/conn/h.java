package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
class h extends InputStream {
    private final InputStream a;
    private final Wire b;

    public h(InputStream inputStream, Wire wire) {
        this.a = inputStream;
        this.b = wire;
    }

    public int read() throws IOException {
        try {
            int read = this.a.read();
            if (read == -1) {
                this.b.input("end of stream");
            } else {
                this.b.input(read);
            }
            return read;
        } catch (IOException e) {
            this.b.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public int read(byte[] bArr) throws IOException {
        try {
            int read = this.a.read(bArr);
            if (read == -1) {
                this.b.input("end of stream");
            } else if (read > 0) {
                this.b.input(bArr, 0, read);
            }
            return read;
        } catch (IOException e) {
            this.b.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int read = this.a.read(bArr, i, i2);
            if (read == -1) {
                this.b.input("end of stream");
            } else if (read > 0) {
                this.b.input(bArr, i, read);
            }
            return read;
        } catch (IOException e) {
            this.b.input("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public long skip(long j) throws IOException {
        try {
            return super.skip(j);
        } catch (IOException e) {
            this.b.input("[skip] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public int available() throws IOException {
        try {
            return this.a.available();
        } catch (IOException e) {
            this.b.input("[available] I/O error : " + e.getMessage());
            throw e;
        }
    }

    public void mark(int i) {
        super.mark(i);
    }

    public void reset() throws IOException {
        super.reset();
    }

    public boolean markSupported() {
        return false;
    }

    public void close() throws IOException {
        try {
            this.a.close();
        } catch (IOException e) {
            this.b.input("[close] I/O error: " + e.getMessage());
            throw e;
        }
    }
}
