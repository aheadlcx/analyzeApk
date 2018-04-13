package cz.msebera.android.httpclient.impl.execchain;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.auth.AuthProtocolState;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.client.AuthenticationStrategy;
import cz.msebera.android.httpclient.client.NonRepeatableRequestException;
import cz.msebera.android.httpclient.client.UserTokenHandler;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.routing.BasicRouteDirector;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRouteDirector;
import cz.msebera.android.httpclient.conn.routing.RouteTracker;
import cz.msebera.android.httpclient.entity.BufferedHttpEntity;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.auth.HttpAuthenticator;
import cz.msebera.android.httpclient.message.BasicHttpRequest;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.protocol.RequestTargetHost;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Immutable
public class MainClientExec implements ClientExecChain {
    private final HttpRequestExecutor a;
    private final HttpClientConnectionManager b;
    private final ConnectionReuseStrategy c;
    private final ConnectionKeepAliveStrategy d;
    private final HttpProcessor e;
    private final AuthenticationStrategy f;
    private final AuthenticationStrategy g;
    private final HttpAuthenticator h;
    private final UserTokenHandler i;
    private final HttpRouteDirector j;
    public HttpClientAndroidLog log;

    public MainClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpProcessor httpProcessor, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        this.log = new HttpClientAndroidLog(getClass());
        Args.notNull(httpRequestExecutor, "HTTP request executor");
        Args.notNull(httpClientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        Args.notNull(httpProcessor, "Proxy HTTP processor");
        Args.notNull(authenticationStrategy, "Target authentication strategy");
        Args.notNull(authenticationStrategy2, "Proxy authentication strategy");
        Args.notNull(userTokenHandler, "User token handler");
        this.h = new HttpAuthenticator();
        this.j = new BasicRouteDirector();
        this.a = httpRequestExecutor;
        this.b = httpClientConnectionManager;
        this.c = connectionReuseStrategy;
        this.d = connectionKeepAliveStrategy;
        this.e = httpProcessor;
        this.f = authenticationStrategy;
        this.g = authenticationStrategy2;
        this.i = userTokenHandler;
    }

