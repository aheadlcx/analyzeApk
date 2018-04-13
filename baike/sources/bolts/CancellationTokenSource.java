package bolts;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CancellationTokenSource implements Closeable {
    private final Object a = new Object();
    private final List<CancellationTokenRegistration> b = new ArrayList();
    private final ScheduledExecutorService c = b.a();
    private ScheduledFuture<?> d;
    private boolean e;
    private boolean f;

    public boolean isCancellationRequested() {
        boolean z;
        synchronized (this.a) {
            b();
            z = this.e;
        }
        return z;
    }

    public CancellationToken getToken() {
        CancellationToken cancellationToken;
        synchronized (this.a) {
            b();
            cancellationToken = new CancellationToken(this);
        }
        return cancellationToken;
    }

    public void cancel() {
        synchronized (this.a) {
            b();
            if (this.e) {
                return;
            }
            c();
            this.e = true;
            List arrayList = new ArrayList(this.b);
            a(arrayList);
        }
    }

    public void cancelAfter(long j) {
        a(j, TimeUnit.MILLISECONDS);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(long r6, java.util.concurrent.TimeUnit r8) {
        /*
        r5 = this;
        r2 = -1;
        r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Delay must be >= -1";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r0 = 0;
        r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x0018;
    L_0x0014:
        r5.cancel();
    L_0x0017:
        return;
    L_0x0018:
        r1 = r5.a;
        monitor-enter(r1);
        r0 = r5.e;	 Catch:{ all -> 0x0021 }
        if (r0 == 0) goto L_0x0024;
    L_0x001f:
        monitor-exit(r1);	 Catch:{ all -> 0x0021 }
        goto L_0x0017;
    L_0x0021:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0021 }
        throw r0;
    L_0x0024:
        r5.c();	 Catch:{ all -> 0x0021 }
        r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x0038;
    L_0x002b:
        r0 = r5.c;	 Catch:{ all -> 0x0021 }
        r2 = new bolts.c;	 Catch:{ all -> 0x0021 }
        r2.<init>(r5);	 Catch:{ all -> 0x0021 }
        r0 = r0.schedule(r2, r6, r8);	 Catch:{ all -> 0x0021 }
        r5.d = r0;	 Catch:{ all -> 0x0021 }
    L_0x0038:
        monitor-exit(r1);	 Catch:{ all -> 0x0021 }
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.CancellationTokenSource.a(long, java.util.concurrent.TimeUnit):void");
    }

    public void close() {
        synchronized (this.a) {
            if (this.f) {
                return;
            }
            c();
            for (CancellationTokenRegistration close : this.b) {
                close.close();
            }
            this.b.clear();
            this.f = true;
        }
    }

    CancellationTokenRegistration a(Runnable runnable) {
        CancellationTokenRegistration cancellationTokenRegistration;
        synchronized (this.a) {
            b();
            cancellationTokenRegistration = new CancellationTokenRegistration(this, runnable);
            if (this.e) {
                cancellationTokenRegistration.a();
            } else {
                this.b.add(cancellationTokenRegistration);
            }
        }
        return cancellationTokenRegistration;
    }

    void a() throws CancellationException {
        synchronized (this.a) {
            b();
            if (this.e) {
                throw new CancellationException();
            }
        }
    }

    void a(CancellationTokenRegistration cancellationTokenRegistration) {
        synchronized (this.a) {
            b();
            this.b.remove(cancellationTokenRegistration);
        }
    }

    private void a(List<CancellationTokenRegistration> list) {
        for (CancellationTokenRegistration a : list) {
            a.a();
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(isCancellationRequested())});
    }

    private void b() {
        if (this.f) {
            throw new IllegalStateException("Object already closed");
        }
    }

    private void c() {
        if (this.d != null) {
            this.d.cancel(true);
            this.d = null;
        }
    }
}
