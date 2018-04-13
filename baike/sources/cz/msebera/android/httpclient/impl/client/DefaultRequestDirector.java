package cz.msebera.android.httpclient.impl.client;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.auth.UsernamePasswordCredentials;
import cz.msebera.android.httpclient.client.AuthenticationHandler;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.RedirectException;
import cz.msebera.android.httpclient.client.RedirectHandler;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.RequestDirector;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.methods.AbortableHttpRequest;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.client.params.HttpClientParams;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.BasicManagedEntity;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.ManagedClientConnection;
import cz.msebera.android.httpclient.conn.routing.BasicRouteDirector;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRouteDirector;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.entity.BufferedHttpEntity;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.auth.BasicScheme;
import cz.msebera.android.httpclient.message.BasicHttpRequest;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpProtocolParams;
import cz.msebera.android.httpclient.protocol.ExecutionContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@NotThreadSafe
@Deprecated
public class DefaultRequestDirector implements RequestDirector {
    protected final ClientConnectionManager a;
    protected final HttpRoutePlanner b;
    protected final ConnectionReuseStrategy c;
    protected final ConnectionKeepAliveStrategy d;
    protected final HttpRequestExecutor e;
    protected final HttpProcessor f;
    protected final HttpRequestRetryHandler g;
    @Deprecated
    protected final RedirectHandler h;
    protected final RedirectStrategy i;
    @Deprecated
    protected final AuthenticationHandler j;
    protected final AuthenticationStrategy k;
    @Deprecated
    protected final AuthenticationHandler l;
    public HttpClientAndroidLog log;
    protected final AuthenticationStrategy m;
    protected final UserTokenHandler n;
    protected final HttpParams o;
    protected ManagedClientConnection p;
    protected final AuthState q;
    protected final AuthState r;
    private final HttpAuthenticator s;
    private int t;
    private int u;
    private final int v;
    private HttpHost w;

    @Deprecated
    public DefaultRequestDirector(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectHandler redirectHandler, AuthenticationHandler authenticationHandler, AuthenticationHandler authenticationHandler2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        this(new HttpClientAndroidLog(DefaultRequestDirector.class), httpRequestExecutor, clientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpRoutePlanner, httpProcessor, httpRequestRetryHandler, new e(redirectHandler), new a(authenticationHandler), new a(authenticationHandler2), userTokenHandler, httpParams);
    }

    @Deprecated
    public DefaultRequestDirector(HttpClientAndroidLog httpClientAndroidLog, HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy, AuthenticationHandler authenticationHandler, AuthenticationHandler authenticationHandler2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        this(new HttpClientAndroidLog(DefaultRequestDirector.class), httpRequestExecutor, clientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, httpRoutePlanner, httpProcessor, httpRequestRetryHandler, redirectStrategy, new a(authenticationHandler), new a(authenticationHandler2), userTokenHandler, httpParams);
    }

