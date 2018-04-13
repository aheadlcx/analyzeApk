package okhttp3.internal.a;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import okio.BufferedSink;

public final class d implements Closeable, Flushable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static final /* synthetic */ boolean j = (!d.class.desiredAssertionStatus());
    final okhttp3.internal.d.a b;
    final int c;
    BufferedSink d;
    final LinkedHashMap<String, b> e;
    int f;
    boolean g;
    boolean h;
    boolean i;
    private long k;
    private long l;
    private long m;
    private final Executor n;
    private final Runnable o;

    public final class a {
        final b a;
        final boolean[] b;
        final /* synthetic */ d c;
        private boolean d;

        void a() {
            if (this.a.f == this) {
                for (int i = 0; i < this.c.c; i++) {
                    try {
                        this.c.b.a(this.a.d[i]);
                    } catch (IOException e) {
                    }
                }
                this.a.f = null;
            }
        }

        public void b() throws IOException {
            synchronized (this.c) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.a.f == this) {
                    this.c.a(this, false);
                }
                this.d = true;
            }
        }
    }

    private final class b {
        final String a;
        final long[] b;
        final File[] c;
        final File[] d;
        boolean e;
        a f;
        long g;

        void a(BufferedSink bufferedSink) throws IOException {
            for (long writeDecimalLong : this.b) {
                bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
            }
        }
    }

    synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b bVar = aVar.a;
            if (bVar.f != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!bVar.e) {
                    int i2 = 0;
                    while (i2 < this.c) {
                        if (!aVar.b[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!this.b.b(bVar.d[i2])) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.c) {
                File file = bVar.d[i];
                if (!z) {
                    this.b.a(file);
                } else if (this.b.b(file)) {
                    File file2 = bVar.c[i];
                    this.b.a(file, file2);
                    long j = bVar.b[i];
                    long c = this.b.c(file2);
                    bVar.b[i] = c;
                    this.l = (this.l - j) + c;
                }
                i++;
            }
            this.f++;
            bVar.f = null;
            if ((bVar.e | z) != 0) {
                bVar.e = true;
                this.d.writeUtf8("CLEAN").writeByte(32);
                this.d.writeUtf8(bVar.a);
                bVar.a(this.d);
                this.d.writeByte(10);
                if (z) {
                    long j2 = this.m;
                    this.m = 1 + j2;
                    bVar.g = j2;
                }
            } else {
                this.e.remove(bVar.a);
                this.d.writeUtf8("REMOVE").writeByte(32);
                this.d.writeUtf8(bVar.a);
                this.d.writeByte(10);
            }
            this.d.flush();
            if (this.l > this.k || a()) {
                this.n.execute(this.o);
            }
        }
    }

    boolean a() {
        return this.f >= 2000 && this.f >= this.e.size();
    }

    boolean a(b bVar) throws IOException {
        if (bVar.f != null) {
            bVar.f.a();
        }
        for (int i = 0; i < this.c; i++) {
            this.b.a(bVar.c[i]);
            this.l -= bVar.b[i];
            bVar.b[i] = 0;
        }
        this.f++;
        this.d.writeUtf8("REMOVE").writeByte(32).writeUtf8(bVar.a).writeByte(10);
        this.e.remove(bVar.a);
        if (a()) {
            this.n.execute(this.o);
        }
        return true;
    }

    public synchronized boolean b() {
        return this.h;
    }

    private synchronized void d() {
        if (b()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.g) {
            d();
            c();
            this.d.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (!this.g || this.h) {
            this.h = true;
        } else {
            for (b bVar : (b[]) this.e.values().toArray(new b[this.e.size()])) {
                if (bVar.f != null) {
                    bVar.f.b();
                }
            }
            c();
            this.d.close();
            this.d = null;
            this.h = true;
        }
    }

    void c() throws IOException {
        while (this.l > this.k) {
            a((b) this.e.values().iterator().next());
        }
        this.i = false;
    }
}
