package okhttp3;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.c;

public final class r {
    private final TlsVersion a;
    private final h b;
    private final List<Certificate> c;
    private final List<Certificate> d;

    private r(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.a = tlsVersion;
        this.b = hVar;
        this.c = list;
        this.d = list2;
    }

    public static r a(SSLSession sSLSession) {
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        h a = h.a(cipherSuite);
        cipherSuite = sSLSession.getProtocol();
        if (cipherSuite == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        Object[] peerCertificates;
        List a2;
        List a3;
        TlsVersion forJavaName = TlsVersion.forJavaName(cipherSuite);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            peerCertificates = null;
        }
        if (peerCertificates != null) {
            a2 = c.a(peerCertificates);
        } else {
            a2 = Collections.emptyList();
        }
        Object[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            a3 = c.a(localCertificates);
        } else {
            a3 = Collections.emptyList();
        }
        return new r(forJavaName, a, a2, a3);
    }

    public h a() {
        return this.b;
    }

    public List<Certificate> b() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (this.a.equals(rVar.a) && this.b.equals(rVar.b) && this.c.equals(rVar.c) && this.d.equals(rVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.a.hashCode() + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
