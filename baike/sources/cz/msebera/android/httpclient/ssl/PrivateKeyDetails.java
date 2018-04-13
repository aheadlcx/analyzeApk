package cz.msebera.android.httpclient.ssl;

import cz.msebera.android.httpclient.util.Args;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public final class PrivateKeyDetails {
    private final String a;
    private final X509Certificate[] b;

    public PrivateKeyDetails(String str, X509Certificate[] x509CertificateArr) {
        this.a = (String) Args.notNull(str, "Private key type");
        this.b = x509CertificateArr;
    }

    public String getType() {
        return this.a;
    }

    public X509Certificate[] getCertChain() {
        return this.b;
    }

    public String toString() {
        return this.a + ':' + Arrays.toString(this.b);
    }
}
