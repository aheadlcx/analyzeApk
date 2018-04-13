package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import qsbk.app.utils.Util;

public class SizeNotifierRelativeLayout extends RelativeLayout implements ISizeNotifier {
    private SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate a;
    private Rect b = new Rect();

    public SizeNotifierRelativeLayout(Context context) {
        super(context);
    }

    public SizeNotifierRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @TargetApi(11)
    public SizeNotifierRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public SizeNotifierRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
    }

    public void setSizeNotifierRelativeLayoutDelegate(SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate sizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate) {
        this.a = sizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.a != null) {
            View rootView = getRootView();
            int height = (rootView.getHeight() - Util.statusBarHeight) - Util.getViewInset(rootView);
            getWindowVisibleDisplayFrame(this.b);
            this.a.onSizeChanged(height - (this.b.bottom - this.b.top));
        }
    }
}
