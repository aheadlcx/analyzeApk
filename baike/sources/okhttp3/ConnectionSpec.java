package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.Util;

public final class ConnectionSpec {
    public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
    public static final ConnectionSpec COMPATIBLE_TLS = new Builder(MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
    public static final ConnectionSpec MODERN_TLS = new Builder(true).cipherSuites(e).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
    private static final CipherSuite[] e = new CipherSuite[]{CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    final boolean a;
    final boolean b;
    @Nullable
    final String[] c;
    @Nullable
    final String[] d;

    public static final class Builder {
        boolean a;
        @Nullable
        String[] b;
        @Nullable
        String[] c;
        boolean d;

        Builder(boolean z) {
            this.a = z;
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.a = connectionSpec.a;
            this.b = connectionSpec.c;
            this.c = connectionSpec.d;
            this.d = connectionSpec.b;
        }

        public Builder allEnabledCipherSuites() {
            if (this.a) {
                this.b = null;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(CipherSuite... cipherSuiteArr) {
            if (this.a) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i = 0; i < cipherSuiteArr.length; i++) {
                    strArr[i] = cipherSuiteArr[i].b;
                }
                return cipherSuites(strArr);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.b = (String[]) strArr.clone();
                return this;
            }
        }

        public Builder allEnabledTlsVersions() {
            if (this.a) {
                this.c = null;
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder tlsVersions(TlsVersion... tlsVersionArr) {
            if (this.a) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].a;
                }
                return tlsVersions(strArr);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder tlsVersions(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.c = (String[]) strArr.clone();
                return this;
            }
        }

        public Builder supportsTlsExtensions(boolean z) {
            if (this.a) {
                this.d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this);
        }
    }

    ConnectionSpec(Builder builder) {
        this.a = builder.a;
        this.c = builder.b;
        this.d = builder.c;
        this.b = builder.d;
    }

    public boolean isTls() {
        return this.a;
    }

    @Nullable
    public List<CipherSuite> cipherSuites() {
        return this.c != null ? CipherSuite.a(this.c) : null;
    }

    @Nullable
    public List<TlsVersion> tlsVersions() {
        return this.d != null ? TlsVersion.a(this.d) : null;
    }

    public boolean supportsTlsExtensions() {
        return this.b;
    }

    void a(SSLSocket sSLSocket, boolean z) {
        ConnectionSpec b = b(sSLSocket, z);
        if (b.d != null) {
            sSLSocket.setEnabledProtocols(b.d);
        }
        if (b.c != null) {
            sSLSocket.setEnabledCipherSuites(b.c);
        }
    }

    private ConnectionSpec b(SSLSocket sSLSocket, boolean z) {
        String[] intersect;
        String[] intersect2;
        if (this.c != null) {
            intersect = Util.intersect(CipherSuite.a, sSLSocket.getEnabledCipherSuites(), this.c);
        } else {
            intersect = sSLSocket.getEnabledCipherSuites();
        }
        if (this.d != null) {
            intersect2 = Util.intersect(Util.NATURAL_ORDER, sSLSocket.getEnabledProtocols(), this.d);
        } else {
            intersect2 = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int indexOf = Util.indexOf(CipherSuite.a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && indexOf != -1) {
            intersect = Util.concat(intersect, supportedCipherSuites[indexOf]);
        }
        return new Builder(this).cipherSuites(intersect).tlsVersions(intersect2).build();
    }

    public boolean isCompatible(SSLSocket sSLSocket) {
        if (!this.a) {
            return false;
        }
        if (this.d != null && !Util.nonEmptyIntersection(Util.NATURAL_ORDER, this.d, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.c == null || Util.nonEmptyIntersection(CipherSuite.a, this.c, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec connectionSpec = (ConnectionSpec) obj;
        if (this.a != connectionSpec.a) {
            return false;
        }
        if (!this.a || (Arrays.equals(this.c, connectionSpec.c) && Arrays.equals(this.d, connectionSpec.d) && this.b == connectionSpec.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (!this.a) {
            return 17;
        }
        return (this.b ? 0 : 1) + ((((Arrays.hashCode(this.c) + 527) * 31) + Arrays.hashCode(this.d)) * 31);
    }

    public String toString() {
        if (!this.a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.c != null ? cipherSuites().toString() : "[all enabled]") + ", tlsVersions=" + (this.d != null ? tlsVersions().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.b + ")";
    }
}
