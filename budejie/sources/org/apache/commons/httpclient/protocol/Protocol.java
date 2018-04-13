package org.apache.commons.httpclient.protocol;

import com.facebook.common.util.UriUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.util.LangUtils;

public class Protocol {
    private static final Map PROTOCOLS = Collections.synchronizedMap(new HashMap());
    private int defaultPort;
    private String scheme;
    private boolean secure;
    private ProtocolSocketFactory socketFactory;

    public static void registerProtocol(String str, Protocol protocol) {
        if (str == null) {
            throw new IllegalArgumentException("id is null");
        } else if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        } else {
            PROTOCOLS.put(str, protocol);
        }
    }

    public static void unregisterProtocol(String str) {
        if (str == null) {
            throw new IllegalArgumentException("id is null");
        }
        PROTOCOLS.remove(str);
    }

    public static Protocol getProtocol(String str) throws IllegalStateException {
        if (str == null) {
            throw new IllegalArgumentException("id is null");
        }
        Protocol protocol = (Protocol) PROTOCOLS.get(str);
        if (protocol == null) {
            return lazyRegisterProtocol(str);
        }
        return protocol;
    }

    private static Protocol lazyRegisterProtocol(String str) throws IllegalStateException {
        Protocol protocol;
        if (UriUtil.HTTP_SCHEME.equals(str)) {
            protocol = new Protocol(UriUtil.HTTP_SCHEME, DefaultProtocolSocketFactory.getSocketFactory(), 80);
            registerProtocol(UriUtil.HTTP_SCHEME, protocol);
            return protocol;
        } else if ("https".equals(str)) {
            protocol = new Protocol("https", SSLProtocolSocketFactory.getSocketFactory(), 443);
            registerProtocol("https", protocol);
            return protocol;
        } else {
            throw new IllegalStateException(new StringBuffer().append("unsupported protocol: '").append(str).append("'").toString());
        }
    }

    public Protocol(String str, ProtocolSocketFactory protocolSocketFactory, int i) {
        if (str == null) {
            throw new IllegalArgumentException("scheme is null");
        } else if (protocolSocketFactory == null) {
            throw new IllegalArgumentException("socketFactory is null");
        } else if (i <= 0) {
            throw new IllegalArgumentException(new StringBuffer().append("port is invalid: ").append(i).toString());
        } else {
            this.scheme = str;
            this.socketFactory = protocolSocketFactory;
            this.defaultPort = i;
            this.secure = protocolSocketFactory instanceof SecureProtocolSocketFactory;
        }
    }

    public Protocol(String str, SecureProtocolSocketFactory secureProtocolSocketFactory, int i) {
        this(str, (ProtocolSocketFactory) secureProtocolSocketFactory, i);
    }

    public int getDefaultPort() {
        return this.defaultPort;
    }

    public ProtocolSocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public String getScheme() {
        return this.scheme;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public int resolvePort(int i) {
        return i <= 0 ? getDefaultPort() : i;
    }

    public String toString() {
        return new StringBuffer().append(this.scheme).append(":").append(this.defaultPort).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Protocol)) {
            return false;
        }
        Protocol protocol = (Protocol) obj;
        if (this.defaultPort == protocol.getDefaultPort() && this.scheme.equalsIgnoreCase(protocol.getScheme()) && this.secure == protocol.isSecure() && this.socketFactory.equals(protocol.getSocketFactory())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), this.scheme.toLowerCase()), this.secure), this.socketFactory);
    }
}
