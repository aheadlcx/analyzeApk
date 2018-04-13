package okio;

import java.io.IOException;

public abstract class f implements q {
    private final q a;

    public f(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = qVar;
    }

    public void a_(c cVar, long j) throws IOException {
        this.a.a_(cVar, j);
    }

    public void flush() throws IOException {
        this.a.flush();
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
