package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.c;

public final class k {
    public static final k a = new a(true).a(h).a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).a(true).a();
    public static final k b = new a(a).a(TlsVersion.TLS_1_0).a(true).a();
    public static final k c = new a(false).a();
    private static final h[] h = new h[]{h.aX, h.bb, h.aY, h.bc, h.bi, h.bh, h.ay, h.aI, h.az, h.aJ, h.ag, h.ah, h.E, h.I, h.i};
    final boolean d;
    final boolean e;
    @Nullable
    final String[] f;
    @Nullable
    final String[] g;

    public static final class a {
        boolean a;
        @Nullable
        String[] b;
        @Nullable
        String[] c;
        boolean d;

        a(boolean z) {
            this.a = z;
        }

        public a(k kVar) {
            this.a = kVar.d;
            this.b = kVar.f;
            this.c = kVar.g;
            this.d = kVar.e;
        }

        public a a(h... hVarArr) {
            if (this.a) {
                String[] strArr = new String[hVarArr.length];
                for (int i = 0; i < hVarArr.length; i++) {
                    strArr[i] = hVarArr[i].bj;
                }
                return a(strArr);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.b = (String[]) strArr.clone();
                return this;
            }
        }

        public a a(TlsVersion... tlsVersionArr) {
            if (this.a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                return b(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a b(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.c = (String[]) strArr.clone();
                return this;
            }
        }

        public a a(boolean z) {
            if (this.a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public k a() {
            return new k(this);
        }
    }

    k(a aVar) {
        this.d = aVar.a;
        this.f = aVar.b;
        this.g = aVar.c;
        this.e = aVar.d;
    }

    public boolean a() {
        return this.d;
    }

    @Nullable
    public List<h> b() {
        return this.f != null ? h.a(this.f) : null;
    }

    @Nullable
    public List<TlsVersion> c() {
        return this.g != null ? TlsVersion.forJavaNames(this.g) : null;
    }

    public boolean d() {
        return this.e;
    }

    void a(SSLSocket sSLSocket, boolean z) {
        k b = b(sSLSocket, z);
        if (b.g != null) {
            sSLSocket.setEnabledProtocols(b.g);
        }
        if (b.f != null) {
            sSLSocket.setEnabledCipherSuites(b.f);
        }
    }

    private k b(SSLSocket sSLSocket, boolean z) {
        String[] a;
        String[] a2;
        if (this.f != null) {
            a = c.a(h.a, sSLSocket.getEnabledCipherSuites(), this.f);
        } else {
            a = sSLSocket.getEnabledCipherSuites();
        }
        if (this.g != null) {
            a2 = c.a(c.g, sSLSocket.getEnabledProtocols(), this.g);
        } else {
            a2 = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int a3 = c.a(h.a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && a3 != -1) {
            a = c.a(a, supportedCipherSuites[a3]);
        }
        return new a(this).a(a).b(a2).a();
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.d) {
            return false;
        }
        if (this.g != null && !c.b(c.g, this.g, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.f == null || c.b(h.a, this.f, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        if (this.d != kVar.d) {
            return false;
        }
        if (!this.d || (Arrays.equals(this.f, kVar.f) && Arrays.equals(this.g, kVar.g) && this.e == kVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.d) {
            return 17;
        }
        return (this.e ? 0 : 1) + ((((Arrays.hashCode(this.f) + 527) * 31) + Arrays.hashCode(this.g)) * 31);
    }

    public String toString() {
        if (!this.d) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.g != null ? c().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.e + ")";
    }
}
