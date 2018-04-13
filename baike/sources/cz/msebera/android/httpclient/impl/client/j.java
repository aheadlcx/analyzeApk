package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.auth.AuthSchemeProvider;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.Configurable;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.client.params.HttpClientParamConfig;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.execchain.ClientExecChain;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpParamsNames;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

@ThreadSafe
class j extends CloseableHttpClient implements Configurable {
    private final ClientExecChain a;
    private final HttpClientConnectionManager b;
    private final HttpRoutePlanner c;
    private final Lookup<CookieSpecProvider> d;
    private final Lookup<AuthSchemeProvider> e;
    private final CookieStore f;
    private final CredentialsProvider g;
    private final RequestConfig h;
    private final List<Closeable> i;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public j(ClientExecChain clientExecChain, HttpClientConnectionManager httpClientConnectionManager, HttpRoutePlanner httpRoutePlanner, Lookup<CookieSpecProvider> lookup, Lookup<AuthSchemeProvider> lookup2, CookieStore cookieStore, CredentialsProvider credentialsProvider, RequestConfig requestConfig, List<Closeable> list) {
        Args.notNull(clientExecChain, "HTTP client exec chain");
        Args.notNull(httpClientConnectionManager, "HTTP connection manager");
        Args.notNull(httpRoutePlanner, "HTTP route planner");
        this.a = clientExecChain;
        this.b = httpClientConnectionManager;
        this.c = httpRoutePlanner;
        this.d = lookup;
        this.e = lookup2;
        this.f = cookieStore;
        this.g = credentialsProvider;
        this.h = requestConfig;
        this.i = list;
    }

    private HttpRoute b(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        HttpHost httpHost2;
        if (httpHost == null) {
            httpHost2 = (HttpHost) httpRequest.getParams().getParameter(ClientPNames.DEFAULT_HOST);
        } else {
            httpHost2 = httpHost;
        }
        return this.c.determineRoute(httpHost2, httpRequest, httpContext);
    }

    private void a(HttpClientContext httpClientContext) {
        if (httpClientContext.getAttribute("http.auth.target-scope") == null) {
            httpClientContext.setAttribute("http.auth.target-scope", new AuthState());
        }
        if (httpClientContext.getAttribute("http.auth.proxy-scope") == null) {
            httpClientContext.setAttribute("http.auth.proxy-scope", new AuthState());
        }
        if (httpClientContext.getAttribute("http.authscheme-registry") == null) {
            httpClientContext.setAttribute("http.authscheme-registry", this.e);
        }
        if (httpClientContext.getAttribute("http.cookiespec-registry") == null) {
            httpClientContext.setAttribute("http.cookiespec-registry", this.d);
        }
        if (httpClientContext.getAttribute("http.cookie-store") == null) {
            httpClientContext.setAttribute("http.cookie-store", this.f);
        }
        if (httpClientContext.getAttribute("http.auth.credentials-provider") == null) {
            httpClientContext.setAttribute("http.auth.credentials-provider", this.g);
        }
        if (httpClientContext.getAttribute("http.request-config") == null) {
            httpClientContext.setAttribute("http.request-config", this.h);
        }
    }

    protected CloseableHttpResponse a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException, ClientProtocolException {
        HttpExecutionAware httpExecutionAware;
        RequestConfig requestConfig = null;
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest instanceof HttpExecutionAware) {
            httpExecutionAware = (HttpExecutionAware) httpRequest;
        } else {
            httpExecutionAware = null;
        }
        try {
            RequestConfig requestConfig2;
            Object wrap = HttpRequestWrapper.wrap(httpRequest, httpHost);
            if (httpContext == null) {
                httpContext = new BasicHttpContext();
            }
            HttpClientContext adapt = HttpClientContext.adapt(httpContext);
            if (httpRequest instanceof Configurable) {
                requestConfig = ((Configurable) httpRequest).getConfig();
            }
            if (requestConfig == null) {
                HttpParams params = httpRequest.getParams();
                if (!(params instanceof HttpParamsNames)) {
                    requestConfig2 = HttpClientParamConfig.getRequestConfig(params);
                } else if (!((HttpParamsNames) params).getNames().isEmpty()) {
                    requestConfig2 = HttpClientParamConfig.getRequestConfig(params);
                }
                if (requestConfig2 != null) {
                    adapt.setRequestConfig(requestConfig2);
                }
                a(adapt);
                return this.a.execute(b(httpHost, wrap, adapt), wrap, adapt, httpExecutionAware);
            }
            requestConfig2 = requestConfig;
            if (requestConfig2 != null) {
                adapt.setRequestConfig(requestConfig2);
            }
            a(adapt);
            return this.a.execute(b(httpHost, wrap, adapt), wrap, adapt, httpExecutionAware);
        } catch (Throwable e) {
            throw new ClientProtocolException(e);
        }
    }

    public RequestConfig getConfig() {
        return this.h;
    }

    public void close() {
        if (this.i != null) {
            for (Closeable close : this.i) {
                try {
                    close.close();
                } catch (Throwable e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    public HttpParams getParams() {
        throw new UnsupportedOperationException();
    }

    public ClientConnectionManager getConnectionManager() {
        return new k(this);
    }
}
