package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.a.d;
import okhttp3.internal.a.f;
import okio.ByteString;
import okio.e;
import okio.g;
import okio.k;
import okio.q;
import okio.r;
import org.apache.http.entity.mime.MIME;

public final class c implements Closeable, Flushable {
    final f a;
    final d b;
    int c;
    int d;
    private int e;
    private int f;
    private int g;

    private final class a implements okhttp3.internal.a.b {
        boolean a;
        final /* synthetic */ c b;
        private final okhttp3.internal.a.d.a c;
        private q d;
        private q e;

        public a(final c cVar, final okhttp3.internal.a.d.a aVar) {
            this.b = cVar;
            this.c = aVar;
            this.d = aVar.a(1);
            this.e = new okio.f(this, this.d) {
                final /* synthetic */ a c;

                public void close() throws IOException {
                    synchronized (this.c.b) {
                        if (this.c.a) {
                            return;
                        }
                        this.c.a = true;
                        c cVar = this.c.b;
                        cVar.c++;
                        super.close();
                        aVar.b();
                    }
                }
            };
        }

        public void a() {
            synchronized (this.b) {
                if (this.a) {
                    return;
                }
                this.a = true;
                c cVar = this.b;
                cVar.d++;
                okhttp3.internal.c.a(this.d);
                try {
                    this.c.c();
                } catch (IOException e) {
                }
            }
        }

        public q b() {
            return this.e;
        }
    }

    private static class b extends ab {
        final okhttp3.internal.a.d.c a;
        private final e b;
        private final String c;
        private final String d;

        public b(final okhttp3.internal.a.d.c cVar, String str, String str2) {
            this.a = cVar;
            this.c = str;
            this.d = str2;
            this.b = k.a(new g(this, cVar.a(1)) {
                final /* synthetic */ b b;

                public void close() throws IOException {
                    cVar.close();
                    super.close();
                }
            });
        }

        public u a() {
            return this.c != null ? u.a(this.c) : null;
        }

        public long b() {
            long j = -1;
            try {
                if (this.d != null) {
                    j = Long.parseLong(this.d);
                }
            } catch (NumberFormatException e) {
            }
            return j;
        }

        public e c() {
            return this.b;
        }
    }

    private static final class c {
        private static final String a = (okhttp3.internal.e.e.b().c() + "-Sent-Millis");
        private static final String b = (okhttp3.internal.e.e.b().c() + "-Received-Millis");
        private final String c;
        private final r d;
        private final String e;
        private final Protocol f;
        private final int g;
        private final String h;
        private final r i;
        private final q j;
        private final long k;
        private final long l;

        public c(r rVar) throws IOException {
            long j = 0;
            TlsVersion tlsVersion = null;
            int i = 0;
            try {
                int i2;
                e a = k.a(rVar);
                this.c = a.r();
                this.e = a.r();
                okhttp3.r.a aVar = new okhttp3.r.a();
                int a2 = c.a(a);
                for (i2 = 0; i2 < a2; i2++) {
                    aVar.a(a.r());
                }
                this.d = aVar.a();
                okhttp3.internal.b.k a3 = okhttp3.internal.b.k.a(a.r());
                this.f = a3.a;
                this.g = a3.b;
                this.h = a3.c;
                okhttp3.r.a aVar2 = new okhttp3.r.a();
                i2 = c.a(a);
                while (i < i2) {
                    aVar2.a(a.r());
                    i++;
                }
                String c = aVar2.c(a);
                String c2 = aVar2.c(b);
                aVar2.b(a);
                aVar2.b(b);
                this.k = c != null ? Long.parseLong(c) : 0;
                if (c2 != null) {
                    j = Long.parseLong(c2);
                }
                this.l = j;
                this.i = aVar2.a();
                if (a()) {
                    c = a.r();
                    if (c.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + c + "\"");
                    }
                    h a4 = h.a(a.r());
                    List a5 = a(a);
                    List a6 = a(a);
                    if (!a.f()) {
                        tlsVersion = TlsVersion.forJavaName(a.r());
                    }
                    this.j = q.a(tlsVersion, a4, a5, a6);
                } else {
                    this.j = null;
                }
                rVar.close();
            } catch (Throwable th) {
                rVar.close();
            }
        }

        public c(aa aaVar) {
            this.c = aaVar.a().a().toString();
            this.d = okhttp3.internal.b.e.c(aaVar);
            this.e = aaVar.a().b();
            this.f = aaVar.b();
            this.g = aaVar.c();
            this.h = aaVar.e();
            this.i = aaVar.g();
            this.j = aaVar.f();
            this.k = aaVar.l();
            this.l = aaVar.m();
        }

        public void a(okhttp3.internal.a.d.a aVar) throws IOException {
            int i;
            int i2 = 0;
            okio.d a = k.a(aVar.a(0));
            a.b(this.c).i(10);
            a.b(this.e).i(10);
            a.k((long) this.d.a()).i(10);
            int a2 = this.d.a();
            for (i = 0; i < a2; i++) {
                a.b(this.d.a(i)).b(": ").b(this.d.b(i)).i(10);
            }
            a.b(new okhttp3.internal.b.k(this.f, this.g, this.h).toString()).i(10);
            a.k((long) (this.i.a() + 2)).i(10);
            i = this.i.a();
            while (i2 < i) {
                a.b(this.i.a(i2)).b(": ").b(this.i.b(i2)).i(10);
                i2++;
            }
            a.b(a).b(": ").k(this.k).i(10);
            a.b(b).b(": ").k(this.l).i(10);
            if (a()) {
                a.i(10);
                a.b(this.j.b().a()).i(10);
                a(a, this.j.c());
                a(a, this.j.d());
                if (this.j.a() != null) {
                    a.b(this.j.a().javaName()).i(10);
                }
            }
            a.close();
        }

