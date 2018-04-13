package retrofit2;

import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.q;
import okhttp3.s;
import okhttp3.u;
import okhttp3.v;
import okhttp3.v$b;
import okhttp3.v.a;
import okhttp3.y;
import okhttp3.y$a;
import okhttp3.z;
import okio.Buffer;

final class k {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String b;
    private final HttpUrl c;
    @Nullable
    private String d;
    @Nullable
    private Builder e;
    private final y$a f = new y$a();
    @Nullable
    private u g;
    private final boolean h;
    @Nullable
    private a i;
    @Nullable
    private q.a j;
    @Nullable
    private z k;

    k(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable s sVar, @Nullable u uVar, boolean z, boolean z2, boolean z3) {
        this.b = str;
        this.c = httpUrl;
        this.d = str2;
        this.g = uVar;
        this.h = z;
        if (sVar != null) {
            this.f.a(sVar);
        }
        if (z2) {
            this.j = new q.a();
        } else if (z3) {
            this.i = new a();
            this.i.a(v.e);
        }
    }

    void a(Object obj) {
        this.d = obj.toString();
    }

    void a(String str, String str2) {
        if ("Content-Type".equalsIgnoreCase(str)) {
            u a = u.a(str2);
            if (a == null) {
                throw new IllegalArgumentException("Malformed content type: " + str2);
            }
            this.g = a;
            return;
        }
        this.f.b(str, str2);
    }

    void a(String str, String str2, boolean z) {
        if (this.d == null) {
            throw new AssertionError();
        }
        this.d = this.d.replace("{" + str + "}", a(str2, z));
    }

    private static String a(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, i);
                a(buffer, str, i, length, z);
                return buffer.readUtf8();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    private static void a(Buffer buffer, String str, int i, int i2, boolean z) {
        Buffer buffer2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(codePointAt);
                    while (!buffer2.exhausted()) {
                        int readByte = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte(a[(readByte >> 4) & 15]);
                        buffer.writeByte(a[readByte & 15]);
                    }
                } else {
                    buffer.writeUtf8CodePoint(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    void b(String str, @Nullable String str2, boolean z) {
        if (this.d != null) {
            this.e = this.c.d(this.d);
            if (this.e == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.c + ", Relative: " + this.d);
            }
            this.d = null;
        }
        if (z) {
            this.e.b(str, str2);
        } else {
            this.e.a(str, str2);
        }
    }

    void c(String str, String str2, boolean z) {
        if (z) {
            this.j.b(str, str2);
        } else {
            this.j.a(str, str2);
        }
    }

    void a(s sVar, z zVar) {
        this.i.a(sVar, zVar);
    }

    void a(v$b v_b) {
        this.i.a(v_b);
    }

    void a(z zVar) {
        this.k = zVar;
    }

    y a() {
        HttpUrl c;
        Builder builder = this.e;
        if (builder != null) {
            c = builder.c();
        } else {
            c = this.c.c(this.d);
            if (c == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.c + ", Relative: " + this.d);
            }
        }
        z zVar = this.k;
        if (zVar == null) {
            if (this.j != null) {
                zVar = this.j.a();
            } else if (this.i != null) {
                zVar = this.i.a();
            } else if (this.h) {
                zVar = z.create(null, new byte[0]);
            }
        }
        u uVar = this.g;
        if (uVar != null) {
            if (zVar != null) {
                zVar = new k$a(zVar, uVar);
            } else {
                this.f.b("Content-Type", uVar.toString());
            }
        }
        return this.f.a(c).a(this.b, zVar).d();
    }
}
