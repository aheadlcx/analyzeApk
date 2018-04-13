package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;

public final class InflaterSource implements Source {
    private final BufferedSource a;
    private final Inflater b;
    private int c;
    private boolean d;

    public InflaterSource(Source source, Inflater inflater) {
        this(Okio.buffer(source), inflater);
    }

    InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = bufferedSource;
            this.b = inflater;
        }
    }

    public long read(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            boolean refill;
            do {
                refill = refill();
                try {
                    n a = buffer.a(1);
                    int inflate = this.b.inflate(a.a, a.c, 8192 - a.c);
                    if (inflate > 0) {
                        a.c += inflate;
                        buffer.b += (long) inflate;
                        return (long) inflate;
                    } else if (this.b.finished() || this.b.needsDictionary()) {
                        a();
                        if (a.b == a.c) {
                            buffer.a = a.pop();
                            o.a(a);
                        }
                        return -1;
                    }
                } catch (Throwable e) {
                    throw new IOException(e);
                }
            } while (!refill);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean refill() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        a();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.exhausted()) {
            return true;
        } else {
            n nVar = this.a.buffer().a;
            this.c = nVar.c - nVar.b;
            this.b.setInput(nVar.a, nVar.b, this.c);
            return false;
        }
    }

    private void a() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.skip((long) remaining);
        }
    }

    public Timeout timeout() {
        return this.a.timeout();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
