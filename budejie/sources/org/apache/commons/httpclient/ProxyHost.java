package org.apache.commons.httpclient;

import com.facebook.common.util.UriUtil;
import org.apache.commons.httpclient.protocol.Protocol;

public class ProxyHost extends HttpHost {
    public ProxyHost(ProxyHost proxyHost) {
        super((HttpHost) proxyHost);
    }

    public ProxyHost(String str, int i) {
        super(str, i, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public ProxyHost(String str) {
        this(str, -1);
    }

    public Object clone() {
        return new ProxyHost(this);
    }
}
