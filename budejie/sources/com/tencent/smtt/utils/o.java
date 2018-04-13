package com.tencent.smtt.utils;

import java.security.MessageDigest;

public class o {
    public static String a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                byte[] bytes = str.getBytes();
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bytes);
                str2 = c.a(instance.digest());
            } catch (Exception e) {
            }
        }
        return str2;
    }
}
