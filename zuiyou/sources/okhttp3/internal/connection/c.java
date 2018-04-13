package okhttp3.internal.connection;

import com.qq.e.comm.constants.ErrorCode$NetWorkError;
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
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.a;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.g;
import okhttp3.i;
import okhttp3.internal.f.d;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.e;
import okhttp3.internal.http2.e.b;
import okhttp3.j;
import okhttp3.k;
import okhttp3.r;
import okhttp3.w;
import okhttp3.y;
import okhttp3.y$a;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class c extends b implements i {
    public boolean a;
    public int b;
    public int c = 1;
    public final List<Reference<f>> d = new ArrayList();
    public long e = Long.MAX_VALUE;
    private final j g;
    private final ac h;
    private Socket i;
    private Socket j;
    private r k;
    private Protocol l;
    private e m;
    private BufferedSource n;
    private BufferedSink o;

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
        HttpUrl a = f.a();
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
        Socket createSocket;
        Proxy b = this.h.b();
        a a = this.h.a();
        if (b.type() == Type.DIRECT || b.type() == Type.HTTP) {
            createSocket = a.c().createSocket();
        } else {
            createSocket = new Socket(b);
        }
        this.i = createSocket;
        this.i.setSoTimeout(i2);
        try {
            okhttp3.internal.e.e.b().a(this.i, this.h.c(), i);
            try {
                this.n = Okio.buffer(Okio.source(this.i));
                this.o = Okio.buffer(Okio.sink(this.i));
            } catch (Throwable e) {
                if ("throw with null exception".equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (Throwable e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.h.c());
            connectException.initCause(e2);
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
            this.m = new e.a(true).a(this.j, this.h.a().a().f(), this.n, this.o).a(this).a();
            this.m.c();
        }
    }

    private void b(b bVar) throws IOException {
        Throwable th;
        Socket socket;
        AssertionError assertionError;
        Throwable th2;
        String str = null;
        a a = this.h.a();
        try {
            Socket socket2 = (SSLSocket) a.i().createSocket(this.i, a.a().f(), a.a().g(), true);
            try {
                k a2 = bVar.a((SSLSocket) socket2);
                if (a2.d()) {
                    okhttp3.internal.e.e.b().a((SSLSocket) socket2, a.a().f(), a.e());
                }
                socket2.startHandshake();
                r a3 = r.a(socket2.getSession());
                if (a.j().verify(a.a().f(), socket2.getSession())) {
                    Protocol protocol;
                    a.k().a(a.a().f(), a3.b());
                    if (a2.d()) {
                        str = okhttp3.internal.e.e.b().a((SSLSocket) socket2);
                    }
                    this.j = socket2;
                    this.n = Okio.buffer(Okio.source(this.j));
                    this.o = Okio.buffer(Okio.sink(this.j));
                    this.k = a3;
                    if (str != null) {
                        protocol = Protocol.get(str);
                    } else {
                        protocol = Protocol.HTTP_1_1;
                    }
                    this.l = protocol;
                    if (socket2 != null) {
                        okhttp3.internal.e.e.b().b((SSLSocket) socket2);
                        return;
                    }
                    return;
                }
                X509Certificate x509Certificate = (X509Certificate) a3.b().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + a.a().f() + " not verified:\n    certificate: " + g.a(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + d.a(x509Certificate));
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

    private y a(int i, int i2, y yVar, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + okhttp3.internal.c.a(httpUrl, true) + " HTTP/1.1";
        aa a;
        do {
            okhttp3.internal.c.a aVar = new okhttp3.internal.c.a(null, null, this.n, this.o);
            this.n.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.o.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            aVar.a(yVar.c(), str);
            aVar.b();
            a = aVar.a(false).a(yVar).a();
            long a2 = okhttp3.internal.b.e.a(a);
            if (a2 == -1) {
                a2 = 0;
            }
            Source b = aVar.b(a2);
            okhttp3.internal.c.b(b, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            b.close();
            switch (a.b()) {
                case 200:
                    if (this.n.buffer().exhausted() && this.o.buffer().exhausted()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case ErrorCode$NetWorkError.RETRY_TIME_JS_ERROR /*407*/:
                    yVar = this.h.a().d().a(this.h, a);
                    if (yVar != null) {
                        break;
                    }
                    throw new IOException("Failed to authenticate with proxy");
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a.b());
            }
        } while (!"close".equalsIgnoreCase(a.a("Connection")));
        return yVar;
    }

    private y f() {
        return new y$a().a(this.h.a().a()).a("Host", okhttp3.internal.c.a(this.h.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", okhttp3.internal.d.a()).d();
    }

    public boolean a(a aVar, @Nullable ac acVar) {
        if (this.d.size() >= this.c || this.a || !okhttp3.internal.a.a.a(this.h.a(), aVar)) {
            return false;
        }
        if (aVar.a().f().equals(a().a().a().f())) {
            return true;
        }
        if (this.m == null || acVar == null || acVar.b().type() != Type.DIRECT || this.h.b().type() != Type.DIRECT || !this.h.c().equals(acVar.c()) || acVar.a().j() != d.a || !a(aVar.a())) {
            return false;
        }
        try {
            aVar.k().a(aVar.a().f(), d().b());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    public boolean a(HttpUrl httpUrl) {
        if (httpUrl.g() != this.h.a().a().g()) {
            return false;
        }
        if (httpUrl.f().equals(this.h.a().a().f())) {
            return true;
        }
        boolean z = this.k != null && d.a.a(httpUrl.f(), (X509Certificate) this.k.b().get(0));
        return z;
    }

    public okhttp3.internal.b.c a(w wVar, f fVar) throws SocketException {
        if (this.m != null) {
            return new okhttp3.internal.http2.d(wVar, fVar, this.m);
        }
        this.j.setSoTimeout(wVar.b());
        this.n.timeout().timeout((long) wVar.b(), TimeUnit.MILLISECONDS);
        this.o.timeout().timeout((long) wVar.c(), TimeUnit.MILLISECONDS);
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
                if (this.n.exhausted()) {
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

    public r d() {
        return this.k;
    }

    public boolean e() {
        return this.m != null;
    }

    public String toString() {
        Object a;
        StringBuilder append = new StringBuilder().append("Connection{").append(this.h.a().a().f()).append(":").append(this.h.a().a().g()).append(", proxy=").append(this.h.b()).append(" hostAddress=").append(this.h.c()).append(" cipherSuite=");
        if (this.k != null) {
            a = this.k.a();
        } else {
            a = "none";
        }
        return append.append(a).append(" protocol=").append(this.l).append('}').toString();
    }
}
