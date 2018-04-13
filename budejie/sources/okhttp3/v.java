package okhttp3;

import com.umeng.analytics.pro.dm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.internal.c;
import okio.ByteString;
import okio.d;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.http.entity.mime.MIME;

public final class v extends z {
    public static final u a = u.a("multipart/mixed");
    public static final u b = u.a("multipart/alternative");
    public static final u c = u.a("multipart/digest");
    public static final u d = u.a("multipart/parallel");
    public static final u e = u.a(MultipartPostMethod.MULTIPART_FORM_CONTENT_TYPE);
    private static final byte[] f = new byte[]{(byte) 58, (byte) 32};
    private static final byte[] g = new byte[]{dm.k, (byte) 10};
    private static final byte[] h = new byte[]{(byte) 45, (byte) 45};
    private final ByteString i;
    private final u j;
    private final u k;
    private final List<b> l;
    private long m = -1;

    public static final class a {
        private final ByteString a;
        private u b;
        private final List<b> c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.b = v.a;
            this.c = new ArrayList();
            this.a = ByteString.encodeUtf8(str);
        }

        public a a(u uVar) {
            if (uVar == null) {
                throw new NullPointerException("type == null");
            } else if (uVar.a().equals("multipart")) {
                this.b = uVar;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + uVar);
            }
        }

        public a a(r rVar, z zVar) {
            return a(b.a(rVar, zVar));
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(bVar);
            return this;
        }

        public v a() {
            if (!this.c.isEmpty()) {
                return new v(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    public static final class b {
        final r a;
        final z b;

        public static b a(r rVar, z zVar) {
            if (zVar == null) {
                throw new NullPointerException("body == null");
            } else if (rVar != null && rVar.a(MIME.CONTENT_TYPE) != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (rVar == null || rVar.a("Content-Length") == null) {
                return new b(rVar, zVar);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        private b(r rVar, z zVar) {
            this.a = rVar;
            this.b = zVar;
        }
    }

    v(ByteString byteString, u uVar, List<b> list) {
        this.i = byteString;
        this.j = uVar;
        this.k = u.a(uVar + "; boundary=" + byteString.utf8());
        this.l = c.a((List) list);
    }

    public u a() {
        return this.k;
    }

    public long b() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        j = a(null, true);
        this.m = j;
        return j;
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(d dVar, boolean z) throws IOException {
        okio.c cVar;
        long j = 0;
        if (z) {
            okio.c cVar2 = new okio.c();
            cVar = cVar2;
            dVar = cVar2;
        } else {
            cVar = null;
        }
        int size = this.l.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.l.get(i);
            r rVar = bVar.a;
            z zVar = bVar.b;
            dVar.c(h);
            dVar.b(this.i);
            dVar.c(g);
            if (rVar != null) {
                int a = rVar.a();
                for (int i2 = 0; i2 < a; i2++) {
                    dVar.b(rVar.a(i2)).c(f).b(rVar.b(i2)).c(g);
                }
            }
            u a2 = zVar.a();
            if (a2 != null) {
                dVar.b("Content-Type: ").b(a2.toString()).c(g);
            }
            long b = zVar.b();
            if (b != -1) {
                dVar.b("Content-Length: ").k(b).c(g);
            } else if (z) {
                cVar.t();
                return -1;
            }
            dVar.c(g);
            if (z) {
                j += b;
            } else {
                zVar.a(dVar);
            }
            dVar.c(g);
        }
        dVar.c(h);
        dVar.b(this.i);
        dVar.c(h);
        dVar.c(g);
        if (!z) {
            return j;
        }
        j += cVar.b();
        cVar.t();
        return j;
    }
}
