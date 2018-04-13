package com.spriteapp.booklibrary.util;

public class RecyclerViewUtil {
    private static final int LEFT_MARGIN = 15;
    private static final int RIGHT_MARGIN = 15;

    public static int getImageWidth(int i, int i2) {
        return (((ScreenUtil.getScreenWidth() - ScreenUtil.dpToPxInt(15.0f)) - ScreenUtil.dpToPxInt(15.0f)) - ScreenUtil.dpToPxInt((float) (i * i2))) / (i + 1);
    }
}
