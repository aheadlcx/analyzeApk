package cz.msebera.android.httpclient.conn.routing;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.LayerType;
import cz.msebera.android.httpclient.conn.routing.RouteInfo.TunnelType;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.net.InetAddress;

@NotThreadSafe
public final class RouteTracker implements RouteInfo, Cloneable {
    private final HttpHost a;
    private final InetAddress b;
    private boolean c;
    private HttpHost[] d;
    private TunnelType e;
    private LayerType f;
    private boolean g;

    public RouteTracker(HttpHost httpHost, InetAddress inetAddress) {
        Args.notNull(httpHost, "Target host");
        this.a = httpHost;
        this.b = inetAddress;
        this.e = TunnelType.PLAIN;
        this.f = LayerType.PLAIN;
    }

    public void reset() {
        this.c = false;
        this.d = null;
        this.e = TunnelType.PLAIN;
        this.f = LayerType.PLAIN;
        this.g = false;
    }

    public RouteTracker(HttpRoute httpRoute) {
        this(httpRoute.getTargetHost(), httpRoute.getLocalAddress());
    }

    public final void connectTarget(boolean z) {
        Asserts.check(!this.c, "Already connected");
        this.c = true;
        this.g = z;
    }

    public final void connectProxy(HttpHost httpHost, boolean z) {
        boolean z2;
        Args.notNull(httpHost, "Proxy host");
        if (this.c) {
            z2 = false;
        } else {
            z2 = true;
        }
        Asserts.check(z2, "Already connected");
        this.c = true;
        this.d = new HttpHost[]{httpHost};
        this.g = z;
    }

    public final void tunnelTarget(boolean z) {
        Asserts.check(this.c, "No tunnel unless connected");
        Asserts.notNull(this.d, "No tunnel without proxy");
        this.e = TunnelType.TUNNELLED;
        this.g = z;
    }

    public final void tunnelProxy(HttpHost httpHost, boolean z) {
        Args.notNull(httpHost, "Proxy host");
        Asserts.check(this.c, "No tunnel unless connected");
        Asserts.notNull(this.d, "No tunnel without proxy");
        Object obj = new HttpHost[(this.d.length + 1)];
        System.arraycopy(this.d, 0, obj, 0, this.d.length);
        obj[obj.length - 1] = httpHost;
        this.d = obj;
        this.g = z;
    }

    public final void layerProtocol(boolean z) {
        Asserts.check(this.c, "No layered protocol unless connected");
        this.f = LayerType.LAYERED;
        this.g = z;
    }

    public final HttpHost getTargetHost() {
        return this.a;
    }

    public final InetAddress getLocalAddress() {
        return this.b;
    }

    public final int getHopCount() {
        if (!this.c) {
            return 0;
        }
        if (this.d == null) {
            return 1;
        }
        return this.d.length + 1;
    }

    public final HttpHost getHopTarget(int i) {
        Args.notNegative(i, "Hop index");
        int hopCount = getHopCount();
        Args.check(i < hopCount, "Hop index exceeds tracked route length");
        if (i < hopCount - 1) {
            return this.d[i];
        }
        return this.a;
    }

    public final HttpHost getProxyHost() {
        return this.d == null ? null : this.d[0];
    }

    public final boolean isConnected() {
        return this.c;
    }

    public final TunnelType getTunnelType() {
        return this.e;
    }

    public final boolean isTunnelled() {
        return this.e == TunnelType.TUNNELLED;
    }

    public final LayerType getLayerType() {
        return this.f;
    }

    public final boolean isLayered() {
        return this.f == LayerType.LAYERED;
    }

    public final boolean isSecure() {
        return this.g;
    }

    public final HttpRoute toRoute() {
        return !this.c ? null : new HttpRoute(this.a, this.b, this.d, this.g, this.e, this.f);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RouteTracker)) {
            return false;
        }
        RouteTracker routeTracker = (RouteTracker) obj;
        if (this.c == routeTracker.c && this.g == routeTracker.g && this.e == routeTracker.e && this.f == routeTracker.f && LangUtils.equals(this.a, routeTracker.a) && LangUtils.equals(this.b, routeTracker.b) && LangUtils.equals(this.d, routeTracker.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = LangUtils.hashCode(LangUtils.hashCode(17, this.a), this.b);
        if (this.d != null) {
            HttpHost[] httpHostArr = this.d;
            int length = httpHostArr.length;
            int i = 0;
            while (i < length) {
                int hashCode2 = LangUtils.hashCode(hashCode, httpHostArr[i]);
                i++;
                hashCode = hashCode2;
            }
        }
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(hashCode, this.c), this.g), this.e), this.f);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((getHopCount() * 30) + 50);
        stringBuilder.append("RouteTracker[");
        if (this.b != null) {
            stringBuilder.append(this.b);
            stringBuilder.append("->");
        }
        stringBuilder.append('{');
        if (this.c) {
            stringBuilder.append('c');
        }
        if (this.e == TunnelType.TUNNELLED) {
            stringBuilder.append('t');
        }
        if (this.f == LayerType.LAYERED) {
            stringBuilder.append('l');
        }
        if (this.g) {
            stringBuilder.append('s');
        }
        stringBuilder.append("}->");
        if (this.d != null) {
            for (Object append : this.d) {
                stringBuilder.append(append);
                stringBuilder.append("->");
            }
        }
        stringBuilder.append(this.a);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
