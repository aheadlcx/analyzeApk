package okhttp3.internal.connection;

import android.support.v4.internal.view.SupportMenu;
import com.baidu.mobstat.Config;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;

public final class RouteSelector {
    private final Address a;
    private final RouteDatabase b;
    private final Call c;
    private final EventListener d;
    private List<Proxy> e = Collections.emptyList();
    private int f;
    private List<InetSocketAddress> g = Collections.emptyList();
    private final List<Route> h = new ArrayList();

    public static final class Selection {
        private final List<Route> a;
        private int b = 0;

        Selection(List<Route> list) {
            this.a = list;
        }

        public boolean hasNext() {
            return this.b < this.a.size();
        }

        public Route next() {
            if (hasNext()) {
                List list = this.a;
                int i = this.b;
                this.b = i + 1;
                return (Route) list.get(i);
            }
            throw new NoSuchElementException();
        }

        public List<Route> getAll() {
            return new ArrayList(this.a);
        }
    }

    public RouteSelector(Address address, RouteDatabase routeDatabase, Call call, EventListener eventListener) {
        this.a = address;
        this.b = routeDatabase;
        this.c = call;
        this.d = eventListener;
        a(address.url(), address.proxy());
    }

    public boolean hasNext() {
        return a() || !this.h.isEmpty();
    }

    public Selection next() throws IOException {
        if (hasNext()) {
            List arrayList = new ArrayList();
            while (a()) {
                Proxy b = b();
                int size = this.g.size();
                for (int i = 0; i < size; i++) {
                    Route route = new Route(this.a, b, (InetSocketAddress) this.g.get(i));
                    if (this.b.shouldPostpone(route)) {
                        this.h.add(route);
                    } else {
                        arrayList.add(route);
                    }
                }
                if (!arrayList.isEmpty()) {
                    break;
                }
            }
            if (arrayList.isEmpty()) {
                arrayList.addAll(this.h);
                this.h.clear();
            }
            return new Selection(arrayList);
        }
        throw new NoSuchElementException();
    }

    public void connectFailed(Route route, IOException iOException) {
        if (!(route.proxy().type() == Type.DIRECT || this.a.proxySelector() == null)) {
            this.a.proxySelector().connectFailed(this.a.url().uri(), route.proxy().address(), iOException);
        }
        this.b.failed(route);
    }

    private void a(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.e = Collections.singletonList(proxy);
        } else {
            List select = this.a.proxySelector().select(httpUrl.uri());
            if (select == null || select.isEmpty()) {
                select = Util.immutableList(new Proxy[]{Proxy.NO_PROXY});
            } else {
                select = Util.immutableList(select);
            }
            this.e = select;
        }
        this.f = 0;
    }

    private boolean a() {
        return this.f < this.e.size();
    }

    private Proxy b() throws IOException {
        if (a()) {
            List list = this.e;
            int i = this.f;
            this.f = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.a.url().host() + "; exhausted proxy configurations: " + this.e);
    }

    private void a(Proxy proxy) throws IOException {
        int port;
        String str;
        this.g = new ArrayList();
        String host;
        if (proxy.type() == Type.DIRECT || proxy.type() == Type.SOCKS) {
            host = this.a.url().host();
            port = this.a.url().port();
            str = host;
        } else {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                host = a(inetSocketAddress);
                port = inetSocketAddress.getPort();
                str = host;
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        }
        if (port < 1 || port > SupportMenu.USER_MASK) {
            throw new SocketException("No route to " + str + Config.TRACE_TODAY_VISIT_SPLIT + port + "; port is out of range");
        } else if (proxy.type() == Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(str, port));
        } else {
            this.d.dnsStart(this.c, str);
            List lookup = this.a.dns().lookup(str);
            if (lookup.isEmpty()) {
                throw new UnknownHostException(this.a.dns() + " returned no addresses for " + str);
            }
            this.d.dnsEnd(this.c, str, lookup);
            int size = lookup.size();
            for (int i = 0; i < size; i++) {
                this.g.add(new InetSocketAddress((InetAddress) lookup.get(i), port));
            }
        }
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
