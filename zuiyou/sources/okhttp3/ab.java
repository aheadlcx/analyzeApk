package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.c;
import okio.Buffer;
import okio.BufferedSource;

public abstract class ab implements Closeable {
    private Reader reader;

    /* renamed from: okhttp3.ab$1 */
    class AnonymousClass1 extends ab {
        final /* synthetic */ u a;
        final /* synthetic */ long b;
        final /* synthetic */ BufferedSource c;

        AnonymousClass1(u uVar, long j, BufferedSource bufferedSource) {
            this.a = uVar;
            this.b = j;
            this.c = bufferedSource;
        }

        @Nullable
        public u contentType() {
            return this.a;
        }

        public long contentLength() {
            return this.b;
        }

        public BufferedSource source() {
            return this.c;
        }
    }

    public abstract long contentLength();

    @Nullable
    public abstract u contentType();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        Closeable source = source();
        try {
            byte[] readByteArray = source.readByteArray();
            if (contentLength == -1 || contentLength == ((long) readByteArray.length)) {
                return readByteArray;
            }
            throw new IOException("Content-Length (" + contentLength + ") and stream length (" + readByteArray.length + ") disagree");
        } finally {
            c.a(source);
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        reader = new ab$a(source(), charset());
        this.reader = reader;
        return reader;
    }

    public final String string() throws IOException {
        Closeable source = source();
        try {
            String readString = source.readString(c.a(source, charset()));
            return readString;
        } finally {
            c.a(source);
        }
    }

    private Charset charset() {
        u contentType = contentType();
        return contentType != null ? contentType.a(c.e) : c.e;
    }

    public void close() {
        c.a(source());
    }

    public static ab create(@Nullable u uVar, String str) {
        Charset charset = c.e;
        if (uVar != null) {
            charset = uVar.b();
            if (charset == null) {
                charset = c.e;
                uVar = u.a(uVar + "; charset=utf-8");
            }
        }
        BufferedSource writeString = new Buffer().writeString(str, charset);
        return create(uVar, writeString.size(), writeString);
    }

    public static ab create(@Nullable u uVar, byte[] bArr) {
        return create(uVar, (long) bArr.length, new Buffer().write(bArr));
    }

    public static ab create(@Nullable u uVar, long j, BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            return new AnonymousClass1(uVar, j, bufferedSource);
        }
        throw new NullPointerException("source == null");
    }
}
