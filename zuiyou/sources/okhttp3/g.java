package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.c;
import okhttp3.internal.f.b;
import okio.ByteString;

public final class g {
    public static final g a = new g$a().a();
    private final Set<g$b> b;
    @Nullable
    private final b c;

    g(Set<g$b> set, @Nullable b bVar) {
        this.b = set;
        this.c = bVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof g) && c.a(this.c, ((g) obj).c) && this.b.equals(((g) obj).b);
        return z;
    }

    public int hashCode() {
        return ((this.c != null ? this.c.hashCode() : 0) * 31) + this.b.hashCode();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List a = a(str);
        if (!a.isEmpty()) {
            int i;
            if (this.c != null) {
                list = this.c.a(list, str);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i2);
                int size2 = a.size();
                int i3 = 0;
                Object obj = null;
                Object obj2 = null;
                while (i3 < size2) {
                    g$b g_b = (g$b) a.get(i3);
                    if (g_b.c.equals("sha256/")) {
                        if (obj == null) {
                            obj = b(x509Certificate);
                        }
                        if (g_b.d.equals(obj)) {
                            return;
                        }
                    } else if (g_b.c.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = a(x509Certificate);
                        }
                        if (g_b.d.equals(obj2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError();
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
                Certificate certificate = (X509Certificate) list.get(i);
                append.append("\n    ").append(a(certificate)).append(": ").append(certificate.getSubjectDN().getName());
            }
            append.append("\n  Pinned certificates for ").append(str).append(":");
            size3 = a.size();
            for (i = 0; i < size3; i++) {
                append.append("\n    ").append((g$b) a.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    List<g$b> a(String str) {
        List<g$b> emptyList = Collections.emptyList();
        for (g$b g_b : this.b) {
            if (g_b.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(g_b);
            }
        }
        return emptyList;
    }

    g a(b bVar) {
        if (c.a(this.c, bVar)) {
            return this;
        }
        return new g(this.b, bVar);
    }

    public static String a(Certificate certificate) {
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
