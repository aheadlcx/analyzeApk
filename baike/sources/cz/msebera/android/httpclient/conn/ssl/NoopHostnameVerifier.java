package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.annotation.Immutable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Immutable
public class NoopHostnameVerifier implements HostnameVerifier {
    public static final NoopHostnameVerifier INSTANCE = new NoopHostnameVerifier();

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }

    public final String toString() {
        return "NO_OP";
    }
}
