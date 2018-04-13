package cz.msebera.android.httpclient;

import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;

@Immutable
public final class HttpHost implements Serializable, Cloneable {
    public static final String DEFAULT_SCHEME_NAME = "http";
    protected final String a;
    protected final String b;
    protected final int c;
    protected final String d;
    protected final InetAddress e;

    public HttpHost(String str, int i, String str2) {
        this.a = (String) Args.containsNoBlanks(str, "Host name");
        this.b = str.toLowerCase(Locale.ROOT);
        if (str2 != null) {
            this.d = str2.toLowerCase(Locale.ROOT);
        } else {
            this.d = "http";
        }
        this.c = i;
        this.e = null;
    }

    public HttpHost(String str, int i) {
        this(str, i, null);
    }

    public static HttpHost create(String str) {
        Args.containsNoBlanks(str, "HTTP Host");
        String str2 = null;
        int indexOf = str.indexOf("://");
        if (indexOf > 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 3);
        }
        indexOf = -1;
        int lastIndexOf = str.lastIndexOf(Config.TRACE_TODAY_VISIT_SPLIT);
        if (lastIndexOf > 0) {
            try {
                indexOf = Integer.parseInt(str.substring(lastIndexOf + 1));
                str = str.substring(0, lastIndexOf);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid HTTP host: " + str);
            }
        }
        return new HttpHost(str, indexOf, str2);
    }

    public HttpHost(String str) {
        this(str, -1, null);
    }

    public HttpHost(InetAddress inetAddress, int i, String str) {
        this((InetAddress) Args.notNull(inetAddress, "Inet address"), inetAddress.getHostName(), i, str);
    }

    public HttpHost(InetAddress inetAddress, String str, int i, String str2) {
        this.e = (InetAddress) Args.notNull(inetAddress, "Inet address");
        this.a = (String) Args.notNull(str, "Hostname");
        this.b = this.a.toLowerCase(Locale.ROOT);
        if (str2 != null) {
            this.d = str2.toLowerCase(Locale.ROOT);
        } else {
            this.d = "http";
        }
        this.c = i;
    }

    public HttpHost(InetAddress inetAddress, int i) {
        this(inetAddress, i, null);
    }

    public HttpHost(InetAddress inetAddress) {
        this(inetAddress, -1, null);
    }

    public HttpHost(HttpHost httpHost) {
        Args.notNull(httpHost, "HTTP host");
        this.a = httpHost.a;
        this.b = httpHost.b;
        this.d = httpHost.d;
        this.c = httpHost.c;
        this.e = httpHost.e;
    }

    public String getHostName() {
        return this.a;
    }

    public int getPort() {
        return this.c;
    }

    public String getSchemeName() {
        return this.d;
    }

    public InetAddress getAddress() {
        return this.e;
    }

    public String toURI() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d);
        stringBuilder.append("://");
        stringBuilder.append(this.a);
        if (this.c != -1) {
            stringBuilder.append(':');
            stringBuilder.append(Integer.toString(this.c));
        }
        return stringBuilder.toString();
    }

    public String toHostString() {
        if (this.c == -1) {
            return this.a;
        }
        StringBuilder stringBuilder = new StringBuilder(this.a.length() + 6);
        stringBuilder.append(this.a);
        stringBuilder.append(Config.TRACE_TODAY_VISIT_SPLIT);
        stringBuilder.append(Integer.toString(this.c));
        return stringBuilder.toString();
    }

    public String toString() {
        return toURI();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        HttpHost httpHost = (HttpHost) obj;
        if (this.b.equals(httpHost.b) && this.c == httpHost.c && this.d.equals(httpHost.d)) {
            if (this.e == null) {
                if (httpHost.e == null) {
                    return true;
                }
            } else if (this.e.equals(httpHost.e)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.b), this.c), this.d);
        if (this.e != null) {
            return LangUtils.hashCode(hashCode, this.e);
        }
        return hashCode;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
