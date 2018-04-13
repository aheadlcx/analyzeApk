package com.ali.auth.third.core.rpc.safe;

import com.ali.auth.third.core.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TriDes {
    private static String TriDes = "DESede/ECB/PKCS5Padding";

    public static String encrypt(String str, String str2) {
        String str3 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(TriDes);
            instance.init(1, secretKeySpec);
            str3 = Base64.encode(instance.doFinal(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static String decrypt(String str, String str2) {
        String trim = str2.trim();
        int intValue = Integer.valueOf(trim.substring(0, 8), 16).intValue();
        if (intValue == trim.length() - 8) {
            trim = trim.substring(8);
        } else {
            trim = trim.substring(8, intValue + 8);
        }
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DESede");
            Cipher instance = Cipher.getInstance(TriDes);
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(Base64.decode(trim)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
