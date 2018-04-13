package com.lt.a.b;

import java.util.List;

public class f {
    public static String a(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : list) {
            stringBuffer.append(append);
        }
        return stringBuffer.toString();
    }

    public static String a(String str, String str2) {
        if (str.startsWith(str2)) {
            str = str.substring(1);
        }
        if (str.endsWith(str2)) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean b(String str) {
        return (str == null || str.length() == 0) && a(str);
    }
}
