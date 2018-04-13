package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.URIUtil;

public class HttpURL extends URI {
    public static final int DEFAULT_PORT = 80;
    public static final char[] DEFAULT_SCHEME = new char[]{'h', 't', 't', 'p'};
    public static final int _default_port = 80;
    public static final char[] _default_scheme = DEFAULT_SCHEME;
    static final long serialVersionUID = -7158031098595039459L;

    protected HttpURL() {
    }

    public HttpURL(char[] cArr, String str) throws URIException, NullPointerException {
        this.protocolCharset = str;
        parseUriReference(new String(cArr), true);
        checkValid();
    }

    public HttpURL(char[] cArr) throws URIException, NullPointerException {
        parseUriReference(new String(cArr), true);
        checkValid();
    }

    public HttpURL(String str, String str2) throws URIException {
        this.protocolCharset = str2;
        parseUriReference(str, false);
        checkValid();
    }

    public HttpURL(String str) throws URIException {
        parseUriReference(str, false);
        checkValid();
    }

    public HttpURL(String str, int i, String str2) throws URIException {
        this(null, null, str, i, str2, null, null);
    }

    public HttpURL(String str, int i, String str2, String str3) throws URIException {
        this(null, null, str, i, str2, str3, null);
    }

    public HttpURL(String str, String str2, String str3) throws URIException {
        this(str, str2, str3, -1, null, null, null);
    }

    public HttpURL(String str, String str2, String str3, int i) throws URIException {
        this(str, str2, str3, i, null, null, null);
    }

    public HttpURL(String str, String str2, String str3, int i, String str4) throws URIException {
        this(str, str2, str3, i, str4, null, null);
    }

    public HttpURL(String str, String str2, String str3, int i, String str4, String str5) throws URIException {
        this(str, str2, str3, i, str4, str5, null);
    }

    public HttpURL(String str, String str2, String str3, String str4) throws URIException {
        this(null, null, str, -1, str2, str3, str4);
    }

    public HttpURL(String str, String str2, String str3, String str4, String str5) throws URIException {
        this(str, str2, -1, str3, str4, str5);
    }

    public HttpURL(String str, String str2, int i, String str3) throws URIException {
        this(str, str2, i, str3, null, null);
    }

    public HttpURL(String str, String str2, int i, String str3, String str4) throws URIException {
        this(str, str2, i, str3, str4, null);
    }

