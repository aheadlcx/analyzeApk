package okhttp3;

import com.alipay.sdk.data.a;
import com.umeng.commonsdk.proguard.g;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call.Factory;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;

public class OkHttpClient implements Cloneable, Factory, WebSocket.Factory {
    static final List<Protocol> a = Util.immutableList(Protocol.HTTP_2, Protocol.HTTP_1_1);
    static final List<ConnectionSpec> b = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    final int A;
    final int B;
    final int C;
    final Dispatcher c;
    @Nullable
    final Proxy d;
    final List<Protocol> e;
    final List<ConnectionSpec> f;
    final List<Interceptor> g;
    final List<Interceptor> h;
    final EventListener.Factory i;
    final ProxySelector j;
    final CookieJar k;
    @Nullable
    final Cache l;
    @Nullable
    final InternalCache m;
    final SocketFactory n;
    @Nullable
    final SSLSocketFactory o;
    @Nullable
    final CertificateChainCleaner p;
    final HostnameVerifier q;
    final CertificatePinner r;
    final Authenticator s;
    final Authenticator t;
    final ConnectionPool u;
    final Dns v;
    final boolean w;
    final boolean x;
    final boolean y;
    final int z;

    public static final class Builder {
        int A;
        Dispatcher a;
        @Nullable
        Proxy b;
        List<Protocol> c;
        List<ConnectionSpec> d;
        final List<Interceptor> e;
        final List<Interceptor> f;
        EventListener.Factory g;
        ProxySelector h;
        CookieJar i;
        @Nullable
        Cache j;
        @Nullable
        InternalCache k;
        SocketFactory l;
        @Nullable
        SSLSocketFactory m;
        @Nullable
        CertificateChainCleaner n;
        HostnameVerifier o;
        CertificatePinner p;
        Authenticator q;
        Authenticator r;
        ConnectionPool s;
        Dns t;
        boolean u;
        boolean v;
        boolean w;
        int x;
        int y;
        int z;

        public Builder() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new Dispatcher();
            this.c = OkHttpClient.a;
            this.d = OkHttpClient.b;
            this.g = EventListener.a(EventListener.NONE);
            this.h = ProxySelector.getDefault();
            this.i = CookieJar.NO_COOKIES;
            this.l = SocketFactory.getDefault();
            this.o = OkHostnameVerifier.INSTANCE;
            this.p = CertificatePinner.DEFAULT;
            this.q = Authenticator.NONE;
            this.r = Authenticator.NONE;
            this.s = new ConnectionPool();
            this.t = Dns.SYSTEM;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = 10000;
            this.y = 10000;
            this.z = 10000;
            this.A = 0;
        }

        Builder(OkHttpClient okHttpClient) {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = okHttpClient.c;
            this.b = okHttpClient.d;
            this.c = okHttpClient.e;
            this.d = okHttpClient.f;
            this.e.addAll(okHttpClient.g);
            this.f.addAll(okHttpClient.h);
            this.g = okHttpClient.i;
            this.h = okHttpClient.j;
            this.i = okHttpClient.k;
            this.k = okHttpClient.m;
            this.j = okHttpClient.l;
            this.l = okHttpClient.n;
            this.m = okHttpClient.o;
            this.n = okHttpClient.p;
            this.o = okHttpClient.q;
            this.p = okHttpClient.r;
            this.q = okHttpClient.s;
            this.r = okHttpClient.t;
            this.s = okHttpClient.u;
            this.t = okHttpClient.v;
            this.u = okHttpClient.w;
            this.v = okHttpClient.x;
            this.w = okHttpClient.y;
            this.x = okHttpClient.z;
            this.y = okHttpClient.A;
            this.z = okHttpClient.B;
            this.A = okHttpClient.C;
        }

        public Builder connectTimeout(long j, TimeUnit timeUnit) {
            this.x = Util.checkDuration(a.f, j, timeUnit);
            return this;
        }

        public Builder readTimeout(long j, TimeUnit timeUnit) {
            this.y = Util.checkDuration(a.f, j, timeUnit);
            return this;
        }

        public Builder writeTimeout(long j, TimeUnit timeUnit) {
            this.z = Util.checkDuration(a.f, j, timeUnit);
            return this;
        }

        public Builder pingInterval(long j, TimeUnit timeUnit) {
            this.A = Util.checkDuration(g.az, j, timeUnit);
            return this;
        }

        public Builder proxy(@Nullable Proxy proxy) {
            this.b = proxy;
            return this;
        }

