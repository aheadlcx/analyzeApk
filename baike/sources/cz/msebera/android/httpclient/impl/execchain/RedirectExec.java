package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.client.RedirectException;
import cz.msebera.android.httpclient.client.RedirectStrategy;
import cz.msebera.android.httpclient.client.config.RequestConfig;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoutePlanner;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@ThreadSafe
public class RedirectExec implements ClientExecChain {
    private final ClientExecChain a;
    private final RedirectStrategy b;
    private final HttpRoutePlanner c;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public RedirectExec(ClientExecChain clientExecChain, HttpRoutePlanner httpRoutePlanner, RedirectStrategy redirectStrategy) {
        Args.notNull(clientExecChain, "HTTP client request executor");
        Args.notNull(httpRoutePlanner, "HTTP route planner");
        Args.notNull(redirectStrategy, "HTTP redirect strategy");
        this.a = clientExecChain;
        this.c = httpRoutePlanner;
        this.b = redirectStrategy;
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        int maxRedirects;
        CloseableHttpResponse execute;
        Args.notNull(httpRoute, "HTTP route");
        Args.notNull(httpRequestWrapper, "HTTP request");
        Args.notNull(httpClientContext, "HTTP context");
        List redirectLocations = httpClientContext.getRedirectLocations();
        if (redirectLocations != null) {
            redirectLocations.clear();
        }
        RequestConfig requestConfig = httpClientContext.getRequestConfig();
        if (requestConfig.getMaxRedirects() > 0) {
            maxRedirects = requestConfig.getMaxRedirects();
        } else {
            maxRedirects = 50;
        }
        int i = 0;
        HttpRequest httpRequest = httpRequestWrapper;
        while (true) {
            execute = this.a.execute(httpRoute, httpRequest, httpClientContext, httpExecutionAware);
            try {
                if (!requestConfig.isRedirectsEnabled() || !this.b.isRedirected(httpRequest, execute, httpClientContext)) {
                    return execute;
                }
                if (i >= maxRedirects) {
                    throw new RedirectException("Maximum redirects (" + maxRedirects + ") exceeded");
                }
                int i2 = i + 1;
                HttpRequest redirect = this.b.getRedirect(httpRequest, execute, httpClientContext);
                if (!redirect.headerIterator().hasNext()) {
                    redirect.setHeaders(httpRequestWrapper.getOriginal().getAllHeaders());
                }
                httpRequest = HttpRequestWrapper.wrap(redirect);
                if (httpRequest instanceof HttpEntityEnclosingRequest) {
                    c.a((HttpEntityEnclosingRequest) httpRequest);
                }
                URI uri = httpRequest.getURI();
                HttpHost extractHost = URIUtils.extractHost(uri);
                if (extractHost == null) {
                    throw new ProtocolException("Redirect URI does not specify a valid host name: " + uri);
                }
                if (!httpRoute.getTargetHost().equals(extractHost)) {
                    AuthState targetAuthState = httpClientContext.getTargetAuthState();
                    if (targetAuthState != null) {
                        this.log.debug("Resetting target auth state");
                        targetAuthState.reset();
                    }
                    targetAuthState = httpClientContext.getProxyAuthState();
                    if (targetAuthState != null) {
                        AuthScheme authScheme = targetAuthState.getAuthScheme();
                        if (authScheme != null && authScheme.isConnectionBased()) {
                            this.log.debug("Resetting proxy auth state");
                            targetAuthState.reset();
                        }
                    }
                }
                httpRoute = this.c.determineRoute(extractHost, httpRequest, httpClientContext);
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Redirecting to '" + uri + "' via " + httpRoute);
                }
                EntityUtils.consume(execute.getEntity());
                execute.close();
                i = i2;
            } catch (RuntimeException e) {
                execute.close();
                throw e;
            } catch (IOException e2) {
                execute.close();
                throw e2;
            } catch (Object e3) {
                try {
                    EntityUtils.consume(execute.getEntity());
                } catch (Throwable e4) {
                    this.log.debug("I/O error while releasing connection", e4);
                    throw e3;
                } finally {
                    execute.close();
                }
                throw e3;
            }
        }
        return execute;
    }
}
