package com.coloros.mcssdk.c;

import android.util.Base64;
import java.nio.charset.Charset;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public abstract class b {
    public static String a(String str, String str2) {
        Cipher instance = Cipher.getInstance("DES");
        instance.init(2, a(str2));
        return new String(instance.doFinal(Base64.decode(str, 0)), Charset.defaultCharset()).trim();
    }

    private static Key a(String str) {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(Base64.decode(str, 0)));
    }
}