        private boolean a() {
            return this.c.startsWith("https://");
        }

        private List<Certificate> a(e eVar) throws IOException {
            int a = c.a(eVar);
            if (a == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                List<Certificate> arrayList = new ArrayList(a);
                for (int i = 0; i < a; i++) {
                    String r = eVar.r();
                    okio.c cVar = new okio.c();
                    cVar.a(ByteString.decodeBase64(r));
                    arrayList.add(instance.generateCertificate(cVar.g()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void a(okio.d dVar, List<Certificate> list) throws IOException {
            try {
                dVar.k((long) list.size()).i(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    dVar.b(ByteString.of(((Certificate) list.get(i)).getEncoded()).base64()).i(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean a(y yVar, aa aaVar) {
            return this.c.equals(yVar.a().toString()) && this.e.equals(yVar.b()) && okhttp3.internal.b.e.a(aaVar, this.d, yVar);
        }

        public aa a(okhttp3.internal.a.d.c cVar) {
            String a = this.i.a(MIME.CONTENT_TYPE);
            String a2 = this.i.a("Content-Length");
            return new okhttp3.aa.a().a(new okhttp3.y.a().a(this.c).a(this.e, null).a(this.d).b()).a(this.f).a(this.g).a(this.h).a(this.i).a(new b(cVar, a, a2)).a(this.j).a(this.k).b(this.l).a();
        }
    }

    public c(File file, long j) {
        this(file, j, okhttp3.internal.d.a.a);
    }

    c(File file, long j, okhttp3.internal.d.a aVar) {
        this.a = new f(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public aa a(y yVar) throws IOException {
                return this.a.a(yVar);
            }

            public okhttp3.internal.a.b a(aa aaVar) throws IOException {
                return this.a.a(aaVar);
            }

            public void b(y yVar) throws IOException {
                this.a.b(yVar);
            }

            public void a(aa aaVar, aa aaVar2) {
                this.a.a(aaVar, aaVar2);
            }

            public void a() {
                this.a.c();
            }

            public void a(okhttp3.internal.a.c cVar) {
                this.a.a(cVar);
            }
        };
        this.b = d.a(aVar, file, 201105, 2, j);
    }

    public static String a(s sVar) {
        return ByteString.encodeUtf8(sVar.toString()).md5().hex();
    }

    aa a(y yVar) {
        try {
            Closeable a = this.b.a(a(yVar.a()));
            if (a == null) {
                return null;
            }
            try {
                c cVar = new c(a.a(0));
                aa a2 = cVar.a((okhttp3.internal.a.d.c) a);
                if (cVar.a(yVar, a2)) {
                    return a2;
                }
                okhttp3.internal.c.a(a2.h());
                return null;
            } catch (IOException e) {
                okhttp3.internal.c.a(a);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    okhttp3.internal.a.b a(aa aaVar) {
        okhttp3.internal.a.d.a aVar;
        String b = aaVar.a().b();
        if (okhttp3.internal.b.f.a(aaVar.a().b())) {
            try {
                b(aaVar.a());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!b.equals("GET") || okhttp3.internal.b.e.b(aaVar)) {
            return null;
        } else {
            c cVar = new c(aaVar);
            try {
                okhttp3.internal.a.d.a b2 = this.b.b(a(aaVar.a().a()));
                if (b2 == null) {
                    return null;
                }
                try {
                    cVar.a(b2);
                    return new a(this, b2);
                } catch (IOException e2) {
                    aVar = b2;
                    a(aVar);
                    return null;
                }
            } catch (IOException e3) {
                aVar = null;
                a(aVar);
                return null;
            }
        }
    }

    void b(y yVar) throws IOException {
        this.b.c(a(yVar.a()));
    }

    void a(aa aaVar, aa aaVar2) {
        c cVar = new c(aaVar2);
        try {
            okhttp3.internal.a.d.a a = ((b) aaVar.h()).a.a();
            if (a != null) {
                cVar.a(a);
                a.b();
            }
        } catch (IOException e) {
            a(null);
        }
    }

    private void a(okhttp3.internal.a.d.a aVar) {
        if (aVar != null) {
            try {
                aVar.c();
            } catch (IOException e) {
            }
        }
    }

    public void a() throws IOException {
        this.b.f();
    }

    public void b() throws IOException {
        this.b.g();
    }

    public void flush() throws IOException {
        this.b.flush();
    }

    public void close() throws IOException {
        this.b.close();
    }

    synchronized void a(okhttp3.internal.a.c cVar) {
        this.g++;
        if (cVar.a != null) {
            this.e++;
        } else if (cVar.b != null) {
            this.f++;
        }
    }

    synchronized void c() {
        this.f++;
    }

    static int a(e eVar) throws IOException {
        try {
            long n = eVar.n();
            String r = eVar.r();
            if (n >= 0 && n <= 2147483647L && r.isEmpty()) {
                return (int) n;
            }
            throw new IOException("expected an int but was \"" + n + r + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
