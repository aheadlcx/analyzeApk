package com.sina.weibo.sdk.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.StateSet;
import java.util.Locale;

public class g {
    private static final String a = g.class.getName();
    private static final String[] b = new String[]{"drawable-xxhdpi", "drawable-xhdpi", "drawable-hdpi", "drawable-mdpi", "drawable-ldpi", "drawable"};

    public static String a(Context context, String str, String str2, String str3) {
        Locale a = a();
        if (Locale.SIMPLIFIED_CHINESE.equals(a) || ("zh".equals(a.getLanguage()) && a.getCountry().contains("CN"))) {
            return str2;
        }
        if (Locale.TRADITIONAL_CHINESE.equals(a) || ("zh".equals(a.getLanguage()) && a.getCountry().contains("TW"))) {
            return str3;
        }
        return str;
    }

    public static Locale a() {
        Locale locale = Locale.getDefault();
        if (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) {
            return locale;
        }
        return (locale.getLanguage().equals("zh") && (locale.getCountry().contains("CN") || locale.getCountry().contains("TW"))) ? locale : Locale.ENGLISH;
    }

    public static ColorStateList a(int i, int i2) {
        int[] iArr = new int[]{i2, i2, i2, i};
        int[][] iArr2 = new int[4][];
        iArr2[0] = new int[]{16842919};
        iArr2[1] = new int[]{16842913};
        iArr2[2] = new int[]{16842908};
        iArr2[3] = StateSet.WILD_CARD;
        return new ColorStateList(iArr2, iArr);
    }
}
