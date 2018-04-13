package com.baidu.mobstat;

import android.annotation.SuppressLint;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class cs {
    @SuppressLint({"TrulyRandom"})
    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr3);
    }

    public static byte[] a() {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        instance.init(128, new SecureRandom());
        return instance.generateKey().getEncoded();
    }

    public static byte[] b() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static String a(byte[] bArr) {
        try {
            return b(a(), b(), bArr);
        } catch (Throwable e) {
            db.b(e);
            return "";
        }
    }

    public static String b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return cv.b(a(bArr, bArr2, cx.a(bArr3))) + "|" + dc.a(bArr) + "|" + dc.a(bArr2);
        } catch (Throwable e) {
            db.b(e);
            return "";
        }
    }
}
