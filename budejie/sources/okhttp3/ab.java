package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.c;
import okio.e;

public abstract class ab implements Closeable {
    private Reader a;

    static final class a extends Reader {
        private final e a;
        private final Charset b;
        private boolean c;
        private Reader d;

        a(e eVar, Charset charset) {
            this.a = eVar;
            this.b = charset;
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.d;
            if (reader == null) {
                reader = new InputStreamReader(this.a.g(), c.a(this.a, this.b));
                this.d = reader;
            }
            return reader.read(cArr, i, i2);
        }

        public void close() throws IOException {
            this.c = true;
            if (this.d != null) {
                this.d.close();
            } else {
                this.a.close();
            }
        }
    }

    public abstract u a();

    public abstract long b();

    public abstract e c();

    public final InputStream d() {
        return c().g();
    }

    public final Reader e() {
        Reader reader = this.a;
        if (reader != null) {
            return reader;
        }
        reader = new a(c(), g());
        this.a = reader;
        return reader;
    }

    public final String f() throws IOException {
        Closeable c = c();
        try {
            String a = c.a(c.a((e) c, g()));
            return a;
        } finally {
            c.a(c);
        }
    }

    private Charset g() {
        u a = a();
        return a != null ? a.a(c.e) : c.e;
    }

    public void close() {
        c.a(c());
    }

    public static ab a(u uVar, byte[] bArr) {
        return a(uVar, (long) bArr.length, new okio.c().b(bArr));
    }

    public static ab a(final u uVar, final long j, final e eVar) {
        if (eVar != null) {
            return new ab() {
                public u a() {
                    return uVar;
                }

                public long b() {
                    return j;
                }

                public e c() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
