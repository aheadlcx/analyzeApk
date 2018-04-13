package com.umeng.onlineconfig.proguard;

import com.umeng.onlineconfig.OnlineConfigLog;
import java.security.MessageDigest;

public class h {
    public static final String a = System.getProperty("line.separator");
    private static final String b = h.class.getName();

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            OnlineConfigLog.i(b, "getMD5 error", e);
            return "";
        }
    }
}
