package com.budejie.www.widget.erroredittext;

import java.util.regex.Pattern;

public class f extends l {
    public f(String str) {
        super(str);
    }

    public boolean a(String str) {
        return b(str);
    }

    public boolean b(String str) {
        boolean a = a("^[\\w\\-－＿[０-９]一-龥Ａ-Ｚａ-ｚ]+$", str);
        if (!a) {
            return a;
        }
        int c = c(str);
        if (c < 4 || c > 24) {
            return false;
        }
        return a;
    }

    public int c(String str) {
        int i = 0;
        String str2 = "[Α-￥]";
        int i2 = 0;
        while (i < str.length()) {
            if (str.substring(i, i + 1).matches(str2)) {
                i2 += 2;
            } else {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private boolean a(String str, String str2) {
        return Pattern.compile(str).matcher(str2).matches();
    }
}
