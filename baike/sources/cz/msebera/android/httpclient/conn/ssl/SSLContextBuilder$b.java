package cz.msebera.android.httpclient.conn.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class SSLContextBuilder$b implements X509TrustManager {
    private final X509TrustManager a;
    private final TrustStrategy b;

    SSLContextBuilder$b(X509TrustManager x509TrustManager, TrustStrategy trustStrategy) {
        this.a = x509TrustManager;
        this.b = trustStrategy;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.a.checkClientTrusted(x509CertificateArr, str);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.b.isTrusted(x509CertificateArr, str)) {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.a.getAcceptedIssuers();
    }
}
