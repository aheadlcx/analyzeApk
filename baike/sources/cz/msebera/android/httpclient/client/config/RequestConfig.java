package cz.msebera.android.httpclient.client.config;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.net.InetAddress;
import java.util.Collection;

@Immutable
public class RequestConfig implements Cloneable {
    public static final RequestConfig DEFAULT = new Builder().build();
    private final boolean a;
    private final HttpHost b;
    private final InetAddress c;
    private final boolean d;
    private final String e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final int i;
    private final boolean j;
    private final Collection<String> k;
    private final Collection<String> l;
    private final int m;
    private final int n;
    private final int o;
    private final boolean p;

    public static class Builder {
        private boolean a;
        private HttpHost b;
        private InetAddress c;
        private boolean d = false;
        private String e;
        private boolean f = true;
        private boolean g = true;
        private boolean h;
        private int i = 50;
        private boolean j = true;
        private Collection<String> k;
        private Collection<String> l;
        private int m = -1;
        private int n = -1;
        private int o = -1;
        private boolean p = true;

        Builder() {
        }

        public Builder setExpectContinueEnabled(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setProxy(HttpHost httpHost) {
            this.b = httpHost;
            return this;
        }

        public Builder setLocalAddress(InetAddress inetAddress) {
            this.c = inetAddress;
            return this;
        }

        @Deprecated
        public Builder setStaleConnectionCheckEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setCookieSpec(String str) {
            this.e = str;
            return this;
        }

        public Builder setRedirectsEnabled(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setRelativeRedirectsAllowed(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setCircularRedirectsAllowed(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setMaxRedirects(int i) {
            this.i = i;
            return this;
        }

        public Builder setAuthenticationEnabled(boolean z) {
            this.j = z;
            return this;
        }

        public Builder setTargetPreferredAuthSchemes(Collection<String> collection) {
            this.k = collection;
            return this;
        }

        public Builder setProxyPreferredAuthSchemes(Collection<String> collection) {
            this.l = collection;
            return this;
        }

        public Builder setConnectionRequestTimeout(int i) {
            this.m = i;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            this.n = i;
            return this;
        }

        public Builder setSocketTimeout(int i) {
            this.o = i;
            return this;
        }

        public Builder setDecompressionEnabled(boolean z) {
            this.p = z;
            return this;
        }

        public RequestConfig build() {
            return new RequestConfig(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
        }
    }

    protected /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    RequestConfig(boolean z, HttpHost httpHost, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4, boolean z7) {
        this.a = z;
        this.b = httpHost;
        this.c = inetAddress;
        this.d = z2;
        this.e = str;
        this.f = z3;
        this.g = z4;
        this.h = z5;
        this.i = i;
        this.j = z6;
        this.k = collection;
        this.l = collection2;
        this.m = i2;
        this.n = i3;
        this.o = i4;
        this.p = z7;
    }

    public boolean isExpectContinueEnabled() {
        return this.a;
    }

    public HttpHost getProxy() {
        return this.b;
    }

    public InetAddress getLocalAddress() {
        return this.c;
    }

    @Deprecated
    public boolean isStaleConnectionCheckEnabled() {
        return this.d;
    }

    public String getCookieSpec() {
        return this.e;
    }

    public boolean isRedirectsEnabled() {
        return this.f;
    }

    public boolean isRelativeRedirectsAllowed() {
        return this.g;
    }

    public boolean isCircularRedirectsAllowed() {
        return this.h;
    }

    public int getMaxRedirects() {
        return this.i;
    }

    public boolean isAuthenticationEnabled() {
        return this.j;
    }

    public Collection<String> getTargetPreferredAuthSchemes() {
        return this.k;
    }

    public Collection<String> getProxyPreferredAuthSchemes() {
        return this.l;
    }

    public int getConnectionRequestTimeout() {
        return this.m;
    }

    public int getConnectTimeout() {
        return this.n;
    }

    public int getSocketTimeout() {
        return this.o;
    }

    public boolean isDecompressionEnabled() {
        return this.p;
    }

    protected RequestConfig a() throws CloneNotSupportedException {
        return (RequestConfig) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append("expectContinueEnabled=").append(this.a);
        stringBuilder.append(", proxy=").append(this.b);
        stringBuilder.append(", localAddress=").append(this.c);
        stringBuilder.append(", cookieSpec=").append(this.e);
        stringBuilder.append(", redirectsEnabled=").append(this.f);
        stringBuilder.append(", relativeRedirectsAllowed=").append(this.g);
        stringBuilder.append(", maxRedirects=").append(this.i);
        stringBuilder.append(", circularRedirectsAllowed=").append(this.h);
        stringBuilder.append(", authenticationEnabled=").append(this.j);
        stringBuilder.append(", targetPreferredAuthSchemes=").append(this.k);
        stringBuilder.append(", proxyPreferredAuthSchemes=").append(this.l);
        stringBuilder.append(", connectionRequestTimeout=").append(this.m);
        stringBuilder.append(", connectTimeout=").append(this.n);
        stringBuilder.append(", socketTimeout=").append(this.o);
        stringBuilder.append(", decompressionEnabled=").append(this.p);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(RequestConfig requestConfig) {
        return new Builder().setExpectContinueEnabled(requestConfig.isExpectContinueEnabled()).setProxy(requestConfig.getProxy()).setLocalAddress(requestConfig.getLocalAddress()).setStaleConnectionCheckEnabled(requestConfig.isStaleConnectionCheckEnabled()).setCookieSpec(requestConfig.getCookieSpec()).setRedirectsEnabled(requestConfig.isRedirectsEnabled()).setRelativeRedirectsAllowed(requestConfig.isRelativeRedirectsAllowed()).setCircularRedirectsAllowed(requestConfig.isCircularRedirectsAllowed()).setMaxRedirects(requestConfig.getMaxRedirects()).setAuthenticationEnabled(requestConfig.isAuthenticationEnabled()).setTargetPreferredAuthSchemes(requestConfig.getTargetPreferredAuthSchemes()).setProxyPreferredAuthSchemes(requestConfig.getProxyPreferredAuthSchemes()).setConnectionRequestTimeout(requestConfig.getConnectionRequestTimeout()).setConnectTimeout(requestConfig.getConnectTimeout()).setSocketTimeout(requestConfig.getSocketTimeout()).setDecompressionEnabled(requestConfig.isDecompressionEnabled());
    }
}
