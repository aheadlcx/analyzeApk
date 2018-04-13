package com.qiniu.android.dns.util;

import java.io.IOException;

public final class Hex {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static class HexDecodeException extends IOException {
        public HexDecodeException(String str) {
            super(str);
        }
    }

    public static byte[] decodeHex(char[] cArr) throws HexDecodeException {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new HexDecodeException("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i2 = 0;
        while (i < length) {
            i++;
            i++;
            bArr[i2] = (byte) (((a(cArr[i], i) << 4) | a(cArr[i], i)) & 255);
            i2++;
        }
        return bArr;
    }

    public static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    public static char[] encodeHex(byte[] bArr, boolean z) {
        return a(bArr, z ? a : b);
    }

    private static char[] a(byte[] bArr, char[] cArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static String encodeHexString(byte[] bArr) {
        return new String(encodeHex(bArr));
    }

    protected static int a(char c, int i) throws HexDecodeException {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new HexDecodeException("Illegal hexadecimal character " + c + " at index " + i);
    }
}
