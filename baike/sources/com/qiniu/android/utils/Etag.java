package com.qiniu.android.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Etag {
    public static String data(byte[] bArr, int i, int i2) {
        try {
            return stream(new ByteArrayInputStream(bArr, i, i2), (long) i2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String data(byte[] bArr) {
        return data(bArr, 0, bArr.length);
    }

    public static String file(File file) throws IOException {
        return stream(new FileInputStream(file), file.length());
    }

    public static String file(String str) throws IOException {
        return file(new File(str));
    }

    public static String stream(InputStream inputStream, long j) throws IOException {
        if (j == 0) {
            return "Fto5o-5ea0sNMlW_75VgGJCv2AcJ";
        }
        byte[] bArr = new byte[65536];
        byte[][] bArr2 = new byte[((int) (((j + 4194304) - 1) / 4194304))][];
        for (int i = 0; i < bArr2.length; i++) {
            long j2 = j - (((long) i) * 4194304);
            if (j2 > 4194304) {
                j2 = 4194304;
            }
            bArr2[i] = a(bArr, inputStream, (int) j2);
        }
        return a(bArr2);
    }

    private static byte[] a(byte[] bArr, InputStream inputStream, int i) throws IOException {
        try {
            MessageDigest instance = MessageDigest.getInstance("sha-1");
            int length = bArr.length;
            int i2 = i;
            while (i2 != 0) {
                int i3 = length > i2 ? i2 : length;
                inputStream.read(bArr, 0, i3);
                instance.update(bArr, 0, i3);
                i2 -= i3;
            }
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(byte[][] bArr) {
        byte b = (byte) 22;
        Object obj = bArr[0];
        int length = obj.length;
        byte[] bArr2 = new byte[(length + 1)];
        if (bArr.length != 1) {
            b = (byte) -106;
            try {
                MessageDigest instance = MessageDigest.getInstance("sha-1");
                for (byte[] update : bArr) {
                    instance.update(update);
                }
                obj = instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
        bArr2[0] = b;
        System.arraycopy(obj, 0, bArr2, 1, length);
        return UrlSafeBase64.encodeToString(bArr2);
    }
}
