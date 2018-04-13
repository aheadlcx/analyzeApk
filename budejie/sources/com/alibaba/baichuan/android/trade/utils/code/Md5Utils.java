package com.alibaba.baichuan.android.trade.utils.code;

import java.security.MessageDigest;

public class Md5Utils {
    public static String md5Digest(byte[] bArr) {
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
