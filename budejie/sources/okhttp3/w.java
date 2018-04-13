package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.a.f;
import okhttp3.internal.c;
import okhttp3.internal.f.b;
import okhttp3.internal.f.d;

public class w implements Cloneable, okhttp3.e.a {
    static final List<Protocol> a = c.a(Protocol.HTTP_2, Protocol.HTTP_1_1);
    static final List<k> b = c.a(k.a, k.b, k.c);
    final int A;
    final int B;
    final n c;
    final Proxy d;
    final List<Protocol> e;
    final List<k> f;
    final List<t> g;
    final List<t> h;
    final ProxySelector i;
    final m j;
    final c k;
    final f l;
    final SocketFactory m;
    final SSLSocketFactory n;
    final b o;
    final HostnameVerifier p;
    final g q;
    final b r;
    final b s;
    final j t;
    final o u;
    final boolean v;
    final boolean w;
    final boolean x;
    final int y;
    final int z;

    public static final class a {
        n a;
        Proxy b;
        List<Protocol> c;
        List<k> d;
        final List<t> e;
        final List<t> f;
        ProxySelector g;
        m h;
        c i;
        f j;
        SocketFactory k;
        SSLSocketFactory l;
        b m;
        HostnameVerifier n;
        g o;
        b p;
        b q;
        j r;
        o s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;
        int z;

        public a() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new n();
            this.c = w.a;
            this.d = w.b;
            this.g = ProxySelector.getDefault();
            this.h = m.a;
            this.k = SocketFactory.getDefault();
            this.n = d.a;
            this.o = g.a;
            this.p = b.a;
            this.q = b.a;
            this.r = new j();
            this.s = o.a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = 10000;
            this.x = 10000;
            this.y = 10000;
            this.z = 0;
        }

        a(w wVar) {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = wVar.c;
            this.b = wVar.d;
            this.c = wVar.e;
            this.d = wVar.f;
            this.e.addAll(wVar.g);
            this.f.addAll(wVar.h);
            this.g = wVar.i;
            this.h = wVar.j;
            this.j = wVar.l;
            this.i = wVar.k;
            this.k = wVar.m;
            this.l = wVar.n;
            this.m = wVar.o;
            this.n = wVar.p;
            this.o = wVar.q;
            this.p = wVar.r;
            this.q = wVar.s;
            this.r = wVar.t;
            this.s = wVar.u;
            this.t = wVar.v;
            this.u = wVar.w;
            this.v = wVar.x;
            this.w = wVar.y;
            this.x = wVar.z;
            this.y = wVar.A;
            this.z = wVar.B;
        }

        public a a(long j, TimeUnit timeUnit) {
            this.w = a(com.alipay.sdk.data.a.f, j, timeUnit);
            return this;
        }

        public a b(long j, TimeUnit timeUnit) {
            this.x = a(com.alipay.sdk.data.a.f, j, timeUnit);
            return this;
        }

        public a c(long j, TimeUnit timeUnit) {
            this.y = a(com.alipay.sdk.data.a.f, j, timeUnit);
            return this;
        }

        private static int a(String str, long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException(str + " < 0");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > 2147483647L) {
                    throw new IllegalArgumentException(str + " too large.");
                } else if (toMillis != 0 || j <= 0) {
                    return (int) toMillis;
                } else {
                    throw new IllegalArgumentException(str + " too small.");
                }
            }
        }

        public a a(c cVar) {
            this.i = cVar;
            this.j = null;
            return this;
        }

        public a a(boolean z) {
            this.v = z;
            return this;
        }

        public a a(t tVar) {
            this.e.add(tVar);
            return this;
        }

        public a b(t tVar) {
            this.f.add(tVar);
            return this;
        }

        public w a() {
            return new w(this);
        }
    }

    static {
        okhttp3.internal.a.a = new okhttp3.internal.a() {
            public void a(okhttp3.r.a aVar, String str) {
                aVar.a(str);
            }

            public void a(okhttp3.r.a aVar, String str, String str2) {
                aVar.b(str, str2);
            }

            public boolean a(j jVar, okhttp3.internal.connection.c cVar) {
                return jVar.b(cVar);
            }

            public okhttp3.internal.connection.c a(j jVar, a aVar, okhttp3.internal.connection.f fVar) {
                return jVar.a(aVar, fVar);
            }

            public Socket b(j jVar, a aVar, okhttp3.internal.connection.f fVar) {
                return jVar.b(aVar, fVar);
            }

            public void b(j jVar, okhttp3.internal.connection.c cVar) {
                jVar.a(cVar);
            }

            public okhttp3.internal.connection.d a(j jVar) {
                return jVar.a;
            }

            public int a(okhttp3.aa.a aVar) {
                return aVar.c;
            }

            public void a(k kVar, SSLSocket sSLSocket, boolean z) {
                kVar.a(sSLSocket, z);
            }
        };
    }

    public w() {
        this(new a());
    }

    w(a aVar) {
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
        this.f = aVar.d;
        this.g = c.a(aVar.e);
        this.h = c.a(aVar.f);
        this.i = aVar.g;
        this.j = aVar.h;
        this.k = aVar.i;
        this.l = aVar.j;
        this.m = aVar.k;
        Object obj = null;
        for (k kVar : this.f) {
            Object obj2;
            if (obj != null || kVar.a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            obj = obj2;
        }
        if (aVar.l != null || obj == null) {
            this.n = aVar.l;
            this.o = aVar.m;
        } else {
            X509TrustManager z = z();
            this.n = a(z);
            this.o = b.a(z);
        }
        this.p = aVar.n;
        this.q = aVar.o.a(this.o);
        this.r = aVar.p;
        this.s = aVar.q;
        this.t = aVar.r;
        this.u = aVar.s;
        this.v = aVar.t;
        this.w = aVar.u;
        this.x = aVar.v;
        this.y = aVar.w;
        this.z = aVar.x;
        this.A = aVar.y;
        this.B = aVar.z;
    }

    private X509TrustManager z() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    public int a() {
        return this.y;
    }

    public int b() {
        return this.z;
    }

    public int c() {
        return this.A;
    }

    public Proxy d() {
        return this.d;
    }

    public ProxySelector e() {
        return this.i;
    }

    public m f() {
        return this.j;
    }

    public c g() {
        return this.k;
    }

    f h() {
        return this.k != null ? this.k.a : this.l;
    }

    public o i() {
        return this.u;
    }

    public SocketFactory j() {
        return this.m;
    }

    public SSLSocketFactory k() {
        return this.n;
    }

    public HostnameVerifier l() {
        return this.p;
    }

    public g m() {
        return this.q;
    }

    public b n() {
        return this.s;
    }

    public b o() {
        return this.r;
    }

    public j p() {
        return this.t;
    }

    public boolean q() {
        return this.v;
    }

    public boolean r() {
        return this.w;
    }

    public boolean s() {
        return this.x;
    }

    public n t() {
        return this.c;
    }

    public List<Protocol> u() {
        return this.e;
    }

    public List<k> v() {
        return this.f;
    }

    public List<t> w() {
        return this.g;
    }

    public List<t> x() {
        return this.h;
    }

    public e a(y yVar) {
        return new x(this, yVar, false);
    }

    public a y() {
        return new a(this);
    }
}
