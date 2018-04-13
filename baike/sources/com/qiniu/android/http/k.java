package com.qiniu.android.http;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

class k implements Authenticator {
    final /* synthetic */ ProxyConfiguration a;

    k(ProxyConfiguration proxyConfiguration) {
        this.a = proxyConfiguration;
    }

    public Request authenticate(Route route, Response response) throws IOException {
        return response.request().newBuilder().header("Proxy-Authorization", Credentials.basic(this.a.user, this.a.password)).header("Proxy-Connection", HTTP.CONN_KEEP_ALIVE).build();
    }
}
