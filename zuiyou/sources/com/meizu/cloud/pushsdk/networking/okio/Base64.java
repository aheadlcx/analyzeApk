package com.meizu.cloud.pushsdk.networking.okio;

import java.io.UnsupportedEncodingException;

final class Base64 {
    private static final byte[] MAP = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] URL_MAP = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, Framer.EXIT_FRAME_PREFIX, (byte) 121, (byte) 122, (byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_REQUEST_FRAME_PREFIX};

    private Base64() {
    }

    public static byte[] decode(String str) {
        int i;
        int length = str.length();
        while (length > 0) {
            char charAt = str.charAt(length - 1);
            if (charAt != '=' && charAt != '\n' && charAt != '\r' && charAt != ' ' && charAt != '\t') {
                break;
            }
            length--;
        }
        Object obj = new byte[((int) ((((long) length) * 6) / 8))];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < length) {
            charAt = str.charAt(i2);
            if (charAt >= 'A' && charAt <= 'Z') {
                i = charAt - 65;
            } else if (charAt >= 'a' && charAt <= 'z') {
                i = charAt - 71;
            } else if (charAt >= '0' && charAt <= '9') {
                i = charAt + 4;
            } else if (charAt == '+' || charAt == '-') {
                i = 62;
            } else if (charAt == '/' || charAt == '_') {
                i = 63;
            } else if (charAt == '\n' || charAt == '\r' || charAt == ' ') {
                i = i3;
                i3 = i4;
                i4 = i5;
                i2++;
                i5 = i4;
                i4 = i3;
                i3 = i;
            } else if (charAt != '\t') {
                return null;
            } else {
                i = i3;
                i3 = i4;
                i4 = i5;
                i2++;
                i5 = i4;
                i4 = i3;
                i3 = i;
            }
            i = ((byte) i) | (i3 << 6);
            i3 = i4 + 1;
            if (i3 % 4 == 0) {
                i4 = i5 + 1;
                obj[i5] = (byte) (i >> 16);
                i5 = i4 + 1;
                obj[i4] = (byte) (i >> 8);
                i4 = i5 + 1;
                obj[i5] = (byte) i;
            } else {
                i4 = i5;
            }
            i2++;
            i5 = i4;
            i4 = i3;
            i3 = i;
        }
        i = i4 % 4;
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            i = i5 + 1;
            obj[i5] = (byte) ((i3 << 12) >> 16);
            i5 = i;
        } else if (i == 3) {
            i = i3 << 6;
            i3 = i5 + 1;
            obj[i5] = (byte) (i >> 16);
            i5 = i3 + 1;
            obj[i3] = (byte) (i >> 8);
        }
        if (i5 == obj.length) {
            return obj;
        }
        byte[] bArr = new byte[i5];
        System.arraycopy(obj, 0, bArr, 0, i5);
        return bArr;
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, MAP);
    }

    public static String encodeUrl(byte[] bArr) {
        return encode(bArr, URL_MAP);
    }

    private static String encode(byte[] bArr, byte[] bArr2) {
        int i = 0;
        byte[] bArr3 = new byte[(((bArr.length + 2) * 4) / 3)];
        int length = bArr.length - (bArr.length % 3);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i + 1;
            bArr3[i] = bArr2[(bArr[i2] & 255) >> 2];
            i = i3 + 1;
            bArr3[i3] = bArr2[((bArr[i2] & 3) << 4) | ((bArr[i2 + 1] & 255) >> 4)];
            int i4 = i + 1;
            bArr3[i] = bArr2[((bArr[i2 + 1] & 15) << 2) | ((bArr[i2 + 2] & 255) >> 6)];
            i3 = i4 + 1;
            bArr3[i4] = bArr2[bArr[i2 + 2] & 63];
            i2 += 3;
            i = i3;
        }
        switch (bArr.length % 3) {
            case 1:
                i2 = i + 1;
                bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
                i = i2 + 1;
                bArr3[i2] = bArr2[(bArr[length] & 3) << 4];
                i2 = i + 1;
                bArr3[i] = (byte) 61;
                i = i2 + 1;
                bArr3[i2] = (byte) 61;
                break;
            case 2:
                i2 = i + 1;
                bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
                i = i2 + 1;
                bArr3[i2] = bArr2[((bArr[length] & 3) << 4) | ((bArr[length + 1] & 255) >> 4)];
                i2 = i + 1;
                bArr3[i] = bArr2[(bArr[length + 1] & 15) << 2];
                i = i2 + 1;
                bArr3[i2] = (byte) 61;
                break;
        }
        try {
            return new String(bArr3, 0, i, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
