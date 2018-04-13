package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;

public final class j implements r {
    private final e a;
    private final Inflater b;
    private int c;
    private boolean d;

    j(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = eVar;
            this.b = inflater;
        }
    }

    public long a(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            boolean b;
            do {
                b = b();
                try {
                    n e = cVar.e(1);
                    int inflate = this.b.inflate(e.a, e.c, 8192 - e.c);
                    if (inflate > 0) {
                        e.c += inflate;
                        cVar.b += (long) inflate;
                        return (long) inflate;
                    } else if (this.b.finished() || this.b.needsDictionary()) {
                        c();
                        if (e.b == e.c) {
                            cVar.a = e.a();
                            o.a(e);
                        }
                        return -1;
                    }
                } catch (Throwable e2) {
                    throw new IOException(e2);
                }
            } while (!b);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean b() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        c();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.f()) {
            return true;
        } else {
            n nVar = this.a.c().a;
            this.c = nVar.c - nVar.b;
            this.b.setInput(nVar.a, nVar.b, this.c);
            return false;
        }
    }

    private void c() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.g((long) remaining);
        }
    }

    public s a() {
        return this.a.a();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
