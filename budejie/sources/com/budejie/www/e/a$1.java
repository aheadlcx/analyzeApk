package com.budejie.www.e;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class a$1 implements HostnameVerifier {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