        public Builder proxySelector(ProxySelector proxySelector) {
            this.h = proxySelector;
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar) {
            if (cookieJar == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.i = cookieJar;
            return this;
        }

        void a(@Nullable InternalCache internalCache) {
            this.k = internalCache;
            this.j = null;
        }

        public Builder cache(@Nullable Cache cache) {
            this.j = cache;
            this.k = null;
            return this;
        }

        public Builder dns(Dns dns) {
            if (dns == null) {
                throw new NullPointerException("dns == null");
            }
            this.t = dns;
            return this;
        }

        public Builder socketFactory(SocketFactory socketFactory) {
            if (socketFactory == null) {
                throw new NullPointerException("socketFactory == null");
            }
            this.l = socketFactory;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.m = sSLSocketFactory;
            this.n = Platform.get().buildCertificateChainCleaner(sSLSocketFactory);
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (x509TrustManager == null) {
                throw new NullPointerException("trustManager == null");
            } else {
                this.m = sSLSocketFactory;
                this.n = CertificateChainCleaner.get(x509TrustManager);
                return this;
            }
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.o = hostnameVerifier;
            return this;
        }

        public Builder certificatePinner(CertificatePinner certificatePinner) {
            if (certificatePinner == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.p = certificatePinner;
            return this;
        }

        public Builder authenticator(Authenticator authenticator) {
            if (authenticator == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.r = authenticator;
            return this;
        }

        public Builder proxyAuthenticator(Authenticator authenticator) {
            if (authenticator == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.q = authenticator;
            return this;
        }

        public Builder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.s = connectionPool;
            return this;
        }

        public Builder followSslRedirects(boolean z) {
            this.u = z;
            return this;
        }

        public Builder followRedirects(boolean z) {
            this.v = z;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean z) {
            this.w = z;
            return this;
        }

        public Builder dispatcher(Dispatcher dispatcher) {
            if (dispatcher == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.a = dispatcher;
            return this;
        }

        public Builder protocols(List<Protocol> list) {
            List arrayList = new ArrayList(list);
            if (!arrayList.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + arrayList);
            } else if (arrayList.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + arrayList);
            } else if (arrayList.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            } else {
                arrayList.remove(Protocol.SPDY_3);
                this.c = Collections.unmodifiableList(arrayList);
                return this;
            }
        }

        public Builder connectionSpecs(List<ConnectionSpec> list) {
            this.d = Util.immutableList((List) list);
            return this;
        }

        public List<Interceptor> interceptors() {
            return this.e;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptor == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.e.add(interceptor);
            return this;
        }

        public List<Interceptor> networkInterceptors() {
            return this.f;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            if (interceptor == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f.add(interceptor);
            return this;
        }

        public Builder eventListener(EventListener eventListener) {
            if (eventListener == null) {
                throw new NullPointerException("eventListener == null");
            }
            this.g = EventListener.a(eventListener);
            return this;
        }

        public Builder eventListenerFactory(EventListener.Factory factory) {
            if (factory == null) {
                throw new NullPointerException("eventListenerFactory == null");
            }
            this.g = factory;
            return this;
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }
    }

    static {
        Internal.instance = new m();
    }

    public OkHttpClient() {
        this(new Builder());
    }

    OkHttpClient(Builder builder) {
        this.c = builder.a;
        this.d = builder.b;
        this.e = builder.c;
        this.f = builder.d;
        this.g = Util.immutableList(builder.e);
        this.h = Util.immutableList(builder.f);
        this.i = builder.g;
        this.j = builder.h;
        this.k = builder.i;
        this.l = builder.j;
        this.m = builder.k;
        this.n = builder.l;
        Object obj = null;
        for (ConnectionSpec connectionSpec : this.f) {
            Object obj2;
            if (obj != null || connectionSpec.isTls()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            obj = obj2;
        }
        if (builder.m != null || obj == null) {
            this.o = builder.m;
            this.p = builder.n;
        } else {
            X509TrustManager b = b();
            this.o = a(b);
            this.p = CertificateChainCleaner.get(b);
        }
        this.q = builder.o;
        this.r = builder.p.a(this.p);
        this.s = builder.q;
        this.t = builder.r;
        this.u = builder.s;
        this.v = builder.t;
        this.w = builder.u;
        this.x = builder.v;
        this.y = builder.w;
        this.z = builder.x;
        this.A = builder.y;
        this.B = builder.z;
        this.C = builder.A;
        if (this.g.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.g);
        } else if (this.h.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.h);
        }
    }

    private X509TrustManager b() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (Exception e) {
            throw Util.assertionError("No System TLS", e);
        }
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (Exception e) {
            throw Util.assertionError("No System TLS", e);
        }
    }

    public int connectTimeoutMillis() {
        return this.z;
    }

    public int readTimeoutMillis() {
        return this.A;
    }

    public int writeTimeoutMillis() {
        return this.B;
    }

    public int pingIntervalMillis() {
        return this.C;
    }

    public Proxy proxy() {
        return this.d;
    }

    public ProxySelector proxySelector() {
        return this.j;
    }

    public CookieJar cookieJar() {
        return this.k;
    }

    public Cache cache() {
        return this.l;
    }

    InternalCache a() {
        return this.l != null ? this.l.a : this.m;
    }

    public Dns dns() {
        return this.v;
    }

    public SocketFactory socketFactory() {
        return this.n;
    }

    public SSLSocketFactory sslSocketFactory() {
        return this.o;
    }

    public HostnameVerifier hostnameVerifier() {
        return this.q;
    }

    public CertificatePinner certificatePinner() {
        return this.r;
    }

    public Authenticator authenticator() {
        return this.t;
    }

    public Authenticator proxyAuthenticator() {
        return this.s;
    }

    public ConnectionPool connectionPool() {
        return this.u;
    }

    public boolean followSslRedirects() {
        return this.w;
    }

    public boolean followRedirects() {
        return this.x;
    }

    public boolean retryOnConnectionFailure() {
        return this.y;
    }

    public Dispatcher dispatcher() {
        return this.c;
    }

    public List<Protocol> protocols() {
        return this.e;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.f;
    }

    public List<Interceptor> interceptors() {
        return this.g;
    }

    public List<Interceptor> networkInterceptors() {
        return this.h;
    }

    public EventListener.Factory eventListenerFactory() {
        return this.i;
    }

    public Call newCall(Request request) {
        return n.a(this, request, false);
    }

    public WebSocket newWebSocket(Request request, WebSocketListener webSocketListener) {
        WebSocket realWebSocket = new RealWebSocket(request, webSocketListener, new Random());
        realWebSocket.connect(this);
        return realWebSocket;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }
}
