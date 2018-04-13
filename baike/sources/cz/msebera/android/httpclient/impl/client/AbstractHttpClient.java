package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.auth.AuthSchemeRegistry;
import cz.msebera.android.httpclient.client.AuthenticationHandler;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.BackoffManager;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.ConnectionBackoffStrategy;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.RedirectHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.RequestDirector;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.client.params.CookiePolicy;
import cz.msebera.android.httpclient.client.params.HttpClientParamConfig;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionManagerFactory;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.cookie.CookieSpecRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.DefaultConnectionReuseStrategy;
import cz.msebera.android.httpclient.impl.auth.BasicSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.DigestSchemeFactory;
import cz.msebera.android.httpclient.impl.auth.NTLMSchemeFactory;
import cz.msebera.android.httpclient.impl.conn.BasicClientConnectionManager;
import cz.msebera.android.httpclient.impl.conn.DefaultHttpRoutePlanner;
import cz.msebera.android.httpclient.impl.conn.SchemeRegistryFactory;
import cz.msebera.android.httpclient.impl.cookie.BestMatchSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.IgnoreSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.NetscapeDraftSpecFactory;
import cz.msebera.android.httpclient.impl.cookie.RFC2109SpecFactory;
import cz.msebera.android.httpclient.impl.cookie.RFC2965SpecFactory;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.BasicHttpProcessor;
import cz.msebera.android.httpclient.protocol.DefaultedHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;

@ThreadSafe
@Deprecated
public abstract class AbstractHttpClient extends CloseableHttpClient {
    @GuardedBy("this")
    private HttpParams a;
    @GuardedBy("this")
    private HttpRequestExecutor b;
    @GuardedBy("this")
    private ClientConnectionManager c;
    @GuardedBy("this")
    private ConnectionReuseStrategy d;
    @GuardedBy("this")
    private ConnectionKeepAliveStrategy e;
    @GuardedBy("this")
    private CookieSpecRegistry f;
    @GuardedBy("this")
    private AuthSchemeRegistry g;
    @GuardedBy("this")
    private BasicHttpProcessor h;
    @GuardedBy("this")
    private ImmutableHttpProcessor i;
    @GuardedBy("this")
    private HttpRequestRetryHandler j;
    @GuardedBy("this")
    private RedirectStrategy k;
    @GuardedBy("this")
    private AuthenticationStrategy l;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    @GuardedBy("this")
    private AuthenticationStrategy m;
    @GuardedBy("this")
    private CookieStore n;
    @GuardedBy("this")
    private CredentialsProvider o;
    @GuardedBy("this")
    private HttpRoutePlanner p;
    @GuardedBy("this")
    private UserTokenHandler q;
    @GuardedBy("this")
    private ConnectionBackoffStrategy r;
    @GuardedBy("this")
    private BackoffManager s;

    protected abstract HttpParams a();

    protected abstract BasicHttpProcessor b();

