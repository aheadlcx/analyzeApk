package com.huawei.hms.support.api.push.a.a.b;

import android.text.TextUtils;
import com.huawei.hms.support.api.push.a.a.a.b;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(str, a());
    }

    public static String a(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        try {
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bArr2 = new byte[16];
            new SecureRandom().nextBytes(bArr2);
            instance.init(1, secretKeySpec, new IvParameterSpec(bArr2));
            return a(com.huawei.hms.support.api.push.a.a.a.a.a(bArr2), com.huawei.hms.support.api.push.a.a.a.a.a(instance.doFinal(str.getBytes("UTF-8"))));
        } catch (Exception e) {
            b("IllegalArgumentException aes cbc encrypter data error", e);
            return null;
        } catch (Exception e2) {
            b("InvalidKeyException aes cbc encrypter data error", e2);
            return null;
        } catch (Exception e22) {
            b("InvalidAlgorithmParameterException aes cbc encrypter data error", e22);
            return null;
        } catch (Exception e222) {
            b("IllegalBlockSizeException aes cbc encrypter data error", e222);
            return null;
        } catch (Exception e2222) {
            b("UnsupportedEncodingException aes cbc encrypter data error", e2222);
            return null;
        } catch (Exception e22222) {
            b("aes cbc encrypter data error", e22222);
            return null;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return b(str, a());
    }

    public static String b(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        try {
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Object c = c(str);
            Object d = d(str);
            if (TextUtils.isEmpty(c) || TextUtils.isEmpty(d)) {
                if (com.huawei.hms.support.log.a.b()) {
                    com.huawei.hms.support.log.a.b("AES128_CBC", "ivParameter or encrypedWord is null");
                }
                return "";
            }
            instance.init(2, secretKeySpec, new IvParameterSpec(com.huawei.hms.support.api.push.a.a.a.a.b(c)));
            return new String(instance.doFinal(com.huawei.hms.support.api.push.a.a.a.a.b(d)), "UTF-8");
        } catch (Exception e) {
            a("aes cbc decrypter data error", e);
            return "";
        } catch (Exception e2) {
            a("aes cbc decrypter data error", e2);
            return "";
        } catch (Exception e22) {
            a("aes cbc decrypter data error", e22);
            return "";
        } catch (Exception e222) {
            a("aes cbc decrypter data error", e222);
            return "";
        } catch (Exception e2222) {
            a("aes cbc decrypter data error", e2222);
            return "";
        } catch (Exception e22222) {
            a("aes cbc encrypter data error", e22222);
            return "";
        }
    }

    private static byte[] a() {
        byte[] b = com.huawei.hms.support.api.push.a.a.a.a.b(b.a());
        byte[] b2 = com.huawei.hms.support.api.push.a.a.a.a.b(b.a());
        return a(a(a(b, b2), com.huawei.hms.support.api.push.a.a.a.a.b("2A57086C86EF54970C1E6EB37BFC72B1")));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        if (bArr == null || bArr2 == null || bArr.length == 0 || bArr2.length == 0) {
            return new byte[0];
        }
        int length = bArr.length;
        if (length != bArr2.length) {
            return new byte[0];
        }
        byte[] bArr3 = new byte[length];
        while (i < length) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            i++;
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr) {
        int i = 0;
        if (bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        while (i < bArr.length) {
            bArr[i] = (byte) (bArr[i] >> 2);
            i++;
        }
        return bArr;
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2.substring(0, 6));
            stringBuffer.append(str.substring(0, 6));
            stringBuffer.append(str2.substring(6, 10));
            stringBuffer.append(str.substring(6, 16));
            stringBuffer.append(str2.substring(10, 16));
            stringBuffer.append(str.substring(16));
            stringBuffer.append(str2.substring(16));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("AES128_CBC", e.getMessage());
            }
            return "";
        }
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(6, 12));
            stringBuffer.append(str.substring(16, 26));
            stringBuffer.append(str.substring(32, 48));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("AES128_CBC", "get iv error:" + e.getMessage());
            }
            return "";
        }
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.substring(0, 6));
            stringBuffer.append(str.substring(12, 16));
            stringBuffer.append(str.substring(26, 32));
            stringBuffer.append(str.substring(48));
            return stringBuffer.toString();
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("AES128_CBC", "get encrypt word error:" + e.getMessage());
            }
            return "";
        }
    }

    private static void a(String str, Exception exception) {
        if (com.huawei.hms.support.log.a.d()) {
            com.huawei.hms.support.log.a.d("AES128_CBC", str + exception.getMessage());
        }
    }

    private static void b(String str, Exception exception) {
        if (com.huawei.hms.support.log.a.d()) {
            com.huawei.hms.support.log.a.d("AES128_CBC", str + exception.getMessage());
        }
    }
}