    public HttpURL(String str, String str2, int i, String str3, String str4, String str5) throws URIException {
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
                if (!(i == -1 && i == 80)) {
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

    public HttpURL(String str, String str2, String str3, int i, String str4, String str5, String str6) throws URIException {
        this(toUserinfo(str, str2), str3, i, str4, str5, str6);
    }

    protected static String toUserinfo(String str, String str2) throws URIException {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append(URIUtil.encode(str, URI.allowed_within_userinfo));
        if (str2 == null) {
            return stringBuffer.toString();
        }
        stringBuffer.append(':');
        stringBuffer.append(URIUtil.encode(str2, URI.allowed_within_userinfo));
        return stringBuffer.toString();
    }

    public HttpURL(HttpURL httpURL, String str) throws URIException {
        this(httpURL, new HttpURL(str));
    }

    public HttpURL(HttpURL httpURL, HttpURL httpURL2) throws URIException {
        super((URI) httpURL, (URI) httpURL2);
        checkValid();
    }

    public char[] getRawScheme() {
        return this._scheme == null ? null : DEFAULT_SCHEME;
    }

    public String getScheme() {
        return this._scheme == null ? null : new String(DEFAULT_SCHEME);
    }

    public int getPort() {
        return this._port == -1 ? 80 : this._port;
    }

    public void setRawUserinfo(char[] cArr, char[] cArr2) throws URIException {
        if (cArr == null || cArr.length == 0) {
            throw new URIException(1, "user required");
        } else if (validate(cArr, URI.within_userinfo) && (cArr2 == null || validate(cArr2, URI.within_userinfo))) {
            String str = new String(cArr);
            String str2 = cArr2 == null ? null : new String(cArr2);
            str = new StringBuffer().append(str).append(str2 == null ? "" : new StringBuffer().append(":").append(str2).toString()).toString();
            str2 = new String(getRawHost());
            if (this._port != -1) {
                str2 = new StringBuffer().append(str2).append(":").append(this._port).toString();
            }
            str2 = new StringBuffer().append(str).append("@").append(str2).toString();
            this._userinfo = str.toCharArray();
            this._authority = str2.toCharArray();
            setURI();
        } else {
            throw new URIException(3, "escaped userinfo not valid");
        }
    }

    public void setEscapedUserinfo(String str, String str2) throws URIException, NullPointerException {
        setRawUserinfo(str.toCharArray(), str2 == null ? null : str2.toCharArray());
    }

    public void setUserinfo(String str, String str2) throws URIException, NullPointerException {
        String protocolCharset = getProtocolCharset();
        setRawUserinfo(URI.encode(str, URI.within_userinfo, protocolCharset), str2 == null ? null : URI.encode(str2, URI.within_userinfo, protocolCharset));
    }

    public void setRawUser(char[] cArr) throws URIException {
        if (cArr == null || cArr.length == 0) {
            throw new URIException(1, "user required");
        } else if (validate(cArr, URI.within_userinfo)) {
            String str = new String(cArr);
            String str2 = new String(getRawPassword());
            str2 = new StringBuffer().append(str).append(str2 == null ? "" : new StringBuffer().append(":").append(str2).toString()).toString();
            str = new String(getRawHost());
            if (this._port != -1) {
                str = new StringBuffer().append(str).append(":").append(this._port).toString();
            }
            str = new StringBuffer().append(str2).append("@").append(str).toString();
            this._userinfo = str2.toCharArray();
            this._authority = str.toCharArray();
            setURI();
        } else {
            throw new URIException(3, "escaped user not valid");
        }
    }

    public void setEscapedUser(String str) throws URIException, NullPointerException {
        setRawUser(str.toCharArray());
    }

    public void setUser(String str) throws URIException, NullPointerException {
        setRawUser(URI.encode(str, URI.allowed_within_userinfo, getProtocolCharset()));
    }

    public char[] getRawUser() {
        if (this._userinfo == null || this._userinfo.length == 0) {
            return null;
        }
        int indexFirstOf = indexFirstOf(this._userinfo, ':');
        if (indexFirstOf == -1) {
            return this._userinfo;
        }
        char[] cArr = new char[indexFirstOf];
        System.arraycopy(this._userinfo, 0, cArr, 0, indexFirstOf);
        return cArr;
    }

    public String getEscapedUser() {
        char[] rawUser = getRawUser();
        return rawUser == null ? null : new String(rawUser);
    }

    public String getUser() throws URIException {
        char[] rawUser = getRawUser();
        return rawUser == null ? null : URI.decode(rawUser, getProtocolCharset());
    }

    public void setRawPassword(char[] cArr) throws URIException {
        if (cArr != null && !validate(cArr, URI.within_userinfo)) {
            throw new URIException(3, "escaped password not valid");
        } else if (getRawUser() == null || getRawUser().length == 0) {
            throw new URIException(1, "username required");
        } else {
            String str = new String(getRawUser());
            String str2 = new String(cArr);
            str2 = new StringBuffer().append(str).append(str2 == null ? "" : new StringBuffer().append(":").append(str2).toString()).toString();
            str = new String(getRawHost());
            if (this._port != -1) {
                str = new StringBuffer().append(str).append(":").append(this._port).toString();
            }
            str = new StringBuffer().append(str2).append("@").append(str).toString();
            this._userinfo = str2.toCharArray();
            this._authority = str.toCharArray();
            setURI();
        }
    }

    public void setEscapedPassword(String str) throws URIException {
        setRawPassword(str == null ? null : str.toCharArray());
    }

    public void setPassword(String str) throws URIException {
        setRawPassword(str == null ? null : URI.encode(str, URI.allowed_within_userinfo, getProtocolCharset()));
    }

    public char[] getRawPassword() {
        int indexFirstOf = indexFirstOf(this._userinfo, ':');
        if (indexFirstOf == -1) {
            return null;
        }
        int length = (this._userinfo.length - indexFirstOf) - 1;
        char[] cArr = new char[length];
        System.arraycopy(this._userinfo, indexFirstOf + 1, cArr, 0, length);
        return cArr;
    }

    public String getEscapedPassword() {
        char[] rawPassword = getRawPassword();
        return rawPassword == null ? null : new String(rawPassword);
    }

    public String getPassword() throws URIException {
        char[] rawPassword = getRawPassword();
        return rawPassword == null ? null : URI.decode(rawPassword, getProtocolCharset());
    }

    public char[] getRawCurrentHierPath() throws URIException {
        return (this._path == null || this._path.length == 0) ? URI.rootPath : super.getRawCurrentHierPath(this._path);
    }

    public char[] getRawAboveHierPath() throws URIException {
        char[] rawCurrentHierPath = getRawCurrentHierPath();
        return (rawCurrentHierPath == null || rawCurrentHierPath.length == 0) ? URI.rootPath : getRawCurrentHierPath(rawCurrentHierPath);
    }

    public char[] getRawPath() {
        char[] rawPath = super.getRawPath();
        return (rawPath == null || rawPath.length == 0) ? URI.rootPath : rawPath;
    }

    public void setQuery(String str, String str2) throws URIException, NullPointerException {
        StringBuffer stringBuffer = new StringBuffer();
        String protocolCharset = getProtocolCharset();
        stringBuffer.append(URI.encode(str, URI.allowed_within_query, protocolCharset));
        stringBuffer.append('=');
        stringBuffer.append(URI.encode(str2, URI.allowed_within_query, protocolCharset));
        this._query = stringBuffer.toString().toCharArray();
        setURI();
    }

    public void setQuery(String[] strArr, String[] strArr2) throws URIException, NullPointerException {
        int length = strArr.length;
        if (length != strArr2.length) {
            throw new URIException("wrong array size of query");
        }
        StringBuffer stringBuffer = new StringBuffer();
        String protocolCharset = getProtocolCharset();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(URI.encode(strArr[i], URI.allowed_within_query, protocolCharset));
            stringBuffer.append('=');
            stringBuffer.append(URI.encode(strArr2[i], URI.allowed_within_query, protocolCharset));
            if (i + 1 < length) {
                stringBuffer.append('&');
            }
        }
        this._query = stringBuffer.toString().toCharArray();
        setURI();
    }

    protected void checkValid() throws URIException {
        if (!equals(this._scheme, DEFAULT_SCHEME) && this._scheme != null) {
            throw new URIException(1, "wrong class use");
        }
    }

    protected void setURI() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this._scheme != null) {
            stringBuffer.append(this._scheme);
            stringBuffer.append(':');
        }
        if (this._is_net_path) {
            stringBuffer.append("//");
            if (this._authority != null) {
                if (this._userinfo == null) {
                    stringBuffer.append(this._authority);
                } else if (this._host != null) {
                    stringBuffer.append(this._host);
                    if (this._port != -1) {
                        stringBuffer.append(':');
                        stringBuffer.append(this._port);
                    }
                }
            }
        }
        if (this._opaque != null && this._is_opaque_part) {
            stringBuffer.append(this._opaque);
        } else if (!(this._path == null || this._path.length == 0)) {
            stringBuffer.append(this._path);
        }
        if (this._query != null) {
            stringBuffer.append('?');
            stringBuffer.append(this._query);
        }
        this._uri = stringBuffer.toString().toCharArray();
        this.hash = 0;
    }
}
