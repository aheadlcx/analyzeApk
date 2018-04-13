package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class a extends s {
    private static final long a = TimeUnit.SECONDS.toMillis(60);
    private static final long c = TimeUnit.MILLISECONDS.toNanos(a);
    private static a d;
    private boolean e;
    private a f;
    private long g;

    private static final class a extends Thread {
        public a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r3 = this;
        L_0x0000:
            r1 = okio.a.class;
            monitor-enter(r1);	 Catch:{ InterruptedException -> 0x000e }
            r0 = okio.a.e();	 Catch:{ all -> 0x000b }
            if (r0 != 0) goto L_0x0010;
        L_0x0009:
            monitor-exit(r1);	 Catch:{ all -> 0x000b }
            goto L_0x0000;
        L_0x000b:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x000b }
            throw r0;	 Catch:{ InterruptedException -> 0x000e }
        L_0x000e:
            r0 = move-exception;
            goto L_0x0000;
        L_0x0010:
            r2 = okio.a.d;	 Catch:{ all -> 0x000b }
            if (r0 != r2) goto L_0x001c;
        L_0x0016:
            r0 = 0;
            okio.a.d = r0;	 Catch:{ all -> 0x000b }
            monitor-exit(r1);	 Catch:{ all -> 0x000b }
            return;
        L_0x001c:
            monitor-exit(r1);	 Catch:{ all -> 0x000b }
            r0.a();	 Catch:{ InterruptedException -> 0x000e }
            goto L_0x0000;
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.a.a.run():void");
        }
    }

    public final void c() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long e_ = e_();
        boolean f_ = f_();
        if (e_ != 0 || f_) {
            this.e = true;
            a(this, e_, f_);
        }
    }

    private static synchronized void a(a aVar, long j, boolean z) {
        synchronized (a.class) {
            if (d == null) {
                d = new a();
                new a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                aVar.g = Math.min(j, aVar.d() - nanoTime) + nanoTime;
            } else if (j != 0) {
                aVar.g = nanoTime + j;
            } else if (z) {
                aVar.g = aVar.d();
            } else {
                throw new AssertionError();
            }
            long b = aVar.b(nanoTime);
            a aVar2 = d;
            while (aVar2.f != null && b >= aVar2.f.b(nanoTime)) {
                aVar2 = aVar2.f;
            }
            aVar.f = aVar2.f;
            aVar2.f = aVar;
            if (aVar2 == d) {
                a.class.notify();
            }
        }
    }

    public final boolean d_() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return b(this);
    }

    private static synchronized boolean b(a aVar) {
        boolean z;
        synchronized (a.class) {
            for (a aVar2 = d; aVar2 != null; aVar2 = aVar2.f) {
                if (aVar2.f == aVar) {
                    aVar2.f = aVar.f;
                    aVar.f = null;
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    private long b(long j) {
        return this.g - j;
    }

    protected void a() {
    }

    public final q a(final q qVar) {
        return new q(this) {
            final /* synthetic */ a b;

            public void a_(c cVar, long j) throws IOException {
                t.a(cVar.b, 0, j);
                long j2 = j;
                while (j2 > 0) {
                    n nVar = cVar.a;
                    long j3 = 0;
                    while (j3 < 65536) {
                        long j4 = ((long) (cVar.a.c - cVar.a.b)) + j3;
                        if (j4 >= j2) {
                            j3 = j2;
                            break;
                        } else {
                            nVar = nVar.f;
                            j3 = j4;
                        }
                    }
                    this.b.c();
                    try {
                        qVar.a_(cVar, j3);
                        j2 -= j3;
                        this.b.a(true);
                    } catch (IOException e) {
                        throw this.b.b(e);
                    } catch (Throwable th) {
                        this.b.a(false);
                    }
                }
            }

            public void flush() throws IOException {
                this.b.c();
                try {
                    qVar.flush();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public void close() throws IOException {
                this.b.c();
                try {
                    qVar.close();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public s a() {
                return this.b;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + qVar + ")";
            }
        };
    }

    public final r a(final r rVar) {
        return new r(this) {
            final /* synthetic */ a b;

            public long a(c cVar, long j) throws IOException {
                this.b.c();
                try {
                    long a = rVar.a(cVar, j);
                    this.b.a(true);
                    return a;
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public void close() throws IOException {
                try {
                    rVar.close();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public s a() {
                return this.b;
            }

            public String toString() {
                return "AsyncTimeout.source(" + rVar + ")";
            }
        };
    }

    final void a(boolean z) throws IOException {
        if (d_() && z) {
            throw a(null);
        }
    }

    final IOException b(IOException iOException) throws IOException {
        return !d_() ? iOException : a(iOException);
    }

    protected IOException a(IOException iOException) {
        IOException interruptedIOException = new InterruptedIOException(com.alipay.sdk.data.a.f);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    static a e() throws InterruptedException {
        a aVar = d.f;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            a.class.wait(a);
            if (d.f != null || System.nanoTime() - nanoTime < c) {
                return null;
            }
            return d;
        }
        nanoTime = aVar.b(System.nanoTime());
        if (nanoTime > 0) {
            long j = nanoTime / 1000000;
            a.class.wait(j, (int) (nanoTime - (1000000 * j)));
            return null;
        }
        d.f = aVar.f;
        aVar.f = null;
        return aVar;
    }
}
