package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.c;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;

public abstract class z {

    /* renamed from: okhttp3.z$1 */
    class AnonymousClass1 extends z {
        final /* synthetic */ u a;
        final /* synthetic */ ByteString b;

        AnonymousClass1(u uVar, ByteString byteString) {
            this.a = uVar;
            this.b = byteString;
        }

        @Nullable
        public u contentType() {
            return this.a;
        }

        public long contentLength() throws IOException {
            return (long) this.b.size();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.b);
        }
    }

    /* renamed from: okhttp3.z$2 */
    class AnonymousClass2 extends z {
        final /* synthetic */ u a;
        final /* synthetic */ int b;
        final /* synthetic */ byte[] c;
        final /* synthetic */ int d;

        AnonymousClass2(u uVar, int i, byte[] bArr, int i2) {
            this.a = uVar;
            this.b = i;
            this.c = bArr;
            this.d = i2;
        }

        @Nullable
        public u contentType() {
            return this.a;
        }

        public long contentLength() {
            return (long) this.b;
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            bufferedSink.write(this.c, this.d, this.b);
        }
    }

    /* renamed from: okhttp3.z$3 */
    class AnonymousClass3 extends z {
        final /* synthetic */ u a;
        final /* synthetic */ File b;

        AnonymousClass3(u uVar, File file) {
            this.a = uVar;
            this.b = file;
        }

        @Nullable
        public u contentType() {
            return this.a;
        }

        public long contentLength() {
            return this.b.length();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            Closeable closeable = null;
            try {
                closeable = Okio.source(this.b);
                bufferedSink.writeAll(closeable);
            } finally {
                c.a(closeable);
            }
        }
    }

    @Nullable
    public abstract u contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static z create(@Nullable u uVar, String str) {
        Charset charset = c.e;
        if (uVar != null) {
            charset = uVar.b();
            if (charset == null) {
                charset = c.e;
                uVar = u.a(uVar + "; charset=utf-8");
            }
        }
        return create(uVar, str.getBytes(charset));
    }

    public static z create(@Nullable u uVar, ByteString byteString) {
        return new AnonymousClass1(uVar, byteString);
    }

    public static z create(@Nullable u uVar, byte[] bArr) {
        return create(uVar, bArr, 0, bArr.length);
    }

    public static z create(@Nullable u uVar, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        c.a((long) bArr.length, (long) i, (long) i2);
        return new AnonymousClass2(uVar, i2, bArr, i);
    }

    public static z create(@Nullable u uVar, File file) {
        if (file != null) {
            return new AnonymousClass3(uVar, file);
        }
        throw new NullPointerException("content == null");
    }
}
