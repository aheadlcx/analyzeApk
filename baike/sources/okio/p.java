package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class p extends ByteString {
    final transient byte[][] e;
    final transient int[] f;

    p(Buffer buffer, int i) {
        int i2 = 0;
        super(null);
        r.checkOffsetAndCount(buffer.b, 0, (long) i);
        n nVar = buffer.a;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (nVar.c == nVar.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += nVar.c - nVar.b;
            i3++;
            nVar = nVar.f;
        }
        this.e = new byte[i3][];
        this.f = new int[(i3 * 2)];
        n nVar2 = buffer.a;
        i4 = 0;
        while (i2 < i) {
            this.e[i4] = nVar2.a;
            int i5 = (nVar2.c - nVar2.b) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.f[i4] = i5;
            this.f[this.e.length + i4] = nVar2.b;
            nVar2.d = true;
            i4++;
            nVar2 = nVar2.f;
            i2 = i5;
        }
    }

    public String utf8() {
        return b().utf8();
    }

    public String string(Charset charset) {
        return b().string(charset);
    }

    public String base64() {
        return b().base64();
    }

    public String hex() {
        return b().hex();
    }

    public ByteString toAsciiLowercase() {
        return b().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return b().toAsciiUppercase();
    }

    public ByteString md5() {
        return b().md5();
    }

    public ByteString sha1() {
        return b().sha1();
    }

    public ByteString sha256() {
        return b().sha256();
    }

    public ByteString hmacSha1(ByteString byteString) {
        return b().hmacSha1(byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return b().hmacSha256(byteString);
    }

    public String base64Url() {
        return b().base64Url();
    }

    public ByteString substring(int i) {
        return b().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return b().substring(i, i2);
    }

    public byte getByte(int i) {
        r.checkOffsetAndCount((long) this.f[this.e.length - 1], (long) i, 1);
        int a = a(i);
        return this.e[a][(i - (a == 0 ? 0 : this.f[a - 1])) + this.f[this.e.length + a]];
    }

    private int a(int i) {
        int binarySearch = Arrays.binarySearch(this.f, 0, this.e.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int size() {
        return this.f[this.e.length - 1];
    }

    public byte[] toByteArray() {
        int i = 0;
        Object obj = new byte[this.f[this.e.length - 1]];
        int length = this.e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f[length + i];
            int i4 = this.f[i];
            System.arraycopy(this.e[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        int i = 0;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f[length + i];
            int i4 = this.f[i];
            outputStream.write(this.e[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    void a(Buffer buffer) {
        int i = 0;
        int length = this.e.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.f[length + i];
            int i4 = this.f[i];
            n nVar = new n(this.e[i], i3, (i3 + i4) - i2);
            if (buffer.a == null) {
                nVar.g = nVar;
                nVar.f = nVar;
                buffer.a = nVar;
            } else {
                buffer.a.g.push(nVar);
            }
            i++;
            i2 = i4;
        }
        buffer.b = ((long) i2) + buffer.b;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            int i4 = a == 0 ? 0 : this.f[a - 1];
            int min = Math.min(i3, ((this.f[a] - i4) + i4) - i);
            if (!byteString.rangeEquals(i2, this.e[a], (i - i4) + this.f[this.e.length + a], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            int i4 = a == 0 ? 0 : this.f[a - 1];
            int min = Math.min(i3, ((this.f[a] - i4) + i4) - i);
            if (!r.arrayRangeEquals(this.e[a], (i - i4) + this.f[this.e.length + a], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public int indexOf(byte[] bArr, int i) {
        return b().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return b().lastIndexOf(bArr, i);
    }

    private ByteString b() {
        return new ByteString(toByteArray());
    }

    byte[] a() {
        return toByteArray();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z;
        if ((obj instanceof ByteString) && ((ByteString) obj).size() == size() && rangeEquals(0, (ByteString) obj, 0, size())) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = this.c;
        if (i == 0) {
            i = 1;
            int length = this.e.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.e[i2];
                int i4 = this.f[length + i2];
                int i5 = this.f[i2];
                i3 = (i5 - i3) + i4;
                int i6 = i4;
                i4 = i;
                for (i = i6; i < i3; i++) {
                    i4 = (i4 * 31) + bArr[i];
                }
                i2++;
                i3 = i5;
                i = i4;
            }
            this.c = i;
        }
        return i;
    }

    public String toString() {
        return b().toString();
    }
}
