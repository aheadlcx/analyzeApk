package org.apache.commons.httpclient;

import com.facebook.common.util.UriUtil;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

public class HttpHost implements Cloneable {
    private String hostname;
    private int port;
    private Protocol protocol;

    public HttpHost(String str, int i, Protocol protocol) {
        this.hostname = null;
        this.port = -1;
        this.protocol = null;
        if (str == null) {
            throw new IllegalArgumentException("Host name may not be null");
        } else if (protocol == null) {
            throw new IllegalArgumentException("Protocol may not be null");
        } else {
            this.hostname = str;
            this.protocol = protocol;
            if (i >= 0) {
                this.port = i;
            } else {
                this.port = this.protocol.getDefaultPort();
            }
        }
    }

    public HttpHost(String str, int i) {
        this(str, i, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public HttpHost(String str) {
        this(str, -1, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public HttpHost(URI uri) throws URIException {
        this(uri.getHost(), uri.getPort(), Protocol.getProtocol(uri.getScheme()));
    }

    public HttpHost(HttpHost httpHost) {
        this.hostname = null;
        this.port = -1;
        this.protocol = null;
        this.hostname = httpHost.hostname;
        this.port = httpHost.port;
        this.protocol = httpHost.protocol;
    }

    public Object clone() {
        return new HttpHost(this);
    }

    public String getHostName() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public String toURI() {
        StringBuffer stringBuffer = new StringBuffer(50);
        if (this.protocol != null) {
            stringBuffer.append(this.protocol.getScheme());
            stringBuffer.append("://");
        }
        stringBuffer.append(this.hostname);
        if (this.port != this.protocol.getDefaultPort()) {
            stringBuffer.append(':');
            stringBuffer.append(this.port);
        }
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(50);
        stringBuffer.append(toURI());
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpHost)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        HttpHost httpHost = (HttpHost) obj;
        if (!this.hostname.equalsIgnoreCase(httpHost.hostname)) {
            return false;
        }
        if (this.port != httpHost.port) {
            return false;
        }
        if (this.protocol.equals(httpHost.protocol)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.hostname), this.port), this.protocol);
    }
}
