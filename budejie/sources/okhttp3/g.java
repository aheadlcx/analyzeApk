package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.c;
import okio.ByteString;

public final class g {
    public static final g a = new a().a();
    private final Set<b> b;
    private final okhttp3.internal.f.b c;

    public static final class a {
        private final List<b> a = new ArrayList();

        public g a() {
            return new g(new LinkedHashSet(this.a), null);
        }
    }

    static final class b {
        final String a;
        final String b;
        final String c;
        final ByteString d;

        boolean a(String str) {
            if (!this.a.startsWith("*.")) {
                return str.equals(this.b);
            }
            return str.regionMatches(false, str.indexOf(46) + 1, this.b, 0, this.b.length());
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && this.a.equals(((b) obj).a) && this.c.equals(((b) obj).c) && this.d.equals(((b) obj).d);
        }

        public int hashCode() {
            return ((((this.a.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public String toString() {
            return this.c + this.d.base64();
        }
    }

    g(Set<b> set, okhttp3.internal.f.b bVar) {
        this.b = set;
        this.c = bVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof g) && c.a(this.c, ((g) obj).c) && this.b.equals(((g) obj).b);
        return z;
    }

    public int hashCode() {
        return ((this.c != null ? this.c.hashCode() : 0) * 31) + this.b.hashCode();
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
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
                    b bVar = (b) a.get(i3);
                    if (bVar.c.equals("sha256/")) {
                        if (obj == null) {
                            obj = b(x509Certificate);
                        }
                        if (bVar.d.equals(obj)) {
                            return;
                        }
                    } else if (bVar.c.equals("sha1/")) {
                        if (obj2 == null) {
                            obj2 = a(x509Certificate);
                        }
                        if (bVar.d.equals(obj2)) {
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
                append.append("\n    ").append((b) a.get(i));
            }
            throw new SSLPeerUnverifiedException(append.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, Arrays.asList(certificateArr));
    }

    List<b> a(String str) {
        List<b> emptyList = Collections.emptyList();
        for (b bVar : this.b) {
            if (bVar.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList();
                }
                emptyList.add(bVar);
            }
        }
        return emptyList;
    }

    g a(okhttp3.internal.f.b bVar) {
        return c.a(this.c, (Object) bVar) ? this : new g(this.b, bVar);
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
