package qsbk.app.utils;

import java.security.MessageDigest;

public class Md5 {
    public static String MD5(String str) {
        String str2 = "";
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            str2 = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String MD5(byte[] bArr, int i, int i2) {
        String str = null;
        if (bArr != null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bArr, i, i2);
                byte[] digest = instance.digest();
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    int i3 = b & 255;
                    if (i3 < 16) {
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(Integer.toHexString(i3));
                }
                str = stringBuffer.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static String MD5_16(String str) {
        return MD5(str).substring(8, 24);
    }
}
