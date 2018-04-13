package okhttp3;

import okio.ByteString;

final class CertificatePinner$a {
    final String a;
    final String b;
    final String c;
    final ByteString d;

    CertificatePinner$a(String str, String str2) {
        String host;
        this.a = str;
        if (str.startsWith("*.")) {
            host = HttpUrl.parse("http://" + str.substring("*.".length())).host();
        } else {
            host = HttpUrl.parse("http://" + str).host();
        }
        this.b = host;
        if (str2.startsWith("sha1/")) {
            this.c = "sha1/";
            this.d = ByteString.decodeBase64(str2.substring("sha1/".length()));
        } else if (str2.startsWith("sha256/")) {
            this.c = "sha256/";
            this.d = ByteString.decodeBase64(str2.substring("sha256/".length()));
        } else {
            throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
        }
        if (this.d == null) {
            throw new IllegalArgumentException("pins must be base64: " + str2);
        }
    }

    boolean a(String str) {
        if (!this.a.startsWith("*.")) {
            return str.equals(this.b);
        }
        int indexOf = str.indexOf(46);
        if ((str.length() - indexOf) - 1 != this.b.length()) {
            return false;
        }
        if (str.regionMatches(false, indexOf + 1, this.b, 0, this.b.length())) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        return (obj instanceof CertificatePinner$a) && this.a.equals(((CertificatePinner$a) obj).a) && this.c.equals(((CertificatePinner$a) obj).c) && this.d.equals(((CertificatePinner$a) obj).d);
    }

    public int hashCode() {
        return ((((this.a.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        return this.c + this.d.base64();
    }
}
