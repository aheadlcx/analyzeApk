package cn.htjyb.c;

import android.text.TextUtils;
import android.util.Base64;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class d {
    private static String a = "^[a-zA-Z0-9][a-zA-Z0-9_.]*@[\\w]+(\\.[\\w])*(\\.[\\w]{2,3})$";
    private static String b = "^[\\w!@#$%^&*()-= _+\\[\\]{}\\\\|;':\",./<>?]{6,20}+$";
    private static String c = "^[0-9]{4,8}$";
    private static String d = "^[0-9]{8,14}$";
    private static String e = "^[0-9]{6,20}$";

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return str.matches(b);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches(c);
    }

    public static boolean a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (i == 86) {
            return str.matches(d);
        }
        return str.matches(e);
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length << 1);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append("0123456789abcdef".charAt((bArr[i] & 240) >> 4));
            stringBuilder.append("0123456789abcdef".charAt(bArr[i] & 15));
        }
        return stringBuilder.toString();
    }

    public static String c(String str) {
        return a(b(str.getBytes()));
    }

    public static byte[] b(byte[] bArr) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    public static boolean d(String str) {
        return str == null || str.length() == 0;
    }

    public static String e(String str) {
        return c(str).substring(0, 16);
    }

    public static double f(String str) {
        double d = 0.0d;
        for (int i = 0; i < str.length(); i++) {
            int codePointAt = Character.codePointAt(str, i);
            if (codePointAt < 0 || codePointAt > 255) {
                d += 1.0d;
            } else {
                d += 0.5d;
            }
        }
        return d;
    }

    public static String a(String str, double d) {
        double d2 = 0.0d;
        double f = f(str);
        if (d <= 0.0d || d > f) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String substring = str.substring(i, i + 1);
            int codePointAt = Character.codePointAt(str, i);
            double d3 = (codePointAt < 0 || codePointAt > 255) ? 1.0d : 0.5d;
            if (d2 + d3 <= d) {
                stringBuilder.append(substring);
                d2 += d3;
            }
        }
        return stringBuilder.toString();
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            try {
                System.out.print("Key为空null");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        Key secretKeySpec = new SecretKeySpec(g(str2).getBytes("utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(1, secretKeySpec);
        byte[] doFinal = instance.doFinal(str.getBytes("utf-8"));
        if (doFinal == null || doFinal.length <= 0) {
            return null;
        }
        byte[] encode = Base64.encode(doFinal, 0);
        if (encode == null || encode.length <= 0) {
            return null;
        }
        return new String(encode, "utf-8");
    }

    private static String g(String str) {
        int i = 0;
        int length = str.length();
        StringBuilder stringBuilder;
        if (length < 16) {
            stringBuilder = new StringBuilder(str);
            while (i < 16 - length) {
                stringBuilder.append("0");
                i++;
            }
            return stringBuilder.toString();
        } else if (length > 16 && length < 24) {
            stringBuilder = new StringBuilder(str);
            while (i < 24 - length) {
                stringBuilder.append("0");
                i++;
            }
            return stringBuilder.toString();
        } else if (length > 24 && length < 32) {
            stringBuilder = new StringBuilder(str);
            while (i < 32 - length) {
                stringBuilder.append("0");
                i++;
            }
            return stringBuilder.toString();
        } else if (length > 32) {
            return str.substring(0, 32);
        } else {
            return str;
        }
    }
}
