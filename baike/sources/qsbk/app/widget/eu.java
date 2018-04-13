package qsbk.app.widget;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import qsbk.app.utils.UIHelper;

class eu extends dm {
    private static final ColorMatrixColorFilter a = new ColorMatrixColorFilter(new float[]{0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});

    public eu(Context context, int i, int i2, int i3, int i4) {
        super(context, i, i2, i3, i4);
    }

    public Drawable getDrawable() {
        Drawable drawable = super.getDrawable();
        if (drawable != null) {
            if (UIHelper.isNightTheme()) {
                drawable.setColorFilter(a);
            } else {
                drawable.setColorFilter(null);
            }
        }
        return drawable;
    }
}
