package qsbk.app.widget.card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ImageCellLayout extends RelativeLayout {
    private static final String a = ImageCellLayout.class.getSimpleName();

    public ImageCellLayout(Context context) {
        super(context);
        a(context);
    }

    public ImageCellLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    @SuppressLint({"NewApi"})
    public ImageCellLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
