package com.loc;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

final class do implements HostnameVerifier {
    final /* synthetic */ bl a;

    do(bl blVar) {
        this.a = blVar;
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        return defaultHostnameVerifier.verify("*.amap.com", sSLSession) || defaultHostnameVerifier.verify("*.apilocate.amap.com", sSLSession);
    }
}
