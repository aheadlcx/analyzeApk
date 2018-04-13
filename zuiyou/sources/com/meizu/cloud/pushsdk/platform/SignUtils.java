package com.meizu.cloud.pushsdk.platform;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SignUtils {
    public static String getSignature(Map<String, String> map, String str) {
        Set<Entry> entrySet = new TreeMap(map).entrySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : entrySet) {
            stringBuilder.append((String) entry.getKey()).append("=").append((String) entry.getValue());
        }
        stringBuilder.append(str);
        return parseStrToMd5L32(stringBuilder.toString());
    }

    public static String parseStrToMd5L32(String str) {
        String str2 = null;
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            str2 = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str2;
    }
}
