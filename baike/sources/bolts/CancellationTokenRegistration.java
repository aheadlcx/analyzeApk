package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable {
    private final Object a = new Object();
    private CancellationTokenSource b;
    private Runnable c;
    private boolean d;

    CancellationTokenRegistration(CancellationTokenSource cancellationTokenSource, Runnable runnable) {
        this.b = cancellationTokenSource;
        this.c = runnable;
    }

    public void close() {
        synchronized (this.a) {
            if (this.d) {
                return;
            }
            this.d = true;
            this.b.a(this);
            this.b = null;
            this.c = null;
        }
    }

    void a() {
        synchronized (this.a) {
            b();
            this.c.run();
            close();
        }
    }

    private void b() {
        if (this.d) {
            throw new IllegalStateException("Object already closed");
        }
    }
}
