package retrofit2;

import com.alipay.sdk.util.h;
import java.io.IOException;
import okhttp3.r;
import okhttp3.s;
import okhttp3.u;
import okhttp3.v;
import okhttp3.v.b;
import okhttp3.y;
import okhttp3.z;
import okio.c;
import okio.d;
import org.apache.http.entity.mime.MIME;

final class k {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String b;
    private final s c;
    private String d;
    private okhttp3.s.a e;
    private final okhttp3.y.a f = new okhttp3.y.a();
    private u g;
    private final boolean h;
    private okhttp3.v.a i;
    private okhttp3.p.a j;
    private z k;

    private static class a extends z {
        private final z a;
        private final u b;

        a(z zVar, u uVar) {
            this.a = zVar;
            this.b = uVar;
        }

        public u a() {
            return this.b;
        }

        public long b() throws IOException {
            return this.a.b();
        }

        public void a(d dVar) throws IOException {
            this.a.a(dVar);
        }
    }

    k(String str, s sVar, String str2, r rVar, u uVar, boolean z, boolean z2, boolean z3) {
        this.b = str;
        this.c = sVar;
        this.d = str2;
        this.g = uVar;
        this.h = z;
        if (rVar != null) {
            this.f.a(rVar);
        }
        if (z2) {
            this.j = new okhttp3.p.a();
        } else if (z3) {
            this.i = new okhttp3.v.a();
            this.i.a(v.e);
        }
    }

    void a(Object obj) {
        if (obj == null) {
            throw new NullPointerException("@Url parameter is null.");
        }
        this.d = obj.toString();
    }

    void a(String str, String str2) {
        if (MIME.CONTENT_TYPE.equalsIgnoreCase(str)) {
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
        this.d = this.d.replace("{" + str + h.d, a(str2, z));
    }

    private static String a(String str, boolean z) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                c cVar = new c();
                cVar.a(str, 0, i);
                a(cVar, str, i, length, z);
                return cVar.q();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    private static void a(c cVar, String str, int i, int i2, boolean z) {
        c cVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                    if (cVar2 == null) {
                        cVar2 = new c();
                    }
                    cVar2.a(codePointAt);
                    while (!cVar2.f()) {
                        int i3 = cVar2.i() & 255;
                        cVar.b(37);
                        cVar.b(a[(i3 >> 4) & 15]);
                        cVar.b(a[i3 & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    void b(String str, String str2, boolean z) {
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

    void a(r rVar, z zVar) {
        this.i.a(rVar, zVar);
    }

    void a(b bVar) {
        this.i.a(bVar);
    }

    void a(z zVar) {
        this.k = zVar;
    }

    y a() {
        s c;
        okhttp3.s.a aVar = this.e;
        if (aVar != null) {
            c = aVar.c();
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
                zVar = z.a(null, new byte[0]);
            }
        }
        u uVar = this.g;
        if (uVar != null) {
            if (zVar != null) {
                zVar = new a(zVar, uVar);
            } else {
                this.f.b(MIME.CONTENT_TYPE, uVar.toString());
            }
        }
        return this.f.a(c).a(this.b, zVar).b();
    }
}
