package com.iflytek.cloud.thirdparty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class g {
    private static g a = new g();
    private SecretKeySpec b;

    private g() {
    }

    public static g a() {
        return a;
    }

    private static byte[] b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes("utf-8"));
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public final byte[] a(String str) {
        String str2 = "134e3265829ff82daf16e7b740a600b5";
        if (this.b == null) {
            byte[] bytes = str2.getBytes();
            byte[] bArr = new byte[16];
            int i = 0;
            while (i < bytes.length && i < bArr.length) {
                bArr[i] = bytes[i];
                i++;
            }
            this.b = new SecretKeySpec(bArr, "AES");
        }
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, this.b, new IvParameterSpec("0102030405060708".getBytes()));
        return b(new e().a(instance.doFinal(str.getBytes())));
    }
}
