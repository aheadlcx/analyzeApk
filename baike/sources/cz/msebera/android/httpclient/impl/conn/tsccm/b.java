package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;

class b implements ConnPerRoute {
    final /* synthetic */ RouteSpecificPool a;

    b(RouteSpecificPool routeSpecificPool) {
        this.a = routeSpecificPool;
    }

    public int getMaxForRoute(HttpRoute httpRoute) {
        return this.a.b;
    }
}
