package org.apache.commons.httpclient;

import cn.v6.sixrooms.constants.CommonStrs;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.auth.AuthChallengeParser;
import org.apache.commons.httpclient.auth.AuthChallengeProcessor;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.auth.MalformedChallengeException;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class HttpMethodDirector {
    private static final Log LOG;
    public static final String PROXY_AUTH_CHALLENGE = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    public static final String WWW_AUTH_CHALLENGE = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    static Class class$org$apache$commons$httpclient$HttpMethodDirector;
    private AuthChallengeProcessor authProcessor = null;
    private HttpConnection conn;
    private ConnectMethod connectMethod;
    private HttpConnectionManager connectionManager;
    private HostConfiguration hostConfiguration;
    private HttpClientParams params;
    private Set redirectLocations = null;
    private boolean releaseConnection = false;
    private HttpState state;

    public void executeMethod(org.apache.commons.httpclient.HttpMethod r9) throws java.io.IOException, org.apache.commons.httpclient.HttpException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00ed in list [B:37:0x00e4]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r1 = 1;
        r2 = 0;
        if (r9 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Method may not be null";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = r8.hostConfiguration;
        r0 = r0.getParams();
        r3 = r8.params;
        r0.setDefaults(r3);
        r0 = r9.getParams();
        r3 = r8.hostConfiguration;
        r3 = r3.getParams();
        r0.setDefaults(r3);
        r0 = r8.hostConfiguration;
        r0 = r0.getParams();
        r3 = "http.default-headers";
        r0 = r0.getParameter(r3);
        r0 = (java.util.Collection) r0;
        if (r0 == 0) goto L_0x003e;
    L_0x0034:
        r3 = r0.iterator();
    L_0x0038:
        r0 = r3.hasNext();
        if (r0 != 0) goto L_0x00ee;
    L_0x003e:
        r0 = r8.params;	 Catch:{ all -> 0x012f }
        r3 = "http.protocol.max-redirects";	 Catch:{ all -> 0x012f }
        r4 = 100;	 Catch:{ all -> 0x012f }
        r4 = r0.getIntParameter(r3, r4);	 Catch:{ all -> 0x012f }
        r0 = r2;	 Catch:{ all -> 0x012f }
    L_0x0049:
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x0065;	 Catch:{ all -> 0x012f }
    L_0x004d:
        r3 = r8.hostConfiguration;	 Catch:{ all -> 0x012f }
        r5 = r8.conn;	 Catch:{ all -> 0x012f }
        r3 = r3.hostEquals(r5);	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x0065;	 Catch:{ all -> 0x012f }
    L_0x0057:
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        r5 = 0;	 Catch:{ all -> 0x012f }
        r3.setLocked(r5);	 Catch:{ all -> 0x012f }
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        r3.releaseConnection();	 Catch:{ all -> 0x012f }
        r3 = 0;	 Catch:{ all -> 0x012f }
        r8.conn = r3;	 Catch:{ all -> 0x012f }
    L_0x0065:
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x00c4;	 Catch:{ all -> 0x012f }
    L_0x0069:
        r3 = r8.connectionManager;	 Catch:{ all -> 0x012f }
        r5 = r8.hostConfiguration;	 Catch:{ all -> 0x012f }
        r6 = r8.params;	 Catch:{ all -> 0x012f }
        r6 = r6.getConnectionManagerTimeout();	 Catch:{ all -> 0x012f }
        r3 = r3.getConnectionWithTimeout(r5, r6);	 Catch:{ all -> 0x012f }
        r8.conn = r3;	 Catch:{ all -> 0x012f }
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        r5 = 1;	 Catch:{ all -> 0x012f }
        r3.setLocked(r5);	 Catch:{ all -> 0x012f }
        r3 = r8.params;	 Catch:{ all -> 0x012f }
        r3 = r3.isAuthenticationPreemptive();	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x008f;	 Catch:{ all -> 0x012f }
    L_0x0087:
        r3 = r8.state;	 Catch:{ all -> 0x012f }
        r3 = r3.isAuthenticationPreemptive();	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x00c4;	 Catch:{ all -> 0x012f }
    L_0x008f:
        r3 = LOG;	 Catch:{ all -> 0x012f }
        r5 = "Preemptively sending default basic credentials";	 Catch:{ all -> 0x012f }
        r3.debug(r5);	 Catch:{ all -> 0x012f }
        r3 = r9.getHostAuthState();	 Catch:{ all -> 0x012f }
        r3.setPreemptive();	 Catch:{ all -> 0x012f }
        r3 = r9.getHostAuthState();	 Catch:{ all -> 0x012f }
        r5 = 1;	 Catch:{ all -> 0x012f }
        r3.setAuthAttempted(r5);	 Catch:{ all -> 0x012f }
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        r3 = r3.isProxied();	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x00c4;	 Catch:{ all -> 0x012f }
    L_0x00ad:
        r3 = r8.conn;	 Catch:{ all -> 0x012f }
        r3 = r3.isSecure();	 Catch:{ all -> 0x012f }
        if (r3 != 0) goto L_0x00c4;	 Catch:{ all -> 0x012f }
    L_0x00b5:
        r3 = r9.getProxyAuthState();	 Catch:{ all -> 0x012f }
        r3.setPreemptive();	 Catch:{ all -> 0x012f }
        r3 = r9.getProxyAuthState();	 Catch:{ all -> 0x012f }
        r5 = 1;	 Catch:{ all -> 0x012f }
        r3.setAuthAttempted(r5);	 Catch:{ all -> 0x012f }
    L_0x00c4:
        r8.authenticate(r9);	 Catch:{ all -> 0x012f }
        r8.executeWithRetry(r9);	 Catch:{ all -> 0x012f }
        r3 = r8.connectMethod;	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x00f9;	 Catch:{ all -> 0x012f }
    L_0x00ce:
        r8.fakeResponse(r9);	 Catch:{ all -> 0x012f }
    L_0x00d1:
        r0 = r8.conn;
        if (r0 == 0) goto L_0x00da;
    L_0x00d5:
        r0 = r8.conn;
        r0.setLocked(r2);
    L_0x00da:
        r0 = r8.releaseConnection;
        if (r0 != 0) goto L_0x00e4;
    L_0x00de:
        r0 = r9.getResponseBodyAsStream();
        if (r0 != 0) goto L_0x00ed;
    L_0x00e4:
        r0 = r8.conn;
        if (r0 == 0) goto L_0x00ed;
    L_0x00e8:
        r0 = r8.conn;
        r0.releaseConnection();
    L_0x00ed:
        return;
    L_0x00ee:
        r0 = r3.next();
        r0 = (org.apache.commons.httpclient.Header) r0;
        r9.addRequestHeader(r0);
        goto L_0x0038;
    L_0x00f9:
        r3 = r8.isRedirectNeeded(r9);	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x019f;	 Catch:{ all -> 0x012f }
    L_0x00ff:
        r3 = r8.processRedirectResponse(r9);	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x019f;	 Catch:{ all -> 0x012f }
    L_0x0105:
        r0 = r0 + 1;	 Catch:{ all -> 0x012f }
        if (r0 < r4) goto L_0x014d;	 Catch:{ all -> 0x012f }
    L_0x0109:
        r0 = LOG;	 Catch:{ all -> 0x012f }
        r1 = "Narrowly avoided an infinite loop in execute";	 Catch:{ all -> 0x012f }
        r0.error(r1);	 Catch:{ all -> 0x012f }
        r0 = new org.apache.commons.httpclient.RedirectException;	 Catch:{ all -> 0x012f }
        r1 = new java.lang.StringBuffer;	 Catch:{ all -> 0x012f }
        r1.<init>();	 Catch:{ all -> 0x012f }
        r3 = "Maximum redirects (";	 Catch:{ all -> 0x012f }
        r1 = r1.append(r3);	 Catch:{ all -> 0x012f }
        r1 = r1.append(r4);	 Catch:{ all -> 0x012f }
        r3 = ") exceeded";	 Catch:{ all -> 0x012f }
        r1 = r1.append(r3);	 Catch:{ all -> 0x012f }
        r1 = r1.toString();	 Catch:{ all -> 0x012f }
        r0.<init>(r1);	 Catch:{ all -> 0x012f }
        throw r0;	 Catch:{ all -> 0x012f }
    L_0x012f:
        r0 = move-exception;
        r1 = r8.conn;
        if (r1 == 0) goto L_0x0139;
    L_0x0134:
        r1 = r8.conn;
        r1.setLocked(r2);
    L_0x0139:
        r1 = r8.releaseConnection;
        if (r1 != 0) goto L_0x0143;
    L_0x013d:
        r1 = r9.getResponseBodyAsStream();
        if (r1 != 0) goto L_0x014c;
    L_0x0143:
        r1 = r8.conn;
        if (r1 == 0) goto L_0x014c;
    L_0x0147:
        r1 = r8.conn;
        r1.releaseConnection();
    L_0x014c:
        throw r0;
    L_0x014d:
        r3 = LOG;	 Catch:{ all -> 0x012f }
        r3 = r3.isDebugEnabled();	 Catch:{ all -> 0x012f }
        if (r3 == 0) goto L_0x0177;	 Catch:{ all -> 0x012f }
    L_0x0155:
        r3 = LOG;	 Catch:{ all -> 0x012f }
        r5 = new java.lang.StringBuffer;	 Catch:{ all -> 0x012f }
        r5.<init>();	 Catch:{ all -> 0x012f }
        r6 = "Execute redirect ";	 Catch:{ all -> 0x012f }
        r5 = r5.append(r6);	 Catch:{ all -> 0x012f }
        r5 = r5.append(r0);	 Catch:{ all -> 0x012f }
        r6 = " of ";	 Catch:{ all -> 0x012f }
        r5 = r5.append(r6);	 Catch:{ all -> 0x012f }
        r5 = r5.append(r4);	 Catch:{ all -> 0x012f }
        r5 = r5.toString();	 Catch:{ all -> 0x012f }
        r3.debug(r5);	 Catch:{ all -> 0x012f }
    L_0x0177:
        r3 = r0;	 Catch:{ all -> 0x012f }
        r0 = r1;	 Catch:{ all -> 0x012f }
    L_0x0179:
        r5 = r8.isAuthenticationNeeded(r9);	 Catch:{ all -> 0x012f }
        if (r5 == 0) goto L_0x018d;	 Catch:{ all -> 0x012f }
    L_0x017f:
        r5 = r8.processAuthenticationResponse(r9);	 Catch:{ all -> 0x012f }
        if (r5 == 0) goto L_0x018d;	 Catch:{ all -> 0x012f }
    L_0x0185:
        r0 = LOG;	 Catch:{ all -> 0x012f }
        r5 = "Retry authentication";	 Catch:{ all -> 0x012f }
        r0.debug(r5);	 Catch:{ all -> 0x012f }
        r0 = r1;	 Catch:{ all -> 0x012f }
    L_0x018d:
        if (r0 == 0) goto L_0x00d1;	 Catch:{ all -> 0x012f }
    L_0x018f:
        r0 = r9.getResponseBodyAsStream();	 Catch:{ all -> 0x012f }
        if (r0 == 0) goto L_0x019c;	 Catch:{ all -> 0x012f }
    L_0x0195:
        r0 = r9.getResponseBodyAsStream();	 Catch:{ all -> 0x012f }
        r0.close();	 Catch:{ all -> 0x012f }
    L_0x019c:
        r0 = r3;
        goto L_0x0049;
    L_0x019f:
        r3 = r0;
        r0 = r2;
        goto L_0x0179;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.HttpMethodDirector.executeMethod(org.apache.commons.httpclient.HttpMethod):void");
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpMethodDirector == null) {
            class$ = class$("org.apache.commons.httpclient.HttpMethodDirector");
            class$org$apache$commons$httpclient$HttpMethodDirector = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpMethodDirector;
        }
        LOG = LogFactory.getLog(class$);
    }

    public HttpMethodDirector(HttpConnectionManager httpConnectionManager, HostConfiguration hostConfiguration, HttpClientParams httpClientParams, HttpState httpState) {
        this.connectionManager = httpConnectionManager;
        this.hostConfiguration = hostConfiguration;
        this.params = httpClientParams;
        this.state = httpState;
        this.authProcessor = new AuthChallengeProcessor(this.params);
    }

    private void authenticate(HttpMethod httpMethod) {
        try {
            if (this.conn.isProxied() && !this.conn.isSecure()) {
                authenticateProxy(httpMethod);
            }
            authenticateHost(httpMethod);
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private boolean cleanAuthHeaders(HttpMethod httpMethod, String str) {
        Header[] requestHeaders = httpMethod.getRequestHeaders(str);
        boolean z = true;
        for (Header header : requestHeaders) {
            if (header.isAutogenerated()) {
                httpMethod.removeRequestHeader(header);
            } else {
                z = false;
            }
        }
        return z;
    }

    private void authenticateHost(HttpMethod httpMethod) throws AuthenticationException {
        if (cleanAuthHeaders(httpMethod, "Authorization")) {
            AuthState hostAuthState = httpMethod.getHostAuthState();
            AuthScheme authScheme = hostAuthState.getAuthScheme();
            if (authScheme == null) {
                return;
            }
            if (hostAuthState.isAuthRequested() || !authScheme.isConnectionBased()) {
                String virtualHost = httpMethod.getParams().getVirtualHost();
                if (virtualHost == null) {
                    virtualHost = this.conn.getHost();
                }
                AuthScope authScope = new AuthScope(virtualHost, this.conn.getPort(), authScheme.getRealm(), authScheme.getSchemeName());
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Authenticating with ").append(authScope).toString());
                }
                Credentials credentials = this.state.getCredentials(authScope);
                if (credentials != null) {
                    virtualHost = authScheme.authenticate(credentials, httpMethod);
                    if (virtualHost != null) {
                        httpMethod.addRequestHeader(new Header("Authorization", virtualHost, true));
                    }
                } else if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append("Required credentials not available for ").append(authScope).toString());
                    if (httpMethod.getHostAuthState().isPreemptive()) {
                        LOG.warn("Preemptive authentication requested but no default credentials available");
                    }
                }
            }
        }
    }

    private void authenticateProxy(HttpMethod httpMethod) throws AuthenticationException {
        if (cleanAuthHeaders(httpMethod, "Proxy-Authorization")) {
            AuthState proxyAuthState = httpMethod.getProxyAuthState();
            AuthScheme authScheme = proxyAuthState.getAuthScheme();
            if (authScheme == null) {
                return;
            }
            if (proxyAuthState.isAuthRequested() || !authScheme.isConnectionBased()) {
                AuthScope authScope = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), authScheme.getRealm(), authScheme.getSchemeName());
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Authenticating with ").append(authScope).toString());
                }
                Credentials proxyCredentials = this.state.getProxyCredentials(authScope);
                if (proxyCredentials != null) {
                    String authenticate = authScheme.authenticate(proxyCredentials, httpMethod);
                    if (authenticate != null) {
                        httpMethod.addRequestHeader(new Header("Proxy-Authorization", authenticate, true));
                    }
                } else if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append("Required proxy credentials not available for ").append(authScope).toString());
                    if (httpMethod.getProxyAuthState().isPreemptive()) {
                        LOG.warn("Preemptive authentication requested but no default proxy credentials available");
                    }
                }
            }
        }
    }

    private void applyConnectionParams(HttpMethod httpMethod) throws IOException {
        int intValue;
        Object parameter = httpMethod.getParams().getParameter("http.socket.timeout");
        if (parameter == null) {
            parameter = this.conn.getParams().getParameter("http.socket.timeout");
        }
        if (parameter != null) {
            intValue = ((Integer) parameter).intValue();
        } else {
            intValue = 0;
        }
        this.conn.setSocketTimeout(intValue);
    }

    private void executeWithRetry(HttpMethod httpMethod) throws IOException, HttpException {
        Throwable th;
        HttpMethodRetryHandler httpMethodRetryHandler;
        int i = 0;
        while (true) {
            i++;
            try {
                break;
            } catch (HttpException e) {
                throw e;
            } catch (Throwable e2) {
                th = e2;
                LOG.debug("Closing the connection.");
                this.conn.close();
                if (httpMethod instanceof HttpMethodBase) {
                    MethodRetryHandler methodRetryHandler = ((HttpMethodBase) httpMethod).getMethodRetryHandler();
                    if (methodRetryHandler != null) {
                        if (!methodRetryHandler.retryMethod(httpMethod, this.conn, new HttpRecoverableException(th.getMessage()), i, httpMethod.isRequestSent())) {
                            LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
                            throw th;
                        }
                    }
                }
                httpMethodRetryHandler = (HttpMethodRetryHandler) httpMethod.getParams().getParameter(HttpMethodParams.RETRY_HANDLER);
                if (httpMethodRetryHandler == null) {
                    httpMethodRetryHandler = new DefaultHttpMethodRetryHandler();
                }
                if (httpMethodRetryHandler.retryMethod(httpMethod, th, i)) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info(new StringBuffer().append("I/O exception (").append(th.getClass().getName()).append(") caught when processing request: ").append(th.getMessage()).toString());
                    }
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(th.getMessage(), th);
                    }
                    LOG.info("Retrying request");
                } else {
                    LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
                    throw th;
                }
            } catch (RuntimeException e3) {
                if (this.conn.isOpen) {
                    LOG.debug("Closing the connection.");
                    this.conn.close();
                }
                this.releaseConnection = true;
                throw e3;
            } catch (IOException e4) {
                if (this.conn.isOpen()) {
                    LOG.debug("Closing the connection.");
                    this.conn.close();
                }
                this.releaseConnection = true;
                throw e4;
            }
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace(new StringBuffer().append("Attempt number ").append(i).append(" to process request").toString());
        }
        if (this.conn.getParams().isStaleCheckingEnabled()) {
            this.conn.closeIfStale();
        }
        if (!this.conn.isOpen()) {
            this.conn.open();
            if (this.conn.isProxied() && this.conn.isSecure() && !(httpMethod instanceof ConnectMethod) && !executeConnect()) {
                return;
            }
        }
        applyConnectionParams(httpMethod);
        httpMethod.execute(this.state, this.conn);
    }

    private boolean executeConnect() throws IOException, HttpException {
        this.connectMethod = new ConnectMethod();
        this.connectMethod.getParams().setDefaults(this.hostConfiguration.getParams());
        while (true) {
            boolean z;
            if (!this.conn.isOpen()) {
                this.conn.open();
            }
            if (this.params.isAuthenticationPreemptive() || this.state.isAuthenticationPreemptive()) {
                LOG.debug("Preemptively sending default basic credentials");
                this.connectMethod.getProxyAuthState().setPreemptive();
                this.connectMethod.getProxyAuthState().setAuthAttempted(true);
            }
            try {
                authenticateProxy(this.connectMethod);
            } catch (Throwable e) {
                LOG.error(e.getMessage(), e);
            }
            applyConnectionParams(this.connectMethod);
            this.connectMethod.execute(this.state, this.conn);
            int statusCode = this.connectMethod.getStatusCode();
            AuthState proxyAuthState = this.connectMethod.getProxyAuthState();
            if (statusCode == 407) {
                z = true;
            } else {
                z = false;
            }
            proxyAuthState.setAuthRequested(z);
            if (proxyAuthState.isAuthRequested() && processAuthenticationResponse(this.connectMethod)) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                break;
            } else if (this.connectMethod.getResponseBodyAsStream() != null) {
                this.connectMethod.getResponseBodyAsStream().close();
            }
        }
        if (statusCode < 200 || statusCode >= 300) {
            return false;
        }
        this.conn.tunnelCreated();
        this.connectMethod = null;
        return true;
    }

    private void fakeResponse(HttpMethod httpMethod) throws IOException, HttpException {
        LOG.debug("CONNECT failed, fake the response for the original method");
        if (httpMethod instanceof HttpMethodBase) {
            ((HttpMethodBase) httpMethod).fakeResponse(this.connectMethod.getStatusLine(), this.connectMethod.getResponseHeaderGroup(), this.connectMethod.getResponseBodyAsStream());
            httpMethod.getProxyAuthState().setAuthScheme(this.connectMethod.getProxyAuthState().getAuthScheme());
            this.connectMethod = null;
            return;
        }
        this.releaseConnection = true;
        LOG.warn("Unable to fake response on method as it is not derived from HttpMethodBase.");
    }

    private boolean processRedirectResponse(HttpMethod httpMethod) throws RedirectException {
        NameValuePair responseHeader = httpMethod.getResponseHeader(CommonStrs.TYPE_LOCATION);
        if (responseHeader == null) {
            LOG.error(new StringBuffer().append("Received redirect response ").append(httpMethod.getStatusCode()).append(" but no location header").toString());
            return false;
        }
        String value = responseHeader.getValue();
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Redirect requested to location '").append(value).append("'").toString());
        }
        try {
            URI uri;
            URI uri2 = new URI(this.conn.getProtocol().getScheme(), null, this.conn.getHost(), this.conn.getPort(), httpMethod.getPath());
            URI uri3 = new URI(value, true);
            if (!uri3.isRelativeURI()) {
                httpMethod.getParams().setDefaults(this.params);
                uri = uri3;
            } else if (this.params.isParameterTrue(HttpClientParams.REJECT_RELATIVE_REDIRECT)) {
                LOG.warn(new StringBuffer().append("Relative redirect location '").append(value).append("' not allowed").toString());
                return false;
            } else {
                LOG.debug("Redirect URI is not absolute - parsing as relative");
                uri = new URI(uri2, uri3);
            }
            httpMethod.setURI(uri);
            this.hostConfiguration.setHost(uri);
            if (this.params.isParameterFalse(HttpClientParams.ALLOW_CIRCULAR_REDIRECTS)) {
                if (this.redirectLocations == null) {
                    this.redirectLocations = new HashSet();
                }
                this.redirectLocations.add(uri2);
                try {
                    if (uri.hasQuery()) {
                        uri.setQuery(null);
                    }
                    if (this.redirectLocations.contains(uri)) {
                        throw new CircularRedirectException(new StringBuffer().append("Circular redirect to '").append(uri).append("'").toString());
                    }
                } catch (URIException e) {
                    return false;
                }
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Redirecting from '").append(uri2.getEscapedURI()).append("' to '").append(uri.getEscapedURI()).toString());
            }
            httpMethod.getHostAuthState().invalidate();
            return true;
        } catch (URIException e2) {
            LOG.warn(new StringBuffer().append("Redirected location '").append(value).append("' is malformed").toString());
            return false;
        }
    }

    private boolean processAuthenticationResponse(HttpMethod httpMethod) {
        LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
        try {
            switch (httpMethod.getStatusCode()) {
                case 401:
                    return processWWWAuthChallenge(httpMethod);
                case 407:
                    return processProxyAuthChallenge(httpMethod);
                default:
                    return false;
            }
        } catch (Throwable e) {
            if (!LOG.isErrorEnabled()) {
                return false;
            }
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    private boolean processWWWAuthChallenge(HttpMethod httpMethod) throws MalformedChallengeException, AuthenticationException {
        AuthState hostAuthState = httpMethod.getHostAuthState();
        Map parseChallenges = AuthChallengeParser.parseChallenges(httpMethod.getResponseHeaders("WWW-Authenticate"));
        if (parseChallenges.isEmpty()) {
            LOG.debug("Authentication challenge(s) not found");
            return false;
        }
        AuthScheme authScheme = null;
        try {
            authScheme = this.authProcessor.processChallenge(hostAuthState, parseChallenges);
        } catch (Throwable e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(e.getMessage());
            }
        }
        if (authScheme == null) {
            return false;
        }
        String virtualHost = httpMethod.getParams().getVirtualHost();
        if (virtualHost == null) {
            virtualHost = this.conn.getHost();
        }
        AuthScope authScope = new AuthScope(virtualHost, this.conn.getPort(), authScheme.getRealm(), authScheme.getSchemeName());
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Authentication scope: ").append(authScope).toString());
        }
        if (!hostAuthState.isAuthAttempted() || !authScheme.isComplete()) {
            hostAuthState.setAuthAttempted(true);
            Credentials credentials = this.state.getCredentials(authScope);
            if (credentials == null) {
                credentials = promptForCredentials(authScheme, httpMethod.getParams(), authScope);
            }
            if (credentials != null) {
                return true;
            }
            if (LOG.isInfoEnabled()) {
                LOG.info(new StringBuffer().append("No credentials available for ").append(authScope).toString());
            }
            return false;
        } else if (promptForCredentials(authScheme, httpMethod.getParams(), authScope) != null) {
            return true;
        } else {
            if (LOG.isInfoEnabled()) {
                LOG.info(new StringBuffer().append("Failure authenticating with ").append(authScope).toString());
            }
            return false;
        }
    }

    private boolean processProxyAuthChallenge(HttpMethod httpMethod) throws MalformedChallengeException, AuthenticationException {
        AuthState proxyAuthState = httpMethod.getProxyAuthState();
        Map parseChallenges = AuthChallengeParser.parseChallenges(httpMethod.getResponseHeaders("Proxy-Authenticate"));
        if (parseChallenges.isEmpty()) {
            LOG.debug("Proxy authentication challenge(s) not found");
            return false;
        }
        AuthScheme authScheme = null;
        try {
            authScheme = this.authProcessor.processChallenge(proxyAuthState, parseChallenges);
        } catch (Throwable e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(e.getMessage());
            }
        }
        if (authScheme == null) {
            return false;
        }
        AuthScope authScope = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), authScheme.getRealm(), authScheme.getSchemeName());
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Proxy authentication scope: ").append(authScope).toString());
        }
        if (!proxyAuthState.isAuthAttempted() || !authScheme.isComplete()) {
            proxyAuthState.setAuthAttempted(true);
            Credentials proxyCredentials = this.state.getProxyCredentials(authScope);
            if (proxyCredentials == null) {
                proxyCredentials = promptForProxyCredentials(authScheme, httpMethod.getParams(), authScope);
            }
            if (proxyCredentials != null) {
                return true;
            }
            if (!LOG.isInfoEnabled()) {
                return false;
            }
            LOG.info(new StringBuffer().append("No credentials available for ").append(authScope).toString());
            return false;
        } else if (promptForProxyCredentials(authScheme, httpMethod.getParams(), authScope) != null) {
            return true;
        } else {
            if (!LOG.isInfoEnabled()) {
                return false;
            }
            LOG.info(new StringBuffer().append("Failure authenticating with ").append(authScope).toString());
            return false;
        }
    }

    private boolean isRedirectNeeded(HttpMethod httpMethod) {
        switch (httpMethod.getStatusCode()) {
            case 301:
            case 302:
            case 303:
            case 307:
                LOG.debug("Redirect required");
                if (httpMethod.getFollowRedirects()) {
                    return true;
                }
                LOG.info("Redirect requested but followRedirects is disabled");
                return false;
            default:
                return false;
        }
    }

    private boolean isAuthenticationNeeded(HttpMethod httpMethod) {
        boolean z;
        httpMethod.getHostAuthState().setAuthRequested(httpMethod.getStatusCode() == 401);
        AuthState proxyAuthState = httpMethod.getProxyAuthState();
        if (httpMethod.getStatusCode() == 407) {
            z = true;
        } else {
            z = false;
        }
        proxyAuthState.setAuthRequested(z);
        if (!httpMethod.getHostAuthState().isAuthRequested() && !httpMethod.getProxyAuthState().isAuthRequested()) {
            return false;
        }
        LOG.debug("Authorization required");
        if (httpMethod.getDoAuthentication()) {
            return true;
        }
        LOG.info("Authentication requested but doAuthentication is disabled");
        return false;
    }

    private Credentials promptForCredentials(AuthScheme authScheme, HttpParams httpParams, AuthScope authScope) {
        LOG.debug("Credentials required");
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpParams.getParameter(CredentialsProvider.PROVIDER);
        if (credentialsProvider != null) {
            Credentials credentials;
            try {
                credentials = credentialsProvider.getCredentials(authScheme, authScope.getHost(), authScope.getPort(), false);
            } catch (Throwable e) {
                LOG.warn(e.getMessage());
                credentials = null;
            }
            if (credentials == null) {
                return credentials;
            }
            this.state.setCredentials(authScope, credentials);
            if (!LOG.isDebugEnabled()) {
                return credentials;
            }
            LOG.debug(new StringBuffer().append(authScope).append(" new credentials given").toString());
            return credentials;
        }
        LOG.debug("Credentials provider not available");
        return null;
    }

    private Credentials promptForProxyCredentials(AuthScheme authScheme, HttpParams httpParams, AuthScope authScope) {
        LOG.debug("Proxy credentials required");
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpParams.getParameter(CredentialsProvider.PROVIDER);
        if (credentialsProvider != null) {
            Credentials credentials;
            try {
                credentials = credentialsProvider.getCredentials(authScheme, authScope.getHost(), authScope.getPort(), true);
            } catch (Throwable e) {
                LOG.warn(e.getMessage());
                credentials = null;
            }
            if (credentials == null) {
                return credentials;
            }
            this.state.setProxyCredentials(authScope, credentials);
            if (!LOG.isDebugEnabled()) {
                return credentials;
            }
            LOG.debug(new StringBuffer().append(authScope).append(" new credentials given").toString());
            return credentials;
        }
        LOG.debug("Proxy credentials provider not available");
        return null;
    }

    public HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }

    public HttpState getState() {
        return this.state;
    }

    public HttpConnectionManager getConnectionManager() {
        return this.connectionManager;
    }

    public HttpParams getParams() {
        return this.params;
    }
}
