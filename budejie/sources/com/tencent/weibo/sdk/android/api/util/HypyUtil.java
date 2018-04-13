package com.tencent.weibo.sdk.android.api.util;

import android.support.v4.view.MotionEventCompat;

public class HypyUtil {
    private static int BEGIN = 45217;
    private static int END = 63486;
    private static char[] chartable = new char[]{'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝'};
    private static char[] initialtable = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z'};
    private static int[] table = new int[27];

    static {
        for (int i = 0; i < 26; i++) {
            table[i] = gbValue(chartable[i]);
        }
        table[26] = END;
    }

    public static String cn2py(String str) {
        int length = str.length();
        String str2 = "";
        int i = 0;
        while (i < length) {
            try {
                String stringBuilder = new StringBuilder(String.valueOf(str2)).append(Char2Initial(str.charAt(i))).toString();
                i++;
                str2 = stringBuilder;
            } catch (Exception e) {
                return "";
            }
        }
        return str2;
    }

    private static char Char2Initial(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ((c - 97) + 65);
        }
        if (c >= 'A' && c <= 'Z') {
            return c;
        }
        int gbValue = gbValue(c);
        if (gbValue < BEGIN || gbValue > END) {
            return c;
        }
        int i = 0;
        while (i < 26 && (gbValue < table[i] || gbValue >= table[i + 1])) {
            i++;
        }
        if (gbValue == END) {
            i = 25;
        }
        return initialtable[i];
    }

    private static int gbValue(char c) {
        try {
            byte[] bytes = new StringBuilder(String.valueOf(new String())).append(c).toString().getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[1] & 255) + ((bytes[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        } catch (Exception e) {
            return 0;
        }
    }
}
