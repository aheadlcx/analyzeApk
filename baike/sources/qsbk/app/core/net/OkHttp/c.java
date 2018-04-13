package qsbk.app.core.net.OkHttp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class c implements HostnameVerifier {
    c() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
