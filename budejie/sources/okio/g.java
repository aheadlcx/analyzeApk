package okio;

import java.io.IOException;

public abstract class g implements r {
    private final r a;

    public g(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = rVar;
    }

    public long a(c cVar, long j) throws IOException {
        return this.a.a(cVar, j);
    }

    public s a() {
        return this.a.a();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.a.toString() + ")";
    }
}
