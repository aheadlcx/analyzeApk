package okio;

import java.io.IOException;
import java.io.OutputStream;

final class f implements Sink {
    final /* synthetic */ Timeout a;
    final /* synthetic */ OutputStream b;

    f(Timeout timeout, OutputStream outputStream) {
        this.a = timeout;
        this.b = outputStream;
    }

    public void write(Buffer buffer, long j) throws IOException {
        r.checkOffsetAndCount(buffer.b, 0, j);
        while (j > 0) {
            this.a.throwIfReached();
            n nVar = buffer.a;
            int min = (int) Math.min(j, (long) (nVar.c - nVar.b));
            this.b.write(nVar.a, nVar.b, min);
            nVar.b += min;
            j -= (long) min;
            buffer.b -= (long) min;
            if (nVar.b == nVar.c) {
                buffer.a = nVar.pop();
                o.a(nVar);
            }
        }
    }

    public void flush() throws IOException {
        this.b.flush();
    }

    public void close() throws IOException {
        this.b.close();
    }

    public Timeout timeout() {
        return this.a;
    }

    public String toString() {
        return "sink(" + this.b + ")";
    }
}