    public MainClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, AuthenticationStrategy authenticationStrategy, AuthenticationStrategy authenticationStrategy2, UserTokenHandler userTokenHandler) {
        this(httpRequestExecutor, httpClientConnectionManager, connectionReuseStrategy, connectionKeepAliveStrategy, new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestTargetHost()}), authenticationStrategy, authenticationStrategy2, userTokenHandler);
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        AuthState authState;
        Throwable e;
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        AuthState targetAuthState = httpClientContext.getTargetAuthState();
        if (targetAuthState == null) {
            targetAuthState = new AuthState();
            httpClientContext.setAttribute("http.auth.target-scope", targetAuthState);
            authState = targetAuthState;
        } else {
            authState = targetAuthState;
        }
        AuthState proxyAuthState = httpClientContext.getProxyAuthState();
        if (proxyAuthState == null) {
            proxyAuthState = new AuthState();
            httpClientContext.setAttribute("http.auth.proxy-scope", proxyAuthState);
        }
        if (httpRequestWrapper instanceof HttpEntityEnclosingRequest) {
            c.a((HttpEntityEnclosingRequest) httpRequestWrapper);
        }
        Object userToken = httpClientContext.getUserToken();
        Object requestConnection = this.b.requestConnection(httpRoute, userToken);
        if (httpExecutionAware != null) {
            if (httpExecutionAware.isAborted()) {
                requestConnection.cancel();
                throw new RequestAbortedException("Request aborted");
            }
            httpExecutionAware.setCancellable(requestConnection);
        }
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        try {
            long j;
            HttpResponse response;
            HttpEntity entity;
            int connectionRequestTimeout = requestConfig.getConnectionRequestTimeout();
            if (connectionRequestTimeout > 0) {
                j = (long) connectionRequestTimeout;
            } else {
                j = 0;
            }
            HttpClientConnection httpClientConnection = requestConnection.get(j, TimeUnit.MILLISECONDS);
            httpClientContext.setAttribute("http.connection", httpClientConnection);
            if (requestConfig.isStaleConnectionCheckEnabled() && httpClientConnection.isOpen()) {
                this.log.debug("Stale connection check");
                if (httpClientConnection.isStale()) {
                    this.log.debug("Stale connection detected");
                    httpClientConnection.close();
                }
            }
            a aVar = new a(this.log, this.b, httpClientConnection);
            if (httpExecutionAware != null) {
                try {
                    httpExecutionAware.setCancellable(aVar);
                } catch (Throwable e2) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                    interruptedIOException.initCause(e2);
                    throw interruptedIOException;
                } catch (HttpException e3) {
                    aVar.abortConnection();
                    throw e3;
                } catch (IOException e4) {
                    aVar.abortConnection();
                    throw e4;
                } catch (RuntimeException e5) {
                    aVar.abortConnection();
                    throw e5;
                }
            }
            int i = 1;
            while (true) {
                if (i <= 1 || c.a(httpRequestWrapper)) {
                    if (httpExecutionAware != null) {
                        if (httpExecutionAware.isAborted()) {
                            throw new RequestAbortedException("Request aborted");
                        }
                    }
                    if (!httpClientConnection.isOpen()) {
                        this.log.debug("Opening connection " + httpRoute);
                        try {
                            a(proxyAuthState, httpClientConnection, httpRoute, (HttpRequest) httpRequestWrapper, httpClientContext);
                        } catch (TunnelRefusedException e6) {
                            if (this.log.isDebugEnabled()) {
                                this.log.debug(e6.getMessage());
                            }
                            response = e6.getResponse();
                        }
                    }
                    int socketTimeout = requestConfig.getSocketTimeout();
                    if (socketTimeout >= 0) {
                        httpClientConnection.setSocketTimeout(socketTimeout);
                    }
                    if (httpExecutionAware == null || !httpExecutionAware.isAborted()) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Executing request " + httpRequestWrapper.getRequestLine());
                        }
                        if (!httpRequestWrapper.containsHeader("Authorization")) {
                            if (this.log.isDebugEnabled()) {
                                this.log.debug("Target auth state: " + authState.getState());
                            }
                            this.h.generateAuthResponse(httpRequestWrapper, authState, httpClientContext);
                        }
                        if (!(httpRequestWrapper.containsHeader("Proxy-Authorization") || httpRoute.isTunnelled())) {
                            if (this.log.isDebugEnabled()) {
                                this.log.debug("Proxy auth state: " + proxyAuthState.getState());
                            }
                            this.h.generateAuthResponse(httpRequestWrapper, proxyAuthState, httpClientContext);
                        }
                        response = this.a.execute(httpRequestWrapper, httpClientConnection, httpClientContext);
                        if (this.c.keepAlive(response, httpClientContext)) {
                            long keepAliveDuration = this.d.getKeepAliveDuration(response, httpClientContext);
                            if (this.log.isDebugEnabled()) {
                                String str;
                                if (keepAliveDuration > 0) {
                                    str = "for " + keepAliveDuration + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + TimeUnit.MILLISECONDS;
                                } else {
                                    str = "indefinitely";
                                }
                                this.log.debug("Connection can be kept alive " + str);
                            }
                            aVar.setValidFor(keepAliveDuration, TimeUnit.MILLISECONDS);
                            aVar.markReusable();
                        } else {
                            aVar.markNonReusable();
                        }
                        if (!a(authState, proxyAuthState, httpRoute, response, httpClientContext)) {
                            break;
                        }
                        entity = response.getEntity();
                        if (aVar.isReusable()) {
                            EntityUtils.consume(entity);
                        } else {
                            httpClientConnection.close();
                            if (proxyAuthState.getState() == AuthProtocolState.SUCCESS && proxyAuthState.getAuthScheme() != null && proxyAuthState.getAuthScheme().isConnectionBased()) {
                                this.log.debug("Resetting proxy auth state");
                                proxyAuthState.reset();
                            }
                            if (authState.getState() == AuthProtocolState.SUCCESS && authState.getAuthScheme() != null && authState.getAuthScheme().isConnectionBased()) {
                                this.log.debug("Resetting target auth state");
                                authState.reset();
                            }
                        }
                        HttpRequest original = httpRequestWrapper.getOriginal();
                        if (!original.containsHeader("Authorization")) {
                            httpRequestWrapper.removeHeaders("Authorization");
                        }
                        if (!original.containsHeader("Proxy-Authorization")) {
                            httpRequestWrapper.removeHeaders("Proxy-Authorization");
                        }
                        i++;
                    } else {
                        throw new RequestAbortedException("Request aborted");
                    }
                }
                throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
            }
            if (userToken == null) {
                requestConnection = this.i.getUserToken(httpClientContext);
                httpClientContext.setAttribute("http.user-token", requestConnection);
            } else {
                requestConnection = userToken;
            }
            if (requestConnection != null) {
                aVar.setState(requestConnection);
            }
            entity = response.getEntity();
            if (entity != null && entity.isStreaming()) {
                return new b(response, aVar);
            }
            aVar.releaseConnection();
            return new b(response, null);
        } catch (Throwable e22) {
            Thread.currentThread().interrupt();
            throw new RequestAbortedException("Request aborted", e22);
        } catch (ExecutionException e7) {
            e22 = e7;
            Throwable cause = e22.getCause();
            if (cause != null) {
                e22 = cause;
            }
            throw new RequestAbortedException("Request execution failed", e22);
        }
    }

    void a(AuthState authState, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, HttpClientContext httpClientContext) throws HttpException, IOException {
        int connectTimeout = httpClientContext.getRequestConfig().getConnectTimeout();
        RouteTracker routeTracker = new RouteTracker(httpRoute);
        int nextStep;
        do {
            Object toRoute = routeTracker.toRoute();
            nextStep = this.j.nextStep(httpRoute, toRoute);
            switch (nextStep) {
                case -1:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + toRoute);
                case 0:
                    this.b.routeComplete(httpClientConnection, httpRoute, httpClientContext);
                    continue;
                case 1:
                    this.b.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
                    routeTracker.connectTarget(httpRoute.isSecure());
                    continue;
                case 2:
                    this.b.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
                    routeTracker.connectProxy(httpRoute.getProxyHost(), false);
                    continue;
                case 3:
                    boolean b = b(authState, httpClientConnection, httpRoute, httpRequest, httpClientContext);
                    this.log.debug("Tunnel to target created.");
                    routeTracker.tunnelTarget(b);
                    continue;
                case 4:
                    int hopCount = toRoute.getHopCount() - 1;
                    boolean a = a(httpRoute, hopCount, httpClientContext);
                    this.log.debug("Tunnel to proxy created.");
                    routeTracker.tunnelProxy(httpRoute.getHopTarget(hopCount), a);
                    continue;
                case 5:
                    this.b.upgrade(httpClientConnection, httpRoute, httpClientContext);
                    routeTracker.layerProtocol(httpRoute.isSecure());
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + nextStep + " from RouteDirector.");
            }
        } while (nextStep > 0);
    }

    private boolean b(AuthState authState, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, HttpClientContext httpClientContext) throws HttpException, IOException {
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        int connectTimeout = requestConfig.getConnectTimeout();
        HttpHost targetHost = httpRoute.getTargetHost();
        HttpHost proxyHost = httpRoute.getProxyHost();
        HttpResponse httpResponse = null;
        HttpRequest basicHttpRequest = new BasicHttpRequest("CONNECT", targetHost.toHostString(), httpRequest.getProtocolVersion());
        this.a.preProcess(basicHttpRequest, this.e, httpClientContext);
        while (httpResponse == null) {
            if (!httpClientConnection.isOpen()) {
                this.b.connect(httpClientConnection, httpRoute, connectTimeout > 0 ? connectTimeout : 0, httpClientContext);
            }
            basicHttpRequest.removeHeaders("Proxy-Authorization");
            this.h.generateAuthResponse(basicHttpRequest, authState, httpClientContext);
            httpResponse = this.a.execute(basicHttpRequest, httpClientConnection, httpClientContext);
            if (httpResponse.getStatusLine().getStatusCode() < 200) {
                throw new HttpException("Unexpected response to CONNECT request: " + httpResponse.getStatusLine());
            } else if (requestConfig.isAuthenticationEnabled() && this.h.isAuthenticationRequested(proxyHost, httpResponse, this.g, authState, httpClientContext) && this.h.handleAuthChallenge(proxyHost, httpResponse, this.g, authState, httpClientContext)) {
                if (this.c.keepAlive(httpResponse, httpClientContext)) {
                    this.log.debug("Connection kept alive");
                    EntityUtils.consume(httpResponse.getEntity());
                } else {
                    httpClientConnection.close();
                }
                httpResponse = null;
            }
        }
        if (httpResponse.getStatusLine().getStatusCode() <= 299) {
            return false;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            httpResponse.setEntity(new BufferedHttpEntity(entity));
        }
        httpClientConnection.close();
        throw new TunnelRefusedException("CONNECT refused by proxy: " + httpResponse.getStatusLine(), httpResponse);
    }

    private boolean a(HttpRoute httpRoute, int i, HttpClientContext httpClientContext) throws HttpException {
        throw new HttpException("Proxy chains are not supported.");
    }

    private boolean a(AuthState authState, AuthState authState2, HttpRoute httpRoute, HttpResponse httpResponse, HttpClientContext httpClientContext) {
        if (httpClientContext.getRequestConfig().isAuthenticationEnabled()) {
            HttpHost httpHost;
            HttpHost targetHost = httpClientContext.getTargetHost();
            if (targetHost == null) {
                targetHost = httpRoute.getTargetHost();
            }
            if (targetHost.getPort() < 0) {
                httpHost = new HttpHost(targetHost.getHostName(), httpRoute.getTargetHost().getPort(), targetHost.getSchemeName());
            } else {
                httpHost = targetHost;
            }
            boolean isAuthenticationRequested = this.h.isAuthenticationRequested(httpHost, httpResponse, this.f, authState, httpClientContext);
            HttpHost proxyHost = httpRoute.getProxyHost();
            if (proxyHost == null) {
                proxyHost = httpRoute.getTargetHost();
            }
            boolean isAuthenticationRequested2 = this.h.isAuthenticationRequested(proxyHost, httpResponse, this.g, authState2, httpClientContext);
            if (isAuthenticationRequested) {
                return this.h.handleAuthChallenge(httpHost, httpResponse, this.f, authState, httpClientContext);
            } else if (isAuthenticationRequested2) {
                return this.h.handleAuthChallenge(proxyHost, httpResponse, this.g, authState2, httpClientContext);
            }
        }
        return false;
    }
}
