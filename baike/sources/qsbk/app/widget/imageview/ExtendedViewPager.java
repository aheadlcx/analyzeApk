package qsbk.app.widget.imageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import qsbk.app.widget.CustomViewPager;

public class ExtendedViewPager extends CustomViewPager {
    public ExtendedViewPager(Context context) {
        super(context);
    }

    public ExtendedViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof TouchImageView) {
            return ((TouchImageView) view).canScrollHorizontallyFroyo(-i);
        }
        return super.canScroll(view, z, i, i2, i3);
    }
}
