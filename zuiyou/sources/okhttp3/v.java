package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.c;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class v extends z {
    public static final u a = u.a("multipart/mixed");
    public static final u b = u.a("multipart/alternative");
    public static final u c = u.a("multipart/digest");
    public static final u d = u.a("multipart/parallel");
    public static final u e = u.a("multipart/form-data");
    private static final byte[] f = new byte[]{(byte) 58, (byte) 32};
    private static final byte[] g = new byte[]{(byte) 13, (byte) 10};
    private static final byte[] h = new byte[]{Framer.STDIN_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX};
    private final ByteString i;
    private final u j;
    private final u k;
    private final List<v$b> l;
    private long m = -1;

    public static final class a {
        private final ByteString a;
        private u b;
        private final List<v$b> c;

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

        public a a(@Nullable s sVar, z zVar) {
            return a(v$b.a(sVar, zVar));
        }

        public a a(String str, String str2) {
            return a(v$b.a(str, str2));
        }

        public a a(String str, @Nullable String str2, z zVar) {
            return a(v$b.a(str, str2, zVar));
        }

        public a a(v$b v_b) {
            if (v_b == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(v_b);
            return this;
        }

        public v a() {
            if (!this.c.isEmpty()) {
                return new v(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    v(ByteString byteString, u uVar, List<v$b> list) {
        this.i = byteString;
        this.j = uVar;
        this.k = u.a(uVar + "; boundary=" + byteString.utf8());
        this.l = c.a((List) list);
    }

    public u contentType() {
        return this.k;
    }

    public long contentLength() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        j = a(null, true);
        this.m = j;
        return j;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        a(bufferedSink, false);
    }

    private long a(@Nullable BufferedSink bufferedSink, boolean z) throws IOException {
        Buffer buffer;
        long j = 0;
        if (z) {
            Buffer buffer2 = new Buffer();
            buffer = buffer2;
            bufferedSink = buffer2;
        } else {
            buffer = null;
        }
        int size = this.l.size();
        for (int i = 0; i < size; i++) {
            v$b v_b = (v$b) this.l.get(i);
            s sVar = v_b.a;
            z zVar = v_b.b;
            bufferedSink.write(h);
            bufferedSink.write(this.i);
            bufferedSink.write(g);
            if (sVar != null) {
                int a = sVar.a();
                for (int i2 = 0; i2 < a; i2++) {
                    bufferedSink.writeUtf8(sVar.a(i2)).write(f).writeUtf8(sVar.b(i2)).write(g);
                }
            }
            u contentType = zVar.contentType();
            if (contentType != null) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(contentType.toString()).write(g);
            }
            long contentLength = zVar.contentLength();
            if (contentLength != -1) {
                bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength).write(g);
            } else if (z) {
                buffer.clear();
                return -1;
            }
            bufferedSink.write(g);
            if (z) {
                j += contentLength;
            } else {
                zVar.writeTo(bufferedSink);
            }
            bufferedSink.write(g);
        }
        bufferedSink.write(h);
        bufferedSink.write(this.i);
        bufferedSink.write(h);
        bufferedSink.write(g);
        if (!z) {
            return j;
        }
        j += buffer.size();
        buffer.clear();
        return j;
    }

    static StringBuilder a(StringBuilder stringBuilder, String str) {
        stringBuilder.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\n':
                    stringBuilder.append("%0A");
                    break;
                case '\r':
                    stringBuilder.append("%0D");
                    break;
                case '\"':
                    stringBuilder.append("%22");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        stringBuilder.append('\"');
        return stringBuilder;
    }
}
