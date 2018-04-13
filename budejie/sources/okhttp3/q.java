package okhttp3;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.c;

public final class q {
    private final TlsVersion a;
    private final h b;
    private final List<Certificate> c;
    private final List<Certificate> d;

    private q(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.a = tlsVersion;
        this.b = hVar;
        this.c = list;
        this.d = list2;
    }

    public static q a(SSLSession sSLSession) {
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
        return new q(forJavaName, a, a2, a3);
    }

    public static q a(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        if (hVar != null) {
            return new q(tlsVersion, hVar, c.a((List) list), c.a((List) list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }

    public TlsVersion a() {
        return this.a;
    }

    public h b() {
        return this.b;
    }

    public List<Certificate> c() {
        return this.c;
    }

    public List<Certificate> d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        if (c.a(this.b, qVar.b) && this.b.equals(qVar.b) && this.c.equals(qVar.c) && this.d.equals(qVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a != null ? this.a.hashCode() : 0) + 527) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
