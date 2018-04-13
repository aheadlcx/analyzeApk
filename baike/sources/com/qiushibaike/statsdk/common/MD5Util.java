package com.qiushibaike.statsdk.common;

import java.security.MessageDigest;
import java.util.Locale;

public final class MD5Util {
    public static String md5(String str, boolean z) {
        try {
            return md5(str.getBytes("UTF-8"), z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(byte[] bArr, boolean z) {
        try {
            return toHexString(MessageDigest.getInstance("MD5").digest(bArr), z);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHexString(byte[] bArr, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (z) {
                toHexString = toHexString.toUpperCase(Locale.ENGLISH);
            }
            if (toHexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }
}
