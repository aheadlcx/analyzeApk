package com.facebook.common.util;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class Hex {
    private static final byte[] DIGITS = new byte[103];
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] SECOND_CHAR = new char[256];

    static {
        int i;
        int i2 = 0;
        for (i = 0; i < 256; i++) {
            FIRST_CHAR[i] = HEX_DIGITS[(i >> 4) & 15];
            SECOND_CHAR[i] = HEX_DIGITS[i & 15];
        }
        for (i = 0; i <= 70; i++) {
            DIGITS[i] = (byte) -1;
        }
        for (byte b = (byte) 0; b < (byte) 10; b = (byte) (b + 1)) {
            DIGITS[b + 48] = b;
        }
        while (i2 < 6) {
            DIGITS[i2 + 65] = (byte) (i2 + 10);
            DIGITS[i2 + 97] = (byte) (i2 + 10);
            i2 = (byte) (i2 + 1);
        }
    }

    public static String byte2Hex(int i) {
        if (i <= 255 && i >= 0) {
            return String.valueOf(FIRST_CHAR[i]) + String.valueOf(SECOND_CHAR[i]);
        }
        throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
    }

    public static String encodeHex(byte[] bArr, boolean z) {
        char[] cArr = new char[(bArr.length * 2)];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            if (i2 == 0 && z) {
                break;
            }
            int i3 = i + 1;
            cArr[i] = FIRST_CHAR[i2];
            i = i3 + 1;
            cArr[i3] = SECOND_CHAR[i2];
        }
        return new String(cArr, 0, i);
    }

    public static byte[] decodeHex(String str) {
        Object obj = 1;
        int length = str.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char charAt = str.charAt(i);
            if (charAt <= 'f') {
                byte b = DIGITS[charAt];
                if (b == (byte) -1) {
                    break;
                }
                i = i3 + 1;
                char charAt2 = str.charAt(i3);
                if (charAt2 > 'f') {
                    break;
                }
                byte b2 = DIGITS[charAt2];
                if (b2 == (byte) -1) {
                    break;
                }
                bArr[i2] = (byte) (b2 | (b << 4));
                i2++;
            } else {
                break;
            }
        }
        obj = null;
        if (obj == null) {
            return bArr;
        }
        throw new IllegalArgumentException("Invalid hexadecimal digit: " + str);
    }

    public static byte[] hexStringToByteArray(String str) {
        return decodeHex(str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
    }
}
