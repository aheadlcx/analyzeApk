package bolts;

import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;

public class e implements Closeable {
    private final Object a;
    private final List<d> b;
    private ScheduledFuture<?> c;
    private boolean d;
    private boolean e;

    public boolean a() {
        boolean z;
        synchronized (this.a) {
            b();
            z = this.d;
        }
        return z;
    }

    public void close() {
        synchronized (this.a) {
            if (this.e) {
                return;
            }
            c();
            for (d close : this.b) {
                close.close();
            }
            this.b.clear();
            this.e = true;
        }
    }

    void a(d dVar) {
        synchronized (this.a) {
            b();
            this.b.remove(dVar);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(a())});
    }

    private void b() {
        if (this.e) {
            throw new IllegalStateException("Object already closed");
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
    }
}
