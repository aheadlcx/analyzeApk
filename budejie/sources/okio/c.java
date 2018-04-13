package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.budejie.www.R$styleable;
import com.umeng.analytics.pro.dm;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public final class c implements Cloneable, d, e {
    private static final byte[] c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    n a;
    long b;

    public /* synthetic */ d b(String str) throws IOException {
        return a(str);
    }

    public /* synthetic */ d b(ByteString byteString) throws IOException {
        return a(byteString);
    }

    public /* synthetic */ d c(byte[] bArr) throws IOException {
        return b(bArr);
    }

    public /* synthetic */ d c(byte[] bArr, int i, int i2) throws IOException {
        return b(bArr, i, i2);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return u();
    }

    public /* synthetic */ d g(int i) throws IOException {
        return d(i);
    }

    public /* synthetic */ d h(int i) throws IOException {
        return c(i);
    }

    public /* synthetic */ d i(int i) throws IOException {
        return b(i);
    }

    public /* synthetic */ d j(long j) throws IOException {
        return i(j);
    }

    public /* synthetic */ d k(long j) throws IOException {
        return h(j);
    }

    public /* synthetic */ d w() throws IOException {
        return e();
    }

    public long b() {
        return this.b;
    }

    public c c() {
        return this;
    }

    public OutputStream d() {
        return new OutputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void write(int i) {
                this.a.b((byte) i);
            }

            public void write(byte[] bArr, int i, int i2) {
                this.a.b(bArr, i, i2);
            }

            public void flush() {
            }

            public void close() {
            }

            public String toString() {
                return this.a + ".outputStream()";
            }
        };
    }

    public c e() {
        return this;
    }

    public boolean f() {
        return this.b == 0;
    }

    public void a(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public InputStream g() {
        return new InputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public int read() {
                if (this.a.b > 0) {
                    return this.a.i() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                return this.a.a(bArr, i, i2);
            }

            public int available() {
                return (int) Math.min(this.a.b, 2147483647L);
            }

            public void close() {
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public c a(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        t.a(this.b, j, j2);
        if (j2 != 0) {
            cVar.b += j2;
            n nVar = this.a;
            while (j >= ((long) (nVar.c - nVar.b))) {
                j -= (long) (nVar.c - nVar.b);
                nVar = nVar.f;
            }
            while (j2 > 0) {
                n nVar2 = new n(nVar);
                nVar2.b = (int) (((long) nVar2.b) + j);
                nVar2.c = Math.min(nVar2.b + ((int) j2), nVar2.c);
                if (cVar.a == null) {
                    nVar2.g = nVar2;
                    nVar2.f = nVar2;
                    cVar.a = nVar2;
                } else {
                    cVar.a.g.a(nVar2);
                }
                j2 -= (long) (nVar2.c - nVar2.b);
                nVar = nVar.f;
                j = 0;
            }
        }
        return this;
    }

    public long h() {
        long j = this.b;
        if (j == 0) {
            return 0;
        }
        n nVar = this.a.g;
        if (nVar.c >= 8192 || !nVar.e) {
            return j;
        }
        return j - ((long) (nVar.c - nVar.b));
    }

    public byte i() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        int i3 = i + 1;
        byte b = nVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = nVar.a();
            o.a(nVar);
        } else {
            nVar.b = i3;
        }
        return b;
    }

    public byte b(long j) {
        t.a(this.b, j, 1);
        n nVar = this.a;
        while (true) {
            int i = nVar.c - nVar.b;
            if (j < ((long) i)) {
                return nVar.a[nVar.b + ((int) j)];
            }
            j -= (long) i;
            nVar = nVar.f;
        }
    }

    public short j() {
        if (this.b < 2) {
            throw new IllegalStateException("size < 2: " + this.b);
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        if (i2 - i < 2) {
            return (short) (((i() & 255) << 8) | (i() & 255));
        }
        byte[] bArr = nVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b -= 2;
        if (i4 == i2) {
            this.a = nVar.a();
            o.a(nVar);
        } else {
            nVar.b = i4;
        }
        return (short) i;
    }

    public int k() {
        if (this.b < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        if (i2 - i < 4) {
            return ((((i() & 255) << 24) | ((i() & 255) << 16)) | ((i() & 255) << 8)) | (i() & 255);
        }
        byte[] bArr = nVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        i3 = i4 + 1;
        i |= (bArr[i4] & 255) << 8;
        i4 = i3 + 1;
        i |= bArr[i3] & 255;
        this.b -= 4;
        if (i4 == i2) {
            this.a = nVar.a();
            o.a(nVar);
            return i;
        }
        nVar.b = i4;
        return i;
    }

    public short l() {
        return t.a(j());
    }

    public int m() {
        return t.a(k());
    }

    public long n() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        Object obj = null;
        Object obj2 = null;
        long j2 = -7;
        do {
            n nVar = this.a;
            byte[] bArr = nVar.a;
            int i2 = nVar.b;
            int i3 = nVar.c;
            while (i2 < i3) {
                int i4 = bArr[i2];
                if (i4 >= (byte) 48 && i4 <= (byte) 57) {
                    int i5 = 48 - i4;
                    if (j < -922337203685477580L || (j == -922337203685477580L && ((long) i5) < j2)) {
                        c b = new c().h(j).b(i4);
                        if (obj == null) {
                            b.i();
                        }
                        throw new NumberFormatException("Number too large: " + b.q());
                    }
                    j = (j * 10) + ((long) i5);
                } else if (i4 != 45 || i != 0) {
                    if (i != 0) {
                        obj2 = 1;
                        if (i2 != i3) {
                            this.a = nVar.a();
                            o.a(nVar);
                        } else {
                            nVar.b = i2;
                        }
                        if (obj2 == null) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(i4));
                    }
                } else {
                    obj = 1;
                    j2--;
                }
                i2++;
                i++;
            }
            if (i2 != i3) {
                nVar.b = i2;
            } else {
                this.a = nVar.a();
                o.a(nVar);
            }
            if (obj2 == null) {
                break;
            }
        } while (this.a != null);
        this.b -= (long) i;
        if (obj != null) {
            return j;
        }
        return -j;
    }

    public long o() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j = 0;
        int i = 0;
        Object obj = null;
        do {
            n nVar = this.a;
            byte[] bArr = nVar.a;
            int i2 = nVar.b;
            int i3 = nVar.c;
            int i4 = i2;
            while (i4 < i3) {
                byte b = bArr[i4];
                if (b >= (byte) 48 && b <= (byte) 57) {
                    i2 = b - 48;
                } else if (b >= (byte) 97 && b <= (byte) 102) {
                    i2 = (b - 97) + 10;
                } else if (b < (byte) 65 || b > (byte) 70) {
                    if (i != 0) {
                        obj = 1;
                        if (i4 != i3) {
                            this.a = nVar.a();
                            o.a(nVar);
                        } else {
                            nVar.b = i4;
                        }
                        if (obj == null) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                    }
                } else {
                    i2 = (b - 65) + 10;
                }
                if ((-1152921504606846976L & j) != 0) {
                    throw new NumberFormatException("Number too large: " + new c().i(j).b((int) b).q());
                }
                i++;
                i4++;
                j = ((long) i2) | (j << 4);
            }
            if (i4 != i3) {
                nVar.b = i4;
            } else {
                this.a = nVar.a();
                o.a(nVar);
            }
            if (obj == null) {
                break;
            }
        } while (this.a != null);
        this.b -= (long) i;
        return j;
    }

    public ByteString p() {
        return new ByteString(s());
    }

    public ByteString c(long j) throws EOFException {
        return new ByteString(f(j));
    }

    public long a(q qVar) throws IOException {
        long j = this.b;
        if (j > 0) {
            qVar.a_(this, j);
        }
        return j;
    }

    public String q() {
        try {
            return a(this.b, t.a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String d(long j) throws EOFException {
        return a(j, t.a);
    }

    public String a(Charset charset) {
        try {
            return a(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String a(long j, Charset charset) throws EOFException {
        t.a(this.b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            n nVar = this.a;
            if (((long) nVar.b) + j > ((long) nVar.c)) {
                return new String(f(j), charset);
            }
            String str = new String(nVar.a, nVar.b, (int) j, charset);
            nVar.b = (int) (((long) nVar.b) + j);
            this.b -= j;
            if (nVar.b != nVar.c) {
                return str;
            }
            this.a = nVar.a();
            o.a(nVar);
            return str;
        }
    }

    public String r() throws EOFException {
        long a = a((byte) 10);
        if (a != -1) {
            return e(a);
        }
        c cVar = new c();
        a(cVar, 0, Math.min(32, this.b));
        throw new EOFException("\\n not found: size=" + b() + " content=" + cVar.p().hex() + "…");
    }

    String e(long j) throws EOFException {
        if (j <= 0 || b(j - 1) != dm.k) {
            String d = d(j);
            g(1);
            return d;
        }
        d = d(j - 1);
        g(2);
        return d;
    }

    public byte[] s() {
        try {
            return f(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] f(long j) throws EOFException {
        t.a(this.b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        a(bArr);
        return bArr;
    }

    public void a(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int a = a(bArr, i, bArr.length - i);
            if (a == -1) {
                throw new EOFException();
            }
            i += a;
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        t.a((long) bArr.length, (long) i, (long) i2);
        n nVar = this.a;
        if (nVar == null) {
            return -1;
        }
        int min = Math.min(i2, nVar.c - nVar.b);
        System.arraycopy(nVar.a, nVar.b, bArr, i, min);
        nVar.b += min;
        this.b -= (long) min;
        if (nVar.b != nVar.c) {
            return min;
        }
        this.a = nVar.a();
        o.a(nVar);
        return min;
    }

    public void t() {
        try {
            g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public void g(long j) throws EOFException {
        while (j > 0) {
            if (this.a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.a.c - this.a.b));
            this.b -= (long) min;
            j -= (long) min;
            n nVar = this.a;
            nVar.b = min + nVar.b;
            if (this.a.b == this.a.c) {
                n nVar2 = this.a;
                this.a = nVar2.a();
                o.a(nVar2);
            }
        }
    }

    public c a(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    public c a(String str) {
        return a(str, 0, str.length());
    }

    public c a(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            while (i < i2) {
                int i3;
                char charAt = str.charAt(i);
                if (charAt < '') {
                    int i4;
                    n e = e(1);
                    byte[] bArr = e.a;
                    int i5 = e.c - i;
                    int min = Math.min(i2, 8192 - i5);
                    i3 = i + 1;
                    bArr[i5 + i] = (byte) charAt;
                    while (i3 < min) {
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '') {
                            break;
                        }
                        i4 = i3 + 1;
                        bArr[i3 + i5] = (byte) charAt2;
                        i3 = i4;
                    }
                    i4 = (i3 + i5) - e.c;
                    e.c += i4;
                    this.b += (long) i4;
                } else if (charAt < 'ࠀ') {
                    b((charAt >> 6) | 192);
                    b((charAt & 63) | 128);
                    i3 = i + 1;
                } else if (charAt < '?' || charAt > '?') {
                    b((charAt >> 12) | 224);
                    b(((charAt >> 6) & 63) | 128);
                    b((charAt & 63) | 128);
                    i3 = i + 1;
                } else {
                    i3 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > '?' || i3 < 56320 || i3 > 57343) {
                        b(63);
                        i++;
                    } else {
                        i3 = ((i3 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        b((i3 >> 18) | R$styleable.Theme_Custom_shape_cmt_like4_bg);
                        b(((i3 >> 12) & 63) | 128);
                        b(((i3 >> 6) & 63) | 128);
                        b((i3 & 63) | 128);
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return this;
        }
    }

    public c a(int i) {
        if (i < 128) {
            b(i);
        } else if (i < 2048) {
            b((i >> 6) | 192);
            b((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                b((i >> 12) | 224);
                b(((i >> 6) & 63) | 128);
                b((i & 63) | 128);
            } else {
                throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
            }
        } else if (i <= 1114111) {
            b((i >> 18) | R$styleable.Theme_Custom_shape_cmt_like4_bg);
            b(((i >> 12) & 63) | 128);
            b(((i >> 6) & 63) | 128);
            b((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public c b(byte[] bArr) {
        if (bArr != null) {
            return b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public c b(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        t.a((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            n e = e(1);
            int min = Math.min(i3 - i, 8192 - e.c);
            System.arraycopy(bArr, i, e.a, e.c, min);
            i += min;
            e.c = min + e.c;
        }
        this.b += (long) i2;
        return this;
    }

    public long a(r rVar) throws IOException {
        if (rVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a = rVar.a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (a == -1) {
                return j;
            }
            j += a;
        }
    }

    public c b(int i) {
        n e = e(1);
        byte[] bArr = e.a;
        int i2 = e.c;
        e.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    public c c(int i) {
        n e = e(2);
        byte[] bArr = e.a;
        int i2 = e.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        e.c = i2;
        this.b += 2;
        return this;
    }

    public c d(int i) {
        n e = e(4);
        byte[] bArr = e.a;
        int i2 = e.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        e.c = i2;
        this.b += 4;
        return this;
    }

    public c h(long j) {
        if (j == 0) {
            return b(48);
        }
        long j2;
        Object obj;
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return a("-9223372036854775808");
            }
            obj = 1;
        } else {
            obj = null;
            j2 = j;
        }
        int i = j2 < 100000000 ? j2 < 10000 ? j2 < 100 ? j2 < 10 ? 1 : 2 : j2 < 1000 ? 3 : 4 : j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8 : j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        if (obj != null) {
            i++;
        }
        n e = e(i);
        byte[] bArr = e.a;
        int i2 = e.c + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = c[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (obj != null) {
            bArr[i2 - 1] = (byte) 45;
        }
        e.c += i;
        this.b = ((long) i) + this.b;
        return this;
    }

    public c i(long j) {
        if (j == 0) {
            return b(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        n e = e(numberOfTrailingZeros);
        byte[] bArr = e.a;
        int i = e.c;
        for (int i2 = (e.c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = c[(int) (15 & j)];
            j >>>= 4;
        }
        e.c += numberOfTrailingZeros;
        this.b = ((long) numberOfTrailingZeros) + this.b;
        return this;
    }

    n e(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = o.a();
            n nVar = this.a;
            n nVar2 = this.a;
            r0 = this.a;
            nVar2.g = r0;
            nVar.f = r0;
            return r0;
        } else {
            r0 = this.a.g;
            if (r0.c + i > 8192 || !r0.e) {
                return r0.a(o.a());
            }
            return r0;
        }
    }

    public void a_(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            t.a(cVar.b, 0, j);
            while (j > 0) {
                n nVar;
                if (j < ((long) (cVar.a.c - cVar.a.b))) {
                    nVar = this.a != null ? this.a.g : null;
                    if (nVar != null && nVar.e) {
                        if ((((long) nVar.c) + j) - ((long) (nVar.d ? 0 : nVar.b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            cVar.a.a(nVar, (int) j);
                            cVar.b -= j;
                            this.b += j;
                            return;
                        }
                    }
                    cVar.a = cVar.a.a((int) j);
                }
                n nVar2 = cVar.a;
                long j2 = (long) (nVar2.c - nVar2.b);
                cVar.a = nVar2.a();
                if (this.a == null) {
                    this.a = nVar2;
                    nVar2 = this.a;
                    nVar = this.a;
                    n nVar3 = this.a;
                    nVar.g = nVar3;
                    nVar2.f = nVar3;
                } else {
                    this.a.g.a(nVar2).b();
                }
                cVar.b -= j2;
                this.b += j2;
                j -= j2;
            }
        }
    }

    public long a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.b == 0) {
            return -1;
        } else {
            if (j > this.b) {
                j = this.b;
            }
            cVar.a_(this, j);
            return j;
        }
    }

    public long a(byte b) {
        return a(b, 0);
    }

    public long a(byte b, long j) {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        n nVar = this.a;
        if (nVar == null) {
            return -1;
        }
        n nVar2;
        if (this.b - j >= j) {
            nVar2 = nVar;
            while (true) {
                long j3 = ((long) (nVar2.c - nVar2.b)) + j2;
                if (j3 >= j) {
                    break;
                }
                nVar2 = nVar2.f;
                j2 = j3;
            }
        } else {
            j2 = this.b;
            nVar2 = nVar;
            while (j2 > j) {
                nVar2 = nVar2.g;
                j2 -= (long) (nVar2.c - nVar2.b);
            }
        }
        while (j2 < this.b) {
            byte[] bArr = nVar2.a;
            int i = nVar2.c;
            for (int i2 = (int) ((((long) nVar2.b) + j) - j2); i2 < i; i2++) {
                if (bArr[i2] == b) {
                    return j2 + ((long) (i2 - nVar2.b));
                }
            }
            j2 += (long) (nVar2.c - nVar2.b);
            nVar2 = nVar2.f;
            j = j2;
        }
        return -1;
    }

    public boolean a(long j, ByteString byteString) {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.b - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (b(((long) i3) + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public void flush() {
    }

    public void close() {
    }

    public s a() {
        return s.b;
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.b != cVar.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        n nVar = this.a;
        n nVar2 = cVar.a;
        int i = nVar.b;
        int i2 = nVar2.b;
        while (j < this.b) {
            long min = (long) Math.min(nVar.c - i, nVar2.c - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                byte b = nVar.a[i];
                i = i2 + 1;
                if (b != nVar2.a[i2]) {
                    return false;
                }
                i3++;
                i2 = i;
                i = i4;
            }
            if (i == nVar.c) {
                nVar = nVar.f;
                i = nVar.b;
            }
            if (i2 == nVar2.c) {
                nVar2 = nVar2.f;
                i2 = nVar2.b;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        n nVar = this.a;
        if (nVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = nVar.b;
            while (i2 < nVar.c) {
                int i3 = nVar.a[i2] + (i * 31);
                i2++;
                i = i3;
            }
            nVar = nVar.f;
        } while (nVar != this.a);
        return i;
    }

    public String toString() {
        return v().toString();
    }

    public c u() {
        c cVar = new c();
        if (this.b == 0) {
            return cVar;
        }
        cVar.a = new n(this.a);
        n nVar = cVar.a;
        n nVar2 = cVar.a;
        n nVar3 = cVar.a;
        nVar2.g = nVar3;
        nVar.f = nVar3;
        for (nVar = this.a.f; nVar != this.a; nVar = nVar.f) {
            cVar.a.g.a(new n(nVar));
        }
        cVar.b = this.b;
        return cVar;
    }

    public ByteString v() {
        if (this.b <= 2147483647L) {
            return f((int) this.b);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }

    public ByteString f(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new p(this, i);
    }
}
