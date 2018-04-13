package com.xiaomi.channel.commonutils.misc;

public class b {
    public static int a(byte[] bArr) {
        if (bArr.length == 4) {
            return (((((bArr[0] & 255) << 24) | 0) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255);
        }
        throw new IllegalArgumentException("the length of bytes must be 4");
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }
}
