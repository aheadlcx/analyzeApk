package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;
import java.net.InetAddress;

@NotThreadSafe
@Deprecated
public class ConnRouteParamBean extends HttpAbstractParamBean {
    public ConnRouteParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setDefaultProxy(HttpHost httpHost) {
        this.a.setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);
    }

    public void setLocalAddress(InetAddress inetAddress) {
        this.a.setParameter(ConnRoutePNames.LOCAL_ADDRESS, inetAddress);
    }

    public void setForcedRoute(HttpRoute httpRoute) {
        this.a.setParameter(ConnRoutePNames.FORCED_ROUTE, httpRoute);
    }
}
