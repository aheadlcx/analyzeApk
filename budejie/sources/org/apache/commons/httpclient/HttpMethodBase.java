package org.apache.commons.httpclient;

import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.socket.common.SocketUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collection;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.mime.MIME;

public abstract class HttpMethodBase implements HttpMethod {
    private static final int DEFAULT_INITIAL_BUFFER_SIZE = 4096;
    private static final Log LOG;
    private static final int RESPONSE_WAIT_TIME_MS = 3000;
    static Class class$org$apache$commons$httpclient$HttpMethodBase;
    private transient boolean aborted = false;
    private boolean connectionCloseForced = false;
    private CookieSpec cookiespec = null;
    private boolean doAuthentication = true;
    private HttpVersion effectiveVersion = null;
    private boolean followRedirects = false;
    private AuthState hostAuthState = new AuthState();
    private HttpHost httphost = null;
    private MethodRetryHandler methodRetryHandler;
    private HttpMethodParams params = new HttpMethodParams();
    private String path = null;
    private AuthState proxyAuthState = new AuthState();
    private String queryString = null;
    private int recoverableExceptionCount = 0;
    private HeaderGroup requestHeaders = new HeaderGroup();
    private boolean requestSent = false;
    private byte[] responseBody = null;
    private HttpConnection responseConnection = null;
    private HeaderGroup responseHeaders = new HeaderGroup();
    private InputStream responseStream = null;
    private HeaderGroup responseTrailerHeaders = new HeaderGroup();
    private StatusLine statusLine = null;
    private boolean used = false;

