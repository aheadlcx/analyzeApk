package com.alibaba.fastjson.util;

import cn.v6.sixrooms.constants.CommonInts;
import com.alibaba.fastjson.JSONException;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

public class IOUtils {
    public static final char[] ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final char[] DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    public static final int[] IA = new int[256];
    private static final ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal();
    private static final ThreadLocal<CharsetDecoder> decoderLocal = new ThreadLocal();
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];
    public static final char[] replaceChars = new char[93];
    static final int[] sizeTable = new int[]{9, 99, 999, CommonInts.USER_MANAGER_REQUEST_CODE, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    public static final byte[] specicalFlags_doubleQuotes = new byte[161];
    public static final boolean[] specicalFlags_doubleQuotesFlags = new boolean[161];
    public static final byte[] specicalFlags_singleQuotes = new byte[161];
    public static final boolean[] specicalFlags_singleQuotesFlags = new boolean[161];

    static {
        int i = 0;
        while (i < firstIdentifierFlags.length) {
            if (i >= 65 && i <= 90) {
                firstIdentifierFlags[i] = true;
            } else if (i >= 97 && i <= 122) {
                firstIdentifierFlags[i] = true;
            } else if (i == 95) {
                firstIdentifierFlags[i] = true;
            }
            i = (char) (i + 1);
        }
        i = 0;
        while (i < identifierFlags.length) {
            if (i >= 65 && i <= 90) {
                identifierFlags[i] = true;
            } else if (i >= 97 && i <= 122) {
                identifierFlags[i] = true;
            } else if (i == 95) {
                identifierFlags[i] = true;
            } else if (i >= 48 && i <= 57) {
                identifierFlags[i] = true;
            }
            i = (char) (i + 1);
        }
        specicalFlags_doubleQuotes[0] = (byte) 4;
        specicalFlags_doubleQuotes[1] = (byte) 4;
        specicalFlags_doubleQuotes[2] = (byte) 4;
        specicalFlags_doubleQuotes[3] = (byte) 4;
        specicalFlags_doubleQuotes[4] = (byte) 4;
        specicalFlags_doubleQuotes[5] = (byte) 4;
        specicalFlags_doubleQuotes[6] = (byte) 4;
        specicalFlags_doubleQuotes[7] = (byte) 4;
        specicalFlags_doubleQuotes[8] = (byte) 1;
        specicalFlags_doubleQuotes[9] = (byte) 1;
        specicalFlags_doubleQuotes[10] = (byte) 1;
        specicalFlags_doubleQuotes[11] = (byte) 4;
        specicalFlags_doubleQuotes[12] = (byte) 1;
        specicalFlags_doubleQuotes[13] = (byte) 1;
        specicalFlags_doubleQuotes[34] = (byte) 1;
        specicalFlags_doubleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[0] = (byte) 4;
        specicalFlags_singleQuotes[1] = (byte) 4;
        specicalFlags_singleQuotes[2] = (byte) 4;
        specicalFlags_singleQuotes[3] = (byte) 4;
        specicalFlags_singleQuotes[4] = (byte) 4;
        specicalFlags_singleQuotes[5] = (byte) 4;
        specicalFlags_singleQuotes[6] = (byte) 4;
        specicalFlags_singleQuotes[7] = (byte) 4;
        specicalFlags_singleQuotes[8] = (byte) 1;
        specicalFlags_singleQuotes[9] = (byte) 1;
        specicalFlags_singleQuotes[10] = (byte) 1;
        specicalFlags_singleQuotes[11] = (byte) 4;
        specicalFlags_singleQuotes[12] = (byte) 1;
        specicalFlags_singleQuotes[13] = (byte) 1;
        specicalFlags_singleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[39] = (byte) 1;
        for (i = 14; i <= 31; i++) {
            specicalFlags_doubleQuotes[i] = (byte) 4;
            specicalFlags_singleQuotes[i] = (byte) 4;
        }
        for (i = 127; i <= 160; i++) {
            specicalFlags_doubleQuotes[i] = (byte) 4;
            specicalFlags_singleQuotes[i] = (byte) 4;
        }
        for (int i2 = 0; i2 < 161; i2++) {
            boolean z;
            boolean[] zArr = specicalFlags_doubleQuotesFlags;
            if (specicalFlags_doubleQuotes[i2] != (byte) 0) {
                z = true;
            } else {
                z = false;
            }
            zArr[i2] = z;
            zArr = specicalFlags_singleQuotesFlags;
            if (specicalFlags_singleQuotes[i2] != (byte) 0) {
                z = true;
            } else {
                z = false;
            }
            zArr[i2] = z;
        }
        replaceChars[0] = '0';
        replaceChars[1] = '1';
        replaceChars[2] = '2';
        replaceChars[3] = '3';
        replaceChars[4] = '4';
        replaceChars[5] = '5';
        replaceChars[6] = '6';
        replaceChars[7] = '7';
        replaceChars[8] = 'b';
        replaceChars[9] = 't';
        replaceChars[10] = 'n';
        replaceChars[11] = 'v';
        replaceChars[12] = 'f';
        replaceChars[13] = 'r';
        replaceChars[34] = '\"';
        replaceChars[39] = '\'';
        replaceChars[47] = '/';
        replaceChars[92] = '\\';
        Arrays.fill(IA, -1);
        int length = CA.length;
        for (i = 0; i < length; i++) {
            IA[CA[i]] = i;
        }
        IA[61] = 0;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static int stringSize(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    public static void getChars(long j, int i, char[] cArr) {
        char c;
        int i2;
        int i3;
        int i4;
        if (j < 0) {
            j = -j;
            c = '-';
            i2 = i;
        } else {
            c = '\u0000';
            i2 = i;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            i3 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            i2--;
            cArr[i2] = DigitOnes[i3];
            i = i2 - 1;
            cArr[i] = DigitTens[i3];
            i2 = i;
            j = j2;
        }
        i3 = (int) j;
        while (i3 >= 65536) {
            i4 = i3 / 100;
            i3 -= ((i4 << 6) + (i4 << 5)) + (i4 << 2);
            i2--;
            cArr[i2] = DigitOnes[i3];
            i2--;
            cArr[i2] = DigitTens[i3];
            i3 = i4;
        }
        i4 = i2;
        while (true) {
            i2 = (52429 * i3) >>> 19;
            int i5 = i3 - ((i2 << 3) + (i2 << 1));
            i3 = i4 - 1;
            cArr[i3] = digits[i5];
            if (i2 == 0) {
                break;
            }
            i4 = i3;
            i3 = i2;
        }
        if (c != '\u0000') {
            cArr[i3 - 1] = c;
        }
    }

    public static void getChars(int i, int i2, char[] cArr) {
        int i3;
        char c;
        int i4;
        if (i < 0) {
            i3 = -i;
            c = '-';
            i4 = i2;
        } else {
            c = '\u0000';
            i3 = i;
            i4 = i2;
        }
        while (i3 >= 65536) {
            i = i3 / 100;
            i3 -= ((i << 6) + (i << 5)) + (i << 2);
            i4--;
            cArr[i4] = DigitOnes[i3];
            i2 = i4 - 1;
            cArr[i2] = DigitTens[i3];
            i4 = i2;
            i3 = i;
        }
        while (true) {
            int i5 = (52429 * i3) >>> 19;
            i4--;
            cArr[i4] = digits[i3 - ((i5 << 3) + (i5 << 1))];
            if (i5 == 0) {
                break;
            }
            i3 = i5;
        }
        if (c != '\u0000') {
            cArr[i4 - 1] = c;
        }
    }

    public static void getChars(byte b, int i, char[] cArr) {
        char c = '\u0000';
        if (b < (byte) 0) {
            c = '-';
            b = -b;
        }
        while (true) {
            byte b2 = (52429 * b) >>> 19;
            i--;
            cArr[i] = digits[b - ((b2 << 3) + (b2 << 1))];
            if (b2 == (byte) 0) {
                break;
            }
            b = b2;
        }
        if (c != '\u0000') {
            cArr[i - 1] = c;
        }
    }

    public static int stringSize(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            decode = charsetDecoder.flush(charBuffer);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
        } catch (Throwable e) {
            throw new JSONException("utf8 decode error, " + e.getMessage(), e);
        }
    }

    public static boolean firstIdentifier(char c) {
        return c < firstIdentifierFlags.length && firstIdentifierFlags[c];
    }

    public static boolean isIdent(char c) {
        return c < identifierFlags.length && identifierFlags[c];
    }

    public static byte[] decodeFast(char[] cArr, int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i4 = (i + i2) - 1;
        int i5 = i;
        while (i5 < i4 && IA[cArr[i5]] < 0) {
            i5++;
        }
        int i6 = i4;
        while (i6 > 0 && IA[cArr[i6]] < 0) {
            i6--;
        }
        int i7 = cArr[i6] == '=' ? cArr[i6 + -1] == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i5) + 1;
        if (i2 > 76) {
            if (cArr[76] == '\r') {
                i4 = i8 / 78;
            } else {
                i4 = 0;
            }
            i4 <<= 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        i8 = 0;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            i5 = (IA[cArr[i5]] << 18) | (IA[cArr[i12]] << 12);
            i12 = i13 + 1;
            int i14 = (IA[cArr[i13]] << 6) | i5;
            i5 = i12 + 1;
            i14 |= IA[cArr[i12]];
            i12 = i11 + 1;
            bArr[i11] = (byte) (i14 >> 16);
            i13 = i12 + 1;
            bArr[i12] = (byte) (i14 >> 8);
            i11 = i13 + 1;
            bArr[i13] = (byte) i14;
            if (i4 > 0) {
                i8++;
                if (i8 == 19) {
                    i5 += 2;
                    i8 = 0;
                }
            }
        }
        if (i11 < i9) {
            i5 = 0;
            for (i4 = i5; i4 <= i6 - i7; i4++) {
                i3++;
                i5 = (IA[cArr[i4]] << (18 - (i3 * 6))) | i5;
            }
            i4 = 16;
            i3 = i11;
            while (i3 < i9) {
                i8 = i3 + 1;
                bArr[i3] = (byte) (i5 >> i4);
                i4 -= 8;
                i3 = i8;
            }
        }
        return bArr;
    }

    public static byte[] decodeFast(String str, int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i4 = (i + i2) - 1;
        int i5 = i;
        while (i5 < i4 && IA[str.charAt(i5)] < 0) {
            i5++;
        }
        int i6 = i4;
        while (i6 > 0 && IA[str.charAt(i6)] < 0) {
            i6--;
        }
        int i7 = str.charAt(i6) == '=' ? str.charAt(i6 + -1) == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i5) + 1;
        if (i2 > 76) {
            if (str.charAt(76) == '\r') {
                i4 = i8 / 78;
            } else {
                i4 = 0;
            }
            i4 <<= 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        i8 = 0;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            i5 = (IA[str.charAt(i5)] << 18) | (IA[str.charAt(i12)] << 12);
            i12 = i13 + 1;
            int i14 = (IA[str.charAt(i13)] << 6) | i5;
            i5 = i12 + 1;
            i14 |= IA[str.charAt(i12)];
            i12 = i11 + 1;
            bArr[i11] = (byte) (i14 >> 16);
            i13 = i12 + 1;
            bArr[i12] = (byte) (i14 >> 8);
            i11 = i13 + 1;
            bArr[i13] = (byte) i14;
            if (i4 > 0) {
                i8++;
                if (i8 == 19) {
                    i5 += 2;
                    i8 = 0;
                }
            }
        }
        if (i11 < i9) {
            i5 = 0;
            for (i4 = i5; i4 <= i6 - i7; i4++) {
                i3++;
                i5 = (IA[str.charAt(i4)] << (18 - (i3 * 6))) | i5;
            }
            i4 = 16;
            i3 = i11;
            while (i3 < i9) {
                i8 = i3 + 1;
                bArr[i3] = (byte) (i5 >> i4);
                i4 -= 8;
                i3 = i8;
            }
        }
        return bArr;
    }

    public static byte[] decodeFast(String str) {
        int i = 0;
        int length = str.length();
        if (length == 0) {
            return new byte[0];
        }
        int i2 = length - 1;
        int i3 = 0;
        while (i3 < i2 && IA[str.charAt(i3) & 255] < 0) {
            i3++;
        }
        int i4 = i2;
        while (i4 > 0 && IA[str.charAt(i4) & 255] < 0) {
            i4--;
        }
        int i5 = str.charAt(i4) == '=' ? str.charAt(i4 + -1) == '=' ? 2 : 1 : 0;
        int i6 = (i4 - i3) + 1;
        if (length > 76) {
            if (str.charAt(76) == '\r') {
                i2 = i6 / 78;
            } else {
                i2 = 0;
            }
            i2 <<= 1;
        } else {
            i2 = 0;
        }
        int i7 = (((i6 - i2) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        length = 0;
        int i9 = 0;
        while (i9 < i8) {
            int i10 = i3 + 1;
            int i11 = i10 + 1;
            i3 = (IA[str.charAt(i3)] << 18) | (IA[str.charAt(i10)] << 12);
            i10 = i11 + 1;
            int i12 = (IA[str.charAt(i11)] << 6) | i3;
            i3 = i10 + 1;
            i12 |= IA[str.charAt(i10)];
            i10 = i9 + 1;
            bArr[i9] = (byte) (i12 >> 16);
            i11 = i10 + 1;
            bArr[i10] = (byte) (i12 >> 8);
            i9 = i11 + 1;
            bArr[i11] = (byte) i12;
            if (i2 > 0) {
                length++;
                if (length == 19) {
                    i3 += 2;
                    length = 0;
                }
            }
        }
        if (i9 < i7) {
            i3 = 0;
            for (i2 = i3; i2 <= i4 - i5; i2++) {
                i++;
                i3 = (IA[str.charAt(i2)] << (18 - (i * 6))) | i3;
            }
            i2 = 16;
            i = i9;
            while (i < i7) {
                length = i + 1;
                bArr[i] = (byte) (i3 >> i2);
                i2 -= 8;
                i = length;
            }
        }
        return bArr;
    }

    public static CharsetDecoder getUTF8Decoder() {
        CharsetDecoder charsetDecoder = (CharsetDecoder) decoderLocal.get();
        if (charsetDecoder != null) {
            return charsetDecoder;
        }
        charsetDecoder = new UTF8Decoder();
        decoderLocal.set(charsetDecoder);
        return charsetDecoder;
    }

    public static void clearChars() {
        charsBufLocal.set(null);
    }

    public static char[] getChars(int i) {
        SoftReference softReference = (SoftReference) charsBufLocal.get();
        if (softReference == null) {
            return allocate(i);
        }
        char[] cArr = (char[]) softReference.get();
        if (cArr == null) {
            return allocate(i);
        }
        if (cArr.length < i) {
            return allocate(i);
        }
        return cArr;
    }

    private static char[] allocate(int i) {
        if (i > 131072) {
            return new char[i];
        }
        int i2;
        if ((i >>> 10) <= 0) {
            i2 = 1024;
        } else {
            i2 = 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
        }
        char[] cArr = new char[i2];
        charsBufLocal.set(new SoftReference(cArr));
        return cArr;
    }

    public static String toString(InputStream inputStream) throws Exception {
        return readAll(new BufferedReader(new InputStreamReader(inputStream)));
    }

    public static String readAll(Reader reader) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int read = reader.read(cArr, 0, cArr.length);
                if (read < 0) {
                    return stringBuilder.toString();
                }
                stringBuilder.append(cArr, 0, read);
            }
        } catch (Throwable e) {
            throw new JSONException("read string from reader error", e);
        }
    }
}
