package com.qiniu.android.http;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import okhttp3.Authenticator;

public final class ProxyConfiguration {
    public final String hostAddress;
    public final String password;
    public final int port;
    public final Type type;
    public final String user;

    public ProxyConfiguration(String str, int i, String str2, String str3, Type type) {
        this.hostAddress = str;
        this.port = i;
        this.user = str2;
        this.password = str3;
        this.type = type;
    }

    public ProxyConfiguration(String str, int i) {
        this(str, i, null, null, Type.HTTP);
    }

    Proxy a() {
        return new Proxy(this.type, new InetSocketAddress(this.hostAddress, this.port));
    }

    Authenticator b() {
        return new k(this);
    }
}
