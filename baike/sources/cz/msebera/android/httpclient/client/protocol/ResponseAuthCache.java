package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.auth.AuthScheme;
import cz.msebera.android.httpclient.auth.AuthState;
import cz.msebera.android.httpclient.client.AuthCache;
import cz.msebera.android.httpclient.conn.scheme.SchemeRegistry;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.client.BasicAuthCache;
import cz.msebera.android.httpclient.protocol.ExecutionContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

@Immutable
@Deprecated
public class ResponseAuthCache implements HttpResponseInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        AuthCache authCache;
        HttpHost httpHost;
        AuthState authState;
        Args.notNull(httpResponse, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        AuthCache authCache2 = (AuthCache) httpContext.getAttribute("http.auth.auth-cache");
        HttpHost httpHost2 = (HttpHost) httpContext.getAttribute("http.target_host");
        AuthState authState2 = (AuthState) httpContext.getAttribute("http.auth.target-scope");
        if (!(httpHost2 == null || authState2 == null)) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Target auth state: " + authState2.getState());
            }
            if (a(authState2)) {
                SchemeRegistry schemeRegistry = (SchemeRegistry) httpContext.getAttribute(ClientContext.SCHEME_REGISTRY);
                if (httpHost2.getPort() < 0) {
                    httpHost2 = new HttpHost(httpHost2.getHostName(), schemeRegistry.getScheme(httpHost2).resolvePort(httpHost2.getPort()), httpHost2.getSchemeName());
                }
                if (authCache2 == null) {
                    authCache2 = new BasicAuthCache();
                    httpContext.setAttribute("http.auth.auth-cache", authCache2);
                }
                switch (c.a[authState2.getState().ordinal()]) {
                    case 1:
                        a(authCache2, httpHost2, authState2.getAuthScheme());
                        authCache = authCache2;
                        break;
                    case 2:
                        b(authCache2, httpHost2, authState2.getAuthScheme());
                        break;
                }
                authCache = authCache2;
                httpHost = (HttpHost) httpContext.getAttribute(ExecutionContext.HTTP_PROXY_HOST);
                authState = (AuthState) httpContext.getAttribute("http.auth.proxy-scope");
                if (httpHost != null && authState != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Proxy auth state: " + authState.getState());
                    }
                    if (a(authState)) {
                        if (authCache == null) {
                            authCache = new BasicAuthCache();
                            httpContext.setAttribute("http.auth.auth-cache", authCache);
                        }
                        switch (c.a[authState.getState().ordinal()]) {
                            case 1:
                                a(authCache, httpHost, authState.getAuthScheme());
                                return;
                            case 2:
                                b(authCache, httpHost, authState.getAuthScheme());
                                return;
                            default:
                                return;
                        }
                    }
                    return;
                }
            }
        }
        authCache = authCache2;
        httpHost = (HttpHost) httpContext.getAttribute(ExecutionContext.HTTP_PROXY_HOST);
        authState = (AuthState) httpContext.getAttribute("http.auth.proxy-scope");
        if (httpHost != null) {
        }
    }

    private boolean a(AuthState authState) {
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String schemeName = authScheme.getSchemeName();
        if (schemeName.equalsIgnoreCase("Basic") || schemeName.equalsIgnoreCase("Digest")) {
            return true;
        }
        return false;
    }

    private void a(AuthCache authCache, HttpHost httpHost, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Caching '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
        }
        authCache.put(httpHost, authScheme);
    }

    private void b(AuthCache authCache, HttpHost httpHost, AuthScheme authScheme) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Removing from cache '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
        }
        authCache.remove(httpHost);
    }
}
