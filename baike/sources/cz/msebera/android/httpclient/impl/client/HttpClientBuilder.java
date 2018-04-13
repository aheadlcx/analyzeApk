package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.BackoffManager;
import cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.config.CookieSpecs;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.entity.InputStreamFactory;
import cz.msebera.android.httpclient.client.protocol.RequestAcceptEncoding;
import cz.msebera.android.httpclient.client.protocol.RequestAddCookies;
import cz.msebera.android.httpclient.client.protocol.RequestAuthCache;
import cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import cz.msebera.android.httpclient.client.protocol.RequestDefaultHeaders;
import cz.msebera.android.httpclient.client.protocol.RequestExpectContinue;
import cz.msebera.android.httpclient.client.protocol.ResponseContentEncoding;
import cz.msebera.android.httpclient.client.protocol.ResponseProcessCookies;
import cz.msebera.android.httpclient.config.ConnectionConfig;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.config.SocketConfig;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.socket.PlainConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.ssl.DefaultHostnameVerifier;
import cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.ssl.X509HostnameVerifier;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcher;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcherLoader;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.NoConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory;
import cz.msebera.android.httpclient.impl.conn.DefaultProxyRoutePlanner;
import cz.msebera.android.httpclient.impl.conn.DefaultRoutePlanner;
import cz.msebera.android.httpclient.impl.conn.DefaultSchemePortResolver;
import cz.msebera.android.httpclient.impl.conn.PoolingHttpClientConnectionManager;
import cz.msebera.android.httpclient.impl.conn.SystemDefaultRoutePlanner;
import cz.msebera.android.httpclient.impl.cookie.DefaultCookieSpecProvider;
import cz.msebera.android.httpclient.impl.cookie.IgnoreSpecProvider;
import cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecProvider;
import cz.msebera.android.httpclient.impl.cookie.RFC6265CookieSpecProvider;
import cz.msebera.android.httpclient.impl.cookie.RFC6265CookieSpecProvider.CompatibilityLevel;
import cz.msebera.android.httpclient.impl.execchain.BackoffStrategyExec;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import cz.msebera.android.httpclient.impl.execchain.MainClientExec;
import cz.msebera.android.httpclient.impl.execchain.ProtocolExec;
import cz.msebera.android.httpclient.impl.execchain.RedirectExec;
import cz.msebera.android.httpclient.impl.execchain.RetryExec;
import cz.msebera.android.httpclient.impl.execchain.ServiceUnavailableRetryExec;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpProcessorBuilder;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.protocol.RequestContent;
import cz.msebera.android.httpclient.protocol.RequestTargetHost;
import cz.msebera.android.httpclient.protocol.RequestUserAgent;
import cz.msebera.android.httpclient.ssl.SSLContexts;
import cz.msebera.android.httpclient.util.TextUtils;
import cz.msebera.android.httpclient.util.VersionInfo;
import java.io.Closeable;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

@NotThreadSafe
public class HttpClientBuilder {
    private CookieStore A;
    private CredentialsProvider B;
    private String C;
    private HttpHost D;
    private Collection<? extends Header> E;
    private SocketConfig F;
    private ConnectionConfig G;
    private RequestConfig H;
    private boolean I;
    private boolean J;
    private long K;
    private TimeUnit L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private int T = 0;
    private int U = 0;
    private long V = -1;
    private TimeUnit W = TimeUnit.MILLISECONDS;
    private List<Closeable> X;
    private PublicSuffixMatcher Y;
    private HttpRequestExecutor a;
    private HostnameVerifier b;
    private LayeredConnectionSocketFactory c;
    private SSLContext d;
    private HttpClientConnectionManager e;
    private boolean f;
    private SchemePortResolver g;
    private ConnectionReuseStrategy h;
    private ConnectionKeepAliveStrategy i;
    private AuthenticationStrategy j;
    private AuthenticationStrategy k;
    private UserTokenHandler l;
    private HttpProcessor m;
    private LinkedList<HttpRequestInterceptor> n;
    private LinkedList<HttpRequestInterceptor> o;
    private LinkedList<HttpResponseInterceptor> p;
    private LinkedList<HttpResponseInterceptor> q;
    private HttpRequestRetryHandler r;
    private HttpRoutePlanner s;
    private RedirectStrategy t;
    private ConnectionBackoffStrategy u;
    private BackoffManager v;
    private ServiceUnavailableRetryStrategy w;
    private Lookup<AuthSchemeProvider> x;
    private Lookup<CookieSpecProvider> y;
    private Map<String, InputStreamFactory> z;

