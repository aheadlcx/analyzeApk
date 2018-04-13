package retrofit2;

import java.io.IOException;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.e;
import okhttp3.f;
import okhttp3.u;
import okhttp3.y;
import okio.c;
import okio.g;
import okio.k;

final class h<T> implements b<T> {
    private final n<T, ?> a;
    private final Object[] b;
    private volatile boolean c;
    private e d;
    private Throwable e;
    private boolean f;

    static final class a extends ab {
        IOException a;
        private final ab b;

        a(ab abVar) {
            this.b = abVar;
        }

        public u a() {
            return this.b.a();
        }

        public long b() {
            return this.b.b();
        }

        public okio.e c() {
            return k.a(new g(this, this.b.c()) {
                final /* synthetic */ a a;

                public long a(c cVar, long j) throws IOException {
                    try {
                        return super.a(cVar, j);
                    } catch (IOException e) {
                        this.a.a = e;
                        throw e;
                    }
                }
            });
        }

        public void close() {
            this.b.close();
        }

        void g() throws IOException {
            if (this.a != null) {
                throw this.a;
            }
        }
    }

    static final class b extends ab {
        private final u a;
        private final long b;

        b(u uVar, long j) {
            this.a = uVar;
            this.b = j;
        }

        public u a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }

        public okio.e c() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return f();
    }

    public /* synthetic */ b d() {
        return f();
    }

    h(n<T, ?> nVar, Object[] objArr) {
        this.a = nVar;
        this.b = objArr;
    }

    public h<T> f() {
        return new h(this.a, this.b);
    }

    public synchronized y e() {
        y a;
        e eVar = this.d;
        if (eVar != null) {
            a = eVar.a();
        } else if (this.e == null) {
            try {
                eVar = g();
                this.d = eVar;
                a = eVar.a();
            } catch (Throwable e) {
                this.e = e;
                throw e;
            } catch (Throwable e2) {
                this.e = e2;
                throw new RuntimeException("Unable to create request.", e2);
            }
        } else if (this.e instanceof IOException) {
            throw new RuntimeException("Unable to create request.", this.e);
        } else {
            throw ((RuntimeException) this.e);
        }
        return a;
    }

    public void a(final d<T> dVar) {
        if (dVar == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            e g;
            this.f = true;
            e eVar = this.d;
            Throwable th = this.e;
            if (eVar == null && th == null) {
                try {
                    g = g();
                    this.d = g;
                } catch (Throwable th2) {
                    th = th2;
                    this.e = th;
                    g = eVar;
                }
            } else {
                g = eVar;
            }
        }
        if (th != null) {
            dVar.onFailure(this, th);
            return;
        }
        if (this.c) {
            g.c();
        }
        g.a(new f(this) {
            final /* synthetic */ h b;

            public void onResponse(e eVar, aa aaVar) throws IOException {
                try {
                    a(this.b.a(aaVar));
                } catch (Throwable th) {
                    a(th);
                }
            }

            public void onFailure(e eVar, IOException iOException) {
                try {
                    dVar.onFailure(this.b, iOException);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            private void a(Throwable th) {
                try {
                    dVar.onFailure(this.b, th);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }

            private void a(l<T> lVar) {
                try {
                    dVar.onResponse(this.b, lVar);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public l<T> a() throws IOException {
        e eVar;
        Throwable e;
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            if (this.e == null) {
                eVar = this.d;
                if (eVar == null) {
                    try {
                        eVar = g();
                        this.d = eVar;
                    } catch (IOException e2) {
                        e = e2;
                        this.e = e;
                        throw e;
                    } catch (RuntimeException e3) {
                        e = e3;
                        this.e = e;
                        throw e;
                    }
                }
            } else if (this.e instanceof IOException) {
                throw ((IOException) this.e);
            } else {
                throw ((RuntimeException) this.e);
            }
        }
        if (this.c) {
            eVar.c();
        }
        return a(eVar.b());
    }

    private e g() throws IOException {
        e a = this.a.c.a(this.a.a(this.b));
        if (a != null) {
            return a;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    l<T> a(aa aaVar) throws IOException {
        ab h = aaVar.h();
        aa a = aaVar.i().a(new b(h.a(), h.b())).a();
        int c = a.c();
        if (c < 200 || c >= 300) {
            try {
                l<T> a2 = l.a(o.a(h), a);
                return a2;
            } finally {
                h.close();
            }
        } else if (c == 204 || c == 205) {
            h.close();
            return l.a(null, a);
        } else {
            ab aVar = new a(h);
            try {
                return l.a(this.a.a(aVar), a);
            } catch (RuntimeException e) {
                aVar.g();
                throw e;
            }
        }
    }

    public void b() {
        this.c = true;
        synchronized (this) {
            e eVar = this.d;
        }
        if (eVar != null) {
            eVar.c();
        }
    }

    public boolean c() {
        boolean z = true;
        if (!this.c) {
            synchronized (this) {
                if (this.d == null || !this.d.d()) {
                    z = false;
                }
            }
        }
        return z;
    }
}
