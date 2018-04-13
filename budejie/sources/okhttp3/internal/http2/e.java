package okhttp3.internal.http2;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okio.ByteString;
import okio.d;

public final class e implements Closeable {
    static final ExecutorService a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.internal.c.a("OkHttp Http2Connection", true));
    static final /* synthetic */ boolean s;
    final boolean b;
    final b c;
    final Map<Integer, g> d = new LinkedHashMap();
    final String e;
    int f;
    int g;
    boolean h;
    final k i;
    long j = 0;
    long k;
    l l = new l();
    final l m = new l();
    boolean n = false;
    final Socket o;
    final h p;
    final c q;
    final Set<Integer> r = new LinkedHashSet();
    private final ExecutorService t;
    private Map<Integer, j> u;
    private int v;

    public static abstract class b {
        public static final b f = new b() {
            public void a(g gVar) throws IOException {
                gVar.a(ErrorCode.REFUSED_STREAM);
            }
        };

        public abstract void a(g gVar) throws IOException;

        public void a(e eVar) {
        }
    }

    public static class a {
        Socket a;
        String b;
        okio.e c;
        d d;
        b e = b.f;
        k f = k.a;
        boolean g;

        public a(boolean z) {
            this.g = z;
        }

        public a a(Socket socket, String str, okio.e eVar, d dVar) {
            this.a = socket;
            this.b = str;
            this.c = eVar;
            this.d = dVar;
            return this;
        }

        public a a(b bVar) {
            this.e = bVar;
            return this;
        }

        public e a() throws IOException {
            return new e(this);
        }
    }

    class c extends okhttp3.internal.b implements b {
        final f a;
        final /* synthetic */ e c;

        c(e eVar, f fVar) {
            this.c = eVar;
            super("OkHttp %s", eVar.e);
            this.a = fVar;
        }

        protected void b() {
            ErrorCode errorCode;
            Throwable th;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            try {
                this.a.a((b) this);
                do {
                } while (this.a.a(false, (b) this));
                try {
                    this.c.a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
                } catch (IOException e) {
                }
                okhttp3.internal.c.a(this.a);
            } catch (IOException e2) {
                errorCode = ErrorCode.PROTOCOL_ERROR;
                try {
                    this.c.a(errorCode, ErrorCode.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                okhttp3.internal.c.a(this.a);
            } catch (Throwable th2) {
                th = th2;
                this.c.a(errorCode, errorCode3);
                okhttp3.internal.c.a(this.a);
                throw th;
            }
        }

        public void a(boolean z, int i, okio.e eVar, int i2) throws IOException {
            if (this.c.d(i)) {
                this.c.a(i, eVar, i2, z);
                return;
            }
            g a = this.c.a(i);
            if (a == null) {
                this.c.a(i, ErrorCode.PROTOCOL_ERROR);
                eVar.g((long) i2);
                return;
            }
            a.a(eVar, i2);
            if (z) {
                a.i();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(boolean r9, int r10, int r11, java.util.List<okhttp3.internal.http2.a> r12) {
            /*
            r8 = this;
            r0 = r8.c;
            r0 = r0.d(r10);
            if (r0 == 0) goto L_0x000e;
        L_0x0008:
            r0 = r8.c;
            r0.a(r10, r12, r9);
        L_0x000d:
            return;
        L_0x000e:
            r6 = r8.c;
            monitor-enter(r6);
            r0 = r8.c;	 Catch:{ all -> 0x0019 }
            r0 = r0.h;	 Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x001c;
        L_0x0017:
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            goto L_0x000d;
        L_0x0019:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            throw r0;
        L_0x001c:
            r0 = r8.c;	 Catch:{ all -> 0x0019 }
            r0 = r0.a(r10);	 Catch:{ all -> 0x0019 }
            if (r0 != 0) goto L_0x0071;
        L_0x0024:
            r0 = r8.c;	 Catch:{ all -> 0x0019 }
            r0 = r0.f;	 Catch:{ all -> 0x0019 }
            if (r10 > r0) goto L_0x002c;
        L_0x002a:
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            goto L_0x000d;
        L_0x002c:
            r0 = r10 % 2;
            r1 = r8.c;	 Catch:{ all -> 0x0019 }
            r1 = r1.g;	 Catch:{ all -> 0x0019 }
            r1 = r1 % 2;
            if (r0 != r1) goto L_0x0038;
        L_0x0036:
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            goto L_0x000d;
        L_0x0038:
            r0 = new okhttp3.internal.http2.g;	 Catch:{ all -> 0x0019 }
            r2 = r8.c;	 Catch:{ all -> 0x0019 }
            r3 = 0;
            r1 = r10;
            r4 = r9;
            r5 = r12;
            r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0019 }
            r1 = r8.c;	 Catch:{ all -> 0x0019 }
            r1.f = r10;	 Catch:{ all -> 0x0019 }
            r1 = r8.c;	 Catch:{ all -> 0x0019 }
            r1 = r1.d;	 Catch:{ all -> 0x0019 }
            r2 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0019 }
            r1.put(r2, r0);	 Catch:{ all -> 0x0019 }
            r1 = okhttp3.internal.http2.e.a;	 Catch:{ all -> 0x0019 }
            r2 = new okhttp3.internal.http2.e$c$1;	 Catch:{ all -> 0x0019 }
            r3 = "OkHttp %s stream %d";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0019 }
            r5 = 0;
            r7 = r8.c;	 Catch:{ all -> 0x0019 }
            r7 = r7.e;	 Catch:{ all -> 0x0019 }
            r4[r5] = r7;	 Catch:{ all -> 0x0019 }
            r5 = 1;
            r7 = java.lang.Integer.valueOf(r10);	 Catch:{ all -> 0x0019 }
            r4[r5] = r7;	 Catch:{ all -> 0x0019 }
            r2.<init>(r8, r3, r4, r0);	 Catch:{ all -> 0x0019 }
            r1.execute(r2);	 Catch:{ all -> 0x0019 }
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            goto L_0x000d;
        L_0x0071:
            monitor-exit(r6);	 Catch:{ all -> 0x0019 }
            r0.a(r12);
            if (r9 == 0) goto L_0x000d;
        L_0x0077:
            r0.i();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.c.a(boolean, int, int, java.util.List):void");
        }

        public void a(int i, ErrorCode errorCode) {
            if (this.c.d(i)) {
                this.c.c(i, errorCode);
                return;
            }
            g b = this.c.b(i);
            if (b != null) {
                b.c(errorCode);
            }
        }

        public void a(boolean z, l lVar) {
            g[] gVarArr;
            long j;
            synchronized (this.c) {
                int d = this.c.m.d();
                if (z) {
                    this.c.m.a();
                }
                this.c.m.a(lVar);
                a(lVar);
                int d2 = this.c.m.d();
                if (d2 == -1 || d2 == d) {
                    gVarArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (d2 - d);
                    if (!this.c.n) {
                        this.c.a(j2);
                        this.c.n = true;
                    }
                    if (this.c.d.isEmpty()) {
                        j = j2;
                        gVarArr = null;
                    } else {
                        j = j2;
                        gVarArr = (g[]) this.c.d.values().toArray(new g[this.c.d.size()]);
                    }
                }
                e.a.execute(new okhttp3.internal.b(this, "OkHttp %s settings", this.c.e) {
                    final /* synthetic */ c a;

                    public void b() {
                        this.a.c.c.a(this.a.c);
                    }
                });
            }
            if (gVarArr != null && j != 0) {
                for (g gVar : gVarArr) {
                    synchronized (gVar) {
                        gVar.a(j);
                    }
                }
            }
        }

        private void a(final l lVar) {
            e.a.execute(new okhttp3.internal.b(this, "OkHttp %s ACK Settings", new Object[]{this.c.e}) {
                final /* synthetic */ c c;

                public void b() {
                    try {
                        this.c.c.p.a(lVar);
                    } catch (IOException e) {
                    }
                }
            });
        }

        public void a() {
        }

        public void a(boolean z, int i, int i2) {
            if (z) {
                j c = this.c.c(i);
                if (c != null) {
                    c.b();
                    return;
                }
                return;
            }
            this.c.a(true, i, i2, null);
        }

        public void a(int i, ErrorCode errorCode, ByteString byteString) {
            if (byteString.size() > 0) {
            }
            synchronized (this.c) {
                g[] gVarArr = (g[]) this.c.d.values().toArray(new g[this.c.d.size()]);
                this.c.h = true;
            }
            for (g gVar : gVarArr) {
                if (gVar.a() > i && gVar.c()) {
                    gVar.c(ErrorCode.REFUSED_STREAM);
                    this.c.b(gVar.a());
                }
            }
        }

        public void a(int i, long j) {
            if (i == 0) {
                synchronized (this.c) {
                    e eVar = this.c;
                    eVar.k += j;
                    this.c.notifyAll();
                }
                return;
            }
            g a = this.c.a(i);
            if (a != null) {
                synchronized (a) {
                    a.a(j);
                }
            }
        }

        public void a(int i, int i2, int i3, boolean z) {
        }

        public void a(int i, int i2, List<a> list) {
            this.c.a(i2, (List) list);
        }
    }

    static {
        boolean z;
        if (e.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        s = z;
    }

    e(a aVar) {
        int i = 2;
        this.i = aVar.f;
        this.b = aVar.g;
        this.c = aVar.e;
        this.g = aVar.g ? 1 : 2;
        if (aVar.g) {
            this.g += 2;
        }
        if (aVar.g) {
            i = 1;
        }
        this.v = i;
        if (aVar.g) {
            this.l.a(7, ViewCompat.MEASURED_STATE_TOO_SMALL);
        }
        this.e = aVar.b;
        this.t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.internal.c.a(okhttp3.internal.c.a("OkHttp %s Push Observer", this.e), true));
        this.m.a(7, SupportMenu.USER_MASK);
        this.m.a(5, 16384);
        this.k = (long) this.m.d();
        this.o = aVar.a;
        this.p = new h(aVar.d, this.b);
        this.q = new c(this, new f(aVar.c, this.b));
    }

    synchronized g a(int i) {
        return (g) this.d.get(Integer.valueOf(i));
    }

    synchronized g b(int i) {
        g gVar;
        gVar = (g) this.d.remove(Integer.valueOf(i));
        notifyAll();
        return gVar;
    }

    public synchronized int a() {
        return this.m.c(Integer.MAX_VALUE);
    }

    public g a(List<a> list, boolean z) throws IOException {
        return b(0, list, z);
    }

    private g b(int i, List<a> list, boolean z) throws IOException {
        g gVar;
        Object obj = null;
        boolean z2 = !z;
        synchronized (this.p) {
            synchronized (this) {
                if (this.h) {
                    throw new ConnectionShutdownException();
                }
                int i2 = this.g;
                this.g += 2;
                gVar = new g(i2, this, z2, false, list);
                if (!z || this.k == 0 || gVar.b == 0) {
                    obj = 1;
                }
                if (gVar.b()) {
                    this.d.put(Integer.valueOf(i2), gVar);
                }
            }
            if (i == 0) {
                this.p.a(z2, i2, i, (List) list);
            } else if (this.b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.p.a(i, i2, (List) list);
            }
        }
        if (obj != null) {
            this.p.b();
        }
        return gVar;
    }

    public void a(int i, boolean z, okio.c cVar, long j) throws IOException {
        if (j == 0) {
            this.p.a(z, i, cVar, 0);
            return;
        }
        while (j > 0) {
            int min;
            boolean z2;
            synchronized (this) {
                while (this.k <= 0) {
                    try {
                        if (this.d.containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.k), this.p.c());
                this.k -= (long) min;
            }
            j -= (long) min;
            h hVar = this.p;
            if (z && j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            hVar.a(z2, i, cVar, min);
        }
    }

    void a(long j) {
        this.k += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void a(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        a.execute(new okhttp3.internal.b(this, "OkHttp %s stream %d", new Object[]{this.e, Integer.valueOf(i)}) {
            final /* synthetic */ e d;

            public void b() {
                try {
                    this.d.b(i2, errorCode2);
                } catch (IOException e) {
                }
            }
        });
    }

    void b(int i, ErrorCode errorCode) throws IOException {
        this.p.a(i, errorCode);
    }

    void a(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        a.execute(new okhttp3.internal.b(this, "OkHttp Window Update %s stream %d", new Object[]{this.e, Integer.valueOf(i)}) {
            final /* synthetic */ e d;

            public void b() {
                try {
                    this.d.p.a(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    void a(boolean z, int i, int i2, j jVar) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final j jVar2 = jVar;
        a.execute(new okhttp3.internal.b(this, "OkHttp %s ping %08x%08x", new Object[]{this.e, Integer.valueOf(i), Integer.valueOf(i2)}) {
            final /* synthetic */ e f;

            public void b() {
                try {
                    this.f.b(z2, i3, i4, jVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    void b(boolean z, int i, int i2, j jVar) throws IOException {
        synchronized (this.p) {
            if (jVar != null) {
                jVar.a();
            }
            this.p.a(z, i, i2);
        }
    }

    synchronized j c(int i) {
        return this.u != null ? (j) this.u.remove(Integer.valueOf(i)) : null;
    }

    public void b() throws IOException {
        this.p.b();
    }

    public void a(ErrorCode errorCode) throws IOException {
        synchronized (this.p) {
            synchronized (this) {
                if (this.h) {
                    return;
                }
                this.h = true;
                int i = this.f;
                this.p.a(i, errorCode, okhttp3.internal.c.a);
            }
        }
    }

    public void close() throws IOException {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    void a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        IOException e;
        if (s || !Thread.holdsLock(this)) {
            IOException iOException;
            g[] gVarArr;
            j[] jVarArr;
            try {
                a(errorCode);
                iOException = null;
            } catch (IOException e2) {
                iOException = e2;
            }
            synchronized (this) {
                if (this.d.isEmpty()) {
                    gVarArr = null;
                } else {
                    g[] gVarArr2 = (g[]) this.d.values().toArray(new g[this.d.size()]);
                    this.d.clear();
                    gVarArr = gVarArr2;
                }
                if (this.u != null) {
                    j[] jVarArr2 = (j[]) this.u.values().toArray(new j[this.u.size()]);
                    this.u = null;
                    jVarArr = jVarArr2;
                } else {
                    jVarArr = null;
                }
            }
            if (gVarArr != null) {
                e2 = iOException;
                for (g a : gVarArr) {
                    try {
                        a.a(errorCode2);
                    } catch (IOException iOException2) {
                        if (e2 != null) {
                            e2 = iOException2;
                        }
                    }
                }
                iOException2 = e2;
            }
            if (jVarArr != null) {
                for (j c : jVarArr) {
                    c.c();
                }
            }
            try {
                this.p.close();
                e2 = iOException2;
            } catch (IOException e3) {
                e2 = e3;
                if (iOException2 != null) {
                    e2 = iOException2;
                }
            }
            try {
                this.o.close();
            } catch (IOException e4) {
                e2 = e4;
            }
            if (e2 != null) {
                throw e2;
            }
            return;
        }
        throw new AssertionError();
    }

    public void c() throws IOException {
        a(true);
    }

    void a(boolean z) throws IOException {
        if (z) {
            this.p.a();
            this.p.b(this.l);
            int d = this.l.d();
            if (d != SupportMenu.USER_MASK) {
                this.p.a(0, (long) (d - SupportMenu.USER_MASK));
            }
        }
        new Thread(this.q).start();
    }

    public synchronized boolean d() {
        return this.h;
    }

    boolean d(int i) {
        return i != 0 && (i & 1) == 0;
    }

    void a(int i, List<a> list) {
        synchronized (this) {
            if (this.r.contains(Integer.valueOf(i))) {
                a(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.r.add(Integer.valueOf(i));
            final int i2 = i;
            final List<a> list2 = list;
            this.t.execute(new okhttp3.internal.b(this, "OkHttp %s Push Request[%s]", new Object[]{this.e, Integer.valueOf(i)}) {
                final /* synthetic */ e d;

                public void b() {
                    if (this.d.i.a(i2, list2)) {
                        try {
                            this.d.p.a(i2, ErrorCode.CANCEL);
                            synchronized (this.d) {
                                this.d.r.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    void a(int i, List<a> list, boolean z) {
        final int i2 = i;
        final List<a> list2 = list;
        final boolean z2 = z;
        this.t.execute(new okhttp3.internal.b(this, "OkHttp %s Push Headers[%s]", new Object[]{this.e, Integer.valueOf(i)}) {
            final /* synthetic */ e e;

            public void b() {
                boolean a = this.e.i.a(i2, list2, z2);
                if (a) {
                    try {
                        this.e.p.a(i2, ErrorCode.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (a || z2) {
                    synchronized (this.e) {
                        this.e.r.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    void a(int i, okio.e eVar, int i2, boolean z) throws IOException {
        final okio.c cVar = new okio.c();
        eVar.a((long) i2);
        eVar.a(cVar, (long) i2);
        if (cVar.b() != ((long) i2)) {
            throw new IOException(cVar.b() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        this.t.execute(new okhttp3.internal.b(this, "OkHttp %s Push Data[%s]", new Object[]{this.e, Integer.valueOf(i)}) {
            final /* synthetic */ e f;

            public void b() {
                try {
                    boolean a = this.f.i.a(i3, cVar, i4, z2);
                    if (a) {
                        this.f.p.a(i3, ErrorCode.CANCEL);
                    }
                    if (a || z2) {
                        synchronized (this.f) {
                            this.f.r.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    void c(int i, ErrorCode errorCode) {
        final int i2 = i;
        final ErrorCode errorCode2 = errorCode;
        this.t.execute(new okhttp3.internal.b(this, "OkHttp %s Push Reset[%s]", new Object[]{this.e, Integer.valueOf(i)}) {
            final /* synthetic */ e d;

            public void b() {
                this.d.i.a(i2, errorCode2);
                synchronized (this.d) {
                    this.d.r.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}
