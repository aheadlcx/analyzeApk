package cz.msebera.android.httpclient.conn;

import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.util.Args;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Deprecated
public class HttpInetSocketAddress extends InetSocketAddress {
    private final HttpHost a;

    public HttpInetSocketAddress(HttpHost httpHost, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        Args.notNull(httpHost, "HTTP host");
        this.a = httpHost;
    }

    public HttpHost getHttpHost() {
        return this.a;
    }

    public String toString() {
        return this.a.getHostName() + Config.TRACE_TODAY_VISIT_SPLIT + getPort();
    }
}
