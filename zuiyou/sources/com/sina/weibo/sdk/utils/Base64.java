package com.sina.weibo.sdk.utils;

public final class Base64 {
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static byte[] codes = new byte[256];

    static {
        int i;
        for (i = 0; i < 256; i++) {
            codes[i] = (byte) -1;
        }
        for (i = 65; i <= 90; i++) {
            codes[i] = (byte) (i - 65);
        }
        for (i = 97; i <= 122; i++) {
            codes[i] = (byte) ((i + 26) - 97);
        }
        for (i = 48; i <= 57; i++) {
            codes[i] = (byte) ((i + 52) - 48);
        }
        codes[43] = (byte) 62;
        codes[47] = (byte) 63;
    }

    public static byte[] decode(byte[] bArr) {
        int i = 0;
        int length = ((bArr.length + 3) / 4) * 3;
        if (bArr.length > 0 && bArr[bArr.length - 1] == (byte) 61) {
            length--;
        }
        if (bArr.length > 1 && bArr[bArr.length - 2] == (byte) 61) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        int i2 = 0;
        int i3 = 0;
        for (byte b : bArr) {
            byte b2 = codes[b & 255];
            if (b2 >= (byte) 0) {
                int i4 = i2 << 6;
                i2 = i3 + 6;
                i3 = i4 | b2;
                if (i2 >= 8) {
                    int i5 = i2 - 8;
                    i2 = i + 1;
                    bArr2[i] = (byte) ((i3 >> i5) & 255);
                    i = i2;
                    i2 = i3;
                    i3 = i5;
                } else {
                    int i6 = i3;
                    i3 = i2;
                    i2 = i6;
                }
            }
        }
        if (i == bArr2.length) {
            return bArr2;
        }
        throw new RuntimeException("miscalculated data length!");
    }

    public static char[] encode(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            Object obj;
            Object obj2;
            int i3 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i3 |= bArr[i2 + 1] & 255;
                obj = 1;
            } else {
                obj = null;
            }
            i3 <<= 8;
            if (i2 + 2 < bArr.length) {
                i3 |= bArr[i2 + 2] & 255;
                obj2 = 1;
            } else {
                obj2 = null;
            }
            cArr[i + 3] = alphabet[obj2 != null ? i3 & 63 : 64];
            int i4 = i3 >> 6;
            int i5 = i + 2;
            char[] cArr2 = alphabet;
            if (obj != null) {
                i3 = i4 & 63;
            } else {
                i3 = 64;
            }
            cArr[i5] = cArr2[i3];
            i3 = i4 >> 6;
            cArr[i + 1] = alphabet[i3 & 63];
            cArr[i + 0] = alphabet[(i3 >> 6) & 63];
            i2 += 3;
            i += 4;
        }
        return cArr;
    }

    public static byte[] encodebyte(byte[] bArr) {
        byte[] bArr2 = new byte[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            Object obj;
            Object obj2;
            int i3 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i3 |= bArr[i2 + 1] & 255;
                obj = 1;
            } else {
                obj = null;
            }
            i3 <<= 8;
            if (i2 + 2 < bArr.length) {
                i3 |= bArr[i2 + 2] & 255;
                obj2 = 1;
            } else {
                obj2 = null;
            }
            bArr2[i + 3] = (byte) alphabet[obj2 != null ? i3 & 63 : 64];
            int i4 = i3 >> 6;
            int i5 = i + 2;
            char[] cArr = alphabet;
            if (obj != null) {
                i3 = i4 & 63;
            } else {
                i3 = 64;
            }
            bArr2[i5] = (byte) cArr[i3];
            i3 = i4 >> 6;
            bArr2[i + 1] = (byte) alphabet[i3 & 63];
            bArr2[i + 0] = (byte) alphabet[(i3 >> 6) & 63];
            i2 += 3;
            i += 4;
        }
        return bArr2;
    }
}
