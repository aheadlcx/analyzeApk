package com.budejie.www.util;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.budejie.www.R;
import java.util.ArrayList;
import java.util.List;

public class at {
    private static List<Integer> a = new ArrayList();

    public static SpannableStringBuilder a(Context context, String str, String... strArr) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        for (String str2 : strArr) {
            int indexOf = str.indexOf(str2);
            int length = str2.length() + indexOf;
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(20, true), indexOf, length, 18);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.user_info_collect_title_highlight)), indexOf, length, 18);
        }
        return spannableStringBuilder;
    }

    public static SpannableStringBuilder a(Context context, String str, String str2, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        b(str, str2);
        if (a.size() > 0) {
            for (Integer num : a) {
                if (num.intValue() == -1) {
                    break;
                }
                spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(z ? R.color.main_red : R.color.main_red_black)), num.intValue(), num.intValue() + 1, 18);
            }
        }
        return spannableStringBuilder;
    }

    private static void b(String str, String str2) {
        if (str2.equals("")) {
            a.clear();
            a.add(Integer.valueOf(-1));
            return;
        }
        int length = str2.length();
        a.clear();
        for (int i = 0; i < length; i++) {
            c(str, "" + str2.charAt(i));
        }
    }

    private static void c(String str, String str2) {
        int indexOf = str.indexOf(str2);
        a.add(Integer.valueOf(indexOf));
        int i = 0 + indexOf;
        String a = a(str, str2.length() + indexOf);
        while (a.length() > 0) {
            indexOf = a.indexOf(str2);
            if (indexOf >= 0) {
                i = (i + indexOf) + str2.length();
                a.add(Integer.valueOf(i));
                a = a(a, indexOf + str2.length());
            } else {
                a = "";
            }
        }
    }

    private static String a(String str, int i) {
        String str2 = "";
        if (i < str.length()) {
            return str.substring(i);
        }
        return str2;
    }

    public static String a(String str, String str2) {
        String trim = str.trim();
        int length = trim.length();
        if (length < 50) {
            return trim;
        }
        int length2 = str2.length();
        int indexOf = trim.indexOf(str2);
        if (indexOf == 0) {
            return trim;
        }
        if (indexOf + length2 == length) {
            return trim.substring(length - 50);
        }
        if (indexOf + length2 < 50 || indexOf <= 15) {
            return trim;
        }
        return trim.substring(indexOf - 15);
    }
}
