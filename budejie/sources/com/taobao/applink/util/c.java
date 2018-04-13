package com.taobao.applink.util;

import android.util.Log;
import java.security.MessageDigest;

public class c {
    public static String a(String str) {
        String str2 = null;
        if (!e.a(str)) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes());
                StringBuffer stringBuffer = new StringBuffer();
                byte[] digest = instance.digest();
                for (int i : digest) {
                    int i2;
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    if (i2 < 16) {
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(Integer.toHexString(i2));
                }
                str2 = stringBuffer.toString();
            } catch (Throwable th) {
                Log.d(TBAppLinkUtil.TAG, th.toString());
            }
        }
        return str2;
    }

    public static String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
