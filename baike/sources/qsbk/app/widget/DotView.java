package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class DotView extends LinearLayout {
    private static final int[] a = new int[]{R.attr.dot_count};
    private static final String b = DotView.class.getSimpleName();
    private final int c = 9;

    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    @TargetApi(11)
    public DotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    @TargetApi(21)
    public DotView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        setDotCount(obtainStyledAttributes.getInt(0, 0));
        obtainStyledAttributes.recycle();
    }

    public final void setDotCount(int i) {
        Log.e(b, "setDotCount " + i);
        if (9 < i) {
            i = 9;
        }
        if (i > 0) {
            removeAllViews();
            for (int i2 = 0; i2 < i; i2++) {
                View imageView = new ImageView(getContext());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 8.0f);
                imageView.setImageResource(UIHelper.getDotNormal());
                addView(imageView, layoutParams);
            }
            ImageView imageView2 = (ImageView) getChildAt(0);
            if (imageView2 != null) {
                imageView2.setImageResource(UIHelper.getDotSelected());
            }
        }
    }

    public void setSelectedDot(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            Log.e(b, this + " has no children.");
            return;
        }
        int childCount2;
        if (i >= getChildCount()) {
            childCount2 = getChildCount() - 1;
        } else {
            childCount2 = i;
        }
        if (childCount2 < 0) {
            childCount2 = 0;
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((ImageView) getChildAt(i2)).setImageResource(UIHelper.getDotNormal());
        }
        ((ImageView) getChildAt(childCount2)).setImageResource(UIHelper.getDotSelected());
    }
}
