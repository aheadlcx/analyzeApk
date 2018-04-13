package com.budejie.www.goddubbing.c;

import android.text.TextUtils;
import com.budejie.www.util.aa;

public class h {
    public static int a(String str, float f) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        int indexOf = str.indexOf("^") + 1;
        if (indexOf >= str.length()) {
            return i;
        }
        try {
            float parseInt = ((float) Integer.parseInt(str.substring(indexOf))) / f;
            if (parseInt >= 1.0f) {
                return 99;
            }
            return ((int) (parseInt * 30.0f)) + 70;
        } catch (NumberFormatException e) {
            if (TextUtils.isEmpty(e.getMessage())) {
                return i;
            }
            aa.e("StringUtil", e.getMessage());
            return i;
        }
    }
}
