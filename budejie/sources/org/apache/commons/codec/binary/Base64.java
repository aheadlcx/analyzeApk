package org.apache.commons.codec.binary;

import com.alibaba.fastjson.parser.JSONLexer;
import com.umeng.analytics.pro.dm;
import java.math.BigInteger;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Base64 implements BinaryDecoder, BinaryEncoder {
    static final byte[] CHUNK_SEPARATOR = new byte[]{dm.k, (byte) 10};
    static final int CHUNK_SIZE = 76;
    private static final byte[] DECODE_TABLE = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) 62, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, PAD, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, dm.k, dm.l, dm.m, dm.n, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) -1, JSONLexer.EOI, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51};
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int MASK_6BITS = 63;
    private static final int MASK_8BITS = 255;
    private static final byte PAD = (byte) 61;
    private static final byte[] STANDARD_ENCODE_TABLE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private byte[] buffer;
    private int currentLinePos;
    private final int decodeSize;
    private final int encodeSize;
    private final byte[] encodeTable;
    private boolean eof;
    private final int lineLength;
    private final byte[] lineSeparator;
    private int modulus;
    private int pos;
    private int readPos;
    private int x;

    public Base64() {
        this(false);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        if (bArr == null) {
            bArr = CHUNK_SEPARATOR;
            i = 0;
        }
        this.lineLength = i > 0 ? (i / 4) * 4 : 0;
        this.lineSeparator = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.lineSeparator, 0, bArr.length);
        if (i > 0) {
            this.encodeSize = bArr.length + 4;
        } else {
            this.encodeSize = 4;
        }
        this.decodeSize = this.encodeSize - 1;
        if (containsBase64Byte(bArr)) {
            throw new IllegalArgumentException(new StringBuffer().append("lineSeperator must not contain base64 characters: [").append(StringUtils.newStringUtf8(bArr)).append("]").toString());
        }
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    boolean hasData() {
        return this.buffer != null;
    }

    int avail() {
        return this.buffer != null ? this.pos - this.readPos : 0;
    }

    private void resizeBuffer() {
        if (this.buffer == null) {
            this.buffer = new byte[8192];
            this.pos = 0;
            this.readPos = 0;
            return;
        }
        Object obj = new byte[(this.buffer.length * 2)];
        System.arraycopy(this.buffer, 0, obj, 0, this.buffer.length);
        this.buffer = obj;
    }

    int readResults(byte[] bArr, int i, int i2) {
        if (this.buffer == null) {
            return this.eof ? -1 : 0;
        } else {
            int min = Math.min(avail(), i2);
            if (this.buffer != bArr) {
                System.arraycopy(this.buffer, this.readPos, bArr, i, min);
                this.readPos += min;
                if (this.readPos < this.pos) {
                    return min;
                }
                this.buffer = null;
                return min;
            }
            this.buffer = null;
            return min;
        }
    }

    void setInitialBuffer(byte[] bArr, int i, int i2) {
        if (bArr != null && bArr.length == i2) {
            this.buffer = bArr;
            this.pos = i;
            this.readPos = i;
        }
    }

    void encode(byte[] bArr, int i, int i2) {
        if (!this.eof) {
            byte[] bArr2;
            int i3;
            if (i2 < 0) {
                this.eof = true;
                if (this.buffer == null || this.buffer.length - this.pos < this.encodeSize) {
                    resizeBuffer();
                }
                switch (this.modulus) {
                    case 1:
                        bArr2 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr2[i3] = this.encodeTable[(this.x >> 2) & 63];
                        bArr2 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr2[i3] = this.encodeTable[(this.x << 4) & 63];
                        if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                            bArr2 = this.buffer;
                            i3 = this.pos;
                            this.pos = i3 + 1;
                            bArr2[i3] = PAD;
                            bArr2 = this.buffer;
                            i3 = this.pos;
                            this.pos = i3 + 1;
                            bArr2[i3] = PAD;
                            break;
                        }
                        break;
                    case 2:
                        bArr2 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr2[i3] = this.encodeTable[(this.x >> 10) & 63];
                        bArr2 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr2[i3] = this.encodeTable[(this.x >> 4) & 63];
                        bArr2 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr2[i3] = this.encodeTable[(this.x << 2) & 63];
                        if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                            bArr2 = this.buffer;
                            i3 = this.pos;
                            this.pos = i3 + 1;
                            bArr2[i3] = PAD;
                            break;
                        }
                        break;
                }
                if (this.lineLength > 0 && this.pos > 0) {
                    System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
                    this.pos += this.lineSeparator.length;
                    return;
                }
                return;
            }
            i3 = 0;
            while (i3 < i2) {
                if (this.buffer == null || this.buffer.length - this.pos < this.encodeSize) {
                    resizeBuffer();
                }
                int i4 = this.modulus + 1;
                this.modulus = i4;
                this.modulus = i4 % 3;
                int i5 = i + 1;
                i4 = bArr[i];
                if (i4 < 0) {
                    i4 += 256;
                }
                this.x = i4 + (this.x << 8);
                if (this.modulus == 0) {
                    bArr2 = this.buffer;
                    int i6 = this.pos;
                    this.pos = i6 + 1;
                    bArr2[i6] = this.encodeTable[(this.x >> 18) & 63];
                    bArr2 = this.buffer;
                    i6 = this.pos;
                    this.pos = i6 + 1;
                    bArr2[i6] = this.encodeTable[(this.x >> 12) & 63];
                    bArr2 = this.buffer;
                    i6 = this.pos;
                    this.pos = i6 + 1;
                    bArr2[i6] = this.encodeTable[(this.x >> 6) & 63];
                    bArr2 = this.buffer;
                    i6 = this.pos;
                    this.pos = i6 + 1;
                    bArr2[i6] = this.encodeTable[this.x & 63];
                    this.currentLinePos += 4;
                    if (this.lineLength > 0 && this.lineLength <= this.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
                        this.pos += this.lineSeparator.length;
                        this.currentLinePos = 0;
                    }
                }
                i3++;
                i = i5;
            }
        }
    }

    void decode(byte[] bArr, int i, int i2) {
        if (!this.eof) {
            int i3;
            if (i2 < 0) {
                this.eof = true;
            }
            int i4 = 0;
            while (i4 < i2) {
                if (this.buffer == null || this.buffer.length - this.pos < this.decodeSize) {
                    resizeBuffer();
                }
                i3 = i + 1;
                byte b = bArr[i];
                if (b == PAD) {
                    this.eof = true;
                    break;
                }
                if (b >= (byte) 0 && b < DECODE_TABLE.length) {
                    b = DECODE_TABLE[b];
                    if (b >= (byte) 0) {
                        int i5 = this.modulus + 1;
                        this.modulus = i5;
                        this.modulus = i5 % 4;
                        this.x = b + (this.x << 6);
                        if (this.modulus == 0) {
                            byte[] bArr2 = this.buffer;
                            i5 = this.pos;
                            this.pos = i5 + 1;
                            bArr2[i5] = (byte) ((this.x >> 16) & 255);
                            bArr2 = this.buffer;
                            i5 = this.pos;
                            this.pos = i5 + 1;
                            bArr2[i5] = (byte) ((this.x >> 8) & 255);
                            bArr2 = this.buffer;
                            i5 = this.pos;
                            this.pos = i5 + 1;
                            bArr2[i5] = (byte) (this.x & 255);
                        }
                    }
                }
                i4++;
                i = i3;
            }
            if (this.eof && this.modulus != 0) {
                this.x <<= 6;
                byte[] bArr3;
                switch (this.modulus) {
                    case 2:
                        this.x <<= 6;
                        bArr3 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr3[i3] = (byte) ((this.x >> 16) & 255);
                        return;
                    case 3:
                        bArr3 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr3[i3] = (byte) ((this.x >> 16) & 255);
                        bArr3 = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 + 1;
                        bArr3[i3] = (byte) ((this.x >> 8) & 255);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static boolean isBase64(byte b) {
        return b == PAD || (b >= (byte) 0 && b < DECODE_TABLE.length && DECODE_TABLE[b] != (byte) -1);
    }

    public static boolean isArrayByteBase64(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            if (!isBase64(bArr[i]) && !isWhiteSpace(bArr[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean containsBase64Byte(byte[] bArr) {
        for (byte isBase64 : bArr) {
            if (isBase64(isBase64)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, true));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base64 decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    public byte[] decode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[((int) ((long) ((bArr.length * 3) / 4)))];
        setInitialBuffer(bArr2, 0, bArr2.length);
        decode(bArr, 0, bArr.length);
        decode(bArr, 0, -1);
        bArr = new byte[this.pos];
        readResults(bArr, 0, bArr.length);
        return bArr;
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        long encodeLength = getEncodeLength(bArr, 76, CHUNK_SEPARATOR);
        if (encodeLength > ((long) i)) {
            throw new IllegalArgumentException(new StringBuffer().append("Input array too big, the output array would be bigger (").append(encodeLength).append(") than the specified maxium size of ").append(i).toString());
        }
        return (z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2)).encode(bArr);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    static byte[] discardWhitespace(byte[] bArr) {
        Object obj = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            switch (bArr[i2]) {
                case (byte) 9:
                case (byte) 10:
                case (byte) 13:
                case (byte) 32:
                    break;
                default:
                    int i3 = i + 1;
                    obj[i] = bArr[i2];
                    i = i3;
                    break;
            }
        }
        Object obj2 = new byte[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    private static boolean isWhiteSpace(byte b) {
        switch (b) {
            case (byte) 9:
            case (byte) 10:
            case (byte) 13:
            case (byte) 32:
                return true;
            default:
                return false;
        }
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public byte[] encode(byte[] bArr) {
        reset();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Object obj = new byte[((int) getEncodeLength(bArr, this.lineLength, this.lineSeparator))];
        setInitialBuffer(obj, 0, obj.length);
        encode(bArr, 0, bArr.length);
        encode(bArr, 0, -1);
        if (this.buffer != obj) {
            readResults(obj, 0, obj.length);
        }
        if (!isUrlSafe() || this.pos >= obj.length) {
            return obj;
        }
        byte[] bArr2 = new byte[this.pos];
        System.arraycopy(obj, 0, bArr2, 0, this.pos);
        return bArr2;
    }

    private static long getEncodeLength(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (i / 4) * 4;
        long length = (long) ((bArr.length * 4) / 3);
        long j = length % 4;
        if (j != 0) {
            length += 4 - j;
        }
        if (i2 > 0) {
            Object obj = length % ((long) i2) == 0 ? 1 : null;
            length += (length / ((long) i2)) * ((long) bArr2.length);
            if (obj == null) {
                return ((long) bArr2.length) + length;
            }
        }
        return length;
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger != null) {
            return encodeBase64(toIntegerBytes(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        Object toByteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == bitLength / 8) {
            return toByteArray;
        }
        int i = 0;
        int length = toByteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            i = 1;
            length--;
        }
        Object obj = new byte[(bitLength / 8)];
        System.arraycopy(toByteArray, i, obj, (bitLength / 8) - length, length);
        return obj;
    }

    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }
}
