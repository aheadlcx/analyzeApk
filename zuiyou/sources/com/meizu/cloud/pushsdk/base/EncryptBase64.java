package com.meizu.cloud.pushsdk.base;

import java.nio.charset.Charset;

class EncryptBase64 {
    private static final char[] base64_table = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final char last2byte = ((char) Integer.parseInt("00000011", 2));
    private static final char last4byte = ((char) Integer.parseInt("00001111", 2));
    private static final char last6byte = ((char) Integer.parseInt("00111111", 2));
    private char[] mBase64Table;
    private int offset = 0;
    private String private_key;

    public EncryptBase64(String str) {
        this.private_key = str;
        initPrivateTable();
    }

    public String encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(((bArr.length + 2) / 3) * 4);
        int i = 0;
        int length = bArr.length;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuilder.append(this.mBase64Table[i3 >>> 2]);
                stringBuilder.append(this.mBase64Table[(last2byte & i3) << 4]);
                stringBuilder.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuilder.append(this.mBase64Table[i3 >>> 2]);
                stringBuilder.append(this.mBase64Table[((last2byte & i3) << 4) | (i2 >>> 4)]);
                stringBuilder.append(this.mBase64Table[(last4byte & i2) << 2]);
                stringBuilder.append("=");
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuilder.append(this.mBase64Table[i3 >>> 2]);
            stringBuilder.append(this.mBase64Table[((i3 & last2byte) << 4) | (i2 >>> 4)]);
            stringBuilder.append(this.mBase64Table[((i2 & last4byte) << 2) | (i4 >>> 6)]);
            stringBuilder.append(this.mBase64Table[last6byte & i4]);
        }
        return stringBuilder.toString();
    }

    public byte[] decode(byte[] bArr, int i) {
        StringBuilder stringBuilder = new StringBuilder((i * 3) / 4);
        int[] iArr = new int[4];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2;
            i2 = 0;
            while (i2 < 4) {
                int i4 = i3 + 1;
                iArr[i2] = base64_to_256(bArr[i3]);
                if (iArr[i2] == 64) {
                    i2++;
                    i3 = i4;
                } else {
                    i2++;
                    i3 = i4;
                }
            }
            stringBuilder.append((char) ((iArr[0] << 2) | (iArr[1] >>> 4)));
            if (iArr[2] != 64) {
                stringBuilder.append((char) (((iArr[1] & last4byte) << 4) | (iArr[2] >>> 2)));
            }
            if (iArr[3] != 64) {
                stringBuilder.append((char) (((iArr[2] & last2byte) << 6) | iArr[3]));
                i2 = i3;
            } else {
                i2 = i3;
            }
        }
        return stringBuilder.toString().getBytes(Charset.forName("ISO8859-1"));
    }

    private void initPrivateTable() {
        int i = 0;
        char[] cArr = new char[base64_table.length];
        this.offset = this.private_key.charAt(0) % 13;
        while (i < base64_table.length) {
            cArr[i] = base64_table[(this.offset + i) % base64_table.length];
            i++;
        }
        this.mBase64Table = cArr;
    }

    private int base64_to_256(byte b) {
        if (b >= (byte) 65 && b <= (byte) 90) {
            return ((b - 65) + (base64_table.length - this.offset)) % base64_table.length;
        }
        if (b >= (byte) 97 && b <= (byte) 122) {
            return (((b - 97) + 26) + (base64_table.length - this.offset)) % base64_table.length;
        }
        if (b >= (byte) 48 && b <= (byte) 57) {
            return (((b - 48) + 52) + (base64_table.length - this.offset)) % base64_table.length;
        }
        if (b == (byte) 43) {
            return ((base64_table.length - this.offset) + 62) % base64_table.length;
        }
        if (b == (byte) 47) {
            return ((base64_table.length - this.offset) + 63) % base64_table.length;
        }
        return 64;
    }
}
