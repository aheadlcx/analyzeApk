package com.budejie.www.util;

import android.text.TextUtils;

public class r {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!a(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(char c) {
        return (c >= '0' && c <= '9') || ((c >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || (c >= '一' && c <= '龥')));
    }
}
