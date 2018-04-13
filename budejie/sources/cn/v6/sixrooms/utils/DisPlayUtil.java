package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.View;

public class DisPlayUtil {
    public static int getPix(Context context, int i, int i2) {
        return (context.getResources().getDisplayMetrics().widthPixels * i) / i2;
    }

    public static int getPix(Context context, int i) {
        return getPix(context, i, 720);
    }

    public static int getTitleBarHeight(Activity activity) {
        return activity.getWindow().findViewById(16908290).getTop() - getStatusHeight(activity);
    }

    public static int getStatusHeight(Context context) {
        int i = 0;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            i = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int getWeightWidth(Context context, float f) {
        return (int) (((float) context.getResources().getDisplayMetrics().widthPixels) * f);
    }

    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float messureTextWidth(float f, String str) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return paint.measureText(str);
    }

    public static int messureTextHeight(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil((double) (fontMetrics.bottom - fontMetrics.ascent));
    }

    public static float getTextAscent(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        return paint.getFontMetrics().ascent;
    }

    public static int getWeightHeight(Context context, float f) {
        return (int) (((float) context.getResources().getDisplayMetrics().heightPixels) * f);
    }

    public static int getPCPlayerHeight(Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        return (int) (((float) context.getResources().getDisplayMetrics().heightPixels) * 0.75f);
    }

    public static int getPCPlayerWidth(Context context) {
        return (getPCPlayerHeight(context) * 4) / 3;
    }

    public static int getPCPlayerMargin(Context context) {
        return (context.getResources().getDisplayMetrics().widthPixels - ((getPCPlayerHeight(context) * 4) / 3)) / 2;
    }

    public static int getFamilyPlayerHeight(Context context) {
        return (int) (((float) context.getResources().getDisplayMetrics().widthPixels) * 0.5f);
    }

    public static int getLandscapePlayerHeight(Activity activity, boolean z) {
        if (z) {
            return (activity.getResources().getDisplayMetrics().widthPixels * 9) / 16;
        }
        return (int) (((float) activity.getResources().getDisplayMetrics().widthPixels) * 0.75f);
    }

    public static int getLandscapePlayerWidth(Activity activity, boolean z) {
        if (z) {
            return ((activity.getResources().getDisplayMetrics().heightPixels - (DensityUtil.getStatusBarHeight(activity) * 0)) * 16) / 9;
        }
        return ((activity.getResources().getDisplayMetrics().heightPixels - (DensityUtil.getStatusBarHeight(activity) * 0)) * 4) / 3;
    }

    public static int getPortraitPlayerHeight(Activity activity, boolean z) {
        if (z) {
            return (activity.getResources().getDisplayMetrics().widthPixels * 16) / 9;
        }
        return (activity.getResources().getDisplayMetrics().widthPixels * 4) / 3;
    }

    public static int getPortraitPlayerWidth(Activity activity, boolean z, int i) {
        if (z) {
            return (i * 9) / 16;
        }
        return ((activity.getResources().getDisplayMetrics().heightPixels - DensityUtil.getStatusBarHeight(activity)) * 3) / 4;
    }

    public static int getLandscapeHeight(Activity activity) {
        return activity.getResources().getDisplayMetrics().heightPixels - (DensityUtil.getStatusBarHeight(activity) * 0);
    }

    public static int getPortraitHeight(Activity activity) {
        return activity.getResources().getDisplayMetrics().heightPixels;
    }

    public static void setPlayerHeight(Context context, View view) {
        view.getLayoutParams().height = getPlayerHeight(context);
        view.invalidate();
    }

    public static int getPlayerHeight(Context context) {
        if (context.getResources().getConfiguration().orientation == 1) {
            return (int) (((float) context.getResources().getDisplayMetrics().widthPixels) * 0.75f);
        }
        return (int) (((float) context.getResources().getDisplayMetrics().heightPixels) * 0.75f);
    }

    public static float getScaleWidth(Context context, float f) {
        return ((float) context.getResources().getDisplayMetrics().widthPixels) / f;
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
