package com.umeng.commonsdk.stateless;

import java.io.IOException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.ssl.X509HostnameVerifier;

class j implements X509HostnameVerifier {
    final /* synthetic */ e a;

    j(e eVar) {
        this.a = eVar;
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }

    public void verify(String str, SSLSocket sSLSocket) throws IOException {
    }

    public void verify(String str, X509Certificate x509Certificate) throws SSLException {
    }

    public void verify(String str, String[] strArr, String[] strArr2) throws SSLException {
    }
}
