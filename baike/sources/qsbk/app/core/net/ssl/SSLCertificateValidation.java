package qsbk.app.core.net.ssl;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class SSLCertificateValidation {
    public static void trustAllCertificate() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new TrustAnyX509TrustManager()}, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
