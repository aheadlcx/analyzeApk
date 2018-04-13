package com.umeng.commonsdk.statistics.idtracking;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;

class t implements HostnameVerifier {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return new BrowserCompatHostnameVerifier().verify("cmnsguider.yunos.com", sSLSession);
    }
}