    protected AbstractHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.a = httpParams;
        this.c = clientConnectionManager;
    }

    protected HttpContext c() {
        HttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute(ClientContext.SCHEME_REGISTRY, getConnectionManager().getSchemeRegistry());
        basicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        basicHttpContext.setAttribute("http.cookie-store", getCookieStore());
        basicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return basicHttpContext;
    }

    protected ClientConnectionManager d() {
        SchemeRegistry createDefault = SchemeRegistryFactory.createDefault();
        HttpParams params = getParams();
        String str = (String) params.getParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME);
        if (str != null) {
            try {
                ClientConnectionManagerFactory clientConnectionManagerFactory = (ClientConnectionManagerFactory) Class.forName(str).newInstance();
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Invalid class name: " + str);
            } catch (IllegalAccessException e2) {
                throw new IllegalAccessError(e2.getMessage());
            } catch (InstantiationException e3) {
                throw new InstantiationError(e3.getMessage());
            }
        }
        clientConnectionManagerFactory = null;
        if (clientConnectionManagerFactory != null) {
            return clientConnectionManagerFactory.newInstance(params, createDefault);
        }
        return new BasicClientConnectionManager(createDefault);
    }

    protected AuthSchemeRegistry e() {
        AuthSchemeRegistry authSchemeRegistry = new AuthSchemeRegistry();
        authSchemeRegistry.register("Basic", new BasicSchemeFactory());
        authSchemeRegistry.register("Digest", new DigestSchemeFactory());
        authSchemeRegistry.register("NTLM", new NTLMSchemeFactory());
        return authSchemeRegistry;
    }

    protected CookieSpecRegistry f() {
        CookieSpecRegistry cookieSpecRegistry = new CookieSpecRegistry();
        cookieSpecRegistry.register("default", new BestMatchSpecFactory());
        cookieSpecRegistry.register("best-match", new BestMatchSpecFactory());
        cookieSpecRegistry.register("compatibility", new BrowserCompatSpecFactory());
        cookieSpecRegistry.register("netscape", new NetscapeDraftSpecFactory());
        cookieSpecRegistry.register(CookiePolicy.RFC_2109, new RFC2109SpecFactory());
        cookieSpecRegistry.register(CookiePolicy.RFC_2965, new RFC2965SpecFactory());
        cookieSpecRegistry.register("ignoreCookies", new IgnoreSpecFactory());
        return cookieSpecRegistry;
    }

    protected HttpRequestExecutor g() {
        return new HttpRequestExecutor();
    }

    protected ConnectionReuseStrategy h() {
        return new DefaultConnectionReuseStrategy();
    }

    protected ConnectionKeepAliveStrategy i() {
        return new DefaultConnectionKeepAliveStrategy();
    }

    protected HttpRequestRetryHandler j() {
        return new DefaultHttpRequestRetryHandler();
    }

    @Deprecated
    protected RedirectHandler k() {
        return new DefaultRedirectHandler();
    }

    protected AuthenticationStrategy l() {
        return new TargetAuthenticationStrategy();
    }

    @Deprecated
    protected AuthenticationHandler m() {
        return new DefaultTargetAuthenticationHandler();
    }

    protected AuthenticationStrategy n() {
        return new ProxyAuthenticationStrategy();
    }

    @Deprecated
    protected AuthenticationHandler o() {
        return new DefaultProxyAuthenticationHandler();
    }

    protected CookieStore p() {
        return new BasicCookieStore();
    }

    protected CredentialsProvider q() {
        return new BasicCredentialsProvider();
    }

    protected HttpRoutePlanner r() {
        return new DefaultHttpRoutePlanner(getConnectionManager().getSchemeRegistry());
    }

    protected UserTokenHandler s() {
        return new DefaultUserTokenHandler();
    }

    public final synchronized HttpParams getParams() {
        if (this.a == null) {
            this.a = a();
        }
        return this.a;
    }

    public synchronized void setParams(HttpParams httpParams) {
        this.a = httpParams;
    }

    public final synchronized ClientConnectionManager getConnectionManager() {
        if (this.c == null) {
            this.c = d();
        }
        return this.c;
    }

    public final synchronized HttpRequestExecutor getRequestExecutor() {
        if (this.b == null) {
            this.b = g();
        }
        return this.b;
    }

    public final synchronized AuthSchemeRegistry getAuthSchemes() {
        if (this.g == null) {
            this.g = e();
        }
        return this.g;
    }

    public synchronized void setAuthSchemes(AuthSchemeRegistry authSchemeRegistry) {
        this.g = authSchemeRegistry;
    }

    public final synchronized ConnectionBackoffStrategy getConnectionBackoffStrategy() {
        return this.r;
    }

    public synchronized void setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy) {
        this.r = connectionBackoffStrategy;
    }

    public final synchronized CookieSpecRegistry getCookieSpecs() {
        if (this.f == null) {
            this.f = f();
        }
        return this.f;
    }

    public final synchronized BackoffManager getBackoffManager() {
        return this.s;
    }

    public synchronized void setBackoffManager(BackoffManager backoffManager) {
        this.s = backoffManager;
    }

    public synchronized void setCookieSpecs(CookieSpecRegistry cookieSpecRegistry) {
        this.f = cookieSpecRegistry;
    }

    public final synchronized ConnectionReuseStrategy getConnectionReuseStrategy() {
        if (this.d == null) {
            this.d = h();
        }
        return this.d;
    }

    public synchronized void setReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        this.d = connectionReuseStrategy;
    }

    public final synchronized ConnectionKeepAliveStrategy getConnectionKeepAliveStrategy() {
        if (this.e == null) {
            this.e = i();
        }
        return this.e;
    }

    public synchronized void setKeepAliveStrategy(ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        this.e = connectionKeepAliveStrategy;
    }

    public final synchronized HttpRequestRetryHandler getHttpRequestRetryHandler() {
        if (this.j == null) {
            this.j = j();
        }
        return this.j;
    }

    public synchronized void setHttpRequestRetryHandler(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.j = httpRequestRetryHandler;
    }

    @Deprecated
    public final synchronized RedirectHandler getRedirectHandler() {
        return k();
    }

    @Deprecated
    public synchronized void setRedirectHandler(RedirectHandler redirectHandler) {
        this.k = new e(redirectHandler);
    }

    public final synchronized RedirectStrategy getRedirectStrategy() {
        if (this.k == null) {
            this.k = new DefaultRedirectStrategy();
        }
        return this.k;
    }

    public synchronized void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.k = redirectStrategy;
    }

    @Deprecated
    public final synchronized AuthenticationHandler getTargetAuthenticationHandler() {
        return m();
    }

    @Deprecated
    public synchronized void setTargetAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        this.l = new a(authenticationHandler);
    }

    public final synchronized AuthenticationStrategy getTargetAuthenticationStrategy() {
        if (this.l == null) {
            this.l = l();
        }
        return this.l;
    }

    public synchronized void setTargetAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.l = authenticationStrategy;
    }

    @Deprecated
    public final synchronized AuthenticationHandler getProxyAuthenticationHandler() {
        return o();
    }

    @Deprecated
    public synchronized void setProxyAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        this.m = new a(authenticationHandler);
    }

    public final synchronized AuthenticationStrategy getProxyAuthenticationStrategy() {
        if (this.m == null) {
            this.m = n();
        }
        return this.m;
    }

    public synchronized void setProxyAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        this.m = authenticationStrategy;
    }

    public final synchronized CookieStore getCookieStore() {
        if (this.n == null) {
            this.n = p();
        }
        return this.n;
    }

    public synchronized void setCookieStore(CookieStore cookieStore) {
        this.n = cookieStore;
    }

    public final synchronized CredentialsProvider getCredentialsProvider() {
        if (this.o == null) {
            this.o = q();
        }
        return this.o;
    }

    public synchronized void setCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.o = credentialsProvider;
    }

    public final synchronized HttpRoutePlanner getRoutePlanner() {
        if (this.p == null) {
            this.p = r();
        }
        return this.p;
    }

    public synchronized void setRoutePlanner(HttpRoutePlanner httpRoutePlanner) {
        this.p = httpRoutePlanner;
    }

    public final synchronized UserTokenHandler getUserTokenHandler() {
        if (this.q == null) {
            this.q = s();
        }
        return this.q;
    }

    public synchronized void setUserTokenHandler(UserTokenHandler userTokenHandler) {
        this.q = userTokenHandler;
    }

    protected final synchronized BasicHttpProcessor t() {
        if (this.h == null) {
            this.h = b();
        }
        return this.h;
    }

    private synchronized HttpProcessor u() {
        HttpProcessor httpProcessor;
        int i = 0;
        synchronized (this) {
            if (this.i == null) {
                int i2;
                BasicHttpProcessor t = t();
                int requestInterceptorCount = t.getRequestInterceptorCount();
                HttpRequestInterceptor[] httpRequestInterceptorArr = new HttpRequestInterceptor[requestInterceptorCount];
                for (i2 = 0; i2 < requestInterceptorCount; i2++) {
                    httpRequestInterceptorArr[i2] = t.getRequestInterceptor(i2);
                }
                i2 = t.getResponseInterceptorCount();
                HttpResponseInterceptor[] httpResponseInterceptorArr = new HttpResponseInterceptor[i2];
                while (i < i2) {
                    httpResponseInterceptorArr[i] = t.getResponseInterceptor(i);
                    i++;
                }
                this.i = new ImmutableHttpProcessor(httpRequestInterceptorArr, httpResponseInterceptorArr);
            }
            httpProcessor = this.i;
        }
        return httpProcessor;
    }

    public synchronized int getResponseInterceptorCount() {
        return t().getResponseInterceptorCount();
    }

    public synchronized HttpResponseInterceptor getResponseInterceptor(int i) {
        return t().getResponseInterceptor(i);
    }

    public synchronized HttpRequestInterceptor getRequestInterceptor(int i) {
        return t().getRequestInterceptor(i);
    }

    public synchronized int getRequestInterceptorCount() {
        return t().getRequestInterceptorCount();
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        t().addInterceptor(httpResponseInterceptor);
        this.i = null;
    }

    public synchronized void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i) {
        t().addInterceptor(httpResponseInterceptor, i);
        this.i = null;
    }

    public synchronized void clearResponseInterceptors() {
        t().clearResponseInterceptors();
        this.i = null;
    }

    public synchronized void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> cls) {
        t().removeResponseInterceptorByClass(cls);
        this.i = null;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        t().addInterceptor(httpRequestInterceptor);
        this.i = null;
    }

    public synchronized void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        t().addInterceptor(httpRequestInterceptor, i);
        this.i = null;
    }

    public synchronized void clearRequestInterceptors() {
        t().clearRequestInterceptors();
        this.i = null;
    }

    public synchronized void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> cls) {
        t().removeRequestInterceptorByClass(cls);
        this.i = null;
    }

    protected final CloseableHttpResponse a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        CloseableHttpResponse newProxy;
        HttpRoute determineRoute;
        Args.notNull(httpRequest, "HTTP request");
        synchronized (this) {
            HttpContext httpContext2;
            HttpContext c = c();
            if (httpContext == null) {
                httpContext2 = c;
            } else {
                Object defaultedHttpContext = new DefaultedHttpContext(httpContext, c);
            }
            HttpParams a = a(httpRequest);
            httpContext2.setAttribute("http.request-config", HttpClientParamConfig.getRequestConfig(a));
            RequestDirector a2 = a(getRequestExecutor(), getConnectionManager(), getConnectionReuseStrategy(), getConnectionKeepAliveStrategy(), getRoutePlanner(), u(), getHttpRequestRetryHandler(), getRedirectStrategy(), getTargetAuthenticationStrategy(), getProxyAuthenticationStrategy(), getUserTokenHandler(), a);
            HttpRoutePlanner routePlanner = getRoutePlanner();
            ConnectionBackoffStrategy connectionBackoffStrategy = getConnectionBackoffStrategy();
            BackoffManager backoffManager = getBackoffManager();
        }
        if (connectionBackoffStrategy == null || backoffManager == null) {
            newProxy = d.newProxy(a2.execute(httpHost, httpRequest, httpContext2));
        } else {
            HttpHost httpHost2;
            if (httpHost != null) {
                httpHost2 = httpHost;
            } else {
                httpHost2 = (HttpHost) a(httpRequest).getParameter(ClientPNames.DEFAULT_HOST);
            }
            try {
                determineRoute = routePlanner.determineRoute(httpHost2, httpRequest, httpContext2);
                newProxy = d.newProxy(a2.execute(httpHost, httpRequest, httpContext2));
                if (connectionBackoffStrategy.shouldBackoff(newProxy)) {
                    backoffManager.backOff(determineRoute);
                } else {
                    backoffManager.probe(determineRoute);
                }
            } catch (Throwable e) {
                if (connectionBackoffStrategy.shouldBackoff(e)) {
                    backoffManager.backOff(determineRoute);
                }
                throw e;
            } catch (Throwable e2) {
                if (connectionBackoffStrategy.shouldBackoff(e2)) {
                    backoffManager.backOff(determineRoute);
                }
                if (e2 instanceof HttpException) {
                    throw ((HttpException) e2);
                } else if (e2 instanceof IOException) {
                    throw ((IOException) e2);
                } else {
                    throw new UndeclaredThrowableException(e2);
                }
            } catch (Throwable e22) {
                throw new ClientProtocolException(e22);
            }
        }
        return newProxy;
    }

    protected RequestDirector a(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        return new DefaultRequestDirector(this.log, httpRequestExecutor, clientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpRoutePlanner, httpProcessor, httpRequestRetryHandler, redirectStrategy, authenticationStrategy, authenticationStrategy2, userTokenHandler, httpParams);
    }

    protected HttpParams a(HttpRequest httpRequest) {
        return new ClientParamsStack(null, getParams(), httpRequest.getParams(), null);
    }

    public void close() {
        getConnectionManager().shutdown();
    }
}
