package cn.v6.sixrooms.utils;

import android.text.TextUtils;

public class MobilePhoneUtils {
    public static boolean isMobileNO(String str) throws Exception {
        String str2 = "[1][358]\\d{9}";
        if (!TextUtils.isEmpty(str)) {
            return str.matches(str2);
        }
        throw new Exception();
    }

    public static boolean isPhoneNumber(String str) throws Exception {
        String str2 = "^\\d{11}$";
        if (!TextUtils.isEmpty(str)) {
            return str.matches(str2);
        }
        throw new Exception();
    }
}
