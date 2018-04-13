package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.Route;

public final class RouteDatabase {
    private final Set<Route> a = new LinkedHashSet();

    public synchronized void failed(Route route) {
        this.a.add(route);
    }

    public synchronized void connected(Route route) {
        this.a.remove(route);
    }

    public synchronized boolean shouldPostpone(Route route) {
        return this.a.contains(route);
    }
}
