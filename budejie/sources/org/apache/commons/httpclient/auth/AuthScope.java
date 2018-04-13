package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.util.LangUtils;

public class AuthScope {
    public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
    public static final String ANY_HOST = null;
    public static final int ANY_PORT = -1;
    public static final String ANY_REALM = null;
    public static final String ANY_SCHEME = null;
    private String host;
    private int port;
    private String realm;
    private String scheme;

    public AuthScope(String str, int i, String str2, String str3) {
        this.scheme = null;
        this.realm = null;
        this.host = null;
        this.port = -1;
        this.host = str == null ? ANY_HOST : str.toLowerCase();
        if (i < 0) {
            i = -1;
        }
        this.port = i;
        if (str2 == null) {
            str2 = ANY_REALM;
        }
        this.realm = str2;
        this.scheme = str3 == null ? ANY_SCHEME : str3.toUpperCase();
    }

    public AuthScope(String str, int i, String str2) {
        this(str, i, str2, ANY_SCHEME);
    }

    public AuthScope(String str, int i) {
        this(str, i, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(AuthScope authScope) {
        this.scheme = null;
        this.realm = null;
        this.host = null;
        this.port = -1;
        if (authScope == null) {
            throw new IllegalArgumentException("Scope may not be null");
        }
        this.host = authScope.getHost();
        this.port = authScope.getPort();
        this.realm = authScope.getRealm();
        this.scheme = authScope.getScheme();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getRealm() {
        return this.realm;
    }

    public String getScheme() {
        return this.scheme;
    }

    private static boolean paramsEqual(String str, String str2) {
        if (str == null) {
            return str == str2;
        } else {
            return str.equals(str2);
        }
    }

    private static boolean paramsEqual(int i, int i2) {
        return i == i2;
    }

    public int match(AuthScope authScope) {
        int i = 0;
        if (paramsEqual(this.scheme, authScope.scheme)) {
            i = 1;
        } else if (!(this.scheme == ANY_SCHEME || authScope.scheme == ANY_SCHEME)) {
            return -1;
        }
        if (paramsEqual(this.realm, authScope.realm)) {
            i += 2;
        } else if (!(this.realm == ANY_REALM || authScope.realm == ANY_REALM)) {
            return -1;
        }
        if (paramsEqual(this.port, authScope.port)) {
            i += 4;
        } else if (!(this.port == -1 || authScope.port == -1)) {
            return -1;
        }
        if (paramsEqual(this.host, authScope.host)) {
            return i + 8;
        }
        if (this.host == ANY_HOST || authScope.host == ANY_HOST) {
            return i;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthScope)) {
            return super.equals(obj);
        }
        AuthScope authScope = (AuthScope) obj;
        if (paramsEqual(this.host, authScope.host) && paramsEqual(this.port, authScope.port) && paramsEqual(this.realm, authScope.realm) && paramsEqual(this.scheme, authScope.scheme)) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.scheme != null) {
            stringBuffer.append(this.scheme.toUpperCase());
            stringBuffer.append(' ');
        }
        if (this.realm != null) {
            stringBuffer.append('\'');
            stringBuffer.append(this.realm);
            stringBuffer.append('\'');
        } else {
            stringBuffer.append("<any realm>");
        }
        if (this.host != null) {
            stringBuffer.append('@');
            stringBuffer.append(this.host);
            if (this.port >= 0) {
                stringBuffer.append(':');
                stringBuffer.append(this.port);
            }
        }
        return stringBuffer.toString();
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.port), this.realm), this.scheme);
    }
}
