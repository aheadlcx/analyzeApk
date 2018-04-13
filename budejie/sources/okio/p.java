package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class p extends ByteString {
    final transient byte[][] a;
    final transient int[] b;

    p(c cVar, int i) {
        int i2 = 0;
        super(null);
        t.a(cVar.b, 0, (long) i);
        n nVar = cVar.a;
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
        this.a = new byte[i3][];
        this.b = new int[(i3 * 2)];
        n nVar2 = cVar.a;
        i4 = 0;
        while (i2 < i) {
            this.a[i4] = nVar2.a;
            int i5 = (nVar2.c - nVar2.b) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.b[i4] = i5;
            this.b[this.a.length + i4] = nVar2.b;
            nVar2.d = true;
            i4++;
            nVar2 = nVar2.f;
            i2 = i5;
        }
    }

    public String utf8() {
        return a().utf8();
    }

    public String string(Charset charset) {
        return a().string(charset);
    }

    public String base64() {
        return a().base64();
    }

    public String hex() {
        return a().hex();
    }

    public ByteString toAsciiLowercase() {
        return a().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return a().toAsciiUppercase();
    }

    public ByteString md5() {
        return a().md5();
    }

    public ByteString sha1() {
        return a().sha1();
    }

    public ByteString sha256() {
        return a().sha256();
    }

    public ByteString hmacSha1(ByteString byteString) {
        return a().hmacSha1(byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return a().hmacSha256(byteString);
    }

    public String base64Url() {
        return a().base64Url();
    }

    public ByteString substring(int i) {
        return a().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return a().substring(i, i2);
    }

    public byte getByte(int i) {
        t.a((long) this.b[this.a.length - 1], (long) i, 1);
        int a = a(i);
        return this.a[a][(i - (a == 0 ? 0 : this.b[a - 1])) + this.b[this.a.length + a]];
    }

    private int a(int i) {
        int binarySearch = Arrays.binarySearch(this.b, 0, this.a.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int size() {
        return this.b[this.a.length - 1];
    }

    public byte[] toByteArray() {
        int i = 0;
        Object obj = new byte[this.b[this.a.length - 1]];
        int length = this.a.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.b[length + i];
            int i4 = this.b[i];
            System.arraycopy(this.a[i], i3, obj, i2, i4 - i2);
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
        int length = this.a.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.b[length + i];
            int i4 = this.b[i];
            outputStream.write(this.a[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    void write(c cVar) {
        int i = 0;
        int length = this.a.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.b[length + i];
            int i4 = this.b[i];
            n nVar = new n(this.a[i], i3, (i3 + i4) - i2);
            if (cVar.a == null) {
                nVar.g = nVar;
                nVar.f = nVar;
                cVar.a = nVar;
            } else {
                cVar.a.g.a(nVar);
            }
            i++;
            i2 = i4;
        }
        cVar.b = ((long) i2) + cVar.b;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            int i4 = a == 0 ? 0 : this.b[a - 1];
            int min = Math.min(i3, ((this.b[a] - i4) + i4) - i);
            if (!byteString.rangeEquals(i2, this.a[a], (i - i4) + this.b[this.a.length + a], min)) {
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
            int i4 = a == 0 ? 0 : this.b[a - 1];
            int min = Math.min(i3, ((this.b[a] - i4) + i4) - i);
            if (!t.a(this.a[a], (i - i4) + this.b[this.a.length + a], bArr, i2, min)) {
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
        return a().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return a().lastIndexOf(bArr, i);
    }

    private ByteString a() {
        return new ByteString(toByteArray());
    }

    byte[] internalArray() {
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
        int i = this.hashCode;
        if (i == 0) {
            i = 1;
            int length = this.a.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.a[i2];
                int i4 = this.b[length + i2];
                int i5 = this.b[i2];
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
            this.hashCode = i;
        }
        return i;
    }

    public String toString() {
        return a().toString();
    }

    private Object writeReplace() {
        return a();
    }
}
