package okhttp3.internal.b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.g;
import okhttp3.i;
import okhttp3.internal.c;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.f;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.s;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.w;
import okhttp3.y;
import okhttp3.z;
import org.apache.http.entity.mime.MIME;

public final class j implements t {
    private final w a;
    private final boolean b;
    private f c;
    private Object d;
    private volatile boolean e;

    public j(w wVar, boolean z) {
        this.a = wVar;
        this.b = z;
    }

    public void a() {
        this.e = true;
        f fVar = this.c;
        if (fVar != null) {
            fVar.e();
        }
    }

    public boolean b() {
        return this.e;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public aa intercept(a aVar) throws IOException {
        boolean z;
        y a = aVar.a();
        this.c = new f(this.a.p(), a(a.a()), this.d);
        aa aaVar = null;
        int i = 0;
        y yVar = a;
        while (!this.e) {
            try {
                aa a2 = ((g) aVar).a(yVar, this.c, null, null);
                if (aaVar != null) {
                    a2 = a2.i().c(aaVar.i().a(null).a()).a();
                }
                yVar = a(a2);
                if (yVar == null) {
                    if (!this.b) {
                        this.c.c();
                    }
                    return a2;
                }
                c.a(a2.h());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.c.c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                } else if (yVar.d() instanceof l) {
                    this.c.c();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", a2.c());
                } else {
                    if (!a(a2, yVar.a())) {
                        this.c.c();
                        this.c = new f(this.a.p(), a(yVar.a()), this.d);
                    } else if (this.c.a() != null) {
                        throw new IllegalStateException("Closing the body of " + a2 + " didn't close its backing stream. Bad interceptor?");
                    }
                    i = i2;
                    aaVar = a2;
                }
            } catch (RouteException e) {
                if (!a(e.getLastConnectException(), false, yVar)) {
                    throw e.getLastConnectException();
                }
            } catch (IOException e2) {
                if (e2 instanceof ConnectionShutdownException) {
                    z = false;
                } else {
                    z = true;
                }
                if (!a(e2, z, yVar)) {
                    throw e2;
                }
            } catch (Throwable th) {
                this.c.a(null);
                this.c.c();
            }
        }
        this.c.c();
        throw new IOException("Canceled");
    }

    private okhttp3.a a(s sVar) {
        SSLSocketFactory k;
        HostnameVerifier l;
        g gVar = null;
        if (sVar.c()) {
            k = this.a.k();
            l = this.a.l();
            gVar = this.a.m();
        } else {
            l = null;
            k = null;
        }
        return new okhttp3.a(sVar.f(), sVar.g(), this.a.i(), this.a.j(), k, l, gVar, this.a.o(), this.a.d(), this.a.u(), this.a.v(), this.a.e());
    }

    private boolean a(IOException iOException, boolean z, y yVar) {
        this.c.a(iOException);
        if (!this.a.s()) {
            return false;
        }
        if ((!z || !(yVar.d() instanceof l)) && a(iOException, z) && this.c.f()) {
            return true;
        }
        return false;
    }

    private boolean a(IOException iOException, boolean z) {
        boolean z2 = true;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                z2 = false;
            }
            return z2;
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    private y a(aa aaVar) throws IOException {
        z zVar = null;
        if (aaVar == null) {
            throw new IllegalStateException();
        }
        i b = this.c.b();
        ac a = b != null ? b.a() : null;
        int c = aaVar.c();
        String b2 = aaVar.a().b();
        switch (c) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!(b2.equals("GET") || b2.equals("HEAD"))) {
                    return null;
                }
            case 401:
                return this.a.n().a(a, aaVar);
            case 407:
                Proxy b3;
                if (a != null) {
                    b3 = a.b();
                } else {
                    b3 = this.a.d();
                }
                if (b3.type() == Type.HTTP) {
                    return this.a.o().a(a, aaVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (aaVar.a().d() instanceof l) {
                    return null;
                }
                return aaVar.a();
            default:
                return null;
        }
        if (!this.a.r()) {
            return null;
        }
        String a2 = aaVar.a("Location");
        if (a2 == null) {
            return null;
        }
        s c2 = aaVar.a().a().c(a2);
        if (c2 == null) {
            return null;
        }
        if (!c2.b().equals(aaVar.a().a().b()) && !this.a.q()) {
            return null;
        }
        y.a e = aaVar.a().e();
        if (f.c(b2)) {
            boolean d = f.d(b2);
            if (f.e(b2)) {
                e.a("GET", null);
            } else {
                if (d) {
                    zVar = aaVar.a().d();
                }
                e.a(b2, zVar);
            }
            if (!d) {
                e.b("Transfer-Encoding");
                e.b("Content-Length");
                e.b(MIME.CONTENT_TYPE);
            }
        }
        if (!a(aaVar, c2)) {
            e.b("Authorization");
        }
        return e.a(c2).b();
    }

    private boolean a(aa aaVar, s sVar) {
        s a = aaVar.a().a();
        return a.f().equals(sVar.f()) && a.g() == sVar.g() && a.b().equals(sVar.b());
    }
}
