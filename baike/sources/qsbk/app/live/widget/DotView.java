package qsbk.app.live.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class DotView extends LinearLayout {
    private static final int[] a = new int[]{R.attr.dot_count};
    private final int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 9;
        this.c = R.drawable.ic_dot_selected;
        this.d = R.drawable.ic_dot_normal;
        this.e = WindowUtils.dp2Px(8);
        this.f = WindowUtils.dp2Px(6);
        this.g = WindowUtils.dp2Px(6);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        setDotCount(obtainStyledAttributes.getInt(0, 0));
        obtainStyledAttributes.recycle();
    }

    public void setDotCount(int i) {
        if (9 < i) {
            i = 9;
        } else if (i <= 0) {
            return;
        }
        removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            View imageView = new ImageView(getContext());
            imageView.setImageResource(getDotNormal());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f, this.g);
            layoutParams.leftMargin = this.e;
            addView(imageView, layoutParams);
        }
        ImageView imageView2 = (ImageView) getChildAt(0);
        if (imageView2 != null) {
            imageView2.setImageResource(getDotSelected());
        }
    }

    public void setSelectedDot(int i) {
        int childCount = getChildCount();
        if (childCount != 0) {
            if (i >= childCount) {
                i = childCount - 1;
            } else if (i < 0) {
                i = 0;
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                ((ImageView) getChildAt(i2)).setImageResource(getDotNormal());
            }
            ((ImageView) getChildAt(i)).setImageResource(getDotSelected());
        }
    }

    public int getDotNormal() {
        return this.c;
    }

    public int getDotSelected() {
        return this.d;
    }

    public void setDotNormal(int i) {
        this.c = i;
    }

    public void setDotSelected(int i) {
        this.d = i;
    }

    public void setLeftMargin(int i) {
        this.e = i;
    }

    public void setDotWide(int i) {
        this.f = i;
    }

    public void setDotHeight(int i) {
        this.g = i;
    }
}
