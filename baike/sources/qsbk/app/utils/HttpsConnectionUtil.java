package qsbk.app.utils;

import android.os.Build.VERSION;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import qsbk.app.http.TLSSocketFactory;

public final class HttpsConnectionUtil {
    private static HostnameVerifier a = new a();
    private static SSLContext b;
    private static X509TrustManager c;

    private static class a implements HostnameVerifier {
        private a() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    private static class b implements X509TrustManager {
        private b() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    static {
        try {
            b = SSLContext.getInstance("TLS");
            c = new b();
            b.init(null, new TrustManager[]{c}, null);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static HostnameVerifier getHostnameVerifier() {
        return a;
    }

    public static X509TrustManager getX509TrustManager() {
        return c;
    }

    public static void setAllTrust() {
        a();
        b();
    }

    public static void setAllTrust(HttpsURLConnection httpsURLConnection) {
        if (httpsURLConnection != null) {
            httpsURLConnection.setHostnameVerifier(a);
            httpsURLConnection.setSSLSocketFactory(getSslSocketFactory());
        }
    }

    public static SSLSocketFactory getSslSocketFactory() {
        return VERSION.SDK_INT <= 19 ? new TLSSocketFactory(b.getSocketFactory()) : b.getSocketFactory();
    }

    private static void a() {
        HttpsURLConnection.setDefaultHostnameVerifier(a);
    }

    private static void b() {
        if (b != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(getSslSocketFactory());
        }
    }
}
