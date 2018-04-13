package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.imageutils.JfifUtil;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.text.Typography;

public final class Buffer implements Cloneable, BufferedSink, BufferedSource {
    private static final byte[] c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    @Nullable
    n a;
    long b;

    public long size() {
        return this.b;
    }

    public Buffer buffer() {
        return this;
    }

    public OutputStream outputStream() {
        return new d(this);
    }

    public Buffer emitCompleteSegments() {
        return this;
    }

    public BufferedSink emit() {
        return this;
    }

    public boolean exhausted() {
        return this.b == 0;
    }

    public void require(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        return this.b >= j;
    }

    public InputStream inputStream() {
        return new e(this);
    }

    public Buffer copyTo(OutputStream outputStream) throws IOException {
        return copyTo(outputStream, 0, this.b);
    }

    public Buffer copyTo(OutputStream outputStream, long j, long j2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        r.checkOffsetAndCount(this.b, j, j2);
        if (j2 != 0) {
            n nVar = this.a;
            while (j >= ((long) (nVar.c - nVar.b))) {
                j -= (long) (nVar.c - nVar.b);
                nVar = nVar.f;
            }
            while (j2 > 0) {
                int i = (int) (((long) nVar.b) + j);
                int min = (int) Math.min((long) (nVar.c - i), j2);
                outputStream.write(nVar.a, i, min);
                j2 -= (long) min;
                nVar = nVar.f;
                j = 0;
            }
        }
        return this;
    }

    public Buffer copyTo(Buffer buffer, long j, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        r.checkOffsetAndCount(this.b, j, j2);
        if (j2 != 0) {
            buffer.b += j2;
            n nVar = this.a;
            while (j >= ((long) (nVar.c - nVar.b))) {
                j -= (long) (nVar.c - nVar.b);
                nVar = nVar.f;
            }
            while (j2 > 0) {
                n nVar2 = new n(nVar);
                nVar2.b = (int) (((long) nVar2.b) + j);
                nVar2.c = Math.min(nVar2.b + ((int) j2), nVar2.c);
                if (buffer.a == null) {
                    nVar2.g = nVar2;
                    nVar2.f = nVar2;
                    buffer.a = nVar2;
                } else {
                    buffer.a.g.push(nVar2);
                }
                j2 -= (long) (nVar2.c - nVar2.b);
                nVar = nVar.f;
                j = 0;
            }
        }
        return this;
    }

    public Buffer writeTo(OutputStream outputStream) throws IOException {
        return writeTo(outputStream, this.b);
    }

