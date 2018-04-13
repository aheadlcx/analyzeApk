package cn.xiaochuankeji.tieba.common.c;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.imagepipeline.producers.af$a;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.e$a;
import okhttp3.f;
import okhttp3.w;
import okhttp3.y;

public class c extends com.facebook.imagepipeline.a.a.a {

    private static class a implements e$a {
        private final e$a a;

        public a(e$a e_a) {
            this.a = e_a;
        }

        public e a(y yVar) {
            return new b(this.a.a(yVar), (c) yVar.e());
        }
    }

    private static class b implements e {
        private final e a;
        private final c b;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return d();
        }

        public b(e eVar, c cVar) {
            this.a = eVar;
            this.b = cVar;
        }

        public aa a() throws IOException {
            return this.a.a();
        }

        public void a(final f fVar) {
            this.a.a(new f(this) {
                final /* synthetic */ b b;

                public void a(e eVar, IOException iOException) {
                    if (fVar != null) {
                        fVar.a(eVar, iOException);
                    }
                }

                public void a(e eVar, aa aaVar) throws IOException {
                    this.b.b.a(aaVar);
                    if (fVar != null) {
                        fVar.a(eVar, aaVar);
                    }
                }
            });
        }

        public void b() {
            this.a.b();
        }

        public boolean c() {
            return this.a.c();
        }

        public e d() {
            return this;
        }
    }

    private static class c implements af$a {
        private final af$a a;
        private final String b;
        private FilterInputStream c;
        private String d;
        private String e;

        public c(af$a af_a, String str) {
            this.a = af_a;
            this.b = str;
        }

        public void a(aa aaVar) {
            this.d = aaVar.a("zy-img-check-md5");
            this.e = aaVar.a("AddressType");
        }

        public void a(InputStream inputStream, int i) throws IOException {
            if (this.a != null) {
                this.c = new d(inputStream, i, this.b, this.d, this.e);
                this.a.a(this.c, i);
            }
        }

        public void a(Throwable th) {
            if (this.a != null) {
                this.a.a(th);
            }
        }

        public void a() {
            if (this.a != null) {
                this.a.a();
            }
        }
    }

    private static class d extends FilterInputStream {
        private final int a;
        private final String b;
        private final String c;
        private final String d;
        private int e;
        private MessageDigest f;

        public d(InputStream inputStream, int i, String str, String str2, String str3) {
            super(inputStream);
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    this.f = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }

        public int read(byte[] bArr) throws IOException {
            int read;
            Exception e;
            try {
                read = super.read(bArr);
                if (read < 0) {
                    try {
                        if (this.e < this.a) {
                            cn.xiaochuankeji.tieba.network.custom.a.a.a().a(this.b, this.d);
                            throw new EOFException();
                        } else if (this.f != null) {
                            String a = a(this.f.digest());
                            if (!(TextUtils.isEmpty(this.c) || this.c.equalsIgnoreCase(a))) {
                                cn.xiaochuankeji.tieba.network.custom.a.a.a().a(this.b, this.d);
                                throw new IOException("checksum failed");
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        cn.xiaochuankeji.tieba.network.custom.a.a.a().a(this.b, this.d);
                        if (e instanceof IOException) {
                            throw e;
                        }
                        try {
                            if ("smartdns".equals(this.d)) {
                                cn.xiaochuankeji.tieba.network.e.a().b(Uri.parse(this.b).getHost());
                            }
                        } catch (Exception e3) {
                        }
                        return read;
                    }
                }
                this.e += read;
                if (this.f != null) {
                    this.f.update(bArr, 0, read);
                }
            } catch (Exception e4) {
                Exception exception = e4;
                read = 0;
                e = exception;
                cn.xiaochuankeji.tieba.network.custom.a.a.a().a(this.b, this.d);
                if (e instanceof IOException) {
                    throw e;
                }
                if ("smartdns".equals(this.d)) {
                    cn.xiaochuankeji.tieba.network.e.a().b(Uri.parse(this.b).getHost());
                }
                return read;
            }
            return read;
        }

        private static String a(byte[] bArr) {
            StringBuilder stringBuilder = new StringBuilder(bArr.length << 1);
            for (int i = 0; i < bArr.length; i++) {
                stringBuilder.append("0123456789abcdef".charAt((bArr[i] & 240) >> 4));
                stringBuilder.append("0123456789abcdef".charAt(bArr[i] & 15));
            }
            return stringBuilder.toString();
        }
    }

    public c(w wVar) {
        super(new a(wVar), wVar.s().a());
    }

    protected void a(com.facebook.imagepipeline.a.a.a.a aVar, af$a af_a, y yVar) {
        af$a cVar = new c(af_a, yVar.a().toString());
        super.a(aVar, cVar, yVar.f().b("Request-Type", "image/*").a(cVar).d());
    }
}
