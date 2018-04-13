package okhttp3.internal.connection;

import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import com.facebook.common.time.Clock;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.g;
import okhttp3.i;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.e;
import okhttp3.internal.http2.e.a;
import okhttp3.internal.http2.e.b;
import okhttp3.j;
import okhttp3.k;
import okhttp3.q;
import okhttp3.s;
import okhttp3.w;
import okhttp3.y;
import okio.d;
import okio.r;

public final class c extends b implements i {
    public boolean a;
    public int b;
    public int c = 1;
    public final List<Reference<f>> d = new ArrayList();
    public long e = Clock.MAX_TIME;
    private final j g;
    private final ac h;
    private Socket i;
    private Socket j;
    private q k;
    private Protocol l;
    private e m;
    private okio.e n;
    private d o;

    public c(j jVar, ac acVar) {
        this.g = jVar;
        this.h = acVar;
    }

    public void a(int i, int i2, int i3, boolean z) {
        if (this.l != null) {
            throw new IllegalStateException("already connected");
        }
        List f = this.h.a().f();
        b bVar = new b(f);
        if (this.h.a().i() == null) {
            if (f.contains(k.c)) {
                String f2 = this.h.a().a().f();
                if (!okhttp3.internal.e.e.b().b(f2)) {
                    throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + f2 + " not permitted by network security policy"));
                }
            }
            throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
        }
        RouteException routeException = null;
        do {
            try {
                if (this.h.d()) {
                    a(i, i2, i3);
                } else {
                    a(i, i2);
                }
                a(bVar);
                if (this.m != null) {
                    synchronized (this.g) {
                        this.c = this.m.a();
                    }
                    return;
                }
                return;
            } catch (IOException e) {
                okhttp3.internal.c.a(this.j);
                okhttp3.internal.c.a(this.i);
                this.j = null;
                this.i = null;
                this.n = null;
                this.o = null;
                this.k = null;
                this.l = null;
                this.m = null;
                if (routeException == null) {
                    routeException = new RouteException(e);
                } else {
                    routeException.addConnectException(e);
                }
                if (!z) {
                    break;
                } else if (!bVar.a(e)) {
                }
                throw routeException;
            }
        } while (bVar.a(e));
        throw routeException;
    }

    private void a(int i, int i2, int i3) throws IOException {
        y f = f();
        s a = f.a();
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: " + 21);
            }
            a(i, i2);
            f = a(i2, i3, f, a);
            if (f != null) {
                okhttp3.internal.c.a(this.i);
                this.i = null;
                this.o = null;
                this.n = null;
            } else {
                return;
            }
        }
    }

    private void a(int i, int i2) throws IOException {
        Proxy b = this.h.b();
        Socket createSocket = (b.type() == Type.DIRECT || b.type() == Type.HTTP) ? this.h.a().c().createSocket() : new Socket(b);
        this.i = createSocket;
        this.i.setSoTimeout(i2);
        try {
            okhttp3.internal.e.e.b().a(this.i, this.h.c(), i);
            this.n = okio.k.a(okio.k.b(this.i));
            this.o = okio.k.a(okio.k.a(this.i));
        } catch (Throwable e) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.h.c());
            connectException.initCause(e);
            throw connectException;
        }
    }

    private void a(b bVar) throws IOException {
        if (this.h.a().i() == null) {
            this.l = Protocol.HTTP_1_1;
            this.j = this.i;
            return;
        }
        b(bVar);
        if (this.l == Protocol.HTTP_2) {
            this.j.setSoTimeout(0);
            this.m = new a(true).a(this.j, this.h.a().a().f(), this.n, this.o).a(this).a();
            this.m.c();
        }
    }

    private void b(b bVar) throws IOException {
        Throwable th;
        Socket socket;
        AssertionError assertionError;
        Throwable th2;
        String str = null;
        okhttp3.a a = this.h.a();
        try {
            Socket socket2 = (SSLSocket) a.i().createSocket(this.i, a.a().f(), a.a().g(), true);
            try {
                k a2 = bVar.a((SSLSocket) socket2);
                if (a2.d()) {
                    okhttp3.internal.e.e.b().a((SSLSocket) socket2, a.a().f(), a.e());
                }
                socket2.startHandshake();
                q a3 = q.a(socket2.getSession());
                if (a.j().verify(a.a().f(), socket2.getSession())) {
                    a.k().check(a.a().f(), a3.c());
                    if (a2.d()) {
                        str = okhttp3.internal.e.e.b().a((SSLSocket) socket2);
                    }
                    this.j = socket2;
                    this.n = okio.k.a(okio.k.b(this.j));
                    this.o = okio.k.a(okio.k.a(this.j));
                    this.k = a3;
                    this.l = str != null ? Protocol.get(str) : Protocol.HTTP_1_1;
                    if (socket2 != null) {
                        okhttp3.internal.e.e.b().b((SSLSocket) socket2);
                        return;
                    }
                    return;
                }
                Certificate certificate = (X509Certificate) a3.c().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + a.a().f() + " not verified:\n    certificate: " + g.a(certificate) + "\n    DN: " + certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + okhttp3.internal.f.d.a(certificate));
            } catch (Throwable e) {
                th = e;
                socket = socket2;
                assertionError = th;
                try {
                    if (okhttp3.internal.c.a(assertionError)) {
                        throw new IOException(assertionError);
                    }
                    throw assertionError;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (Throwable e2) {
                th = e2;
                socket = socket2;
                th2 = th;
                if (socket != null) {
                    okhttp3.internal.e.e.b().b((SSLSocket) socket);
                }
                okhttp3.internal.c.a(socket);
                throw th2;
            }
        } catch (AssertionError e3) {
            assertionError = e3;
            if (okhttp3.internal.c.a(assertionError)) {
                throw new IOException(assertionError);
            }
            throw assertionError;
        }
    }

    private y a(int i, int i2, y yVar, s sVar) throws IOException {
        String str = "CONNECT " + okhttp3.internal.c.a(sVar, true) + " HTTP/1.1";
        aa a;
        do {
            okhttp3.internal.c.a aVar = new okhttp3.internal.c.a(null, null, this.n, this.o);
            this.n.a().a((long) i, TimeUnit.MILLISECONDS);
            this.o.a().a((long) i2, TimeUnit.MILLISECONDS);
            aVar.a(yVar.c(), str);
            aVar.b();
            a = aVar.a(false).a(yVar).a();
            long a2 = okhttp3.internal.b.e.a(a);
            if (a2 == -1) {
                a2 = 0;
            }
            r b = aVar.b(a2);
            okhttp3.internal.c.b(b, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            b.close();
            switch (a.c()) {
                case 200:
                    if (this.n.c().f() && this.o.c().f()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case 407:
                    yVar = this.h.a().d().a(this.h, a);
                    if (yVar != null) {
                        break;
                    }
                    throw new IOException("Failed to authenticate with proxy");
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a.c());
            }
        } while (!BoxingVoteBean.BOXING_VOTE_STATE_CLOSE.equalsIgnoreCase(a.a("Connection")));
        return yVar;
    }

    private y f() {
        return new y.a().a(this.h.a().a()).a("Host", okhttp3.internal.c.a(this.h.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", okhttp3.internal.d.a()).b();
    }

    public boolean a(okhttp3.a aVar) {
        return this.d.size() < this.c && aVar.equals(a().a()) && !this.a;
    }

    public okhttp3.internal.b.c a(w wVar, f fVar) throws SocketException {
        if (this.m != null) {
            return new okhttp3.internal.http2.d(wVar, fVar, this.m);
        }
        this.j.setSoTimeout(wVar.b());
        this.n.a().a((long) wVar.b(), TimeUnit.MILLISECONDS);
        this.o.a().a((long) wVar.c(), TimeUnit.MILLISECONDS);
        return new okhttp3.internal.c.a(wVar, fVar, this.n, this.o);
    }

    public ac a() {
        return this.h;
    }

    public void b() {
        okhttp3.internal.c.a(this.i);
    }

    public Socket c() {
        return this.j;
    }

    public boolean a(boolean z) {
        if (this.j.isClosed() || this.j.isInputShutdown() || this.j.isOutputShutdown()) {
            return false;
        }
        if (this.m != null) {
            if (this.m.d()) {
                return false;
            }
            return true;
        } else if (!z) {
            return true;
        } else {
            int soTimeout;
            try {
                soTimeout = this.j.getSoTimeout();
                this.j.setSoTimeout(1);
                if (this.n.f()) {
                    this.j.setSoTimeout(soTimeout);
                    return false;
                }
                this.j.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException e) {
                return true;
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                this.j.setSoTimeout(soTimeout);
            }
        }
    }

    public void a(okhttp3.internal.http2.g gVar) throws IOException {
        gVar.a(ErrorCode.REFUSED_STREAM);
    }

    public void a(e eVar) {
        synchronized (this.g) {
            this.c = eVar.a();
        }
    }

    public q d() {
        return this.k;
    }

    public boolean e() {
        return this.m != null;
    }

    public String toString() {
        return "Connection{" + this.h.a().a().f() + ":" + this.h.a().a().g() + ", proxy=" + this.h.b() + " hostAddress=" + this.h.c() + " cipherSuite=" + (this.k != null ? this.k.b() : "none") + " protocol=" + this.l + '}';
    }
}
