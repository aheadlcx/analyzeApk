package com.alibaba.sdk.android.httpdns;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

class o implements HostnameVerifier {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        f.d("Https request, set hostnameVerifier");
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("203.107.1.1", sSLSession);
    }
}
