package okhttp3.internal.f;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.e.e;

public abstract class b {
    public abstract List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    public static b a(X509TrustManager x509TrustManager) {
        return e.b().a(x509TrustManager);
    }
}
