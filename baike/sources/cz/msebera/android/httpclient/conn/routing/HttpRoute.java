package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Immutable
public final class HttpRoute implements RouteInfo, Cloneable {
    private final HttpHost a;
    private final InetAddress b;
    private final List<HttpHost> c;
    private final TunnelType d;
    private final LayerType e;
    private final boolean f;

    private HttpRoute(HttpHost httpHost, InetAddress inetAddress, List<HttpHost> list, boolean z, TunnelType tunnelType, LayerType layerType) {
        Args.notNull(httpHost, "Target host");
        this.a = a(httpHost);
        this.b = inetAddress;
        if (list == null || list.isEmpty()) {
            this.c = null;
        } else {
            this.c = new ArrayList(list);
        }
        if (tunnelType == TunnelType.TUNNELLED) {
            Args.check(this.c != null, "Proxy required if tunnelled");
        }
        this.f = z;
        if (tunnelType == null) {
            tunnelType = TunnelType.PLAIN;
        }
        this.d = tunnelType;
        if (layerType == null) {
            layerType = LayerType.PLAIN;
        }
        this.e = layerType;
    }

    private static int a(String str) {
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        if ("https".equalsIgnoreCase(str)) {
            return 443;
        }
        return -1;
    }

    private static HttpHost a(HttpHost httpHost) {
        if (httpHost.getPort() >= 0) {
            return httpHost;
        }
        InetAddress address = httpHost.getAddress();
        String schemeName = httpHost.getSchemeName();
        if (address != null) {
            return new HttpHost(address, a(schemeName), schemeName);
        }
        return new HttpHost(httpHost.getHostName(), a(schemeName), schemeName);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost[] httpHostArr, boolean z, TunnelType tunnelType, LayerType layerType) {
        this(httpHost, inetAddress, httpHostArr != null ? Arrays.asList(httpHostArr) : null, z, tunnelType, layerType);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z, TunnelType tunnelType, LayerType layerType) {
        this(httpHost, inetAddress, httpHost2 != null ? Collections.singletonList(httpHost2) : null, z, tunnelType, layerType);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, boolean z) {
        this(httpHost, inetAddress, Collections.emptyList(), z, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost) {
        this(httpHost, null, Collections.emptyList(), false, TunnelType.PLAIN, LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost, InetAddress inetAddress, HttpHost httpHost2, boolean z) {
        this(httpHost, inetAddress, Collections.singletonList(Args.notNull(httpHost2, "Proxy host")), z, z ? TunnelType.TUNNELLED : TunnelType.PLAIN, z ? LayerType.LAYERED : LayerType.PLAIN);
    }

    public HttpRoute(HttpHost httpHost, HttpHost httpHost2) {
        this(httpHost, null, httpHost2, false);
    }

    public final HttpHost getTargetHost() {
        return this.a;
    }

    public final InetAddress getLocalAddress() {
        return this.b;
    }

    public final InetSocketAddress getLocalSocketAddress() {
        return this.b != null ? new InetSocketAddress(this.b, 0) : null;
    }

    public final int getHopCount() {
        return this.c != null ? this.c.size() + 1 : 1;
    }

    public final HttpHost getHopTarget(int i) {
        Args.notNegative(i, "Hop index");
        int hopCount = getHopCount();
        Args.check(i < hopCount, "Hop index exceeds tracked route length");
        if (i < hopCount - 1) {
            return (HttpHost) this.c.get(i);
        }
        return this.a;
    }

    public final HttpHost getProxyHost() {
        return (this.c == null || this.c.isEmpty()) ? null : (HttpHost) this.c.get(0);
    }

    public final TunnelType getTunnelType() {
        return this.d;
    }

    public final boolean isTunnelled() {
        return this.d == TunnelType.TUNNELLED;
    }

    public final LayerType getLayerType() {
        return this.e;
    }

    public final boolean isLayered() {
        return this.e == LayerType.LAYERED;
    }

    public final boolean isSecure() {
        return this.f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpRoute)) {
            return false;
        }
        HttpRoute httpRoute = (HttpRoute) obj;
        if (this.f == httpRoute.f && this.d == httpRoute.d && this.e == httpRoute.e && LangUtils.equals(this.a, httpRoute.a) && LangUtils.equals(this.b, httpRoute.b) && LangUtils.equals(this.c, httpRoute.c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = LangUtils.hashCode(LangUtils.hashCode(17, this.a), this.b);
        if (this.c != null) {
            i = hashCode;
            for (Object hashCode2 : this.c) {
                i = LangUtils.hashCode(i, hashCode2);
            }
        } else {
            i = hashCode;
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(i, this.f), this.d), this.e);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((getHopCount() * 30) + 50);
        if (this.b != null) {
            stringBuilder.append(this.b);
            stringBuilder.append("->");
        }
        stringBuilder.append('{');
        if (this.d == TunnelType.TUNNELLED) {
            stringBuilder.append('t');
        }
        if (this.e == LayerType.LAYERED) {
            stringBuilder.append('l');
        }
        if (this.f) {
            stringBuilder.append('s');
        }
        stringBuilder.append("}->");
        if (this.c != null) {
            for (HttpHost append : this.c) {
                stringBuilder.append(append);
                stringBuilder.append("->");
            }
        }
        stringBuilder.append(this.a);
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
