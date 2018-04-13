package com.spriteapp.booklibrary.util;

import android.util.Base64;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class EncryptUtils {
    private static byte[] iv1 = new byte[]{(byte) 18, (byte) 52, (byte) 86, (byte) 120, (byte) -112, (byte) -85, (byte) -51, (byte) -17};

    public static byte[] desEncrypt(byte[] bArr, String str) {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(iv1);
        Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, generateSecret, ivParameterSpec);
        return instance.doFinal(bArr);
    }

    public static byte[] desDecrypt(byte[] bArr, String str) {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(iv1);
        Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, generateSecret, ivParameterSpec);
        return instance.doFinal(bArr);
    }

    public static String encrypt(String str, String str2) {
        String str3 = null;
        try {
            str3 = base64Encode(desEncrypt(str.getBytes(), str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static String decrypt(String str, String str2) {
        try {
            return new String(desDecrypt(base64Decode(str.getBytes()), str2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String base64Encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] base64Decode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.decode(bArr, 0);
    }
}
