package com.budejie.www.util;

import android.text.TextUtils;

public class ar {
    public static int a(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        return str.length() + b(str);
    }

    public static int b(String str) {
        int i = 0;
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            if (((char) ((byte) toCharArray[i])) != toCharArray[i]) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private static boolean a(char c) {
        if (((char) ((byte) c)) != c) {
            return true;
        }
        return false;
    }

    public static String a(String str, int i) {
        String str2 = "";
        if (!TextUtils.isEmpty(str) && i != 0) {
            char[] toCharArray = str.toCharArray();
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                if (a(toCharArray[i2])) {
                    if (i > 1) {
                        str2 = str2 + toCharArray[i2];
                        i -= 2;
                    }
                } else if (i > 0) {
                    str2 = str2 + toCharArray[i2];
                    i--;
                }
                if (i <= 0) {
                    break;
                }
            }
        }
        return str2;
    }
}
