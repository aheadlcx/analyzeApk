package okhttp3;

import java.io.IOException;
import okhttp3.internal.c;
import okio.ByteString;
import okio.d;

public abstract class z {
    public abstract u a();

    public abstract void a(d dVar) throws IOException;

    public long b() throws IOException {
        return -1;
    }

    public static z a(final u uVar, final ByteString byteString) {
        return new z() {
            public u a() {
                return uVar;
            }

            public long b() throws IOException {
                return (long) byteString.size();
            }

            public void a(d dVar) throws IOException {
                dVar.b(byteString);
            }
        };
    }

    public static z a(u uVar, byte[] bArr) {
        return a(uVar, bArr, 0, bArr.length);
    }

    public static z a(final u uVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        c.a((long) bArr.length, (long) i, (long) i2);
        return new z() {
            public u a() {
                return uVar;
            }

            public long b() {
                return (long) i2;
            }

            public void a(d dVar) throws IOException {
                dVar.c(bArr, i, i2);
            }
        };
    }
}
