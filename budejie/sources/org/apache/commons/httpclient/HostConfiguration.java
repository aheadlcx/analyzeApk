package org.apache.commons.httpclient;

import com.facebook.common.util.UriUtil;
import java.net.InetAddress;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HostConfiguration implements Cloneable {
    public static final HostConfiguration ANY_HOST_CONFIGURATION = new HostConfiguration();
    private HttpHost host = null;
    private InetAddress localAddress = null;
    private HostParams params = new HostParams();
    private ProxyHost proxyHost = null;

    public HostConfiguration(HostConfiguration hostConfiguration) {
        synchronized (hostConfiguration) {
            try {
                if (hostConfiguration.host != null) {
                    this.host = (HttpHost) hostConfiguration.host.clone();
                } else {
                    this.host = null;
                }
                if (hostConfiguration.proxyHost != null) {
                    this.proxyHost = (ProxyHost) hostConfiguration.proxyHost.clone();
                } else {
                    this.proxyHost = null;
                }
                this.localAddress = hostConfiguration.getLocalAddress();
                this.params = (HostParams) hostConfiguration.getParams().clone();
            } catch (CloneNotSupportedException e) {
                throw new IllegalArgumentException("Host configuration could not be cloned");
            }
        }
    }

    public Object clone() {
        return new HostConfiguration(this);
    }

    public synchronized String toString() {
        StringBuffer stringBuffer;
        Object obj = null;
        stringBuffer = new StringBuffer(50);
        stringBuffer.append("HostConfiguration[");
        if (this.host != null) {
            stringBuffer.append("host=").append(this.host);
            obj = 1;
        }
        if (this.proxyHost != null) {
            if (obj != null) {
                stringBuffer.append(", ");
            } else {
                int i = 1;
            }
            stringBuffer.append("proxyHost=").append(this.proxyHost);
        }
        if (this.localAddress != null) {
            if (obj != null) {
                stringBuffer.append(", ");
            } else {
                i = 1;
            }
            stringBuffer.append("localAddress=").append(this.localAddress);
            if (obj != null) {
                stringBuffer.append(", ");
            }
            stringBuffer.append("params=").append(this.params);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean hostEquals(org.apache.commons.httpclient.HttpConnection r4) {
        /*
        r3 = this;
        r0 = 0;
        monitor-enter(r3);
        if (r4 != 0) goto L_0x000f;
    L_0x0004:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x000c }
        r1 = "Connection may not be null";
        r0.<init>(r1);	 Catch:{ all -> 0x000c }
        throw r0;	 Catch:{ all -> 0x000c }
    L_0x000c:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x000f:
        r1 = r3.host;	 Catch:{ all -> 0x000c }
        if (r1 == 0) goto L_0x0023;
    L_0x0013:
        r1 = r3.host;	 Catch:{ all -> 0x000c }
        r1 = r1.getHostName();	 Catch:{ all -> 0x000c }
        r2 = r4.getHost();	 Catch:{ all -> 0x000c }
        r1 = r1.equalsIgnoreCase(r2);	 Catch:{ all -> 0x000c }
        if (r1 != 0) goto L_0x0025;
    L_0x0023:
        monitor-exit(r3);
        return r0;
    L_0x0025:
        r1 = r3.host;	 Catch:{ all -> 0x000c }
        r1 = r1.getPort();	 Catch:{ all -> 0x000c }
        r2 = r4.getPort();	 Catch:{ all -> 0x000c }
        if (r1 != r2) goto L_0x0023;
    L_0x0031:
        r1 = r3.host;	 Catch:{ all -> 0x000c }
        r1 = r1.getProtocol();	 Catch:{ all -> 0x000c }
        r2 = r4.getProtocol();	 Catch:{ all -> 0x000c }
        r1 = r1.equals(r2);	 Catch:{ all -> 0x000c }
        if (r1 == 0) goto L_0x0023;
    L_0x0041:
        r1 = r3.localAddress;	 Catch:{ all -> 0x000c }
        if (r1 == 0) goto L_0x0053;
    L_0x0045:
        r1 = r3.localAddress;	 Catch:{ all -> 0x000c }
        r2 = r4.getLocalAddress();	 Catch:{ all -> 0x000c }
        r1 = r1.equals(r2);	 Catch:{ all -> 0x000c }
        if (r1 == 0) goto L_0x0023;
    L_0x0051:
        r0 = 1;
        goto L_0x0023;
    L_0x0053:
        r1 = r4.getLocalAddress();	 Catch:{ all -> 0x000c }
        if (r1 == 0) goto L_0x0051;
    L_0x0059:
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.HostConfiguration.hostEquals(org.apache.commons.httpclient.HttpConnection):boolean");
    }

    public synchronized boolean proxyEquals(HttpConnection httpConnection) {
        boolean z = true;
        synchronized (this) {
            if (httpConnection == null) {
                throw new IllegalArgumentException("Connection may not be null");
            }
            if (this.proxyHost != null) {
                if (!(this.proxyHost.getHostName().equalsIgnoreCase(httpConnection.getProxyHost()) && this.proxyHost.getPort() == httpConnection.getProxyPort())) {
                    z = false;
                }
            } else if (httpConnection.getProxyHost() != null) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean isHostSet() {
        return this.host != null;
    }

    public synchronized void setHost(HttpHost httpHost) {
        this.host = httpHost;
    }

    public synchronized void setHost(String str, int i, String str2) {
        this.host = new HttpHost(str, i, Protocol.getProtocol(str2));
    }

    public synchronized void setHost(String str, String str2, int i, Protocol protocol) {
        setHost(str, i, protocol);
        this.params.setVirtualHost(str2);
    }

    public synchronized void setHost(String str, int i, Protocol protocol) {
        if (str == null) {
            throw new IllegalArgumentException("host must not be null");
        } else if (protocol == null) {
            throw new IllegalArgumentException("protocol must not be null");
        } else {
            this.host = new HttpHost(str, i, protocol);
        }
    }

    public synchronized void setHost(String str, int i) {
        setHost(str, i, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public synchronized void setHost(String str) {
        Protocol protocol = Protocol.getProtocol(UriUtil.HTTP_SCHEME);
        setHost(str, protocol.getDefaultPort(), protocol);
    }

    public synchronized void setHost(URI uri) {
        try {
            setHost(uri.getHost(), uri.getPort(), uri.getScheme());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public synchronized String getHostURL() {
        if (this.host == null) {
            throw new IllegalStateException("Host must be set to create a host URL");
        }
        return this.host.toURI();
    }

    public synchronized String getHost() {
        String hostName;
        if (this.host != null) {
            hostName = this.host.getHostName();
        } else {
            hostName = null;
        }
        return hostName;
    }

    public synchronized String getVirtualHost() {
        return this.params.getVirtualHost();
    }

    public synchronized int getPort() {
        int port;
        if (this.host != null) {
            port = this.host.getPort();
        } else {
            port = -1;
        }
        return port;
    }

    public synchronized Protocol getProtocol() {
        Protocol protocol;
        if (this.host != null) {
            protocol = this.host.getProtocol();
        } else {
            protocol = null;
        }
        return protocol;
    }

    public synchronized boolean isProxySet() {
        return this.proxyHost != null;
    }

    public synchronized void setProxyHost(ProxyHost proxyHost) {
        this.proxyHost = proxyHost;
    }

    public synchronized void setProxy(String str, int i) {
        this.proxyHost = new ProxyHost(str, i);
    }

    public synchronized String getProxyHost() {
        String hostName;
        if (this.proxyHost != null) {
            hostName = this.proxyHost.getHostName();
        } else {
            hostName = null;
        }
        return hostName;
    }

    public synchronized int getProxyPort() {
        int port;
        if (this.proxyHost != null) {
            port = this.proxyHost.getPort();
        } else {
            port = -1;
        }
        return port;
    }

    public synchronized void setLocalAddress(InetAddress inetAddress) {
        this.localAddress = inetAddress;
    }

    public synchronized InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public HostParams getParams() {
        return this.params;
    }

    public void setParams(HostParams hostParams) {
        if (hostParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = hostParams;
    }

    public synchronized boolean equals(Object obj) {
        boolean z = true;
        synchronized (this) {
            if (!(obj instanceof HostConfiguration)) {
                z = false;
            } else if (obj != this) {
                HostConfiguration hostConfiguration = (HostConfiguration) obj;
                if (!(LangUtils.equals(this.host, hostConfiguration.host) && LangUtils.equals(this.proxyHost, hostConfiguration.proxyHost) && LangUtils.equals(this.localAddress, hostConfiguration.localAddress))) {
                    z = false;
                }
            }
        }
        return z;
    }

    public synchronized int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.proxyHost), this.localAddress);
    }
}