    public abstract String getName();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpMethodBase == null) {
            class$ = class$("org.apache.commons.httpclient.HttpMethodBase");
            class$org$apache$commons$httpclient$HttpMethodBase = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpMethodBase;
        }
        LOG = LogFactory.getLog(class$);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HttpMethodBase(java.lang.String r5) throws java.lang.IllegalArgumentException, java.lang.IllegalStateException {
        /*
        r4 = this;
        r3 = 1;
        r2 = 0;
        r1 = 0;
        r4.<init>();
        r0 = new org.apache.commons.httpclient.HeaderGroup;
        r0.<init>();
        r4.requestHeaders = r0;
        r4.statusLine = r1;
        r0 = new org.apache.commons.httpclient.HeaderGroup;
        r0.<init>();
        r4.responseHeaders = r0;
        r0 = new org.apache.commons.httpclient.HeaderGroup;
        r0.<init>();
        r4.responseTrailerHeaders = r0;
        r4.path = r1;
        r4.queryString = r1;
        r4.responseStream = r1;
        r4.responseConnection = r1;
        r4.responseBody = r1;
        r4.followRedirects = r2;
        r4.doAuthentication = r3;
        r0 = new org.apache.commons.httpclient.params.HttpMethodParams;
        r0.<init>();
        r4.params = r0;
        r0 = new org.apache.commons.httpclient.auth.AuthState;
        r0.<init>();
        r4.hostAuthState = r0;
        r0 = new org.apache.commons.httpclient.auth.AuthState;
        r0.<init>();
        r4.proxyAuthState = r0;
        r4.used = r2;
        r4.recoverableExceptionCount = r2;
        r4.httphost = r1;
        r4.connectionCloseForced = r2;
        r4.effectiveVersion = r1;
        r4.aborted = r2;
        r4.requestSent = r2;
        r4.cookiespec = r1;
        if (r5 == 0) goto L_0x005a;
    L_0x0052:
        r0 = "";
        r0 = r5.equals(r0);	 Catch:{ URIException -> 0x0066 }
        if (r0 == 0) goto L_0x005c;
    L_0x005a:
        r5 = "/";
    L_0x005c:
        r0 = new org.apache.commons.httpclient.URI;	 Catch:{ URIException -> 0x0066 }
        r1 = 1;
        r0.<init>(r5, r1);	 Catch:{ URIException -> 0x0066 }
        r4.setURI(r0);	 Catch:{ URIException -> 0x0066 }
        return;
    L_0x0066:
        r0 = move-exception;
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuffer;
        r2.<init>();
        r3 = "Invalid uri '";
        r2 = r2.append(r3);
        r2 = r2.append(r5);
        r3 = "': ";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.HttpMethodBase.<init>(java.lang.String):void");
    }

    public URI getURI() throws URIException {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.httphost != null) {
            stringBuffer.append(this.httphost.getProtocol().getScheme());
            stringBuffer.append("://");
            stringBuffer.append(this.httphost.getHostName());
            int port = this.httphost.getPort();
            if (!(port == -1 || port == this.httphost.getProtocol().getDefaultPort())) {
                stringBuffer.append(":");
                stringBuffer.append(port);
            }
        }
        stringBuffer.append(this.path);
        if (this.queryString != null) {
            stringBuffer.append('?');
            stringBuffer.append(this.queryString);
        }
        return new URI(stringBuffer.toString(), true);
    }

    public void setURI(URI uri) throws URIException {
        if (uri.isAbsoluteURI()) {
            this.httphost = new HttpHost(uri);
        }
        setPath(uri.getPath() == null ? "/" : uri.getEscapedPath());
        setQueryString(uri.getEscapedQuery());
    }

    public void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public void setHttp11(boolean z) {
        if (z) {
            this.params.setVersion(HttpVersion.HTTP_1_1);
        } else {
            this.params.setVersion(HttpVersion.HTTP_1_0);
        }
    }

    public boolean getDoAuthentication() {
        return this.doAuthentication;
    }

    public void setDoAuthentication(boolean z) {
        this.doAuthentication = z;
    }

    public boolean isHttp11() {
        return this.params.getVersion().equals(HttpVersion.HTTP_1_1);
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void addRequestHeader(Header header) {
        LOG.trace("HttpMethodBase.addRequestHeader(Header)");
        if (header == null) {
            LOG.debug("null header value ignored");
        } else {
            getRequestHeaderGroup().addHeader(header);
        }
    }

    public void addResponseFooter(Header header) {
        getResponseTrailerHeaderGroup().addHeader(header);
    }

    public String getPath() {
        return (this.path == null || this.path.equals("")) ? "/" : this.path;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public void setQueryString(NameValuePair[] nameValuePairArr) {
        LOG.trace("enter HttpMethodBase.setQueryString(NameValuePair[])");
        this.queryString = EncodingUtil.formUrlEncode(nameValuePairArr, "UTF-8");
    }

    public String getQueryString() {
        return this.queryString;
    }

    public void setRequestHeader(String str, String str2) {
        setRequestHeader(new Header(str, str2));
    }

    public void setRequestHeader(Header header) {
        Header[] headers = getRequestHeaderGroup().getHeaders(header.getName());
        for (Header removeHeader : headers) {
            getRequestHeaderGroup().removeHeader(removeHeader);
        }
        getRequestHeaderGroup().addHeader(header);
    }

    public Header getRequestHeader(String str) {
        if (str == null) {
            return null;
        }
        return getRequestHeaderGroup().getCondensedHeader(str);
    }

    public Header[] getRequestHeaders() {
        return getRequestHeaderGroup().getAllHeaders();
    }

    public Header[] getRequestHeaders(String str) {
        return getRequestHeaderGroup().getHeaders(str);
    }

    protected HeaderGroup getRequestHeaderGroup() {
        return this.requestHeaders;
    }

    protected HeaderGroup getResponseTrailerHeaderGroup() {
        return this.responseTrailerHeaders;
    }

    protected HeaderGroup getResponseHeaderGroup() {
        return this.responseHeaders;
    }

    public Header[] getResponseHeaders(String str) {
        return getResponseHeaderGroup().getHeaders(str);
    }

    public int getStatusCode() {
        return this.statusLine.getStatusCode();
    }

    public StatusLine getStatusLine() {
        return this.statusLine;
    }

    private boolean responseAvailable() {
        return (this.responseBody == null && this.responseStream == null) ? false : true;
    }

    public Header[] getResponseHeaders() {
        return getResponseHeaderGroup().getAllHeaders();
    }

    public Header getResponseHeader(String str) {
        if (str == null) {
            return null;
        }
        return getResponseHeaderGroup().getCondensedHeader(str);
    }

    public long getResponseContentLength() {
        long j = -1;
        Header[] headers = getResponseHeaderGroup().getHeaders("Content-Length");
        if (headers.length != 0) {
            if (headers.length > 1) {
                LOG.warn("Multiple content-length headers detected");
            }
            int length = headers.length - 1;
            while (length >= 0) {
                try {
                    j = Long.parseLong(headers[length].getValue());
                    break;
                } catch (Throwable e) {
                    if (LOG.isWarnEnabled()) {
                        LOG.warn(new StringBuffer().append("Invalid content-length value: ").append(e.getMessage()).toString());
                    }
                    length--;
                }
            }
        }
        return j;
    }

    public byte[] getResponseBody() throws IOException {
        if (this.responseBody == null) {
            InputStream responseBodyAsStream = getResponseBodyAsStream();
            if (responseBodyAsStream != null) {
                long responseContentLength = getResponseContentLength();
                if (responseContentLength > 2147483647L) {
                    throw new IOException(new StringBuffer().append("Content too large to be buffered: ").append(responseContentLength).append(" bytes").toString());
                }
                int intParameter = getParams().getIntParameter(HttpMethodParams.BUFFER_WARN_TRIGGER_LIMIT, 1048576);
                if (responseContentLength == -1 || responseContentLength > ((long) intParameter)) {
                    LOG.warn("Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.");
                }
                LOG.debug("Buffering response body");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(responseContentLength > 0 ? (int) responseContentLength : 4096);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = responseBodyAsStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.close();
                setResponseStream(null);
                this.responseBody = byteArrayOutputStream.toByteArray();
            }
        }
        return this.responseBody;
    }

    public InputStream getResponseBodyAsStream() throws IOException {
        if (this.responseStream != null) {
            return this.responseStream;
        }
        if (this.responseBody == null) {
            return null;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(this.responseBody);
        LOG.debug("re-creating response stream from byte array");
        return byteArrayInputStream;
    }

    public String getResponseBodyAsString() throws IOException {
        byte[] responseBody;
        if (responseAvailable()) {
            responseBody = getResponseBody();
        } else {
            responseBody = null;
        }
        if (responseBody != null) {
            return EncodingUtil.getString(responseBody, getResponseCharSet());
        }
        return null;
    }

    public Header[] getResponseFooters() {
        return getResponseTrailerHeaderGroup().getAllHeaders();
    }

    public Header getResponseFooter(String str) {
        if (str == null) {
            return null;
        }
        return getResponseTrailerHeaderGroup().getCondensedHeader(str);
    }

    protected void setResponseStream(InputStream inputStream) {
        this.responseStream = inputStream;
    }

    protected InputStream getResponseStream() {
        return this.responseStream;
    }

    public String getStatusText() {
        return this.statusLine.getReasonPhrase();
    }

    public void setStrictMode(boolean z) {
        if (z) {
            this.params.makeStrict();
        } else {
            this.params.makeLenient();
        }
    }

    public boolean isStrictMode() {
        return false;
    }

    public void addRequestHeader(String str, String str2) {
        addRequestHeader(new Header(str, str2));
    }

    protected boolean isConnectionCloseForced() {
        return this.connectionCloseForced;
    }

    protected void setConnectionCloseForced(boolean z) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Force-close connection: ").append(z).toString());
        }
        this.connectionCloseForced = z;
    }

    protected boolean shouldCloseConnection(HttpConnection httpConnection) {
        if (isConnectionCloseForced()) {
            LOG.debug("Should force-close connection.");
            return true;
        }
        NameValuePair nameValuePair = null;
        if (!httpConnection.isTransparent()) {
            nameValuePair = this.responseHeaders.getFirstHeader("proxy-connection");
        }
        if (nameValuePair == null) {
            nameValuePair = this.responseHeaders.getFirstHeader("connection");
        }
        if (nameValuePair == null) {
            nameValuePair = this.requestHeaders.getFirstHeader("connection");
        }
        if (nameValuePair != null) {
            if (nameValuePair.getValue().equalsIgnoreCase(BoxingVoteBean.BOXING_VOTE_STATE_CLOSE)) {
                if (!LOG.isDebugEnabled()) {
                    return true;
                }
                LOG.debug(new StringBuffer().append("Should close connection in response to directive: ").append(nameValuePair.getValue()).toString());
                return true;
            } else if (nameValuePair.getValue().equalsIgnoreCase("keep-alive")) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Should NOT close connection in response to directive: ").append(nameValuePair.getValue()).toString());
                }
                return false;
            } else if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Unknown directive: ").append(nameValuePair.toExternalForm()).toString());
            }
        }
        LOG.debug("Resorting to protocol version default close connection policy");
        if (this.effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Should NOT close connection, using ").append(this.effectiveVersion.toString()).toString());
            }
        } else if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Should close connection, using ").append(this.effectiveVersion.toString()).toString());
        }
        return this.effectiveVersion.lessEquals(HttpVersion.HTTP_1_0);
    }

    private void checkExecuteConditions(HttpState httpState, HttpConnection httpConnection) throws HttpException {
        if (httpState == null) {
            throw new IllegalArgumentException("HttpState parameter may not be null");
        } else if (httpConnection == null) {
            throw new IllegalArgumentException("HttpConnection parameter may not be null");
        } else if (this.aborted) {
            throw new IllegalStateException("Method has been aborted");
        } else if (!validate()) {
            throw new ProtocolException("HttpMethodBase object not valid");
        }
    }

    public int execute(HttpState httpState, HttpConnection httpConnection) throws HttpException, IOException {
        LOG.trace("enter HttpMethodBase.execute(HttpState, HttpConnection)");
        this.responseConnection = httpConnection;
        checkExecuteConditions(httpState, httpConnection);
        this.statusLine = null;
        this.connectionCloseForced = false;
        httpConnection.setLastResponseInputStream(null);
        if (this.effectiveVersion == null) {
            this.effectiveVersion = this.params.getVersion();
        }
        writeRequest(httpState, httpConnection);
        this.requestSent = true;
        readResponse(httpState, httpConnection);
        this.used = true;
        return this.statusLine.getStatusCode();
    }

    public void abort() {
        if (!this.aborted) {
            this.aborted = true;
            HttpConnection httpConnection = this.responseConnection;
            if (httpConnection != null) {
                httpConnection.close();
            }
        }
    }

    public boolean hasBeenUsed() {
        return this.used;
    }

    public void recycle() {
        LOG.trace("enter HttpMethodBase.recycle()");
        releaseConnection();
        this.path = null;
        this.followRedirects = false;
        this.doAuthentication = true;
        this.queryString = null;
        getRequestHeaderGroup().clear();
        getResponseHeaderGroup().clear();
        getResponseTrailerHeaderGroup().clear();
        this.statusLine = null;
        this.effectiveVersion = null;
        this.aborted = false;
        this.used = false;
        this.params = new HttpMethodParams();
        this.responseBody = null;
        this.recoverableExceptionCount = 0;
        this.connectionCloseForced = false;
        this.hostAuthState.invalidate();
        this.proxyAuthState.invalidate();
        this.cookiespec = null;
        this.requestSent = false;
    }

    public void releaseConnection() {
        try {
            if (this.responseStream != null) {
                try {
                    this.responseStream.close();
                } catch (IOException e) {
                }
            }
            ensureConnectionRelease();
        } catch (Throwable th) {
            ensureConnectionRelease();
        }
    }

    public void removeRequestHeader(String str) {
        Header[] headers = getRequestHeaderGroup().getHeaders(str);
        for (Header removeHeader : headers) {
            getRequestHeaderGroup().removeHeader(removeHeader);
        }
    }

    public void removeRequestHeader(Header header) {
        if (header != null) {
            getRequestHeaderGroup().removeHeader(header);
        }
    }

    public boolean validate() {
        return true;
    }

    private CookieSpec getCookieSpec(HttpState httpState) {
        if (this.cookiespec == null) {
            int cookiePolicy = httpState.getCookiePolicy();
            if (cookiePolicy == -1) {
                this.cookiespec = CookiePolicy.getCookieSpec(this.params.getCookiePolicy());
            } else {
                this.cookiespec = CookiePolicy.getSpecByPolicy(cookiePolicy);
            }
            this.cookiespec.setValidDateFormats((Collection) this.params.getParameter(HttpMethodParams.DATE_PATTERNS));
        }
        return this.cookiespec;
    }

    protected void addCookieRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        int i = 0;
        LOG.trace("enter HttpMethodBase.addCookieRequestHeader(HttpState, HttpConnection)");
        Header[] headers = getRequestHeaderGroup().getHeaders("Cookie");
        for (Header header : headers) {
            if (header.isAutogenerated()) {
                getRequestHeaderGroup().removeHeader(header);
            }
        }
        CookieSpec cookieSpec = getCookieSpec(httpState);
        String virtualHost = this.params.getVirtualHost();
        if (virtualHost == null) {
            virtualHost = httpConnection.getHost();
        }
        Cookie[] match = cookieSpec.match(virtualHost, httpConnection.getPort(), getPath(), httpConnection.isSecure(), httpState.getCookies());
        if (match != null && match.length > 0) {
            if (getParams().isParameterTrue(HttpMethodParams.SINGLE_COOKIE_HEADER)) {
                getRequestHeaderGroup().addHeader(new Header("Cookie", cookieSpec.formatCookies(match), true));
                return;
            }
            while (i < match.length) {
                getRequestHeaderGroup().addHeader(new Header("Cookie", cookieSpec.formatCookie(match[i]), true));
                i++;
            }
        }
    }

    protected void addHostRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.addHostRequestHeader(HttpState, HttpConnection)");
        String virtualHost = this.params.getVirtualHost();
        if (virtualHost != null) {
            LOG.debug(new StringBuffer().append("Using virtual host name: ").append(virtualHost).toString());
        } else {
            virtualHost = httpConnection.getHost();
        }
        int port = httpConnection.getPort();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Adding Host request header");
        }
        if (httpConnection.getProtocol().getDefaultPort() != port) {
            virtualHost = new StringBuffer().append(virtualHost).append(":").append(port).toString();
        }
        setRequestHeader("Host", virtualHost);
    }

    protected void addProxyConnectionHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.addProxyConnectionHeader(HttpState, HttpConnection)");
        if (!httpConnection.isTransparent() && getRequestHeader("Proxy-Connection") == null) {
            addRequestHeader("Proxy-Connection", "Keep-Alive");
        }
    }

    protected void addRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.addRequestHeaders(HttpState, HttpConnection)");
        addUserAgentRequestHeader(httpState, httpConnection);
        addHostRequestHeader(httpState, httpConnection);
        addCookieRequestHeader(httpState, httpConnection);
        addProxyConnectionHeader(httpState, httpConnection);
    }

    protected void addUserAgentRequestHeader(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.addUserAgentRequestHeaders(HttpState, HttpConnection)");
        if (getRequestHeader("User-Agent") == null) {
            String str = (String) getParams().getParameter(HttpMethodParams.USER_AGENT);
            if (str == null) {
                str = "Jakarta Commons-HttpClient";
            }
            setRequestHeader("User-Agent", str);
        }
    }

    protected void checkNotUsed() throws IllegalStateException {
        if (this.used) {
            throw new IllegalStateException("Already used.");
        }
    }

    protected void checkUsed() throws IllegalStateException {
        if (!this.used) {
            throw new IllegalStateException("Not Used.");
        }
    }

    protected static String generateRequestLine(HttpConnection httpConnection, String str, String str2, String str3, String str4) {
        LOG.trace("enter HttpMethodBase.generateRequestLine(HttpConnection, String, String, String, String)");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" ");
        if (!httpConnection.isTransparent()) {
            Protocol protocol = httpConnection.getProtocol();
            stringBuffer.append(protocol.getScheme().toLowerCase());
            stringBuffer.append("://");
            stringBuffer.append(httpConnection.getHost());
            if (!(httpConnection.getPort() == -1 || httpConnection.getPort() == protocol.getDefaultPort())) {
                stringBuffer.append(":");
                stringBuffer.append(httpConnection.getPort());
            }
        }
        if (str2 == null) {
            stringBuffer.append("/");
        } else {
            if (!(httpConnection.isTransparent() || str2.startsWith("/"))) {
                stringBuffer.append("/");
            }
            stringBuffer.append(str2);
        }
        if (str3 != null) {
            if (str3.indexOf("?") != 0) {
                stringBuffer.append("?");
            }
            stringBuffer.append(str3);
        }
        stringBuffer.append(" ");
        stringBuffer.append(str4);
        stringBuffer.append(SocketUtil.CRLF);
        return stringBuffer.toString();
    }

    protected void processResponseBody(HttpState httpState, HttpConnection httpConnection) {
    }

    protected void processResponseHeaders(HttpState httpState, HttpConnection httpConnection) {
        Header[] headers;
        LOG.trace("enter HttpMethodBase.processResponseHeaders(HttpState, HttpConnection)");
        Header[] headers2 = getResponseHeaderGroup().getHeaders("set-cookie2");
        if (headers2.length == 0) {
            headers = getResponseHeaderGroup().getHeaders("set-cookie");
        } else {
            headers = headers2;
        }
        CookieSpec cookieSpec = getCookieSpec(httpState);
        String virtualHost = this.params.getVirtualHost();
        if (virtualHost == null) {
            virtualHost = httpConnection.getHost();
        }
        for (Header header : headers) {
            Cookie[] parse;
            try {
                parse = cookieSpec.parse(virtualHost, httpConnection.getPort(), getPath(), httpConnection.isSecure(), header);
            } catch (Throwable e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append("Invalid cookie header: \"").append(header.getValue()).append("\". ").append(e.getMessage()).toString());
                }
                parse = null;
            }
            if (parse != null) {
                for (Cookie cookie : parse) {
                    try {
                        cookieSpec.validate(virtualHost, httpConnection.getPort(), getPath(), httpConnection.isSecure(), cookie);
                        httpState.addCookie(cookie);
                        if (LOG.isDebugEnabled()) {
                            LOG.debug(new StringBuffer().append("Cookie accepted: \"").append(cookieSpec.formatCookie(cookie)).append("\"").toString());
                        }
                    } catch (Throwable e2) {
                        if (LOG.isWarnEnabled()) {
                            LOG.warn(new StringBuffer().append("Cookie rejected: \"").append(cookieSpec.formatCookie(cookie)).append("\". ").append(e2.getMessage()).toString());
                        }
                    }
                }
            }
        }
    }

    protected void processStatusLine(HttpState httpState, HttpConnection httpConnection) {
    }

    protected void readResponse(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.readResponse(HttpState, HttpConnection)");
        while (this.statusLine == null) {
            readStatusLine(httpState, httpConnection);
            processStatusLine(httpState, httpConnection);
            readResponseHeaders(httpState, httpConnection);
            processResponseHeaders(httpState, httpConnection);
            int statusCode = this.statusLine.getStatusCode();
            if (statusCode >= 100 && statusCode < 200) {
                if (LOG.isInfoEnabled()) {
                    LOG.info(new StringBuffer().append("Discarding unexpected response: ").append(this.statusLine.toString()).toString());
                }
                this.statusLine = null;
            }
        }
        readResponseBody(httpState, httpConnection);
        processResponseBody(httpState, httpConnection);
    }

    protected void readResponseBody(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.readResponseBody(HttpState, HttpConnection)");
        InputStream readResponseBody = readResponseBody(httpConnection);
        if (readResponseBody == null) {
            responseBodyConsumed();
            return;
        }
        httpConnection.setLastResponseInputStream(readResponseBody);
        setResponseStream(readResponseBody);
    }

    private InputStream readResponseBody(HttpConnection httpConnection) throws HttpException, IOException {
        InputStream wireLogInputStream;
        LOG.trace("enter HttpMethodBase.readResponseBody(HttpConnection)");
        this.responseBody = null;
        InputStream responseInputStream = httpConnection.getResponseInputStream();
        if (Wire.CONTENT_WIRE.enabled()) {
            wireLogInputStream = new WireLogInputStream(responseInputStream, Wire.CONTENT_WIRE);
        } else {
            wireLogInputStream = responseInputStream;
        }
        boolean canResponseHaveBody = canResponseHaveBody(this.statusLine.getStatusCode());
        NameValuePair firstHeader = this.responseHeaders.getFirstHeader("Transfer-Encoding");
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if (!("chunked".equalsIgnoreCase(value) || "identity".equalsIgnoreCase(value) || !LOG.isWarnEnabled())) {
                LOG.warn(new StringBuffer().append("Unsupported transfer encoding: ").append(value).toString());
            }
            HeaderElement[] elements = firstHeader.getElements();
            int length = elements.length;
            if (length <= 0 || !"chunked".equalsIgnoreCase(elements[length - 1].getName())) {
                LOG.info("Response content is not chunk-encoded");
                setConnectionCloseForced(true);
            } else if (httpConnection.isResponseAvailable(httpConnection.getParams().getSoTimeout())) {
                wireLogInputStream = new ChunkedInputStream(wireLogInputStream, this);
            } else if (getParams().isParameterTrue(HttpMethodParams.STRICT_TRANSFER_ENCODING)) {
                throw new ProtocolException("Chunk-encoded body declared but not sent");
            } else {
                LOG.warn("Chunk-encoded body missing");
                wireLogInputStream = null;
            }
            responseInputStream = wireLogInputStream;
        } else {
            long responseContentLength = getResponseContentLength();
            if (responseContentLength == -1) {
                if (canResponseHaveBody && this.effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1)) {
                    String value2;
                    firstHeader = this.responseHeaders.getFirstHeader("Connection");
                    if (firstHeader != null) {
                        value2 = firstHeader.getValue();
                    } else {
                        value2 = null;
                    }
                    if (!BoxingVoteBean.BOXING_VOTE_STATE_CLOSE.equalsIgnoreCase(value2)) {
                        LOG.info("Response content length is not known");
                        setConnectionCloseForced(true);
                    }
                }
                responseInputStream = wireLogInputStream;
            } else {
                responseInputStream = new ContentLengthInputStream(wireLogInputStream, responseContentLength);
            }
        }
        if (!canResponseHaveBody) {
            responseInputStream = null;
        }
        if (responseInputStream != null) {
            return new AutoCloseInputStream(responseInputStream, new ResponseConsumedWatcher(this) {
                private final HttpMethodBase this$0;

                {
                    this.this$0 = r1;
                }

                public void responseConsumed() {
                    this.this$0.responseBodyConsumed();
                }
            });
        }
        return responseInputStream;
    }

    protected void readResponseHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.readResponseHeaders(HttpState,HttpConnection)");
        getResponseHeaderGroup().clear();
        Header[] parseHeaders = HttpParser.parseHeaders(httpConnection.getResponseInputStream(), getParams().getHttpElementCharset());
        if (Wire.HEADER_WIRE.enabled()) {
            for (Header toExternalForm : parseHeaders) {
                Wire.HEADER_WIRE.input(toExternalForm.toExternalForm());
            }
        }
        getResponseHeaderGroup().setHeaders(parseHeaders);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void readStatusLine(org.apache.commons.httpclient.HttpState r7, org.apache.commons.httpclient.HttpConnection r8) throws java.io.IOException, org.apache.commons.httpclient.HttpException {
        /*
        r6 = this;
        r0 = LOG;
        r1 = "enter HttpMethodBase.readStatusLine(HttpState, HttpConnection)";
        r0.trace(r1);
        r0 = r6.getParams();
        r1 = "http.protocol.status-line-garbage-limit";
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r1 = r0.getIntParameter(r1, r2);
        r0 = 0;
    L_0x0015:
        r2 = r6.getParams();
        r2 = r2.getHttpElementCharset();
        r2 = r8.readLine(r2);
        if (r2 != 0) goto L_0x0048;
    L_0x0023:
        if (r0 != 0) goto L_0x0048;
    L_0x0025:
        r0 = new org.apache.commons.httpclient.NoHttpResponseException;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "The server ";
        r1 = r1.append(r2);
        r2 = r8.getHost();
        r1 = r1.append(r2);
        r2 = " failed to respond";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0048:
        r3 = org.apache.commons.httpclient.Wire.HEADER_WIRE;
        r3 = r3.enabled();
        if (r3 == 0) goto L_0x0068;
    L_0x0050:
        r3 = org.apache.commons.httpclient.Wire.HEADER_WIRE;
        r4 = new java.lang.StringBuffer;
        r4.<init>();
        r4 = r4.append(r2);
        r5 = "\r\n";
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.input(r4);
    L_0x0068:
        if (r2 == 0) goto L_0x00c1;
    L_0x006a:
        r3 = org.apache.commons.httpclient.StatusLine.startsWithHTTP(r2);
        if (r3 == 0) goto L_0x00c1;
    L_0x0070:
        r0 = new org.apache.commons.httpclient.StatusLine;
        r0.<init>(r2);
        r6.statusLine = r0;
        r0 = r6.statusLine;
        r0 = r0.getHttpVersion();
        r1 = r6.getParams();
        r2 = "http.protocol.unambiguous-statusline";
        r1 = r1.isParameterFalse(r2);
        if (r1 == 0) goto L_0x00ec;
    L_0x0089:
        r1 = "HTTP";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x00ec;
    L_0x0091:
        r0 = r6.getParams();
        r1 = org.apache.commons.httpclient.HttpVersion.HTTP_1_0;
        r0.setVersion(r1);
        r0 = LOG;
        r0 = r0.isWarnEnabled();
        if (r0 == 0) goto L_0x00c0;
    L_0x00a2:
        r0 = LOG;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "Ambiguous status line (HTTP protocol version missing):";
        r1 = r1.append(r2);
        r2 = r6.statusLine;
        r2 = r2.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.warn(r1);
    L_0x00c0:
        return;
    L_0x00c1:
        if (r2 == 0) goto L_0x00c5;
    L_0x00c3:
        if (r0 < r1) goto L_0x00e8;
    L_0x00c5:
        r0 = new org.apache.commons.httpclient.ProtocolException;
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r2 = "The server ";
        r1 = r1.append(r2);
        r2 = r8.getHost();
        r1 = r1.append(r2);
        r2 = " failed to respond with a valid HTTP response";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00e8:
        r0 = r0 + 1;
        goto L_0x0015;
    L_0x00ec:
        r0 = org.apache.commons.httpclient.HttpVersion.parse(r0);
        r6.effectiveVersion = r0;
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.HttpMethodBase.readStatusLine(org.apache.commons.httpclient.HttpState, org.apache.commons.httpclient.HttpConnection):void");
    }

    protected void writeRequest(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        String str = null;
        LOG.trace("enter HttpMethodBase.writeRequest(HttpState, HttpConnection)");
        writeRequestLine(httpState, httpConnection);
        writeRequestHeaders(httpState, httpConnection);
        httpConnection.writeLine();
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output(SocketUtil.CRLF);
        }
        HttpVersion version = getParams().getVersion();
        NameValuePair requestHeader = getRequestHeader("Expect");
        if (requestHeader != null) {
            str = requestHeader.getValue();
        }
        if (str != null && str.compareToIgnoreCase("100-continue") == 0) {
            if (version.greaterEquals(HttpVersion.HTTP_1_1)) {
                httpConnection.flushRequestOutputStream();
                int soTimeout = httpConnection.getParams().getSoTimeout();
                try {
                    httpConnection.setSocketTimeout(3000);
                    readStatusLine(httpState, httpConnection);
                    processStatusLine(httpState, httpConnection);
                    readResponseHeaders(httpState, httpConnection);
                    processResponseHeaders(httpState, httpConnection);
                    if (this.statusLine.getStatusCode() == 100) {
                        this.statusLine = null;
                        LOG.debug("OK to continue received");
                        httpConnection.setSocketTimeout(soTimeout);
                    } else {
                        httpConnection.setSocketTimeout(soTimeout);
                        return;
                    }
                } catch (InterruptedIOException e) {
                    if (ExceptionUtil.isSocketTimeoutException(e)) {
                        removeRequestHeader("Expect");
                        LOG.info("100 (continue) read timeout. Resume sending the request");
                    } else {
                        throw e;
                    }
                } catch (Throwable th) {
                    httpConnection.setSocketTimeout(soTimeout);
                }
            } else {
                removeRequestHeader("Expect");
                LOG.info("'Expect: 100-continue' handshake is only supported by HTTP/1.1 or higher");
            }
        }
        writeRequestBody(httpState, httpConnection);
        httpConnection.flushRequestOutputStream();
    }

    protected boolean writeRequestBody(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        return true;
    }

    protected void writeRequestHeaders(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.writeRequestHeaders(HttpState,HttpConnection)");
        addRequestHeaders(httpState, httpConnection);
        String httpElementCharset = getParams().getHttpElementCharset();
        Header[] requestHeaders = getRequestHeaders();
        for (Header toExternalForm : requestHeaders) {
            String toExternalForm2 = toExternalForm.toExternalForm();
            if (Wire.HEADER_WIRE.enabled()) {
                Wire.HEADER_WIRE.output(toExternalForm2);
            }
            httpConnection.print(toExternalForm2, httpElementCharset);
        }
    }

    protected void writeRequestLine(HttpState httpState, HttpConnection httpConnection) throws IOException, HttpException {
        LOG.trace("enter HttpMethodBase.writeRequestLine(HttpState, HttpConnection)");
        String requestLine = getRequestLine(httpConnection);
        if (Wire.HEADER_WIRE.enabled()) {
            Wire.HEADER_WIRE.output(requestLine);
        }
        httpConnection.print(requestLine, getParams().getHttpElementCharset());
    }

    private String getRequestLine(HttpConnection httpConnection) {
        return generateRequestLine(httpConnection, getName(), getPath(), getQueryString(), this.effectiveVersion.toString());
    }

    public HttpMethodParams getParams() {
        return this.params;
    }

    public void setParams(HttpMethodParams httpMethodParams) {
        if (httpMethodParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpMethodParams;
    }

    public HttpVersion getEffectiveVersion() {
        return this.effectiveVersion;
    }

    private static boolean canResponseHaveBody(int i) {
        LOG.trace("enter HttpMethodBase.canResponseHaveBody(int)");
        if ((i >= 100 && i <= 199) || i == 204 || i == 304) {
            return false;
        }
        return true;
    }

    public String getProxyAuthenticationRealm() {
        return this.proxyAuthState.getRealm();
    }

    public String getAuthenticationRealm() {
        return this.hostAuthState.getRealm();
    }

    protected String getContentCharSet(Header header) {
        LOG.trace("enter getContentCharSet( Header contentheader )");
        String str = null;
        if (header != null) {
            HeaderElement[] elements = header.getElements();
            if (elements.length == 1) {
                NameValuePair parameterByName = elements[0].getParameterByName("charset");
                if (parameterByName != null) {
                    str = parameterByName.getValue();
                }
            }
        }
        if (str == null) {
            str = getParams().getContentCharset();
            if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Default charset used: ").append(str).toString());
            }
        }
        return str;
    }

    public String getRequestCharSet() {
        return getContentCharSet(getRequestHeader(MIME.CONTENT_TYPE));
    }

    public String getResponseCharSet() {
        return getContentCharSet(getResponseHeader(MIME.CONTENT_TYPE));
    }

    public int getRecoverableExceptionCount() {
        return this.recoverableExceptionCount;
    }

    protected void responseBodyConsumed() {
        this.responseStream = null;
        if (this.responseConnection != null) {
            this.responseConnection.setLastResponseInputStream(null);
            if (shouldCloseConnection(this.responseConnection)) {
                this.responseConnection.close();
            } else {
                try {
                    if (this.responseConnection.isResponseAvailable()) {
                        if (getParams().isParameterTrue(HttpMethodParams.WARN_EXTRA_INPUT)) {
                            LOG.warn("Extra response data detected - closing connection");
                        }
                        this.responseConnection.close();
                    }
                } catch (Throwable e) {
                    LOG.warn(e.getMessage());
                    this.responseConnection.close();
                }
            }
        }
        this.connectionCloseForced = false;
        ensureConnectionRelease();
    }

    private void ensureConnectionRelease() {
        if (this.responseConnection != null) {
            this.responseConnection.releaseConnection();
            this.responseConnection = null;
        }
    }

    public HostConfiguration getHostConfiguration() {
        HostConfiguration hostConfiguration = new HostConfiguration();
        hostConfiguration.setHost(this.httphost);
        return hostConfiguration;
    }

    public void setHostConfiguration(HostConfiguration hostConfiguration) {
        if (hostConfiguration != null) {
            this.httphost = new HttpHost(hostConfiguration.getHost(), hostConfiguration.getPort(), hostConfiguration.getProtocol());
        } else {
            this.httphost = null;
        }
    }

    public MethodRetryHandler getMethodRetryHandler() {
        return this.methodRetryHandler;
    }

    public void setMethodRetryHandler(MethodRetryHandler methodRetryHandler) {
        this.methodRetryHandler = methodRetryHandler;
    }

    void fakeResponse(StatusLine statusLine, HeaderGroup headerGroup, InputStream inputStream) {
        this.used = true;
        this.statusLine = statusLine;
        this.responseHeaders = headerGroup;
        this.responseBody = null;
        this.responseStream = inputStream;
    }

    public AuthState getHostAuthState() {
        return this.hostAuthState;
    }

    public AuthState getProxyAuthState() {
        return this.proxyAuthState;
    }

    public boolean isAborted() {
        return this.aborted;
    }

    public boolean isRequestSent() {
        return this.requestSent;
    }
}
