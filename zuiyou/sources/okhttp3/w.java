package okhttp3;

import com.iflytek.cloud.SpeechConstant;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.a.e;
import okhttp3.internal.c;
import okhttp3.internal.f.b;
import okhttp3.internal.f.d;

public class w implements Cloneable, e$a {
    static final List<Protocol> a = c.a(new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});
    static final List<k> b = c.a(new k[]{k.a, k.c});
    final int A;
    final int B;
    final int C;
    final n c;
    @Nullable
    final Proxy d;
    final List<Protocol> e;
    final List<k> f;
    final List<t> g;
    final List<t> h;
    final okhttp3.p.a i;
    final ProxySelector j;
    final m k;
    @Nullable
    final c l;
    @Nullable
    final e m;
    final SocketFactory n;
    @Nullable
    final SSLSocketFactory o;
    @Nullable
    final b p;
    final HostnameVerifier q;
    final g r;
    final b s;
    final b t;
    final j u;
    final o v;
    final boolean w;
    final boolean x;
    final boolean y;
    final int z;

    public static final class a {
        int A = 0;
        n a = new n();
        @Nullable
        Proxy b;
        List<Protocol> c = w.a;
        List<k> d = w.b;
        final List<t> e = new ArrayList();
        final List<t> f = new ArrayList();
        okhttp3.p.a g = p.a(p.a);
        ProxySelector h = ProxySelector.getDefault();
        m i = m.a;
        @Nullable
        c j;
        @Nullable
        e k;
        SocketFactory l = SocketFactory.getDefault();
        @Nullable
        SSLSocketFactory m;
        @Nullable
        b n;
        HostnameVerifier o = d.a;
        g p = g.a;
        b q = b.a;
        b r = b.a;
        j s = new j();
        o t = o.a;
        boolean u = true;
        boolean v = true;
        boolean w = true;
        int x = 10000;
        int y = 10000;
        int z = 10000;

        public a a(long j, TimeUnit timeUnit) {
            this.x = a(SpeechConstant.NET_TIMEOUT, j, timeUnit);
            return this;
        }

        public a b(long j, TimeUnit timeUnit) {
            this.y = a(SpeechConstant.NET_TIMEOUT, j, timeUnit);
            return this;
        }

        public a c(long j, TimeUnit timeUnit) {
            this.z = a(SpeechConstant.NET_TIMEOUT, j, timeUnit);
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

        public a a(@Nullable Proxy proxy) {
            this.b = proxy;
            return this;
        }

        public a a(@Nullable c cVar) {
            this.j = cVar;
            this.k = null;
            return this;
        }

        public a a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (x509TrustManager == null) {
                throw new NullPointerException("trustManager == null");
            } else {
                this.m = sSLSocketFactory;
                this.n = b.a(x509TrustManager);
                return this;
            }
        }

        public a a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.o = hostnameVerifier;
            return this;
        }

        public a a(boolean z) {
            this.u = z;
            return this;
        }

        public a b(boolean z) {
            this.v = z;
            return this;
        }

        public a c(boolean z) {
            this.w = z;
            return this;
        }

        public a a(n nVar) {
            if (nVar == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.a = nVar;
            return this;
        }

        public a a(t tVar) {
            this.e.add(tVar);
            return this;
        }

        public w a() {
            return new w(this);
        }
    }

    static {
        okhttp3.internal.a.a = new w$1();
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
        this.n = aVar.l;
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
        if (aVar.m != null || obj == null) {
            this.o = aVar.m;
            this.p = aVar.n;
        } else {
            X509TrustManager y = y();
            this.o = a(y);
            this.p = b.a(y);
        }
        this.q = aVar.o;
        this.r = aVar.p.a(this.p);
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
        this.C = aVar.A;
    }

    private X509TrustManager y() {
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
        return this.z;
    }

    public int b() {
        return this.A;
    }

    public int c() {
        return this.B;
    }

    public Proxy d() {
        return this.d;
    }

    public ProxySelector e() {
        return this.j;
    }

    public m f() {
        return this.k;
    }

    e g() {
        return this.l != null ? this.l.a : this.m;
    }

    public o h() {
        return this.v;
    }

    public SocketFactory i() {
        return this.n;
    }

    public SSLSocketFactory j() {
        return this.o;
    }

    public HostnameVerifier k() {
        return this.q;
    }

    public g l() {
        return this.r;
    }

    public b m() {
        return this.t;
    }

    public b n() {
        return this.s;
    }

    public j o() {
        return this.u;
    }

    public boolean p() {
        return this.w;
    }

    public boolean q() {
        return this.x;
    }

    public boolean r() {
        return this.y;
    }

    public n s() {
        return this.c;
    }

    public List<Protocol> t() {
        return this.e;
    }

    public List<k> u() {
        return this.f;
    }

    public List<t> v() {
        return this.g;
    }

    public List<t> w() {
        return this.h;
    }

    okhttp3.p.a x() {
        return this.i;
    }

    public e a(y yVar) {
        return new x(this, yVar, false);
    }
}
