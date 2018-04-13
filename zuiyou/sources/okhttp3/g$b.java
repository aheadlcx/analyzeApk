package okhttp3;

import okio.ByteString;

final class g$b {
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
        return (obj instanceof g$b) && this.a.equals(((g$b) obj).a) && this.c.equals(((g$b) obj).c) && this.d.equals(((g$b) obj).d);
    }

    public int hashCode() {
        return ((((this.a.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    public String toString() {
        return this.c + this.d.base64();
    }
}
