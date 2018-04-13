package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import com.budejie.www.util.j;
import com.budejie.www.widget.FixedViewPager;

public class TabPageIndicator extends HorizontalScrollView implements b {
    private static final CharSequence a = "";
    private Runnable b;
    private final OnClickListener c = new TabPageIndicator$1(this);
    private final a d;
    private FixedViewPager e;
    private OnPageChangeListener f;
    private int g;
    private int h;
    private TabPageIndicator$a i;
    private b j;
    private int k = 0;

    public interface b {
        void a(int i);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHorizontalScrollBarEnabled(false);
        this.d = new a(context, j.bb);
        addView(this.d, new LayoutParams(-2, -1));
    }

    public void setOnTabReselectedListener(TabPageIndicator$a tabPageIndicator$a) {
        this.i = tabPageIndicator$a;
    }

    public void setOnTabSelectedListener(b bVar) {
        this.j = bVar;
    }

    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.d.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.g = -1;
        } else if (childCount > 2) {
            this.g = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
        } else {
            this.g = MeasureSpec.getSize(i) / 2;
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        mode = getMeasuredWidth();
        if (z && measuredWidth != mode) {
            setCurrentItem(this.h);
        }
    }

    private void a(int i) {
        View childAt = this.d.getChildAt(i);
        if (this.b != null) {
            removeCallbacks(this.b);
        }
        this.b = new TabPageIndicator$2(this, childAt);
        post(this.b);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            post(this.b);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            removeCallbacks(this.b);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.f != null) {
            this.f.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f != null) {
            this.f.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        setCurrentItem(i);
        if (this.f != null) {
            this.f.onPageSelected(i);
        }
    }

    public void setCurrentItem(int i) {
        if (this.e == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.h = i;
        this.e.setCurrentItem(i);
        int childCount = this.d.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.d.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f = onPageChangeListener;
    }
}
