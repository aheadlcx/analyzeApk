package cn.v6.sixrooms.surfaceanim.specialframe.util;

import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public class ScalePxUtil {
    private static int a = 0;
    private static int b = 0;

    public static int getScalePx(int i, int i2) {
        if (i2 == 0) {
            return AnimSceneResManager.getInstance().getScalePx(a + i);
        }
        return AnimSceneResManager.getInstance().getScalePx(b + i);
    }

    public static void setOffset(int i, int i2) {
        a = i;
        b = i2;
    }
}
