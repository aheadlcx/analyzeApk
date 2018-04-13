package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.ac;
import okhttp3.internal.b.c;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.j;
import okhttp3.w;

public final class f {
    static final /* synthetic */ boolean b = (!f.class.desiredAssertionStatus());
    public final okhttp3.a a;
    private ac c;
    private final j d;
    private final Object e;
    private final e f;
    private int g;
    private c h;
    private boolean i;
    private boolean j;
    private c k;

    public static final class a extends WeakReference<f> {
        public final Object a;

        a(f fVar, Object obj) {
            super(fVar);
            this.a = obj;
        }
    }

    public f(j jVar, okhttp3.a aVar, Object obj) {
        this.d = jVar;
        this.a = aVar;
        this.f = new e(aVar, g());
        this.e = obj;
    }

    public c a(w wVar, boolean z) {
        try {
            c a = a(wVar.a(), wVar.b(), wVar.c(), wVar.r(), z).a(wVar, this);
            synchronized (this.d) {
                this.k = a;
            }
            return a;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private c a(int i, int i2, int i3, boolean z, boolean z2) throws IOException {
        c a;
        while (true) {
            a = a(i, i2, i3, z);
            synchronized (this.d) {
                if (a.b != 0) {
                    if (a.a(z2)) {
                        break;
                    }
                    d();
                } else {
                    break;
                }
            }
        }
        return a;
    }

    private c a(int i, int i2, int i3, boolean z) throws IOException {
        c cVar;
        Socket socket = null;
        synchronized (this.d) {
            if (this.i) {
                throw new IllegalStateException("released");
            } else if (this.k != null) {
                throw new IllegalStateException("codec != null");
            } else if (this.j) {
                throw new IOException("Canceled");
            } else {
                cVar = this.h;
                if (cVar == null || cVar.a) {
                    okhttp3.internal.a.a.a(this.d, this.a, this, null);
                    if (this.h != null) {
                        cVar = this.h;
                    } else {
                        ac acVar = this.c;
                        if (acVar == null) {
                            acVar = this.f.b();
                        }
                        synchronized (this.d) {
                            if (this.j) {
                                throw new IOException("Canceled");
                            }
                            okhttp3.internal.a.a.a(this.d, this.a, this, acVar);
                            if (this.h != null) {
                                this.c = acVar;
                                cVar = this.h;
                            } else {
                                this.c = acVar;
                                this.g = 0;
                                c cVar2 = new c(this.d, acVar);
                                a(cVar2);
                                cVar2.a(i, i2, i3, z);
                                g().b(cVar2.a());
                                synchronized (this.d) {
                                    okhttp3.internal.a.a.b(this.d, cVar2);
                                    if (cVar2.e()) {
                                        Socket a = okhttp3.internal.a.a.a(this.d, this.a, this);
                                        cVar = this.h;
                                        socket = a;
                                    } else {
                                        cVar = cVar2;
                                    }
                                }
                                okhttp3.internal.c.a(socket);
                            }
                        }
                    }
                }
            }
        }
        return cVar;
    }

    public void a(boolean z, c cVar) {
        Socket a;
        synchronized (this.d) {
            if (cVar != null) {
                if (cVar == this.k) {
                    if (!z) {
                        c cVar2 = this.h;
                        cVar2.b++;
                    }
                    a = a(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.k + " but was " + cVar);
        }
        okhttp3.internal.c.a(a);
    }

    public c a() {
        c cVar;
        synchronized (this.d) {
            cVar = this.k;
        }
        return cVar;
    }

    private d g() {
        return okhttp3.internal.a.a.a(this.d);
    }

    public synchronized c b() {
        return this.h;
    }

    public void c() {
        Socket a;
        synchronized (this.d) {
            a = a(false, true, false);
        }
        okhttp3.internal.c.a(a);
    }

    public void d() {
        Socket a;
        synchronized (this.d) {
            a = a(true, false, false);
        }
        okhttp3.internal.c.a(a);
    }

    private Socket a(boolean z, boolean z2, boolean z3) {
        if (b || Thread.holdsLock(this.d)) {
            if (z3) {
                this.k = null;
            }
            if (z2) {
                this.i = true;
            }
            if (this.h == null) {
                return null;
            }
            if (z) {
                this.h.a = true;
            }
            if (this.k != null) {
                return null;
            }
            if (!this.i && !this.h.a) {
                return null;
            }
            Socket c;
            c(this.h);
            if (this.h.d.isEmpty()) {
                this.h.e = System.nanoTime();
                if (okhttp3.internal.a.a.a(this.d, this.h)) {
                    c = this.h.c();
                    this.h = null;
                    return c;
                }
            }
            c = null;
            this.h = null;
            return c;
        }
        throw new AssertionError();
    }

    public void e() {
        synchronized (this.d) {
            this.j = true;
            c cVar = this.k;
            c cVar2 = this.h;
        }
        if (cVar != null) {
            cVar.c();
        } else if (cVar2 != null) {
            cVar2.b();
        }
    }

    public void a(IOException iOException) {
        Socket a;
        boolean z = false;
        synchronized (this.d) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.g++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.g > 1) {
                    this.c = null;
                }
                a = a(z, false, true);
            } else {
                if (this.h != null && (!this.h.e() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.h.b == 0) {
                        if (!(this.c == null || iOException == null)) {
                            this.f.a(this.c, iOException);
                        }
                        this.c = null;
                    }
                }
                a = a(z, false, true);
            }
            z = true;
            a = a(z, false, true);
        }
        okhttp3.internal.c.a(a);
    }

    public void a(c cVar) {
        if (!b && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        } else if (this.h != null) {
            throw new IllegalStateException();
        } else {
            this.h = cVar;
            cVar.d.add(new a(this, this.e));
        }
    }

    private void c(c cVar) {
        int size = cVar.d.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) cVar.d.get(i)).get() == this) {
                cVar.d.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket b(c cVar) {
        if (!b && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        } else if (this.k == null && this.h.d.size() == 1) {
            Reference reference = (Reference) this.h.d.get(0);
            Socket a = a(true, false, false);
            this.h = cVar;
            cVar.d.add(reference);
            return a;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean f() {
        return this.c != null || this.f.a();
    }

    public String toString() {
        c b = b();
        return b != null ? b.toString() : this.a.toString();
    }
}
