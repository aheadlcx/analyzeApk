package okhttp3.internal.b;

import com.qq.e.comm.constants.ErrorCode$NetWorkError;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.connect.common.Constants;
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
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.g;
import okhttp3.i;
import okhttp3.internal.c;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.f;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.w;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;

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
        this.c = new f(this.a.o(), a(a.a()), this.d);
        aa aaVar = null;
        int i = 0;
        y yVar = a;
        while (!this.e) {
            try {
                aa a2 = ((g) aVar).a(yVar, this.c, null, null);
                if (aaVar != null) {
                    a2 = a2.h().c(aaVar.h().a(null).a()).a();
                }
                yVar = a(a2);
                if (yVar == null) {
                    if (!this.b) {
                        this.c.c();
                    }
                    return a2;
                }
                c.a(a2.g());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.c.c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                } else if (yVar.d() instanceof l) {
                    this.c.c();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", a2.b());
                } else {
                    if (!a(a2, yVar.a())) {
                        this.c.c();
                        this.c = new f(this.a.o(), a(yVar.a()), this.d);
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

    private okhttp3.a a(HttpUrl httpUrl) {
        SSLSocketFactory j;
        HostnameVerifier k;
        g gVar = null;
        if (httpUrl.c()) {
            j = this.a.j();
            k = this.a.k();
            gVar = this.a.l();
        } else {
            k = null;
            j = null;
        }
        return new okhttp3.a(httpUrl.f(), httpUrl.g(), this.a.h(), this.a.i(), j, k, gVar, this.a.n(), this.a.d(), this.a.t(), this.a.u(), this.a.e());
    }

    private boolean a(IOException iOException, boolean z, y yVar) {
        this.c.a(iOException);
        if (!this.a.r()) {
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
        ac a;
        i b = this.c.b();
        if (b != null) {
            a = b.a();
        } else {
            a = null;
        }
        int b2 = aaVar.b();
        String b3 = aaVar.a().b();
        switch (b2) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
                if (!(b3.equals(Constants.HTTP_GET) || b3.equals("HEAD"))) {
                    return null;
                }
            case 401:
                return this.a.m().a(a, aaVar);
            case ErrorCode$NetWorkError.RETRY_TIME_JS_ERROR /*407*/:
                Proxy b4;
                if (a != null) {
                    b4 = a.b();
                } else {
                    b4 = this.a.d();
                }
                if (b4.type() == Type.HTTP) {
                    return this.a.n().a(a, aaVar);
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
        if (!this.a.q()) {
            return null;
        }
        String a2 = aaVar.a("Location");
        if (a2 == null) {
            return null;
        }
        HttpUrl c = aaVar.a().a().c(a2);
        if (c == null) {
            return null;
        }
        if (!c.b().equals(aaVar.a().a().b()) && !this.a.p()) {
            return null;
        }
        y$a f = aaVar.a().f();
        if (f.c(b3)) {
            boolean d = f.d(b3);
            if (f.e(b3)) {
                f.a(Constants.HTTP_GET, null);
            } else {
                if (d) {
                    zVar = aaVar.a().d();
                }
                f.a(b3, zVar);
            }
            if (!d) {
                f.b("Transfer-Encoding");
                f.b("Content-Length");
                f.b("Content-Type");
            }
        }
        if (!a(aaVar, c)) {
            f.b("Authorization");
        }
        return f.a(c).d();
    }

    private boolean a(aa aaVar, HttpUrl httpUrl) {
        HttpUrl a = aaVar.a().a();
        return a.f().equals(httpUrl.f()) && a.g() == httpUrl.g() && a.b().equals(httpUrl.b());
    }
}
