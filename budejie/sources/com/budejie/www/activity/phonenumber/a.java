package com.budejie.www.activity.phonenumber;

import android.util.Base64;
import com.budejie.www.util.aa;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static byte[] b = new byte[]{(byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 11, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 11};

    public static String a(byte[] bArr) throws NoSuchAlgorithmException {
        int i = 0;
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        byte[] digest = instance.digest();
        int length = digest.length;
        char[] cArr = new char[(length * 2)];
        int i2 = 0;
        while (i < length) {
            byte b = digest[i];
            int i3 = i2 + 1;
            cArr[i2] = a[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public static String a(String str, String str2, long j) {
        try {
            return a((a(str.concat(str2).getBytes()) + Long.toString(j)).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(String str) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec("a6841c04403200a2c1f34d4994cb885f".getBytes(), "AES"), new IvParameterSpec(b));
            byte[] doFinal = instance.doFinal(str.getBytes());
            aa.a("aes/256", new String(doFinal));
            String encodeToString = Base64.encodeToString(doFinal, 0);
            aa.a("aes/256", encodeToString);
            return encodeToString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
