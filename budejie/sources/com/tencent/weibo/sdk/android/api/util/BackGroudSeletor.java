package com.tencent.weibo.sdk.android.api.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BackGroudSeletor {
    static int[] EMPTY_STATE_SET = new int[0];
    static int[] ENABLED_STATE_SET = new int[]{16842910};
    static int[] PRESSED_ENABLED_STATE_SET = new int[]{16842910, 16842919};
    private static String pix = "";

    private BackGroudSeletor() {
    }

    public static StateListDrawable createBgByImageIds(String[] strArr, Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = getdrawble(strArr[0], context);
        stateListDrawable.addState(PRESSED_ENABLED_STATE_SET, getdrawble(strArr[1], context));
        stateListDrawable.addState(ENABLED_STATE_SET, drawable);
        stateListDrawable.addState(EMPTY_STATE_SET, drawable);
        return stateListDrawable;
    }

    public static Drawable getdrawble(String str, Context context) {
        IOException e;
        Bitmap decodeStream;
        try {
            File file = new File(new StringBuilder(String.valueOf(str)).append(pix).append(".png").toString());
            String stringBuilder = new StringBuilder(String.valueOf(str)).append(pix).append(".png").toString();
            if (!file.isFile()) {
                stringBuilder = new StringBuilder(String.valueOf(str)).append("480x800").append(".png").toString();
            }
            decodeStream = BitmapFactory.decodeStream(context.getAssets().open(stringBuilder));
            try {
                return new BitmapDrawable(decodeStream);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            decodeStream = null;
            if (decodeStream != null) {
                decodeStream.recycle();
            }
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream zipPic(InputStream inputStream) {
        return null;
    }

    public static String getPix() {
        return pix;
    }

    public static void setPix(String str) {
        pix = str;
    }
}
