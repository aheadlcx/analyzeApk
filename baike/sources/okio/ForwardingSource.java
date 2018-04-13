package okio;

import java.io.IOException;

public abstract class ForwardingSource implements Source {
    private final Source a;

    public ForwardingSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = source;
    }

    public final Source delegate() {
        return this.a;
    }

    public long read(Buffer buffer, long j) throws IOException {
        return this.a.read(buffer, j);
    }

    public Timeout timeout() {
        return this.a.timeout();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.a.toString() + ")";
    }
}
