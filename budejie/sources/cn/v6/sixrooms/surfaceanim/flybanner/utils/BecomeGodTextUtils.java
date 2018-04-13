package cn.v6.sixrooms.surfaceanim.flybanner.utils;

import android.graphics.Paint;

public class BecomeGodTextUtils {
    public static final String go = "GO";
    public static final String s1 = "恭喜";
    public static final String s2 = "在";
    public static final String s3 = "的房间";
    public static final String s4 = "荣升";
    public static final String s5 = "惊喜大礼1分钟后降临!";

    public static TextInfo getNewlineInfo(Paint paint, String str, String str2, float f) {
        TextInfo textInfo = new TextInfo();
        if (paint == null || str == null || str2 == null) {
            return null;
        }
        float measureText = paint.measureText(s1);
        float measureText2 = paint.measureText(s2);
        float measureText3 = paint.measureText(str);
        float measureText4 = paint.measureText(str2);
        measureText2 += measureText + measureText3;
        if (measureText2 > f) {
            return null;
        }
        if ((measureText2 + measureText4) + 5.0f < f) {
            textInfo.setNewLine(false);
            return textInfo;
        }
        char[] toCharArray = str2.toCharArray();
        measureText = 0.0f;
        for (int i = 0; i < toCharArray.length; i++) {
            measureText += paint.measureText(String.valueOf(toCharArray[i]));
            if (measureText2 + measureText > f) {
                textInfo.setNewLine(true);
                textInfo.setFrontStr(str2.substring(0, i));
                textInfo.setBackStr(str2.substring(i));
                return textInfo;
            }
        }
        return textInfo;
    }
}
