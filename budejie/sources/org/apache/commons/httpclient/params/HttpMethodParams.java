package org.apache.commons.httpclient.params;

import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpMethodParams extends DefaultHttpParams {
    public static final String BUFFER_WARN_TRIGGER_LIMIT = "http.method.response.buffer.warnlimit";
    public static final String COOKIE_POLICY = "http.protocol.cookie-policy";
    public static final String CREDENTIAL_CHARSET = "http.protocol.credential-charset";
    public static final String DATE_PATTERNS = "http.dateparser.patterns";
    public static final String HEAD_BODY_CHECK_TIMEOUT = "http.protocol.head-body-timeout";
    public static final String HTTP_CONTENT_CHARSET = "http.protocol.content-charset";
    public static final String HTTP_ELEMENT_CHARSET = "http.protocol.element-charset";
    private static final Log LOG;
    public static final String MULTIPART_BOUNDARY = "http.method.multipart.boundary";
    private static final String[] PROTOCOL_STRICTNESS_PARAMETERS = new String[]{UNAMBIGUOUS_STATUS_LINE, SINGLE_COOKIE_HEADER, STRICT_TRANSFER_ENCODING, REJECT_HEAD_BODY, WARN_EXTRA_INPUT};
    public static final String PROTOCOL_VERSION = "http.protocol.version";
    public static final String REJECT_HEAD_BODY = "http.protocol.reject-head-body";
    public static final String RETRY_HANDLER = "http.method.retry-handler";
    public static final String SINGLE_COOKIE_HEADER = "http.protocol.single-cookie-header";
    public static final String SO_TIMEOUT = "http.socket.timeout";
    public static final String STATUS_LINE_GARBAGE_LIMIT = "http.protocol.status-line-garbage-limit";
    public static final String STRICT_TRANSFER_ENCODING = "http.protocol.strict-transfer-encoding";
    public static final String UNAMBIGUOUS_STATUS_LINE = "http.protocol.unambiguous-statusline";
    public static final String USER_AGENT = "http.useragent";
    public static final String USE_EXPECT_CONTINUE = "http.protocol.expect-continue";
    public static final String VIRTUAL_HOST = "http.virtual-host";
    public static final String WARN_EXTRA_INPUT = "http.protocol.warn-extra-input";
    static Class class$org$apache$commons$httpclient$params$HttpMethodParams;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$params$HttpMethodParams == null) {
            class$ = class$("org.apache.commons.httpclient.params.HttpMethodParams");
            class$org$apache$commons$httpclient$params$HttpMethodParams = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$params$HttpMethodParams;
        }
        LOG = LogFactory.getLog(class$);
    }

    public HttpMethodParams() {
        super(DefaultHttpParams.getDefaultParams());
    }

    public HttpMethodParams(HttpParams httpParams) {
        super(httpParams);
    }

    public String getHttpElementCharset() {
        String str = (String) getParameter(HTTP_ELEMENT_CHARSET);
        if (str != null) {
            return str;
        }
        LOG.warn("HTTP element charset not configured, using US-ASCII");
        return "US-ASCII";
    }

    public void setHttpElementCharset(String str) {
        setParameter(HTTP_ELEMENT_CHARSET, str);
    }

    public String getContentCharset() {
        String str = (String) getParameter(HTTP_CONTENT_CHARSET);
        if (str != null) {
            return str;
        }
        LOG.warn("Default content charset not configured, using ISO-8859-1");
        return "ISO-8859-1";
    }

    public void setContentCharset(String str) {
        setParameter(HTTP_CONTENT_CHARSET, str);
    }

    public String getCredentialCharset() {
        String str = (String) getParameter(CREDENTIAL_CHARSET);
        if (str != null) {
            return str;
        }
        LOG.debug("Credential charset not configured, using HTTP element charset");
        return getHttpElementCharset();
    }

    public void setCredentialCharset(String str) {
        setParameter(CREDENTIAL_CHARSET, str);
    }

    public HttpVersion getVersion() {
        Object parameter = getParameter(PROTOCOL_VERSION);
        if (parameter == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (HttpVersion) parameter;
    }

    public void setVersion(HttpVersion httpVersion) {
        setParameter(PROTOCOL_VERSION, httpVersion);
    }

    public String getCookiePolicy() {
        Object parameter = getParameter(COOKIE_POLICY);
        if (parameter == null) {
            return CookiePolicy.DEFAULT;
        }
        return (String) parameter;
    }

    public void setCookiePolicy(String str) {
        setParameter(COOKIE_POLICY, str);
    }

    public int getSoTimeout() {
        return getIntParameter("http.socket.timeout", 0);
    }

    public void setSoTimeout(int i) {
        setIntParameter("http.socket.timeout", i);
    }

    public void setVirtualHost(String str) {
        setParameter(VIRTUAL_HOST, str);
    }

    public String getVirtualHost() {
        return (String) getParameter(VIRTUAL_HOST);
    }

    public void makeStrict() {
        setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.TRUE);
        setIntParameter(STATUS_LINE_GARBAGE_LIMIT, 0);
    }

    public void makeLenient() {
        setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.FALSE);
        setIntParameter(STATUS_LINE_GARBAGE_LIMIT, Integer.MAX_VALUE);
    }
}