    public Buffer writeTo(OutputStream outputStream, long j) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        r.checkOffsetAndCount(this.b, 0, j);
        n nVar = this.a;
        while (j > 0) {
            n pop;
            int min = (int) Math.min(j, (long) (nVar.c - nVar.b));
            outputStream.write(nVar.a, nVar.b, min);
            nVar.b += min;
            this.b -= (long) min;
            j -= (long) min;
            if (nVar.b == nVar.c) {
                pop = nVar.pop();
                this.a = pop;
                o.a(nVar);
            } else {
                pop = nVar;
            }
            nVar = pop;
        }
        return this;
    }

    public Buffer readFrom(InputStream inputStream) throws IOException {
        a(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public Buffer readFrom(InputStream inputStream, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        a(inputStream, j, false);
        return this;
    }

    private void a(InputStream inputStream, long j, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j > 0 || z) {
                n a = a(1);
                int read = inputStream.read(a.a, a.c, (int) Math.min(j, (long) (8192 - a.c)));
                if (read == -1) {
                    break;
                }
                a.c += read;
                this.b += (long) read;
                j -= (long) read;
            } else {
                return;
            }
        }
        if (!z) {
            throw new EOFException();
        }
    }

    public long completeSegmentByteCount() {
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

    public byte readByte() {
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
            this.a = nVar.pop();
            o.a(nVar);
        } else {
            nVar.b = i3;
        }
        return b;
    }

    public byte getByte(long j) {
        r.checkOffsetAndCount(this.b, j, 1);
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

    public short readShort() {
        if (this.b < 2) {
            throw new IllegalStateException("size < 2: " + this.b);
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = nVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        i = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b -= 2;
        if (i4 == i2) {
            this.a = nVar.pop();
            o.a(nVar);
        } else {
            nVar.b = i4;
        }
        return (short) i;
    }

    public int readInt() {
        if (this.b < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        if (i2 - i < 4) {
            return ((((readByte() & 255) << 24) | ((readByte() & 255) << 16)) | ((readByte() & 255) << 8)) | (readByte() & 255);
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
            this.a = nVar.pop();
            o.a(nVar);
            return i;
        }
        nVar.b = i4;
        return i;
    }

    public long readLong() {
        if (this.b < 8) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        n nVar = this.a;
        int i = nVar.b;
        int i2 = nVar.c;
        if (i2 - i < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (((long) readInt()) & 4294967295L);
        }
        byte[] bArr = nVar.a;
        int i3 = i + 1;
        long j = (((long) bArr[i]) & 255) << 56;
        i = i3 + 1;
        long j2 = ((((long) bArr[i3]) & 255) << 48) | j;
        int i4 = i + 1;
        i = i4 + 1;
        j2 = (j2 | ((((long) bArr[i]) & 255) << 40)) | ((((long) bArr[i4]) & 255) << 32);
        i4 = i + 1;
        i = i4 + 1;
        j2 = (j2 | ((((long) bArr[i]) & 255) << 24)) | ((((long) bArr[i4]) & 255) << 16);
        i4 = i + 1;
        int i5 = i4 + 1;
        long j3 = (((long) bArr[i4]) & 255) | (j2 | ((((long) bArr[i]) & 255) << 8));
        this.b -= 8;
        if (i5 == i2) {
            this.a = nVar.pop();
            o.a(nVar);
            return j3;
        }
        nVar.b = i5;
        return j3;
    }

    public short readShortLe() {
        return r.reverseBytesShort(readShort());
    }

    public int readIntLe() {
        return r.reverseBytesInt(readInt());
    }

    public long readLongLe() {
        return r.reverseBytesLong(readLong());
    }

    public long readDecimalLong() {
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
                        Buffer writeByte = new Buffer().writeDecimalLong(j).writeByte(i4);
                        if (obj == null) {
                            writeByte.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + writeByte.readUtf8());
                    }
                    j = (j * 10) + ((long) i5);
                } else if (i4 != 45 || i != 0) {
                    if (i != 0) {
                        obj2 = 1;
                        if (i2 != i3) {
                            this.a = nVar.pop();
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
                this.a = nVar.pop();
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

    public long readHexadecimalUnsignedLong() {
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
                            this.a = nVar.pop();
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
                    throw new NumberFormatException("Number too large: " + new Buffer().writeHexadecimalUnsignedLong(j).writeByte((int) b).readUtf8());
                }
                i++;
                i4++;
                j = ((long) i2) | (j << 4);
            }
            if (i4 != i3) {
                nVar.b = i4;
            } else {
                this.a = nVar.pop();
                o.a(nVar);
            }
            if (obj == null) {
                break;
            }
        } while (this.a != null);
        this.b -= (long) i;
        return j;
    }

    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    public ByteString readByteString(long j) throws EOFException {
        return new ByteString(readByteArray(j));
    }

    public int select(Options options) {
        n nVar = this.a;
        if (nVar == null) {
            return options.indexOf(ByteString.EMPTY);
        }
        ByteString[] byteStringArr = options.a;
        int length = byteStringArr.length;
        int i = 0;
        while (i < length) {
            ByteString byteString = byteStringArr[i];
            if (this.b < ((long) byteString.size()) || !a(nVar, nVar.b, byteString, 0, byteString.size())) {
                i++;
            } else {
                try {
                    skip((long) byteString.size());
                    return i;
                } catch (EOFException e) {
                    throw new AssertionError(e);
                }
            }
        }
        return -1;
    }

    int a(Options options) {
        n nVar = this.a;
        ByteString[] byteStringArr = options.a;
        int length = byteStringArr.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = byteStringArr[i];
            int min = (int) Math.min(this.b, (long) byteString.size());
            if (min == 0 || a(nVar, nVar.b, byteString, 0, min)) {
                return i;
            }
        }
        return -1;
    }

    public void readFully(Buffer buffer, long j) throws EOFException {
        if (this.b < j) {
            buffer.write(this, this.b);
            throw new EOFException();
        } else {
            buffer.write(this, j);
        }
    }

    public long readAll(Sink sink) throws IOException {
        long j = this.b;
        if (j > 0) {
            sink.write(this, j);
        }
        return j;
    }

    public String readUtf8() {
        try {
            return readString(this.b, r.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String readUtf8(long j) throws EOFException {
        return readString(j, r.UTF_8);
    }

    public String readString(Charset charset) {
        try {
            return readString(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String readString(long j, Charset charset) throws EOFException {
        r.checkOffsetAndCount(this.b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return "";
        } else {
            n nVar = this.a;
            if (((long) nVar.b) + j > ((long) nVar.c)) {
                return new String(readByteArray(j), charset);
            }
            String str = new String(nVar.a, nVar.b, (int) j, charset);
            nVar.b = (int) (((long) nVar.b) + j);
            this.b -= j;
            if (nVar.b != nVar.c) {
                return str;
            }
            this.a = nVar.pop();
            o.a(nVar);
            return str;
        }
    }

    @Nullable
    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf == -1) {
            return this.b != 0 ? readUtf8(this.b) : null;
        } else {
            return a(indexOf);
        }
    }

    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public String readUtf8LineStrict(long j) throws EOFException {
        long j2 = Long.MAX_VALUE;
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        if (j != Long.MAX_VALUE) {
            j2 = j + 1;
        }
        long indexOf = indexOf((byte) 10, 0, j2);
        if (indexOf != -1) {
            return a(indexOf);
        }
        if (j2 < size() && getByte(j2 - 1) == (byte) 13 && getByte(j2) == (byte) 10) {
            return a(j2);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0, Math.min(32, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
    }

    String a(long j) throws EOFException {
        if (j <= 0 || getByte(j - 1) != (byte) 13) {
            String readUtf8 = readUtf8(j);
            skip(1);
            return readUtf8;
        }
        readUtf8 = readUtf8(j - 1);
        skip(2);
        return readUtf8;
    }

    public int readUtf8CodePoint() throws EOFException {
        if (this.b == 0) {
            throw new EOFException();
        }
        int i;
        int i2;
        int i3;
        byte b = getByte(0);
        if ((b & 128) == 0) {
            i = 0;
            i2 = b & 127;
            i3 = 1;
        } else if ((b & 224) == JfifUtil.MARKER_SOFn) {
            i2 = b & 31;
            i3 = 2;
            i = 128;
        } else if ((b & 240) == 224) {
            i2 = b & 15;
            i3 = 3;
            i = 2048;
        } else if ((b & 248) == 240) {
            i2 = b & 7;
            i3 = 4;
            i = 65536;
        } else {
            skip(1);
            return 65533;
        }
        if (this.b < ((long) i3)) {
            throw new EOFException("size < " + i3 + ": " + this.b + " (to read code point prefixed 0x" + Integer.toHexString(b) + ")");
        }
        int i4 = i2;
        i2 = 1;
        while (i2 < i3) {
            b = getByte((long) i2);
            if ((b & JfifUtil.MARKER_SOFn) == 128) {
                i2++;
                i4 = (b & 63) | (i4 << 6);
            } else {
                skip((long) i2);
                return 65533;
            }
        }
        skip((long) i3);
        if (i4 > 1114111) {
            return 65533;
        }
        if (i4 >= GeneratorBase.SURR1_FIRST && i4 <= GeneratorBase.SURR2_LAST) {
            return 65533;
        }
        if (i4 < i) {
            return 65533;
        }
        return i4;
    }

    public byte[] readByteArray() {
        try {
            return readByteArray(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] readByteArray(long j) throws EOFException {
        r.checkOffsetAndCount(this.b, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        readFully(bArr);
        return bArr;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        r.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
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
        this.a = nVar.pop();
        o.a(nVar);
        return min;
    }

    public void clear() {
        try {
            skip(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public void skip(long j) throws EOFException {
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
                this.a = nVar2.pop();
                o.a(nVar2);
            }
        }
    }

    public Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.a(this);
        return this;
    }

    public Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    public Buffer writeUtf8(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
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
                    n a = a(1);
                    byte[] bArr = a.a;
                    int i5 = a.c - i;
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
                    i4 = (i3 + i5) - a.c;
                    a.c += i4;
                    this.b += (long) i4;
                } else if (charAt < 'ࠀ') {
                    writeByte((charAt >> 6) | JfifUtil.MARKER_SOFn);
                    writeByte((charAt & 63) | 128);
                    i3 = i + 1;
                } else if (charAt < '?' || charAt > '?') {
                    writeByte((charAt >> 12) | 224);
                    writeByte(((charAt >> 6) & 63) | 128);
                    writeByte((charAt & 63) | 128);
                    i3 = i + 1;
                } else {
                    i3 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > CharCompanionObject.MAX_HIGH_SURROGATE || i3 < GeneratorBase.SURR2_FIRST || i3 > GeneratorBase.SURR2_LAST) {
                        writeByte(63);
                        i++;
                    } else {
                        i3 = ((i3 & -56321) | ((charAt & -55297) << 10)) + 65536;
                        writeByte((i3 >> 18) | 240);
                        writeByte(((i3 >> 12) & 63) | 128);
                        writeByte(((i3 >> 6) & 63) | 128);
                        writeByte((i3 & 63) | 128);
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return this;
        }
    }

    public Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | JfifUtil.MARKER_SOFn);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i < GeneratorBase.SURR1_FIRST || i > GeneratorBase.SURR2_LAST) {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            } else {
                writeByte(63);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    public Buffer writeString(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(r.UTF_8)) {
            return writeUtf8(str, i, i2);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return write(bytes, 0, bytes.length);
        }
    }

    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public Buffer write(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        r.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            n a = a(1);
            int min = Math.min(i3 - i, 8192 - a.c);
            System.arraycopy(bArr, i, a.a, a.c, min);
            i += min;
            a.c = min + a.c;
        }
        this.b += (long) i2;
        return this;
    }

    public long writeAll(Source source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = source.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public BufferedSink write(Source source, long j) throws IOException {
        while (j > 0) {
            long read = source.read(this, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return this;
    }

    public Buffer writeByte(int i) {
        n a = a(1);
        byte[] bArr = a.a;
        int i2 = a.c;
        a.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    public Buffer writeShort(int i) {
        n a = a(2);
        byte[] bArr = a.a;
        int i2 = a.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        a.c = i2;
        this.b += 2;
        return this;
    }

    public Buffer writeShortLe(int i) {
        return writeShort(r.reverseBytesShort((short) i));
    }

    public Buffer writeInt(int i) {
        n a = a(4);
        byte[] bArr = a.a;
        int i2 = a.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        a.c = i2;
        this.b += 4;
        return this;
    }

    public Buffer writeIntLe(int i) {
        return writeInt(r.reverseBytesInt(i));
    }

    public Buffer writeLong(long j) {
        n a = a(8);
        byte[] bArr = a.a;
        int i = a.c;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 40) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 32) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 16) & 255));
        i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 8) & 255));
        i = i2 + 1;
        bArr[i2] = (byte) ((int) (j & 255));
        a.c = i;
        this.b += 8;
        return this;
    }

    public Buffer writeLongLe(long j) {
        return writeLong(r.reverseBytesLong(j));
    }

    public Buffer writeDecimalLong(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        long j2;
        Object obj;
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return writeUtf8("-9223372036854775808");
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
        n a = a(i);
        byte[] bArr = a.a;
        int i2 = a.c + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = c[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (obj != null) {
            bArr[i2 - 1] = (byte) 45;
        }
        a.c += i;
        this.b = ((long) i) + this.b;
        return this;
    }

    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        n a = a(numberOfTrailingZeros);
        byte[] bArr = a.a;
        int i = a.c;
        for (int i2 = (a.c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = c[(int) (15 & j)];
            j >>>= 4;
        }
        a.c += numberOfTrailingZeros;
        this.b = ((long) numberOfTrailingZeros) + this.b;
        return this;
    }

    n a(int i) {
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
                return r0.push(o.a());
            }
            return r0;
        }
    }

    public void write(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        } else if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            r.checkOffsetAndCount(buffer.b, 0, j);
            while (j > 0) {
                n nVar;
                if (j < ((long) (buffer.a.c - buffer.a.b))) {
                    nVar = this.a != null ? this.a.g : null;
                    if (nVar != null && nVar.e) {
                        if ((((long) nVar.c) + j) - ((long) (nVar.d ? 0 : nVar.b)) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            buffer.a.writeTo(nVar, (int) j);
                            buffer.b -= j;
                            this.b += j;
                            return;
                        }
                    }
                    buffer.a = buffer.a.split((int) j);
                }
                n nVar2 = buffer.a;
                long j2 = (long) (nVar2.c - nVar2.b);
                buffer.a = nVar2.pop();
                if (this.a == null) {
                    this.a = nVar2;
                    nVar2 = this.a;
                    nVar = this.a;
                    n nVar3 = this.a;
                    nVar.g = nVar3;
                    nVar2.f = nVar3;
                } else {
                    this.a.g.push(nVar2).compact();
                }
                buffer.b -= j2;
                this.b += j2;
                j -= j2;
            }
        }
    }

    public long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.b == 0) {
            return -1;
        } else {
            if (j > this.b) {
                j = this.b;
            }
            buffer.write(this, j);
            return j;
        }
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j, long j2) {
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(this.b), Long.valueOf(j), Long.valueOf(j2)}));
        }
        if (j2 > this.b) {
            j2 = this.b;
        }
        if (j == j2) {
            return -1;
        }
        n nVar = this.a;
        if (nVar == null) {
            return -1;
        }
        long j3;
        n nVar2;
        long j4;
        if (this.b - j >= j) {
            j3 = 0;
            nVar2 = nVar;
            while (true) {
                j4 = ((long) (nVar2.c - nVar2.b)) + j3;
                if (j4 >= j) {
                    break;
                }
                nVar2 = nVar2.f;
                j3 = j4;
            }
        } else {
            j3 = this.b;
            nVar2 = nVar;
            while (j3 > j) {
                nVar2 = nVar2.g;
                j3 -= (long) (nVar2.c - nVar2.b);
            }
        }
        j4 = j3;
        while (j4 < j2) {
            byte[] bArr = nVar2.a;
            int min = (int) Math.min((long) nVar2.c, (((long) nVar2.b) + j2) - j4);
            for (int i = (int) ((((long) nVar2.b) + j) - j4); i < min; i++) {
                if (bArr[i] == b) {
                    return ((long) (i - nVar2.b)) + j4;
                }
            }
            j3 = ((long) (nVar2.c - nVar2.b)) + j4;
            nVar2 = nVar2.f;
            j4 = j3;
            j = j3;
        }
        return -1;
    }

    public long indexOf(ByteString byteString) throws IOException {
        return indexOf(byteString, 0);
    }

    public long indexOf(ByteString byteString, long j) throws IOException {
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        } else if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        } else {
            n nVar = this.a;
            if (nVar == null) {
                return -1;
            }
            long j2;
            n nVar2;
            long j3;
            if (this.b - j >= j) {
                j2 = 0;
                nVar2 = nVar;
                while (true) {
                    j3 = ((long) (nVar2.c - nVar2.b)) + j2;
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
            byte b = byteString.getByte(0);
            int size = byteString.size();
            long j4 = (this.b - ((long) size)) + 1;
            long j5 = j2;
            n nVar3 = nVar2;
            while (j5 < j4) {
                byte[] bArr = nVar3.a;
                int min = (int) Math.min((long) nVar3.c, (((long) nVar3.b) + j4) - j5);
                int i = (int) ((((long) nVar3.b) + j) - j5);
                while (i < min) {
                    if (bArr[i] == b && a(nVar3, i + 1, byteString, 1, size)) {
                        return ((long) (i - nVar3.b)) + j5;
                    }
                    i++;
                }
                j3 = ((long) (nVar3.c - nVar3.b)) + j5;
                nVar3 = nVar3.f;
                j5 = j3;
                j = j3;
            }
            return -1;
        }
    }

    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0);
    }

    public long indexOfElement(ByteString byteString, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        n nVar = this.a;
        if (nVar == null) {
            return -1;
        }
        long j2;
        n nVar2;
        if (this.b - j >= j) {
            j2 = 0;
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
        byte[] bArr;
        int i;
        int i2;
        byte b;
        if (byteString.size() == 2) {
            byte b2 = byteString.getByte(0);
            byte b3 = byteString.getByte(1);
            while (j2 < this.b) {
                bArr = nVar2.a;
                i = nVar2.c;
                for (i2 = (int) ((((long) nVar2.b) + j) - j2); i2 < i; i2++) {
                    b = bArr[i2];
                    if (b == b2 || b == b3) {
                        return j2 + ((long) (i2 - nVar2.b));
                    }
                }
                j2 += (long) (nVar2.c - nVar2.b);
                nVar2 = nVar2.f;
                j = j2;
            }
        } else {
            byte[] a = byteString.a();
            while (j2 < this.b) {
                bArr = nVar2.a;
                i2 = (int) ((((long) nVar2.b) + j) - j2);
                i = nVar2.c;
                for (int i3 = i2; i3 < i; i3++) {
                    b = bArr[i3];
                    for (byte b4 : a) {
                        if (b == b4) {
                            return j2 + ((long) (i3 - nVar2.b));
                        }
                    }
                }
                j2 += (long) (nVar2.c - nVar2.b);
                nVar2 = nVar2.f;
                j = j2;
            }
        }
        return -1;
    }

    public boolean rangeEquals(long j, ByteString byteString) {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.b - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (getByte(((long) i3) + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(n nVar, int i, ByteString byteString, int i2, int i3) {
        int i4 = nVar.c;
        byte[] bArr = nVar.a;
        int i5 = i;
        n nVar2 = nVar;
        while (i2 < i3) {
            if (i5 == i4) {
                nVar2 = nVar2.f;
                bArr = nVar2.a;
                i5 = nVar2.b;
                i4 = nVar2.c;
            }
            if (bArr[i5] != byteString.getByte(i2)) {
                return false;
            }
            i5++;
            i2++;
        }
        return true;
    }

    public void flush() {
    }

    public void close() {
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public ByteString md5() {
        return a("MD5");
    }

    public ByteString sha1() {
        return a("SHA-1");
    }

    public ByteString sha256() {
        return a("SHA-256");
    }

    public ByteString sha512() {
        return a("SHA-512");
    }

    private ByteString a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            if (this.a != null) {
                instance.update(this.a.a, this.a.b, this.a.c - this.a.b);
                for (n nVar = this.a.f; nVar != this.a; nVar = nVar.f) {
                    instance.update(nVar.a, nVar.b, nVar.c - nVar.b);
                }
            }
            return ByteString.of(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public ByteString hmacSha1(ByteString byteString) {
        return a("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return a("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        return a("HmacSHA512", byteString);
    }

    private ByteString a(String str, ByteString byteString) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(byteString.toByteArray(), str));
            if (this.a != null) {
                instance.update(this.a.a, this.a.b, this.a.c - this.a.b);
                for (n nVar = this.a.f; nVar != this.a; nVar = nVar.f) {
                    instance.update(nVar.a, nVar.b, nVar.c - nVar.b);
                }
            }
            return ByteString.of(instance.doFinal());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (Throwable e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        if (this.b != buffer.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        n nVar = this.a;
        n nVar2 = buffer.a;
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
        return snapshot().toString();
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.b == 0) {
            return buffer;
        }
        buffer.a = new n(this.a);
        n nVar = buffer.a;
        n nVar2 = buffer.a;
        n nVar3 = buffer.a;
        nVar2.g = nVar3;
        nVar.f = nVar3;
        for (nVar = this.a.f; nVar != this.a; nVar = nVar.f) {
            buffer.a.g.push(new n(nVar));
        }
        buffer.b = this.b;
        return buffer;
    }

    public ByteString snapshot() {
        if (this.b <= 2147483647L) {
            return snapshot((int) this.b);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }

    public ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new p(this, i);
    }
}
