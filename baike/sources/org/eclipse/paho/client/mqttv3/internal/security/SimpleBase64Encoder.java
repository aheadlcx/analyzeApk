package org.eclipse.paho.client.mqttv3.internal.security;

public class SimpleBase64Encoder {
    private static final char[] a = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        int i = 0;
        while (length >= 3) {
            stringBuffer.append(a((long) ((((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8)) | (bArr[i + 2] & 255)), 4));
            i += 3;
            length -= 3;
        }
        if (length == 2) {
            stringBuffer.append(a((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)), 3));
        }
        if (length == 1) {
            stringBuffer.append(a((long) (bArr[i] & 255), 2));
        }
        return stringBuffer.toString();
    }

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[((length * 3) / 4)];
        int i = length;
        int i2 = 0;
        length = 0;
        while (i >= 4) {
            int i3;
            long a = a(bytes, i2, 4);
            i -= 4;
            i2 += 4;
            for (i3 = 2; i3 >= 0; i3--) {
                bArr[length + i3] = (byte) ((int) (255 & a));
                a >>= 8;
            }
            length += 3;
        }
        if (i == 3) {
            a = a(bytes, i2, 3);
            for (i3 = 1; i3 >= 0; i3--) {
                bArr[length + i3] = (byte) ((int) (255 & a));
                a >>= 8;
            }
        }
        if (i == 2) {
            bArr[length] = (byte) ((int) (a(bytes, i2, 2) & 255));
        }
        return bArr;
    }

    private static final String a(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        while (i > 0) {
            i--;
            stringBuffer.append(a[(int) (63 & j)]);
            j >>= 6;
        }
        return stringBuffer.toString();
    }

    private static final long a(byte[] bArr, int i, int i2) {
        long j = null;
        long j2 = 0;
        while (i2 > 0) {
            long j3;
            i2--;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b == (byte) 47) {
                j3 = 1;
            } else {
                j3 = 0;
            }
            if (b >= (byte) 48 && b <= (byte) 57) {
                j3 = (long) ((b + 2) - 48);
            }
            if (b >= (byte) 65 && b <= (byte) 90) {
                j3 = (long) ((b + 12) - 65);
            }
            if (b >= (byte) 97 && b <= (byte) 122) {
                j3 = (long) ((b + 38) - 97);
            }
            j2 += j3 << j;
            j += 6;
            i = i3;
        }
        return j2;
    }
}
