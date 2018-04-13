package okhttp3;

import com.alipay.sdk.util.h;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import javax.annotation.Nullable;

public final class Route {
    final Address a;
    final Proxy b;
    final InetSocketAddress c;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (address == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.a = address;
            this.b = proxy;
            this.c = inetSocketAddress;
        }
    }

    public Address address() {
        return this.a;
    }

    public Proxy proxy() {
        return this.b;
    }

    public InetSocketAddress socketAddress() {
        return this.c;
    }

    public boolean requiresTunnel() {
        return this.a.i != null && this.b.type() == Type.HTTP;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Route) && ((Route) obj).a.equals(this.a) && ((Route) obj).b.equals(this.b) && ((Route) obj).c.equals(this.c);
    }

    public int hashCode() {
        return ((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Route{" + this.c + h.d;
    }
}
