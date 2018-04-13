package com.budejie.www.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

public class s implements InputFilter {
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (charSequence.equals("\n") && TextUtils.isEmpty(spanned.toString())) {
            return "";
        }
        if (charSequence.equals("\n")) {
            String obj = spanned.toString();
            int length = obj.length();
            if (i3 >= 0 && i3 < length) {
                Object obj2 = "";
                if (i3 > 0) {
                    obj2 = Character.valueOf(obj.charAt(i3 - 1)).toString();
                }
                Object obj3 = "";
                if (i3 < length - 1) {
                    obj3 = Character.valueOf(obj.charAt(i3 + 1)).toString();
                }
                if ("\n".equals(obj2) || "\n".equals(r1)) {
                    return "";
                }
            } else if (i3 >= 2 && i3 == length) {
                String ch = Character.valueOf(obj.charAt(i3 - 1)).toString();
                String ch2 = Character.valueOf(obj.charAt(i3 - 2)).toString();
                if ("\n".equals(ch) && "\n".equals(ch2)) {
                    return "";
                }
            }
        }
        return null;
    }
}
