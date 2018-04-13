package com.bdj.picture.edit.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.bdj.picture.edit.bean.CPoint;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static int a = 20;

    public static int a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void a(Context context, View view, int i) {
        view.clearAnimation();
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (b(context) - i), 0.0f);
        translateAnimation.setDuration(500);
        view.startAnimation(translateAnimation);
        view.setVisibility(0);
    }

    public static void b(Context context, View view, int i) {
        view.clearAnimation();
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (b(context) - i));
        translateAnimation.setDuration(500);
        view.startAnimation(translateAnimation);
        view.setVisibility(8);
    }

    public static float a(int i, int i2) {
        float f = (float) i2;
        if (i - 5 <= 0) {
            return f;
        }
        if (f == 72.0f) {
            if (i > 5 && i <= 10) {
                return (float) (i2 - 8);
            }
            if (i > 10 && i <= 12) {
                return (float) (i2 - 12);
            }
            if (i <= 12 || i > 14) {
                return (float) (i2 - 30);
            }
            return (float) (i2 - 24);
        } else if (f != 32.0f) {
            return (float) (i2 - ((Math.min(i, 12) - 5) * 6));
        } else {
            if (i > 11 && i <= 15) {
                return (float) (i2 - ((i - 11) * 2));
            }
            if (i <= 15 || i > 19) {
                return (float) (i2 - 12);
            }
            return (float) ((i2 - 8) - (i - 15));
        }
    }

    public static CPoint a(Context context, int i, int i2, int i3) {
        context.getResources().getDimensionPixelSize(com.bdj.picture.edit.a.b.view_height_45);
        return new CPoint((a(context) / 2) + (a * i), (((a(context) / 2) + (a * i)) + i2) - i3);
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.isNull(str)) {
            return jSONObject.getString(str);
        }
        if (jSONObject.isNull(str.toLowerCase())) {
            return null;
        }
        return jSONObject.getString(str.toLowerCase());
    }

    public static Integer b(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.isNull(str)) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (jSONObject.isNull(str.toLowerCase())) {
            return null;
        }
        return Integer.valueOf(jSONObject.getInt(str.toLowerCase()));
    }
}