    public DefaultRequestDirector(HttpClientAndroidLog httpClientAndroidLog, HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectStrategy redirectStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        Args.notNull(httpClientAndroidLog, "Log");
        Args.notNull(httpRequestExecutor, "Request executor");
        Args.notNull(clientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.notNull(httpRoutePlanner, "Route planner");
        Args.notNull(httpProcessor, "HTTP protocol processor");
        Args.notNull(httpRequestRetryHandler, "HTTP request retry handler");
        Args.notNull(redirectStrategy, "Redirect strategy");
        Args.notNull(authenticationStrategy, "Target authentication strategy");
        Args.notNull(authenticationStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler, "User token handler");
        Args.notNull(httpParams, "HTTP parameters");
        this.log = httpClientAndroidLog;
        this.s = new HttpAuthenticator(httpClientAndroidLog);
        this.e = httpRequestExecutor;
        this.a = clientConnectionManager;
        this.c = connectionReuseStrategy;
        this.d = connectionKeepAliveStrategy;
        this.b = httpRoutePlanner;
        this.f = httpProcessor;
        this.g = httpRequestRetryHandler;
        this.i = redirectStrategy;
        this.k = authenticationStrategy;
        this.m = authenticationStrategy2;
        this.n = userTokenHandler;
        this.o = httpParams;
        if (redirectStrategy instanceof e) {
            this.h = ((e) redirectStrategy).getHandler();
        } else {
            this.h = null;
        }
        if (authenticationStrategy instanceof a) {
            this.j = ((a) authenticationStrategy).getHandler();
        } else {
            this.j = null;
        }
        if (authenticationStrategy2 instanceof a) {
            this.l = ((a) authenticationStrategy2).getHandler();
        } else {
            this.l = null;
        }
        this.p = null;
        this.t = 0;
        this.u = 0;
        this.q = new AuthState();
        this.r = new AuthState();
        this.v = this.o.getIntParameter(ClientPNames.MAX_REDIRECTS, 100);
    }

    private RequestWrapper a(HttpRequest httpRequest) throws ProtocolException {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) httpRequest);
        }
        return new RequestWrapper(httpRequest);
    }

    protected void a(RequestWrapper requestWrapper, HttpRoute httpRoute) throws ProtocolException {
        try {
            URI uri = requestWrapper.getURI();
            if (httpRoute.getProxyHost() == null || httpRoute.isTunnelled()) {
                if (uri.isAbsolute()) {
                    uri = URIUtils.rewriteURI(uri, null, true);
                } else {
                    uri = URIUtils.rewriteURI(uri);
                }
            } else if (uri.isAbsolute()) {
                uri = URIUtils.rewriteURI(uri);
            } else {
                uri = URIUtils.rewriteURI(uri, httpRoute.getTargetHost(), true);
            }
            requestWrapper.setURI(uri);
        } catch (Throwable e) {
            throw new ProtocolException("Invalid URI: " + requestWrapper.getRequestLine().getUri(), e);
        }
    }

    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Object obj = null;
        httpContext.setAttribute("http.auth.target-scope", this.q);
        httpContext.setAttribute("http.auth.proxy-scope", this.r);
        HttpRequest a = a(httpRequest);
        a.setParams(this.o);
        HttpRoute a2 = a(httpHost, a, httpContext);
        this.w = (HttpHost) a.getParams().getParameter(ClientPNames.VIRTUAL_HOST);
        if (this.w != null && this.w.getPort() == -1) {
            int port = (httpHost != null ? httpHost : a2.getTargetHost()).getPort();
            if (port != -1) {
                this.w = new HttpHost(this.w.getHostName(), port, this.w.getSchemeName());
            }
        }
        RoutedRequest routedRequest = new RoutedRequest(a, a2);
        HttpResponse httpResponse = null;
        boolean z = false;
        while (obj == null) {
            try {
                RequestWrapper request = routedRequest.getRequest();
                HttpRoute route = routedRequest.getRoute();
                Object attribute = httpContext.getAttribute("http.user-token");
                if (this.p == null) {
                    ClientConnectionRequest requestConnection = this.a.requestConnection(route, attribute);
                    if (httpRequest instanceof AbortableHttpRequest) {
                        ((AbortableHttpRequest) httpRequest).setConnectionRequest(requestConnection);
                    }
                    this.p = requestConnection.getConnection(HttpClientParams.getConnectionManagerTimeout(this.o), TimeUnit.MILLISECONDS);
                    if (HttpConnectionParams.isStaleCheckingEnabled(this.o) && this.p.isOpen()) {
                        this.log.debug("Stale connection check");
                        if (this.p.isStale()) {
                            this.log.debug("Stale connection detected");
                            this.p.close();
                        }
                    }
                }
                if (httpRequest instanceof AbortableHttpRequest) {
                    ((AbortableHttpRequest) httpRequest).setReleaseTrigger(this.p);
                }
                try {
                    a(routedRequest, httpContext);
                    String userInfo = request.getURI().getUserInfo();
                    if (userInfo != null) {
                        this.q.update(new BasicScheme(), new UsernamePasswordCredentials(userInfo));
                    }
                    if (this.w != null) {
                        httpHost = this.w;
                    } else {
                        URI uri = request.getURI();
                        if (uri.isAbsolute()) {
                            httpHost = URIUtils.extractHost(uri);
                        }
                    }
                    if (httpHost == null) {
                        httpHost = route.getTargetHost();
                    }
                    request.resetHeaders();
                    a(request, route);
                    httpContext.setAttribute("http.target_host", httpHost);
                    httpContext.setAttribute("http.route", route);
                    httpContext.setAttribute("http.connection", this.p);
                    this.e.preProcess(request, this.f, httpContext);
                    HttpResponse b = b(routedRequest, httpContext);
                    if (b == null) {
                        httpResponse = b;
                    } else {
                        b.setParams(this.o);
                        this.e.postProcess(b, this.f, httpContext);
                        z = this.c.keepAlive(b, httpContext);
                        if (z) {
                            long keepAliveDuration = this.d.getKeepAliveDuration(b, httpContext);
                            if (this.log.isDebugEnabled()) {
                                if (keepAliveDuration > 0) {
                                    userInfo = "for " + keepAliveDuration + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + TimeUnit.MILLISECONDS;
                                } else {
                                    userInfo = "indefinitely";
                                }
                                this.log.debug("Connection can be kept alive " + userInfo);
                            }
                            this.p.setIdleDuration(keepAliveDuration, TimeUnit.MILLISECONDS);
                        }
                        RoutedRequest a3 = a(routedRequest, b, httpContext);
                        if (a3 == null) {
                            obj = 1;
                        } else {
                            if (z) {
                                EntityUtils.consume(b.getEntity());
                                this.p.markReusable();
                            } else {
                                this.p.close();
                                if (this.r.getState().compareTo(AuthProtocolState.CHALLENGED) > 0 && this.r.getAuthScheme() != null && this.r.getAuthScheme().isConnectionBased()) {
                                    this.log.debug("Resetting proxy auth state");
                                    this.r.reset();
                                }
                                if (this.q.getState().compareTo(AuthProtocolState.CHALLENGED) > 0 && this.q.getAuthScheme() != null && this.q.getAuthScheme().isConnectionBased()) {
                                    this.log.debug("Resetting target auth state");
                                    this.q.reset();
                                }
                            }
                            if (!a3.getRoute().equals(routedRequest.getRoute())) {
                                a();
                            }
                            routedRequest = a3;
                        }
                        if (this.p != null) {
                            Object userToken;
                            if (attribute == null) {
                                userToken = this.n.getUserToken(httpContext);
                                httpContext.setAttribute("http.user-token", userToken);
                            } else {
                                userToken = attribute;
                            }
                            if (userToken != null) {
                                this.p.setState(userToken);
                            }
                        }
                        httpResponse = b;
                    }
                } catch (TunnelRefusedException e) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(e.getMessage());
                    }
                    httpResponse = e.getResponse();
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (Throwable e3) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                interruptedIOException.initCause(e3);
                throw interruptedIOException;
            } catch (HttpException e4) {
                b();
                throw e4;
            } catch (IOException e5) {
                b();
                throw e5;
            } catch (RuntimeException e6) {
                b();
                throw e6;
            }
        }
        if (httpResponse == null || httpResponse.getEntity() == null || !httpResponse.getEntity().isStreaming()) {
            if (z) {
                this.p.markReusable();
            }
            a();
        } else {
            httpResponse.setEntity(new BasicManagedEntity(httpResponse.getEntity(), this.p, z));
        }
        return httpResponse;
    }

    private void a(RoutedRequest routedRequest, HttpContext httpContext) throws HttpException, IOException {
        HttpRoute route = routedRequest.getRoute();
        RequestWrapper request = routedRequest.getRequest();
        int i = 0;
        while (true) {
            httpContext.setAttribute("http.request", request);
            i++;
            try {
                break;
            } catch (Throwable e) {
                try {
                    this.p.close();
                } catch (IOException e2) {
                }
                if (!this.g.retryRequest(e, i, httpContext)) {
                    throw e;
                } else if (this.log.isInfoEnabled()) {
                    this.log.info("I/O exception (" + e.getClass().getName() + ") caught when connecting to " + route + ": " + e.getMessage());
                    if (this.log.isDebugEnabled()) {
                        this.log.debug(e.getMessage(), e);
                    }
                    this.log.info("Retrying connect to " + route);
                }
            }
        }
        if (this.p.isOpen()) {
            this.p.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.o));
        } else {
            this.p.open(route, httpContext, this.o);
        }
        a(route, httpContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cz.msebera.android.httpclient.HttpResponse b(cz.msebera.android.httpclient.impl.client.RoutedRequest r8, cz.msebera.android.httpclient.protocol.HttpContext r9) throws cz.msebera.android.httpclient.HttpException, java.io.IOException {
        /*
        r7 = this;
        r1 = 0;
        r2 = r8.getRequest();
        r3 = r8.getRoute();
        r0 = r1;
    L_0x000a:
        r4 = r7.t;
        r4 = r4 + 1;
        r7.t = r4;
        r2.incrementExecCount();
        r4 = r2.isRepeatable();
        if (r4 != 0) goto L_0x0032;
    L_0x0019:
        r1 = r7.log;
        r2 = "Cannot retry non-repeatable request";
        r1.debug(r2);
        if (r0 == 0) goto L_0x002a;
    L_0x0022:
        r1 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r2 = "Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.";
        r1.<init>(r2, r0);
        throw r1;
    L_0x002a:
        r0 = new cz.msebera.android.httpclient.client.NonRepeatableRequestException;
        r1 = "Cannot retry request with a non-repeatable request entity.";
        r0.<init>(r1);
        throw r0;
    L_0x0032:
        r0 = r7.p;	 Catch:{ IOException -> 0x0087 }
        r0 = r0.isOpen();	 Catch:{ IOException -> 0x0087 }
        if (r0 != 0) goto L_0x004e;
    L_0x003a:
        r0 = r3.isTunnelled();	 Catch:{ IOException -> 0x0087 }
        if (r0 != 0) goto L_0x007f;
    L_0x0040:
        r0 = r7.log;	 Catch:{ IOException -> 0x0087 }
        r4 = "Reopening the direct connection.";
        r0.debug(r4);	 Catch:{ IOException -> 0x0087 }
        r0 = r7.p;	 Catch:{ IOException -> 0x0087 }
        r4 = r7.o;	 Catch:{ IOException -> 0x0087 }
        r0.open(r3, r9, r4);	 Catch:{ IOException -> 0x0087 }
    L_0x004e:
        r0 = r7.log;	 Catch:{ IOException -> 0x0087 }
        r0 = r0.isDebugEnabled();	 Catch:{ IOException -> 0x0087 }
        if (r0 == 0) goto L_0x0076;
    L_0x0056:
        r0 = r7.log;	 Catch:{ IOException -> 0x0087 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0087 }
        r4.<init>();	 Catch:{ IOException -> 0x0087 }
        r5 = "Attempt ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r5 = r7.t;	 Catch:{ IOException -> 0x0087 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r5 = " to execute request";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0087 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0087 }
        r0.debug(r4);	 Catch:{ IOException -> 0x0087 }
    L_0x0076:
        r0 = r7.e;	 Catch:{ IOException -> 0x0087 }
        r4 = r7.p;	 Catch:{ IOException -> 0x0087 }
        r1 = r0.execute(r2, r4, r9);	 Catch:{ IOException -> 0x0087 }
    L_0x007e:
        return r1;
    L_0x007f:
        r0 = r7.log;	 Catch:{ IOException -> 0x0087 }
        r4 = "Proxied connection. Need to start over.";
        r0.debug(r4);	 Catch:{ IOException -> 0x0087 }
        goto L_0x007e;
    L_0x0087:
        r0 = move-exception;
        r4 = r7.log;
        r5 = "Closing the connection.";
        r4.debug(r5);
        r4 = r7.p;	 Catch:{ IOException -> 0x0140 }
        r4.close();	 Catch:{ IOException -> 0x0140 }
    L_0x0094:
        r4 = r7.g;
        r5 = r2.getExecCount();
        r4 = r4.retryRequest(r0, r5, r9);
        if (r4 == 0) goto L_0x0113;
    L_0x00a0:
        r4 = r7.log;
        r4 = r4.isInfoEnabled();
        if (r4 == 0) goto L_0x00e0;
    L_0x00a8:
        r4 = r7.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "I/O exception (";
        r5 = r5.append(r6);
        r6 = r0.getClass();
        r6 = r6.getName();
        r5 = r5.append(r6);
        r6 = ") caught when processing request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r0.getMessage();
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.info(r5);
    L_0x00e0:
        r4 = r7.log;
        r4 = r4.isDebugEnabled();
        if (r4 == 0) goto L_0x00f1;
    L_0x00e8:
        r4 = r7.log;
        r5 = r0.getMessage();
        r4.debug(r5, r0);
    L_0x00f1:
        r4 = r7.log;
        r4 = r4.isInfoEnabled();
        if (r4 == 0) goto L_0x000a;
    L_0x00f9:
        r4 = r7.log;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Retrying request to ";
        r5 = r5.append(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.info(r5);
        goto L_0x000a;
    L_0x0113:
        r1 = r0 instanceof cz.msebera.android.httpclient.NoHttpResponseException;
        if (r1 == 0) goto L_0x013f;
    L_0x0117:
        r1 = new cz.msebera.android.httpclient.NoHttpResponseException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r3.getTargetHost();
        r3 = r3.toHostString();
        r2 = r2.append(r3);
        r3 = " failed to respond";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        r0 = r0.getStackTrace();
        r1.setStackTrace(r0);
        throw r1;
    L_0x013f:
        throw r0;
    L_0x0140:
        r4 = move-exception;
        goto L_0x0094;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.client.DefaultRequestDirector.b(cz.msebera.android.httpclient.impl.client.RoutedRequest, cz.msebera.android.httpclient.protocol.HttpContext):cz.msebera.android.httpclient.HttpResponse");
    }

    protected void a() {
        try {
            this.p.releaseConnection();
        } catch (Throwable e) {
            this.log.debug("IOException releasing connection", e);
        }
        this.p = null;
    }

    protected HttpRoute a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        HttpRoutePlanner httpRoutePlanner = this.b;
        if (httpHost == null) {
            httpHost = (HttpHost) httpRequest.getParams().getParameter(ClientPNames.DEFAULT_HOST);
        }
        return httpRoutePlanner.determineRoute(httpHost, httpRequest, httpContext);
    }

    protected void a(HttpRoute httpRoute, HttpContext httpContext) throws HttpException, IOException {
        HttpRouteDirector basicRouteDirector = new BasicRouteDirector();
        int nextStep;
        do {
            Object route = this.p.getRoute();
            nextStep = basicRouteDirector.nextStep(httpRoute, route);
            switch (nextStep) {
                case -1:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + route);
                case 0:
                    break;
                case 1:
                case 2:
                    this.p.open(httpRoute, httpContext, this.o);
                    continue;
                case 3:
                    boolean b = b(httpRoute, httpContext);
                    this.log.debug("Tunnel to target created.");
                    this.p.tunnelTarget(b, this.o);
                    continue;
                case 4:
                    int hopCount = route.getHopCount() - 1;
                    boolean a = a(httpRoute, hopCount, httpContext);
                    this.log.debug("Tunnel to proxy created.");
                    this.p.tunnelProxy(httpRoute.getHopTarget(hopCount), a, this.o);
                    continue;
                case 5:
                    this.p.layerProtocol(httpContext, this.o);
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + nextStep + " from RouteDirector.");
            }
        } while (nextStep > 0);
    }

    protected boolean b(HttpRoute httpRoute, HttpContext httpContext) throws HttpException, IOException {
        HttpHost proxyHost = httpRoute.getProxyHost();
        HttpHost targetHost = httpRoute.getTargetHost();
        while (true) {
            if (!this.p.isOpen()) {
                this.p.open(httpRoute, httpContext, this.o);
            }
            HttpRequest c = c(httpRoute, httpContext);
            c.setParams(this.o);
            httpContext.setAttribute("http.target_host", targetHost);
            httpContext.setAttribute("http.route", httpRoute);
            httpContext.setAttribute(ExecutionContext.HTTP_PROXY_HOST, proxyHost);
            httpContext.setAttribute("http.connection", this.p);
            httpContext.setAttribute("http.request", c);
            this.e.preProcess(c, this.f, httpContext);
            HttpResponse execute = this.e.execute(c, this.p, httpContext);
            execute.setParams(this.o);
            this.e.postProcess(execute, this.f, httpContext);
            if (execute.getStatusLine().getStatusCode() < 200) {
                throw new HttpException("Unexpected response to CONNECT request: " + execute.getStatusLine());
            } else if (HttpClientParams.isAuthenticating(this.o)) {
                if (this.s.isAuthenticationRequested(proxyHost, execute, this.m, this.r, httpContext) && this.s.authenticate(proxyHost, execute, this.m, this.r, httpContext)) {
                    if (this.c.keepAlive(execute, httpContext)) {
                        this.log.debug("Connection kept alive");
                        EntityUtils.consume(execute.getEntity());
                    } else {
                        this.p.close();
                    }
                }
            }
        }
        if (execute.getStatusLine().getStatusCode() > 299) {
            HttpEntity entity = execute.getEntity();
            if (entity != null) {
                execute.setEntity(new BufferedHttpEntity(entity));
            }
            this.p.close();
            throw new TunnelRefusedException("CONNECT refused by proxy: " + execute.getStatusLine(), execute);
        }
        this.p.markReusable();
        return false;
    }

    protected boolean a(HttpRoute httpRoute, int i, HttpContext httpContext) throws HttpException, IOException {
        throw new HttpException("Proxy chains are not supported.");
    }

    protected HttpRequest c(HttpRoute httpRoute, HttpContext httpContext) {
        HttpHost targetHost = httpRoute.getTargetHost();
        String hostName = targetHost.getHostName();
        int port = targetHost.getPort();
        if (port < 0) {
            port = this.a.getSchemeRegistry().getScheme(targetHost.getSchemeName()).getDefaultPort();
        }
        StringBuilder stringBuilder = new StringBuilder(hostName.length() + 6);
        stringBuilder.append(hostName);
        stringBuilder.append(':');
        stringBuilder.append(Integer.toString(port));
        return new BasicHttpRequest("CONNECT", stringBuilder.toString(), HttpProtocolParams.getVersion(this.o));
    }

    protected RoutedRequest a(RoutedRequest routedRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpRoute route = routedRequest.getRoute();
        Object request = routedRequest.getRequest();
        HttpParams params = request.getParams();
        if (HttpClientParams.isAuthenticating(params)) {
            HttpHost httpHost;
            HttpHost httpHost2 = (HttpHost) httpContext.getAttribute("http.target_host");
            if (httpHost2 == null) {
                httpHost2 = route.getTargetHost();
            }
            if (httpHost2.getPort() < 0) {
                httpHost = new HttpHost(httpHost2.getHostName(), this.a.getSchemeRegistry().getScheme(httpHost2).getDefaultPort(), httpHost2.getSchemeName());
            } else {
                httpHost = httpHost2;
            }
            boolean isAuthenticationRequested = this.s.isAuthenticationRequested(httpHost, httpResponse, this.k, this.q, httpContext);
            HttpHost proxyHost = route.getProxyHost();
            if (proxyHost == null) {
                proxyHost = route.getTargetHost();
            }
            boolean isAuthenticationRequested2 = this.s.isAuthenticationRequested(proxyHost, httpResponse, this.m, this.r, httpContext);
            if (isAuthenticationRequested) {
                if (this.s.authenticate(httpHost, httpResponse, this.k, this.q, httpContext)) {
                    return routedRequest;
                }
            }
            if (isAuthenticationRequested2) {
                if (this.s.authenticate(proxyHost, httpResponse, this.m, this.r, httpContext)) {
                    return routedRequest;
                }
            }
        }
        if (!HttpClientParams.isRedirecting(params) || !this.i.isRedirected(request, httpResponse, httpContext)) {
            return null;
        }
        if (this.u >= this.v) {
            throw new RedirectException("Maximum redirects (" + this.v + ") exceeded");
        }
        this.u++;
        this.w = null;
        HttpRequest redirect = this.i.getRedirect(request, httpResponse, httpContext);
        redirect.setHeaders(request.getOriginal().getAllHeaders());
        URI uri = redirect.getURI();
        HttpHost extractHost = URIUtils.extractHost(uri);
        if (extractHost == null) {
            throw new ProtocolException("Redirect URI does not specify a valid host name: " + uri);
        }
        if (!route.getTargetHost().equals(extractHost)) {
            this.log.debug("Resetting target auth state");
            this.q.reset();
            AuthScheme authScheme = this.r.getAuthScheme();
            if (authScheme != null && authScheme.isConnectionBased()) {
                this.log.debug("Resetting proxy auth state");
                this.r.reset();
            }
        }
        redirect = a(redirect);
        redirect.setParams(params);
        HttpRoute a = a(extractHost, redirect, httpContext);
        RoutedRequest routedRequest2 = new RoutedRequest(redirect, a);
        if (!this.log.isDebugEnabled()) {
            return routedRequest2;
        }
        this.log.debug("Redirecting to '" + uri + "' via " + a);
        return routedRequest2;
    }

    private void b() {
        ManagedClientConnection managedClientConnection = this.p;
        if (managedClientConnection != null) {
            this.p = null;
            try {
                managedClientConnection.abortConnection();
            } catch (Throwable e) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(e.getMessage(), e);
                }
            }
            try {
                managedClientConnection.releaseConnection();
            } catch (Throwable e2) {
                this.log.debug("Error releasing connection", e2);
            }
        }
    }
}
