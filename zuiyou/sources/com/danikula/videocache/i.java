package com.danikula.videocache;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

class i extends ProxySelector {
    private static final List<Proxy> a = Arrays.asList(new Proxy[]{Proxy.NO_PROXY});
    private final ProxySelector b;
    private final String c;
    private final int d;

    i(ProxySelector proxySelector, String str, int i) {
        this.b = (ProxySelector) k.a((Object) proxySelector);
        this.c = (String) k.a((Object) str);
        this.d = i;
    }

    static void a(String str, int i) {
        ProxySelector.setDefault(new i(ProxySelector.getDefault(), str, i));
    }

    public List<Proxy> select(URI uri) {
        Object obj = (this.c.equals(uri.getHost()) && this.d == uri.getPort()) ? 1 : null;
        return obj != null ? a : this.b.select(uri);
    }

    public void connectFailed(URI uri, SocketAddress socketAddress, IOException iOException) {
        this.b.connectFailed(uri, socketAddress, iOException);
    }
}
