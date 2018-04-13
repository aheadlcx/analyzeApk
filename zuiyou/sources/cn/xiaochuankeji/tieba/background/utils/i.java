package cn.xiaochuankeji.tieba.background.utils;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

public class i {
    public static void a(View view) {
        if (VERSION.SDK_INT > 8) {
            view.setOverScrollMode(2);
            view.setHorizontalFadingEdgeEnabled(false);
            view.setVerticalFadingEdgeEnabled(false);
        }
    }

    public static int a(float f, @ColorInt int i, @ColorInt int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16)) | ((i5 + ((int) (((float) (((i2 >> 8) & 255) - i5)) * f))) << 8)) | (i6 + ((int) (((float) ((i2 & 255) - i6)) * f)));
    }

    public static float a(Resources resources, float f) {
        return TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    public static void a(EditText editText, int i) {
        editText.setFilters(new InputFilter[]{new LengthFilter(i)});
    }
}
