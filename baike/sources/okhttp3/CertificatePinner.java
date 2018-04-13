package okhttp3;

import com.baidu.mobstat.Config;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new CertificatePinner$Builder().build();
    private final Set<CertificatePinner$a> a;
    @Nullable
    private final CertificateChainCleaner b;

    CertificatePinner(Set<CertificatePinner$a> set, @Nullable CertificateChainCleaner certificateChainCleaner) {
        this.a = set;
        this.b = certificateChainCleaner;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof CertificatePinner) && Util.equal(this.b, ((CertificatePinner) obj).b) && this.a.equals(((CertificatePinner) obj).a);
        return z;
    }

    public int hashCode() {
        return ((this.b != null ? this.b.hashCode() : 0) * 31) + this.a.hashCode();
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List a = a(str);
        if (!a.isEmpty()) {
            X509Certificate x509Certificate;
            int i;
            if (this.b != null) {
                list = this.b.clean(list, str);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                x509Certificate = (X509Certificate) list.get(i2);
                int size2 = a.size();
                int i3 = 0;
                Object obj = null;
                Object obj2 = null;
                while (i3 < size2) {
                    CertificatePinner$a certificatePinner$a = (CertificatePinner$a) a.get(i3);
                    if (certificatePinner$a.c.equals("sha256/")) {
                        if (obj == null) {
                            obj = b(x509Certificate);
                        }
                        if (certificatePinner$a.d.equals(obj)) {
                            return;
                        }
                    } else if (certificatePinner$a.c.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = a(x509Certificate);
                        }
                        if (certificatePinner$a.d.equals(obj2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError("unsupported hashAlgorithm: " + certificatePinner$a.c);
                    }
                    Object obj3 = obj;
                    i3++;
                    obj2 = obj2;
                    obj = obj3;
                }
            }
            StringBuilder append = new StringBuilder().append("Certificate pinning failure!").append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (i = 0; i < size3; i++) {
                x509Certificate = (X509Certificate) list.get(i);
                append.append("\n    ").append(pin(x509Certificate)).append(": ").append(x509Certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(Config.TRACE_TODAY_VISIT_SPLIT);
            size3 = a.size();
            for (i = 0; i < size3; i++) {
                append.append("\n    ").append((CertificatePinner$a) a.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }

    List<CertificatePinner$a> a(String str) {
        List<CertificatePinner$a> emptyList = Collections.emptyList();
        for (CertificatePinner$a certificatePinner$a : this.a) {
            if (certificatePinner$a.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(certificatePinner$a);
            }
        }
        return emptyList;
    }

    CertificatePinner a(@Nullable CertificateChainCleaner certificateChainCleaner) {
        if (Util.equal(this.b, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.a, certificateChainCleaner);
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + b((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static ByteString a(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString b(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }
}
