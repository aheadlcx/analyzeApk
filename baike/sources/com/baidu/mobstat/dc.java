package com.baidu.mobstat;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class dc {
    public static String a(byte[] bArr) {
        try {
            return cv.b(a(false, cw.a(), bArr));
        } catch (Throwable e) {
            db.b(e);
            return "";
        }
    }

    public static byte[] a(boolean z, byte[] bArr, byte[] bArr2) {
        RSAKey a = a(z, bArr);
        return a(1, (Key) a, ((a.getModulus().bitLength() + 1) / 8) - 11, bArr2);
    }

    public static byte[] b(boolean z, byte[] bArr, byte[] bArr2) {
        RSAKey a = a(z, bArr);
        return a(2, (Key) a, (a.getModulus().bitLength() + 1) / 8, bArr2);
    }

    private static RSAKey a(boolean z, byte[] bArr) {
        KeyFactory instance = KeyFactory.getInstance("RSA");
        if (z) {
            return (RSAPrivateKey) instance.generatePrivate(new PKCS8EncodedKeySpec(bArr));
        }
        return (RSAPublicKey) instance.generatePublic(new X509EncodedKeySpec(bArr));
    }

    private static byte[] a(int i, Key key, int i2, byte[] bArr) {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(i, key);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (i3 < bArr.length) {
            int length = bArr.length - i3;
            if (length > i2) {
                length = i2;
            }
            byteArrayOutputStream.write(instance.doFinal(bArr, i3, length));
            i3 += i2;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