    public static HttpClientBuilder create() {
        return new HttpClientBuilder();
    }

    protected HttpClientBuilder() {
    }

    public final HttpClientBuilder setRequestExecutor(HttpRequestExecutor httpRequestExecutor) {
        this.a = httpRequestExecutor;
        return this;
    }

    @Deprecated
    public final HttpClientBuilder setHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        this.b = x509HostnameVerifier;
        return this;
    }

    public final HttpClientBuilder setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.b = hostnameVerifier;
        return this;
    }

    public final HttpClientBuilder setPublicSuffixMatcher(PublicSuffixMatcher publicSuffixMatcher) {
        this.Y = publicSuffixMatcher;
        return this;
    }

    public final HttpClientBuilder setSslcontext(SSLContext sSLContext) {
        this.d = sSLContext;
        return this;
    }

    public final HttpClientBuilder setSSLSocketFactory(LayeredConnectionSocketFactory layeredConnectionSocketFactory) {
        this.c = layeredConnectionSocketFactory;
        return this;
    }

    public final HttpClientBuilder setMaxConnTotal(int i) {
        this.T = i;
        return this;
    }

    public final HttpClientBuilder setMaxConnPerRoute(int i) {
        this.U = i;
        return this;
    }

    public final HttpClientBuilder setDefaultSocketConfig(SocketConfig socketConfig) {
        this.F = socketConfig;
        return this;
    }

    public final HttpClientBuilder setDefaultConnectionConfig(ConnectionConfig connectionConfig) {
        this.G = connectionConfig;
        return this;
    }

    public final HttpClientBuilder setConnectionTimeToLive(long j, TimeUnit timeUnit) {
        this.V = j;
        this.W = timeUnit;
        return this;
    }

    public final HttpClientBuilder setConnectionManager(HttpClientConnectionManager httpClientConnectionManager) {
        this.e = httpClientConnectionManager;
        return this;
    }

    public final HttpClientBuilder setConnectionManagerShared(boolean z) {
        this.f = z;
        return this;
    }

    public final HttpClientBuilder setConnectionReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        this.h = connectionReuseStrategy;
        return this;
    }

    public final HttpClientBuilder setKeepAliveStrategy(ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        this.i = connectionKeepAliveStrategy;
        return this;
    }

    public final HttpClientBuilder setTargetAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.j = authenticationStrategy;
        return this;
    }

    public final HttpClientBuilder setProxyAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.k = authenticationStrategy;
        return this;
    }

    public final HttpClientBuilder setUserTokenHandler(UserTokenHandler userTokenHandler) {
        this.l = userTokenHandler;
        return this;
    }

    public final HttpClientBuilder disableConnectionState() {
        this.S = true;
        return this;
    }

    public final HttpClientBuilder setSchemePortResolver(SchemePortResolver schemePortResolver) {
        this.g = schemePortResolver;
        return this;
    }

    public final HttpClientBuilder setUserAgent(String str) {
        this.C = str;
        return this;
    }

    public final HttpClientBuilder setDefaultHeaders(Collection<? extends Header> collection) {
        this.E = collection;
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.p == null) {
                this.p = new LinkedList();
            }
            this.p.addFirst(httpResponseInterceptor);
        }
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.q == null) {
                this.q = new LinkedList();
            }
            this.q.addLast(httpResponseInterceptor);
        }
        return this;
    }

    public final HttpClientBuilder addInterceptorFirst(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.n == null) {
                this.n = new LinkedList();
            }
            this.n.addFirst(httpRequestInterceptor);
        }
        return this;
    }

    public final HttpClientBuilder addInterceptorLast(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.o == null) {
                this.o = new LinkedList();
            }
            this.o.addLast(httpRequestInterceptor);
        }
        return this;
    }

    public final HttpClientBuilder disableCookieManagement() {
        this.Q = true;
        return this;
    }

    public final HttpClientBuilder disableContentCompression() {
        this.P = true;
        return this;
    }

    public final HttpClientBuilder disableAuthCaching() {
        this.R = true;
        return this;
    }

    public final HttpClientBuilder setHttpProcessor(HttpProcessor httpProcessor) {
        this.m = httpProcessor;
        return this;
    }

    public final HttpClientBuilder setRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.r = httpRequestRetryHandler;
        return this;
    }

    public final HttpClientBuilder disableAutomaticRetries() {
        this.O = true;
        return this;
    }

    public final HttpClientBuilder setProxy(HttpHost httpHost) {
        this.D = httpHost;
        return this;
    }

    public final HttpClientBuilder setRoutePlanner(HttpRoutePlanner httpRoutePlanner) {
        this.s = httpRoutePlanner;
        return this;
    }

    public final HttpClientBuilder setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.t = redirectStrategy;
        return this;
    }

    public final HttpClientBuilder disableRedirectHandling() {
        this.N = true;
        return this;
    }

    public final HttpClientBuilder setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy) {
        this.u = connectionBackoffStrategy;
        return this;
    }

    public final HttpClientBuilder setBackoffManager(BackoffManager backoffManager) {
        this.v = backoffManager;
        return this;
    }

    public final HttpClientBuilder setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy) {
        this.w = serviceUnavailableRetryStrategy;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieStore(CookieStore cookieStore) {
        this.A = cookieStore;
        return this;
    }

    public final HttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.B = credentialsProvider;
        return this;
    }

    public final HttpClientBuilder setDefaultAuthSchemeRegistry(Lookup<AuthSchemeProvider> lookup) {
        this.x = lookup;
        return this;
    }

    public final HttpClientBuilder setDefaultCookieSpecRegistry(Lookup<CookieSpecProvider> lookup) {
        this.y = lookup;
        return this;
    }

    public final HttpClientBuilder setContentDecoderRegistry(Map<String, InputStreamFactory> map) {
        this.z = map;
        return this;
    }

    public final HttpClientBuilder setDefaultRequestConfig(RequestConfig requestConfig) {
        this.H = requestConfig;
        return this;
    }

    public final HttpClientBuilder useSystemProperties() {
        this.M = true;
        return this;
    }

    public final HttpClientBuilder evictExpiredConnections() {
        this.I = true;
        return this;
    }

    public final HttpClientBuilder evictIdleConnections(Long l, TimeUnit timeUnit) {
        this.J = true;
        this.K = l.longValue();
        this.L = timeUnit;
        return this;
    }

    protected ClientExecChain a(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpProcessor httpProcessor, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        return new MainClientExec(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpProcessor, authenticationStrategy, authenticationStrategy2, userTokenHandler);
    }

    protected ClientExecChain a(ClientExecChain clientExecChain) {
        return clientExecChain;
    }

    protected ClientExecChain b(ClientExecChain clientExecChain) {
        return clientExecChain;
    }

    protected void a(Closeable closeable) {
        if (closeable != null) {
            if (this.X == null) {
                this.X = new ArrayList();
            }
            this.X.add(closeable);
        }
    }

    private static String[] a(String str) {
        if (TextUtils.isBlank(str)) {
            return null;
        }
        return str.split(" *, *");
    }

    public CloseableHttpClient build() {
        PublicSuffixMatcher publicSuffixMatcher;
        HttpRequestExecutor httpRequestExecutor;
        String userAgent;
        ClientExecChain a;
        HttpProcessor httpProcessor;
        HttpProcessorBuilder create;
        Iterator it;
        List arrayList;
        RegistryBuilder create2;
        HttpRequestRetryHandler httpRequestRetryHandler;
        ClientExecChain retryExec;
        HttpRoutePlanner httpRoutePlanner;
        SchemePortResolver schemePortResolver;
        RedirectStrategy redirectStrategy;
        ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy;
        ClientExecChain clientExecChain;
        Lookup lookup;
        Lookup lookup2;
        CookieStore cookieStore;
        CredentialsProvider credentialsProvider;
        TimeUnit timeUnit;
        List list;
        RequestConfig requestConfig;
        PublicSuffixMatcher publicSuffixMatcher2 = this.Y;
        if (publicSuffixMatcher2 == null) {
            publicSuffixMatcher = PublicSuffixMatcherLoader.getDefault();
        } else {
            publicSuffixMatcher = publicSuffixMatcher2;
        }
        HttpRequestExecutor httpRequestExecutor2 = this.a;
        if (httpRequestExecutor2 == null) {
            httpRequestExecutor = new HttpRequestExecutor();
        } else {
            httpRequestExecutor = httpRequestExecutor2;
        }
        HttpClientConnectionManager httpClientConnectionManager = this.e;
        if (httpClientConnectionManager == null) {
            Object obj = this.c;
            if (obj == null) {
                HostnameVerifier defaultHostnameVerifier;
                String[] a2 = this.M ? a(System.getProperty("https.protocols")) : null;
                String[] a3 = this.M ? a(System.getProperty("https.cipherSuites")) : null;
                HostnameVerifier hostnameVerifier = this.b;
                if (hostnameVerifier == null) {
                    defaultHostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
                } else {
                    defaultHostnameVerifier = hostnameVerifier;
                }
                if (this.d != null) {
                    obj = new SSLConnectionSocketFactory(this.d, a2, a3, defaultHostnameVerifier);
                } else if (this.M) {
                    SSLConnectionSocketFactory sSLConnectionSocketFactory = new SSLConnectionSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), a2, a3, defaultHostnameVerifier);
                } else {
                    obj = new SSLConnectionSocketFactory(SSLContexts.createDefault(), defaultHostnameVerifier);
                }
            }
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(RegistryBuilder.create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", obj).build(), null, null, null, this.V, this.W != null ? this.W : TimeUnit.MILLISECONDS);
            if (this.F != null) {
                poolingHttpClientConnectionManager.setDefaultSocketConfig(this.F);
            }
            if (this.G != null) {
                poolingHttpClientConnectionManager.setDefaultConnectionConfig(this.G);
            }
            if (this.M) {
                if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
                    int parseInt = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
                    poolingHttpClientConnectionManager.setDefaultMaxPerRoute(parseInt);
                    poolingHttpClientConnectionManager.setMaxTotal(parseInt * 2);
                }
            }
            if (this.T > 0) {
                poolingHttpClientConnectionManager.setMaxTotal(this.T);
            }
            if (this.U > 0) {
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(this.U);
            }
            httpClientConnectionManager = poolingHttpClientConnectionManager;
        }
        ConnectionReuseStrategy connectionReuseStrategy = this.h;
        if (connectionReuseStrategy == null) {
            if (this.M) {
                DefaultConnectionReuseStrategy defaultConnectionReuseStrategy;
                if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
                    defaultConnectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
                } else {
                    defaultConnectionReuseStrategy = NoConnectionReuseStrategy.INSTANCE;
                }
                connectionReuseStrategy = defaultConnectionReuseStrategy;
            } else {
                connectionReuseStrategy = DefaultConnectionReuseStrategy.INSTANCE;
            }
        }
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = this.i;
        if (connectionKeepAliveStrategy == null) {
            connectionKeepAliveStrategy = DefaultConnectionKeepAliveStrategy.INSTANCE;
        }
        AuthenticationStrategy authenticationStrategy = this.j;
        if (authenticationStrategy == null) {
            authenticationStrategy = TargetAuthenticationStrategy.INSTANCE;
        }
        AuthenticationStrategy authenticationStrategy2 = this.k;
        if (authenticationStrategy2 == null) {
            authenticationStrategy2 = ProxyAuthenticationStrategy.INSTANCE;
        }
        UserTokenHandler userTokenHandler = this.l;
        if (userTokenHandler == null) {
            if (this.S) {
                userTokenHandler = NoopUserTokenHandler.INSTANCE;
            } else {
                userTokenHandler = DefaultUserTokenHandler.INSTANCE;
            }
        }
        String str = this.C;
        if (str == null) {
            if (this.M) {
                str = System.getProperty("http.agent");
            }
            if (str == null) {
                userAgent = VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", getClass());
                a = a(a(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, new ImmutableHttpProcessor(new RequestTargetHost(), new RequestUserAgent(userAgent)), authenticationStrategy, authenticationStrategy2, userTokenHandler));
                httpProcessor = this.m;
                if (httpProcessor == null) {
                    create = HttpProcessorBuilder.create();
                    if (this.n != null) {
                        it = this.n.iterator();
                        while (it.hasNext()) {
                            create.addFirst((HttpRequestInterceptor) it.next());
                        }
                    }
                    if (this.p != null) {
                        it = this.p.iterator();
                        while (it.hasNext()) {
                            create.addFirst((HttpResponseInterceptor) it.next());
                        }
                    }
                    create.addAll(new HttpRequestInterceptor[]{new RequestDefaultHeaders(this.E), new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(userAgent), new RequestExpectContinue()});
                    if (!this.Q) {
                        create.add(new RequestAddCookies());
                    }
                    if (!this.P) {
                        if (this.z == null) {
                            arrayList = new ArrayList(this.z.keySet());
                            Collections.sort(arrayList);
                            create.add(new RequestAcceptEncoding(arrayList));
                        } else {
                            create.add(new RequestAcceptEncoding());
                        }
                    }
                    if (!this.R) {
                        create.add(new RequestAuthCache());
                    }
                    if (!this.Q) {
                        create.add(new ResponseProcessCookies());
                    }
                    if (!this.P) {
                        if (this.z == null) {
                            create2 = RegistryBuilder.create();
                            for (Entry entry : this.z.entrySet()) {
                                create2.register((String) entry.getKey(), entry.getValue());
                            }
                            create.add(new ResponseContentEncoding(create2.build()));
                        } else {
                            create.add(new ResponseContentEncoding());
                        }
                    }
                    if (this.o != null) {
                        it = this.o.iterator();
                        while (it.hasNext()) {
                            create.addLast((HttpRequestInterceptor) it.next());
                        }
                    }
                    if (this.q != null) {
                        it = this.q.iterator();
                        while (it.hasNext()) {
                            create.addLast((HttpResponseInterceptor) it.next());
                        }
                    }
                    httpProcessor = create.build();
                }
                a = b(new ProtocolExec(a, httpProcessor));
                if (this.O) {
                    httpRequestRetryHandler = this.r;
                    if (httpRequestRetryHandler == null) {
                        httpRequestRetryHandler = DefaultHttpRequestRetryHandler.INSTANCE;
                    }
                    retryExec = new RetryExec(a, httpRequestRetryHandler);
                } else {
                    retryExec = a;
                }
                httpRoutePlanner = this.s;
                if (httpRoutePlanner == null) {
                    schemePortResolver = this.g;
                    if (schemePortResolver == null) {
                        schemePortResolver = DefaultSchemePortResolver.INSTANCE;
                    }
                    if (this.D != null) {
                        httpRoutePlanner = new DefaultProxyRoutePlanner(this.D, schemePortResolver);
                    } else if (this.M) {
                        httpRoutePlanner = new DefaultRoutePlanner(schemePortResolver);
                    } else {
                        httpRoutePlanner = new SystemDefaultRoutePlanner(schemePortResolver, ProxySelector.getDefault());
                    }
                }
                if (!this.N) {
                    redirectStrategy = this.t;
                    if (redirectStrategy == null) {
                        redirectStrategy = DefaultRedirectStrategy.INSTANCE;
                    }
                    retryExec = new RedirectExec(retryExec, httpRoutePlanner, redirectStrategy);
                }
                serviceUnavailableRetryStrategy = this.w;
                if (serviceUnavailableRetryStrategy != null) {
                    retryExec = new ServiceUnavailableRetryExec(retryExec, serviceUnavailableRetryStrategy);
                }
                if (this.v != null || this.u == null) {
                    clientExecChain = retryExec;
                } else {
                    clientExecChain = new BackoffStrategyExec(retryExec, this.u, this.v);
                }
                lookup = this.x;
                if (lookup == null) {
                    lookup = RegistryBuilder.create().register("Basic", new BasicSchemeFactory()).register("Digest", new DigestSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).build();
                }
                lookup2 = this.y;
                if (lookup2 == null) {
                    DefaultCookieSpecProvider defaultCookieSpecProvider = new DefaultCookieSpecProvider(publicSuffixMatcher);
                    lookup2 = RegistryBuilder.create().register("default", defaultCookieSpecProvider).register("best-match", defaultCookieSpecProvider).register("compatibility", defaultCookieSpecProvider).register(CookieSpecs.STANDARD, new RFC6265CookieSpecProvider(CompatibilityLevel.RELAXED, publicSuffixMatcher)).register(CookieSpecs.STANDARD_STRICT, new RFC6265CookieSpecProvider(CompatibilityLevel.STRICT, publicSuffixMatcher)).register("netscape", new NetscapeDraftSpecProvider()).register("ignoreCookies", new IgnoreSpecProvider()).build();
                }
                cookieStore = this.A;
                if (cookieStore == null) {
                    cookieStore = new BasicCookieStore();
                }
                credentialsProvider = this.B;
                if (credentialsProvider == null) {
                    if (this.M) {
                        credentialsProvider = new BasicCredentialsProvider();
                    } else {
                        credentialsProvider = new SystemDefaultCredentialsProvider();
                    }
                }
                arrayList = this.X == null ? new ArrayList(this.X) : null;
                if (this.f) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    if (this.I || this.J) {
                        long j = this.K <= 0 ? this.K : 10;
                        if (this.L == null) {
                            timeUnit = this.L;
                        } else {
                            timeUnit = TimeUnit.SECONDS;
                        }
                        IdleConnectionEvictor idleConnectionEvictor = new IdleConnectionEvictor(httpClientConnectionManager, j, timeUnit);
                        arrayList.add(new f(this, idleConnectionEvictor));
                        idleConnectionEvictor.start();
                    }
                    arrayList.add(new g(this, httpClientConnectionManager));
                    list = arrayList;
                } else {
                    list = arrayList;
                }
                if (this.H == null) {
                    requestConfig = this.H;
                } else {
                    requestConfig = RequestConfig.DEFAULT;
                }
                return new j(clientExecChain, httpClientConnectionManager, httpRoutePlanner, lookup2, lookup, cookieStore, credentialsProvider, requestConfig, list);
            }
        }
        userAgent = str;
        a = a(a(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, new ImmutableHttpProcessor(new RequestTargetHost(), new RequestUserAgent(userAgent)), authenticationStrategy, authenticationStrategy2, userTokenHandler));
        httpProcessor = this.m;
        if (httpProcessor == null) {
            create = HttpProcessorBuilder.create();
            if (this.n != null) {
                it = this.n.iterator();
                while (it.hasNext()) {
                    create.addFirst((HttpRequestInterceptor) it.next());
                }
            }
            if (this.p != null) {
                it = this.p.iterator();
                while (it.hasNext()) {
                    create.addFirst((HttpResponseInterceptor) it.next());
                }
            }
            create.addAll(new HttpRequestInterceptor[]{new RequestDefaultHeaders(this.E), new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(userAgent), new RequestExpectContinue()});
            if (this.Q) {
                create.add(new RequestAddCookies());
            }
            if (this.P) {
                if (this.z == null) {
                    create.add(new RequestAcceptEncoding());
                } else {
                    arrayList = new ArrayList(this.z.keySet());
                    Collections.sort(arrayList);
                    create.add(new RequestAcceptEncoding(arrayList));
                }
            }
            if (this.R) {
                create.add(new RequestAuthCache());
            }
            if (this.Q) {
                create.add(new ResponseProcessCookies());
            }
            if (this.P) {
                if (this.z == null) {
                    create.add(new ResponseContentEncoding());
                } else {
                    create2 = RegistryBuilder.create();
                    for (Entry entry2 : this.z.entrySet()) {
                        create2.register((String) entry2.getKey(), entry2.getValue());
                    }
                    create.add(new ResponseContentEncoding(create2.build()));
                }
            }
            if (this.o != null) {
                it = this.o.iterator();
                while (it.hasNext()) {
                    create.addLast((HttpRequestInterceptor) it.next());
                }
            }
            if (this.q != null) {
                it = this.q.iterator();
                while (it.hasNext()) {
                    create.addLast((HttpResponseInterceptor) it.next());
                }
            }
            httpProcessor = create.build();
        }
        a = b(new ProtocolExec(a, httpProcessor));
        if (this.O) {
            retryExec = a;
        } else {
            httpRequestRetryHandler = this.r;
            if (httpRequestRetryHandler == null) {
                httpRequestRetryHandler = DefaultHttpRequestRetryHandler.INSTANCE;
            }
            retryExec = new RetryExec(a, httpRequestRetryHandler);
        }
        httpRoutePlanner = this.s;
        if (httpRoutePlanner == null) {
            schemePortResolver = this.g;
            if (schemePortResolver == null) {
                schemePortResolver = DefaultSchemePortResolver.INSTANCE;
            }
            if (this.D != null) {
                httpRoutePlanner = new DefaultProxyRoutePlanner(this.D, schemePortResolver);
            } else if (this.M) {
                httpRoutePlanner = new DefaultRoutePlanner(schemePortResolver);
            } else {
                httpRoutePlanner = new SystemDefaultRoutePlanner(schemePortResolver, ProxySelector.getDefault());
            }
        }
        if (this.N) {
            redirectStrategy = this.t;
            if (redirectStrategy == null) {
                redirectStrategy = DefaultRedirectStrategy.INSTANCE;
            }
            retryExec = new RedirectExec(retryExec, httpRoutePlanner, redirectStrategy);
        }
        serviceUnavailableRetryStrategy = this.w;
        if (serviceUnavailableRetryStrategy != null) {
            retryExec = new ServiceUnavailableRetryExec(retryExec, serviceUnavailableRetryStrategy);
        }
        if (this.v != null) {
        }
        clientExecChain = retryExec;
        lookup = this.x;
        if (lookup == null) {
            lookup = RegistryBuilder.create().register("Basic", new BasicSchemeFactory()).register("Digest", new DigestSchemeFactory()).register("NTLM", new NTLMSchemeFactory()).build();
        }
        lookup2 = this.y;
        if (lookup2 == null) {
            DefaultCookieSpecProvider defaultCookieSpecProvider2 = new DefaultCookieSpecProvider(publicSuffixMatcher);
            lookup2 = RegistryBuilder.create().register("default", defaultCookieSpecProvider2).register("best-match", defaultCookieSpecProvider2).register("compatibility", defaultCookieSpecProvider2).register(CookieSpecs.STANDARD, new RFC6265CookieSpecProvider(CompatibilityLevel.RELAXED, publicSuffixMatcher)).register(CookieSpecs.STANDARD_STRICT, new RFC6265CookieSpecProvider(CompatibilityLevel.STRICT, publicSuffixMatcher)).register("netscape", new NetscapeDraftSpecProvider()).register("ignoreCookies", new IgnoreSpecProvider()).build();
        }
        cookieStore = this.A;
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        credentialsProvider = this.B;
        if (credentialsProvider == null) {
            if (this.M) {
                credentialsProvider = new BasicCredentialsProvider();
            } else {
                credentialsProvider = new SystemDefaultCredentialsProvider();
            }
        }
        if (this.X == null) {
        }
        if (this.f) {
            list = arrayList;
        } else {
            if (arrayList == null) {
                arrayList = new ArrayList(1);
            }
            if (this.K <= 0) {
            }
            if (this.L == null) {
                timeUnit = TimeUnit.SECONDS;
            } else {
                timeUnit = this.L;
            }
            IdleConnectionEvictor idleConnectionEvictor2 = new IdleConnectionEvictor(httpClientConnectionManager, j, timeUnit);
            arrayList.add(new f(this, idleConnectionEvictor2));
            idleConnectionEvictor2.start();
            arrayList.add(new g(this, httpClientConnectionManager));
            list = arrayList;
        }
        if (this.H == null) {
            requestConfig = RequestConfig.DEFAULT;
        } else {
            requestConfig = this.H;
        }
        return new j(clientExecChain, httpClientConnectionManager, httpRoutePlanner, lookup2, lookup, cookieStore, credentialsProvider, requestConfig, list);
    }
}
