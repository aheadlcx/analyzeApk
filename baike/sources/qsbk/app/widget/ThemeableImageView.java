package qsbk.app.widget;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import qsbk.app.utils.UIHelper;

public class ThemeableImageView extends ImageView {
    private static final ColorMatrixColorFilter a = new ColorMatrixColorFilter(new float[]{0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});

    public ThemeableImageView(Context context) {
        super(context);
    }

    public ThemeableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ThemeableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageDrawable(Drawable drawable) {
        if (isInEditMode()) {
            super.setImageDrawable(drawable);
            return;
        }
        if (drawable != null) {
            if (UIHelper.isNightTheme()) {
                drawable.setColorFilter(a);
            } else {
                drawable.setColorFilter(null);
            }
        }
        super.setImageDrawable(drawable);
    }
}
