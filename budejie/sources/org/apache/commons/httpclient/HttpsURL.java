package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.URIUtil;

public class HttpsURL extends HttpURL {
    public static final int DEFAULT_PORT = 443;
    public static final char[] DEFAULT_SCHEME = new char[]{'h', 't', 't', 'p', 's'};
    public static final int _default_port = 443;
    public static final char[] _default_scheme = DEFAULT_SCHEME;
    static final long serialVersionUID = 887844277028676648L;

    protected HttpsURL() {
    }

    public HttpsURL(char[] cArr, String str) throws URIException, NullPointerException {
        this.protocolCharset = str;
        parseUriReference(new String(cArr), true);
        checkValid();
    }

    public HttpsURL(char[] cArr) throws URIException, NullPointerException {
        parseUriReference(new String(cArr), true);
        checkValid();
    }

    public HttpsURL(String str, String str2) throws URIException {
        this.protocolCharset = str2;
        parseUriReference(str, false);
        checkValid();
    }

    public HttpsURL(String str) throws URIException {
        parseUriReference(str, false);
        checkValid();
    }

    public HttpsURL(String str, int i, String str2) throws URIException {
        this(null, str, i, str2, null, null);
    }

    public HttpsURL(String str, int i, String str2, String str3) throws URIException {
        this(null, str, i, str2, str3, null);
    }

    public HttpsURL(String str, String str2, String str3) throws URIException {
        this(str, str2, str3, -1, null, null, null);
    }

    public HttpsURL(String str, String str2, String str3, int i) throws URIException {
        this(str, str2, str3, i, null, null, null);
    }

    public HttpsURL(String str, String str2, String str3, int i, String str4) throws URIException {
        this(str, str2, str3, i, str4, null, null);
    }

    public HttpsURL(String str, String str2, String str3, int i, String str4, String str5) throws URIException {
        this(str, str2, str3, i, str4, str5, null);
    }

    public HttpsURL(String str, String str2, String str3, String str4) throws URIException {
        this(null, str, -1, str2, str3, str4);
    }

    public HttpsURL(String str, String str2, String str3, String str4, String str5) throws URIException {
        this(str, str2, -1, str3, str4, str5);
    }

    public HttpsURL(String str, String str2, int i, String str3) throws URIException {
        this(str, str2, i, str3, null, null);
    }

    public HttpsURL(String str, String str2, int i, String str3, String str4) throws URIException {
        this(str, str2, i, str3, str4, null);
    }

    public HttpsURL(String str, String str2, int i, String str3, String str4, String str5) throws URIException {
        StringBuffer stringBuffer = new StringBuffer();
        if (!(str == null && str2 == null && i == -1)) {
            this._scheme = DEFAULT_SCHEME;
            stringBuffer.append(_default_scheme);
            stringBuffer.append("://");
            if (str != null) {
                stringBuffer.append(str);
                stringBuffer.append('@');
            }
            if (str2 != null) {
                stringBuffer.append(URIUtil.encode(str2, URI.allowed_host));
                if (!(i == -1 && i == 443)) {
                    stringBuffer.append(':');
                    stringBuffer.append(i);
                }
            }
        }
        if (str3 != null) {
            if (URI.scheme == null || str3.startsWith("/")) {
                stringBuffer.append(URIUtil.encode(str3, URI.allowed_abs_path));
            } else {
                throw new URIException(1, "abs_path requested");
            }
        }
        if (str4 != null) {
            stringBuffer.append('?');
            stringBuffer.append(URIUtil.encode(str4, URI.allowed_query));
        }
        if (str5 != null) {
            stringBuffer.append('#');
            stringBuffer.append(URIUtil.encode(str5, URI.allowed_fragment));
        }
        parseUriReference(stringBuffer.toString(), true);
        checkValid();
    }

    public HttpsURL(String str, String str2, String str3, int i, String str4, String str5, String str6) throws URIException {
        this(HttpURL.toUserinfo(str, str2), str3, i, str4, str5, str6);
    }

    public HttpsURL(HttpsURL httpsURL, String str) throws URIException {
        this(httpsURL, new HttpsURL(str));
    }

    public HttpsURL(HttpsURL httpsURL, HttpsURL httpsURL2) throws URIException {
        super((HttpURL) httpsURL, (HttpURL) httpsURL2);
        checkValid();
    }

    public char[] getRawScheme() {
        return this._scheme == null ? null : DEFAULT_SCHEME;
    }

    public String getScheme() {
        return this._scheme == null ? null : new String(DEFAULT_SCHEME);
    }

    public int getPort() {
        return this._port == -1 ? 443 : this._port;
    }

    protected void checkValid() throws URIException {
        if (!equals(this._scheme, DEFAULT_SCHEME) && this._scheme != null) {
            throw new URIException(1, "wrong class use");
        }
    }
}
