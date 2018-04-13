package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;

@NotThreadSafe
@Deprecated
public class RoutedRequest {
    protected final RequestWrapper a;
    protected final HttpRoute b;

    public RoutedRequest(RequestWrapper requestWrapper, HttpRoute httpRoute) {
        this.a = requestWrapper;
        this.b = httpRoute;
    }

    public final RequestWrapper getRequest() {
        return this.a;
    }

    public final HttpRoute getRoute() {
        return this.b;
    }
}
