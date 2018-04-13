package com.facebook.drawee.view;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import javax.annotation.Nullable;

public class a {

    public static class a {
        public int a;
        public int b;
    }

    public static void a(a aVar, float f, @Nullable LayoutParams layoutParams, int i, int i2) {
        if (f > 0.0f && layoutParams != null) {
            if (a(layoutParams.height)) {
                aVar.b = MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (MeasureSpec.getSize(aVar.a) - i)) / f) + ((float) i2)), aVar.b), 1073741824);
            } else if (a(layoutParams.width)) {
                aVar.a = MeasureSpec.makeMeasureSpec(View.resolveSize((int) ((((float) (MeasureSpec.getSize(aVar.b) - i2)) * f) + ((float) i)), aVar.a), 1073741824);
            }
        }
    }

    private static boolean a(int i) {
        return i == 0 || i == -2;
    }
}
