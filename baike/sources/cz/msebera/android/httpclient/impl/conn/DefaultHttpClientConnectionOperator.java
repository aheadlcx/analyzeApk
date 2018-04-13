package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.conn.DnsResolver;
import cz.msebera.android.httpclient.conn.HttpClientConnectionOperator;
import cz.msebera.android.httpclient.conn.ManagedHttpClientConnection;
import cz.msebera.android.httpclient.conn.SchemePortResolver;
import cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
import cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory;
import cz.msebera.android.httpclient.conn.socket.LayeredConnectionSocketFactory;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

@Immutable
public class DefaultHttpClientConnectionOperator implements HttpClientConnectionOperator {
    private final Lookup<ConnectionSocketFactory> a;
    private final SchemePortResolver b;
    private final DnsResolver c;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public DefaultHttpClientConnectionOperator(Lookup<ConnectionSocketFactory> lookup, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        Args.notNull(lookup, "Socket factory registry");
        this.a = lookup;
        if (schemePortResolver == null) {
            schemePortResolver = DefaultSchemePortResolver.INSTANCE;
        }
        this.b = schemePortResolver;
        if (dnsResolver == null) {
            dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        }
        this.c = dnsResolver;
    }

    private Lookup<ConnectionSocketFactory> a(HttpContext httpContext) {
        Lookup<ConnectionSocketFactory> lookup = (Lookup) httpContext.getAttribute(ClientContext.SOCKET_FACTORY_REGISTRY);
        if (lookup == null) {
            return this.a;
        }
        return lookup;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(cz.msebera.android.httpclient.conn.ManagedHttpClientConnection r13, cz.msebera.android.httpclient.HttpHost r14, java.net.InetSocketAddress r15, int r16, cz.msebera.android.httpclient.config.SocketConfig r17, cz.msebera.android.httpclient.protocol.HttpContext r18) throws java.io.IOException {
        /*
        r12 = this;
        r0 = r18;
        r1 = r12.a(r0);
        r2 = r14.getSchemeName();
        r1 = r1.lookup(r2);
        r1 = (cz.msebera.android.httpclient.conn.socket.ConnectionSocketFactory) r1;
        if (r1 != 0) goto L_0x002f;
    L_0x0012:
        r1 = new cz.msebera.android.httpclient.conn.UnsupportedSchemeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r14.getSchemeName();
        r2 = r2.append(r3);
        r3 = " protocol is not supported";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x002f:
        r2 = r14.getAddress();
        if (r2 == 0) goto L_0x00d6;
    L_0x0035:
        r2 = 1;
        r2 = new java.net.InetAddress[r2];
        r3 = 0;
        r4 = r14.getAddress();
        r2[r3] = r4;
        r8 = r2;
    L_0x0040:
        r2 = r12.b;
        r11 = r2.resolve(r14);
        r2 = 0;
        r9 = r2;
    L_0x0048:
        r2 = r8.length;
        if (r9 >= r2) goto L_0x00d5;
    L_0x004b:
        r4 = r8[r9];
        r2 = r8.length;
        r2 = r2 + -1;
        if (r9 != r2) goto L_0x00e3;
    L_0x0052:
        r2 = 1;
        r10 = r2;
    L_0x0054:
        r0 = r18;
        r3 = r1.createSocket(r0);
        r2 = r17.getSoTimeout();
        r3.setSoTimeout(r2);
        r2 = r17.isSoReuseAddress();
        r3.setReuseAddress(r2);
        r2 = r17.isTcpNoDelay();
        r3.setTcpNoDelay(r2);
        r2 = r17.isSoKeepAlive();
        r3.setKeepAlive(r2);
        r2 = r17.getSoLinger();
        if (r2 < 0) goto L_0x0080;
    L_0x007c:
        r5 = 1;
        r3.setSoLinger(r5, r2);
    L_0x0080:
        r13.bind(r3);
        r5 = new java.net.InetSocketAddress;
        r5.<init>(r4, r11);
        r2 = r12.log;
        r2 = r2.isDebugEnabled();
        if (r2 == 0) goto L_0x00a8;
    L_0x0090:
        r2 = r12.log;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "Connecting to ";
        r4 = r4.append(r6);
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2.debug(r4);
    L_0x00a8:
        r2 = r16;
        r4 = r14;
        r6 = r15;
        r7 = r18;
        r2 = r1.connectSocket(r2, r3, r4, r5, r6, r7);	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r13.bind(r2);	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r2 = r12.log;	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r2 = r2.isDebugEnabled();	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        if (r2 == 0) goto L_0x00d5;
    L_0x00bd:
        r2 = r12.log;	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r3.<init>();	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r4 = "Connection established ";
        r3 = r3.append(r4);	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r3 = r3.append(r13);	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r3 = r3.toString();	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
        r2.debug(r3);	 Catch:{ SocketTimeoutException -> 0x00e7, ConnectException -> 0x00f0, NoRouteToHostException -> 0x010b }
    L_0x00d5:
        return;
    L_0x00d6:
        r2 = r12.c;
        r3 = r14.getHostName();
        r2 = r2.resolve(r3);
        r8 = r2;
        goto L_0x0040;
    L_0x00e3:
        r2 = 0;
        r10 = r2;
        goto L_0x0054;
    L_0x00e7:
        r2 = move-exception;
        if (r10 == 0) goto L_0x010f;
    L_0x00ea:
        r1 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException;
        r1.<init>(r2, r14, r8);
        throw r1;
    L_0x00f0:
        r2 = move-exception;
        if (r10 == 0) goto L_0x010f;
    L_0x00f3:
        r1 = r2.getMessage();
        r3 = "Connection timed out";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x0105;
    L_0x00ff:
        r1 = new cz.msebera.android.httpclient.conn.ConnectTimeoutException;
        r1.<init>(r2, r14, r8);
        throw r1;
    L_0x0105:
        r1 = new cz.msebera.android.httpclient.conn.HttpHostConnectException;
        r1.<init>(r2, r14, r8);
        throw r1;
    L_0x010b:
        r2 = move-exception;
        if (r10 == 0) goto L_0x010f;
    L_0x010e:
        throw r2;
    L_0x010f:
        r2 = r12.log;
        r2 = r2.isDebugEnabled();
        if (r2 == 0) goto L_0x013b;
    L_0x0117:
        r2 = r12.log;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Connect to ";
        r3 = r3.append(r4);
        r3 = r3.append(r5);
        r4 = " timed out. ";
        r3 = r3.append(r4);
        r4 = "Connection will be retried using another IP address";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.debug(r3);
    L_0x013b:
        r2 = r9 + 1;
        r9 = r2;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.DefaultHttpClientConnectionOperator.connect(cz.msebera.android.httpclient.conn.ManagedHttpClientConnection, cz.msebera.android.httpclient.HttpHost, java.net.InetSocketAddress, int, cz.msebera.android.httpclient.config.SocketConfig, cz.msebera.android.httpclient.protocol.HttpContext):void");
    }

    public void upgrade(ManagedHttpClientConnection managedHttpClientConnection, HttpHost httpHost, HttpContext httpContext) throws IOException {
        ConnectionSocketFactory connectionSocketFactory = (ConnectionSocketFactory) a(HttpClientContext.adapt(httpContext)).lookup(httpHost.getSchemeName());
        if (connectionSocketFactory == null) {
            throw new UnsupportedSchemeException(httpHost.getSchemeName() + " protocol is not supported");
        } else if (connectionSocketFactory instanceof LayeredConnectionSocketFactory) {
            managedHttpClientConnection.bind(((LayeredConnectionSocketFactory) connectionSocketFactory).createLayeredSocket(managedHttpClientConnection.getSocket(), httpHost.getHostName(), this.b.resolve(httpHost), httpContext));
        } else {
            throw new UnsupportedSchemeException(httpHost.getSchemeName() + " protocol does not support connection upgrade");
        }
    }
}
