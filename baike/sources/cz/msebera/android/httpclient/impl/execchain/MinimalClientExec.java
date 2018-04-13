package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.HttpClientConnection;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.protocol.RequestContent;
import cz.msebera.android.httpclient.protocol.RequestTargetHost;
import cz.msebera.android.httpclient.protocol.RequestUserAgent;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.VersionInfo;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Immutable
public class MinimalClientExec implements ClientExecChain {
    private final HttpRequestExecutor a;
    private final HttpClientConnectionManager b;
    private final ConnectionReuseStrategy c;
    private final ConnectionKeepAliveStrategy d;
    private final HttpProcessor e;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public MinimalClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        Args.notNull(httpRequestExecutor, "HTTP request executor");
        Args.notNull(httpClientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        this.e = new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", getClass()))});
        this.a = httpRequestExecutor;
        this.b = httpClientConnectionManager;
        this.c = connectionReuseStrategy;
        this.d = connectionKeepAliveStrategy;
    }

    static void a(HttpRequestWrapper httpRequestWrapper, HttpRoute httpRoute) throws ProtocolException {
        try {
            URI uri = httpRequestWrapper.getURI();
            if (uri != null) {
                if (uri.isAbsolute()) {
                    uri = URIUtils.rewriteURI(uri, null, true);
                } else {
                    uri = URIUtils.rewriteURI(uri);
                }
                httpRequestWrapper.setURI(uri);
            }
        } catch (Throwable e) {
            throw new ProtocolException("Invalid URI: " + httpRequestWrapper.getRequestLine().getUri(), e);
        }
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        Throwable e;
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        a(httpRequestWrapper, httpRoute);
        Object requestConnection = this.b.requestConnection(httpRoute, null);
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
            int connectTimeout;
            HttpResponse execute;
            HttpEntity entity;
            int connectionRequestTimeout = requestConfig.getConnectionRequestTimeout();
            if (connectionRequestTimeout > 0) {
                j = (long) connectionRequestTimeout;
            } else {
                j = 0;
            }
            HttpClientConnection httpClientConnection = requestConnection.get(j, TimeUnit.MILLISECONDS);
            a aVar = new a(this.log, this.b, httpClientConnection);
            if (httpExecutionAware != null) {
                try {
                    if (httpExecutionAware.isAborted()) {
                        aVar.close();
                        throw new RequestAbortedException("Request aborted");
                    }
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
            if (!httpClientConnection.isOpen()) {
                connectTimeout = requestConfig.getConnectTimeout();
                HttpClientConnectionManager httpClientConnectionManager = this.b;
                if (connectTimeout <= 0) {
                    connectTimeout = 0;
                }
                httpClientConnectionManager.connect(httpClientConnection, httpRoute, connectTimeout, httpClientContext);
                this.b.routeComplete(httpClientConnection, httpRoute, httpClientContext);
            }
            connectTimeout = requestConfig.getSocketTimeout();
            if (connectTimeout >= 0) {
                httpClientConnection.setSocketTimeout(connectTimeout);
            }
            HttpRequest original = httpRequestWrapper.getOriginal();
            if (original instanceof HttpUriRequest) {
                URI uri = ((HttpUriRequest) original).getURI();
                if (uri.isAbsolute()) {
                    requestConnection = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
                    if (requestConnection == null) {
                        requestConnection = httpRoute.getTargetHost();
                    }
                    httpClientContext.setAttribute("http.target_host", requestConnection);
                    httpClientContext.setAttribute("http.request", httpRequestWrapper);
                    httpClientContext.setAttribute("http.connection", httpClientConnection);
                    httpClientContext.setAttribute("http.route", httpRoute);
                    this.e.process(httpRequestWrapper, httpClientContext);
                    execute = this.a.execute(httpRequestWrapper, httpClientConnection, httpClientContext);
                    this.e.process(execute, httpClientContext);
                    if (this.c.keepAlive(execute, httpClientContext)) {
                        aVar.markNonReusable();
                    } else {
                        aVar.setValidFor(this.d.getKeepAliveDuration(execute, httpClientContext), TimeUnit.MILLISECONDS);
                        aVar.markReusable();
                    }
                    entity = execute.getEntity();
                    if (entity == null && entity.isStreaming()) {
                        return new b(execute, aVar);
                    }
                    aVar.releaseConnection();
                    return new b(execute, null);
                }
            }
            requestConnection = null;
            if (requestConnection == null) {
                requestConnection = httpRoute.getTargetHost();
            }
            httpClientContext.setAttribute("http.target_host", requestConnection);
            httpClientContext.setAttribute("http.request", httpRequestWrapper);
            httpClientContext.setAttribute("http.connection", httpClientConnection);
            httpClientContext.setAttribute("http.route", httpRoute);
            this.e.process(httpRequestWrapper, httpClientContext);
            execute = this.a.execute(httpRequestWrapper, httpClientConnection, httpClientContext);
            this.e.process(execute, httpClientContext);
            if (this.c.keepAlive(execute, httpClientContext)) {
                aVar.markNonReusable();
            } else {
                aVar.setValidFor(this.d.getKeepAliveDuration(execute, httpClientContext), TimeUnit.MILLISECONDS);
                aVar.markReusable();
            }
            entity = execute.getEntity();
            if (entity == null) {
            }
            aVar.releaseConnection();
            return new b(execute, null);
        } catch (Throwable e22) {
            Thread.currentThread().interrupt();
            throw new RequestAbortedException("Request aborted", e22);
        } catch (ExecutionException e6) {
            e22 = e6;
            Throwable cause = e22.getCause();
            if (cause != null) {
                e22 = cause;
            }
            throw new RequestAbortedException("Request execution failed", e22);
        }
    }
}
