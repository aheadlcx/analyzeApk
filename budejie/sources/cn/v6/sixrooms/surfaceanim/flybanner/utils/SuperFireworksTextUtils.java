package cn.v6.sixrooms.surfaceanim.flybanner.utils;

import android.graphics.Paint;

public class SuperFireworksTextUtils {
    public static final String s1 = "送";
    public static final String s2 = "超级烟花，5分钟后引爆！";
    public static final String s3 = "速去领豆>>";

    public static TextInfo getNewlineInfo(Paint paint, String str, String str2, float f) {
        TextInfo textInfo = new TextInfo();
        if (paint == null || str == null || str2 == null) {
            return null;
        }
        float measureText = paint.measureText(s1) + paint.measureText(str);
        if ((measureText + paint.measureText(str2)) + 5.0f < f) {
            textInfo.setNewLine(false);
            return textInfo;
        }
        char[] toCharArray = str2.toCharArray();
        float f2 = 0.0f;
        for (int i = 0; i < toCharArray.length; i++) {
            f2 += paint.measureText(String.valueOf(toCharArray[i]));
            if (measureText + f2 > f) {
                textInfo.setNewLine(true);
                textInfo.setFrontStr(str2.substring(0, i));
                textInfo.setBackStr(str2.substring(i));
                return textInfo;
            }
        }
        return null;
    }
}
