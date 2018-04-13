package com.qiniu.android.utils;

import java.io.UnsupportedEncodingException;

public final class StringUtils {
    public static String join(String[] strArr, String str) {
        int i = 0;
        if (strArr == null) {
            return null;
        }
        int i2;
        int i3;
        int length = strArr.length;
        if (str == null || str.equals("")) {
            i2 = 0;
        } else {
            i2 = str.length();
        }
        if (length == 0) {
            i3 = 0;
        } else {
            i3 = ((strArr[0] == null ? 16 : strArr[0].length()) + i2) * length;
        }
        StringBuilder stringBuilder = new StringBuilder(i3);
        while (i < length) {
            if (i > 0) {
                stringBuilder.append(str);
            }
            if (strArr[i] != null) {
                stringBuilder.append(strArr[i]);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static String jsonJoin(String[] strArr) {
        int i = 0;
        int length = strArr.length;
        StringBuilder stringBuilder = new StringBuilder((strArr[0].length() + 3) * length);
        while (i < length) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\"');
            stringBuilder.append(strArr[i]);
            stringBuilder.append('\"');
            i++;
        }
        return stringBuilder.toString();
    }

    public static byte[] utf8Bytes(String str) {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String strip(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > '\u001f' && charAt < '') {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
