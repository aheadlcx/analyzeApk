package cn.v6.sixrooms.utils;

import android.text.TextUtils;

public class V6StringUtils {
    public static String removeSpecialCharacter(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("އ", " ").replace("سيط", " ").replaceAll("الله", " ").replaceAll("المهر", " ").replaceAll("✿ﻬ", "");
    }
}
