package com.sprite.ads.internal.imageCache;

import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class miTM implements TrustManager, X509TrustManager {
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public boolean isClientTrusted(X509Certificate[] x509CertificateArr) {
        return true;
    }

    public boolean isServerTrusted(X509Certificate[] x509CertificateArr) {
        return true;
    }
}
