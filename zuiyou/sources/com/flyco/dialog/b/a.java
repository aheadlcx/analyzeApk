package com.flyco.dialog.b;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class a {
    public static Drawable a(int i, float f) {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(f);
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    public static Drawable a(int i, float[] fArr) {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(fArr);
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    public static StateListDrawable a(float f, int i, int i2, int i3) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = null;
        Drawable drawable2 = null;
        if (i3 == 0) {
            drawable = a(i, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f});
            drawable2 = a(i2, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f});
        } else if (i3 == 1) {
            drawable = a(i, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, 0.0f, 0.0f});
            drawable2 = a(i2, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, 0.0f, 0.0f});
        } else if (i3 == -1) {
            drawable = a(i, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, f, f});
            drawable2 = a(i2, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, f, f});
        } else if (i3 == -2) {
            drawable = a(i, f);
            drawable2 = a(i2, f);
        }
        stateListDrawable.addState(new int[]{-16842919}, drawable);
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        return stateListDrawable;
    }
}
