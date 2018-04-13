package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;

final class a implements ConnPerRoute {
    a() {
    }

    public int getMaxForRoute(HttpRoute httpRoute) {
        return 2;
    }
}
