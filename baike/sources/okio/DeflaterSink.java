package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class DeflaterSink implements Sink {
    private final BufferedSink a;
    private final Deflater b;
    private boolean c;

    public DeflaterSink(Sink sink, Deflater deflater) {
        this(Okio.buffer(sink), deflater);
    }

    DeflaterSink(BufferedSink bufferedSink, Deflater deflater) {
        if (bufferedSink == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = bufferedSink;
            this.b = deflater;
        }
    }

    public void write(Buffer buffer, long j) throws IOException {
        r.checkOffsetAndCount(buffer.b, 0, j);
        while (j > 0) {
            n nVar = buffer.a;
            int min = (int) Math.min(j, (long) (nVar.c - nVar.b));
            this.b.setInput(nVar.a, nVar.b, min);
            a(false);
            buffer.b -= (long) min;
            nVar.b += min;
            if (nVar.b == nVar.c) {
                buffer.a = nVar.pop();
                o.a(nVar);
            }
            j -= (long) min;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        Buffer buffer = this.a.buffer();
        while (true) {
            int deflate;
            n a = buffer.a(1);
            if (z) {
                deflate = this.b.deflate(a.a, a.c, 8192 - a.c, 2);
            } else {
                deflate = this.b.deflate(a.a, a.c, 8192 - a.c);
            }
            if (deflate > 0) {
                a.c += deflate;
                buffer.b += (long) deflate;
                this.a.emitCompleteSegments();
            } else if (this.b.needsInput()) {
                break;
            }
        }
        if (a.b == a.c) {
            buffer.a = a.pop();
            o.a(a);
        }
    }

    public void flush() throws IOException {
        a(true);
        this.a.flush();
    }

    void a() throws IOException {
        this.b.finish();
        a(false);
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.c) {
            Throwable th2 = null;
            try {
                a();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.b.end();
                th = th2;
            } catch (Throwable th4) {
                th = th4;
                if (th2 != null) {
                    th = th2;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th22) {
                if (th == null) {
                    th = th22;
                }
            }
            this.c = true;
            if (th != null) {
                r.sneakyRethrow(th);
            }
        }
    }

    public Timeout timeout() {
        return this.a.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.a + ")";
    }
}
