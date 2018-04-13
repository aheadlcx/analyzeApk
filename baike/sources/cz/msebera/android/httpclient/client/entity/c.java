package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.IOException;
import java.io.InputStream;

@NotThreadSafe
class c extends InputStream {
    private final InputStream a;
    private final InputStreamFactory b;
    private InputStream c;

    public c(InputStream inputStream, InputStreamFactory inputStreamFactory) {
        this.a = inputStream;
        this.b = inputStreamFactory;
    }

    private void a() throws IOException {
        if (this.c == null) {
            this.c = this.b.create(this.a);
        }
    }

    public int read() throws IOException {
        a();
        return this.c.read();
    }

    public int read(byte[] bArr) throws IOException {
        a();
        return this.c.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        a();
        return this.c.read(bArr, i, i2);
    }

    public long skip(long j) throws IOException {
        a();
        return this.c.skip(j);
    }

    public boolean markSupported() {
        return false;
    }

    public int available() throws IOException {
        a();
        return this.c.available();
    }

    public void close() throws IOException {
        try {
            if (this.c != null) {
                this.c.close();
            }
            this.a.close();
        } catch (Throwable th) {
            this.a.close();
        }
    }
}
