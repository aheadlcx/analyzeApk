package com.baidu.mobstat;

import android.text.TextUtils;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ct {
    public static byte[] a(int i, byte[] bArr) {
        int i2 = i - 1;
        if (i2 < 0 || cw.a.length <= i2) {
            return new byte[0];
        }
        Key secretKeySpec = new SecretKeySpec(cw.a[i2].getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static byte[] b(int i, byte[] bArr) {
        int i2 = i - 1;
        if (i2 < 0 || cw.a.length <= i2) {
            return new byte[0];
        }
        Key secretKeySpec = new SecretKeySpec(cw.a[i2].getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static String c(int i, byte[] bArr) {
        try {
            return cv.b(a(i, bArr));
        } catch (Throwable e) {
            db.a(e);
            return "";
        }
    }

    public static String d(int i, byte[] bArr) {
        Object c = c(i, bArr);
        return TextUtils.isEmpty(c) ? "" : c + "|" + i;
    }
}
