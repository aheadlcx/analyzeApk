package com.ishumei.f;

import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class b {
    public static String a(String str, byte[] bArr, int i) {
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(2, new SecretKeySpec(str.getBytes("utf-8"), "DES"));
            Object obj = new byte[i];
            System.arraycopy(instance.doFinal(bArr), 0, obj, 0, i);
            return new String(obj, "utf-8");
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    public static byte[] b(String str, byte[] bArr, int i) {
        try {
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(2, new SecretKeySpec(str.getBytes("utf-8"), "DES"));
            Object obj = new byte[i];
            System.arraycopy(instance.doFinal(bArr), 0, obj, 0, i);
            return obj;
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }
}
