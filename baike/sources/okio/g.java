package okio;

import java.io.IOException;
import java.io.InputStream;

final class g implements Source {
    final /* synthetic */ Timeout a;
    final /* synthetic */ InputStream b;

    g(Timeout timeout, InputStream inputStream) {
        this.a = timeout;
        this.b = inputStream;
    }

    public long read(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.a.throwIfReached();
                n a = buffer.a(1);
                int read = this.b.read(a.a, a.c, (int) Math.min(j, (long) (8192 - a.c)));
                if (read == -1) {
                    return -1;
                }
                a.c += read;
                buffer.b += (long) read;
                return (long) read;
            } catch (AssertionError e) {
                if (Okio.a(e)) {
                    throw new IOException(e);
                }
                throw e;
            }
        }
    }

    public void close() throws IOException {
        this.b.close();
    }

    public Timeout timeout() {
        return this.a;
    }

    public String toString() {
        return "source(" + this.b + ")";
    }
}
