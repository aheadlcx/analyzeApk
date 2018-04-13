package cn.v6.sixrooms.widgets.phone.indicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.R$attr;
import java.util.HashMap;
import java.util.Map;

public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    private static final CharSequence a = "";
    private CharSequence b;
    private Map<Integer, Integer> c;
    private Runnable d;
    private final OnClickListener e;
    private final a f;
    private ViewPager g;
    private OnPageChangeListener h;
    private int i;
    private int j;
    private int k;
    private OnTabReselectedListener l;

    public interface OnTabReselectedListener {
        void onTabReselected(int i);
    }

    private class a extends TextView {
        final /* synthetic */ TabPageIndicator a;
        private int b;

        public a(TabPageIndicator tabPageIndicator, Context context) {
            this.a = tabPageIndicator;
            super(context, null, R$attr.vpiTabPageIndicatorStyle);
        }

        public final void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.a.i > 0 && getMeasuredWidth() > this.a.i) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.i, 1073741824), i2);
            }
        }

        public final int a() {
            return this.b;
        }
    }

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new HashMap();
        this.e = new b(this);
        setHorizontalScrollBarEnabled(false);
        this.f = new a(context, R$attr.vpiTabPageIndicatorStyle);
        addView(this.f, new LayoutParams(-2, -1));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener onTabReselectedListener) {
        this.l = onTabReselectedListener;
    }

    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.i = -1;
        } else if (childCount > 2) {
            this.i = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
        } else {
            this.i = MeasureSpec.getSize(i) / 2;
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        mode = getMeasuredWidth();
        if (z && measuredWidth != mode) {
            setCurrentItem(this.j);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            post(this.d);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            removeCallbacks(this.d);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.h != null) {
            this.h.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.h != null) {
            this.h.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        this.k = i;
        this.j = ((Integer) this.c.get(Integer.valueOf(i))).intValue();
        setCurrentItem(this.j);
        if (this.h != null) {
            this.h.onPageSelected(i);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.g != viewPager) {
            if (this.g != null) {
                this.g.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.g = viewPager;
            viewPager.setOnPageChangeListener(this);
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        this.f.removeAllViews();
        PagerAdapter adapter = this.g.getAdapter();
        IconPagerAdapter iconPagerAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconPagerAdapter = (IconPagerAdapter) adapter;
        }
        int count = adapter.getCount();
        int i = 0;
        int i2 = 0;
        while (i < count) {
            CharSequence charSequence;
            int iconResId;
            CharSequence pageTitle = adapter.getPageTitle(i);
            if (pageTitle == null) {
                charSequence = a;
            } else {
                charSequence = pageTitle;
            }
            if (iconPagerAdapter != null) {
                iconResId = iconPagerAdapter.getIconResId(i);
            } else {
                iconResId = 0;
            }
            if (charSequence.equals(this.b)) {
                this.c.put(Integer.valueOf(i), Integer.valueOf(i2 - 1));
                iconResId = i2;
            } else {
                this.c.put(Integer.valueOf(i), Integer.valueOf(i2));
                int i3 = i2 + 1;
                View aVar = new a(this, getContext());
                aVar.b = i2;
                aVar.setFocusable(true);
                aVar.setOnClickListener(this.e);
                aVar.setText(charSequence);
                if (iconResId != 0) {
                    aVar.setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);
                }
                this.f.addView(aVar, new LinearLayout.LayoutParams(0, -1, 1.0f));
                iconResId = i3;
            }
            this.b = charSequence;
            i++;
            i2 = iconResId;
        }
        if (this.j > this.f.getChildCount()) {
            this.j = this.f.getChildCount() - 1;
        }
        setCurrentItem(this.j);
        requestLayout();
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.g == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.j = i;
        int childCount = this.f.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.f.getChildAt(i2);
            a aVar = (a) childAt;
            if (aVar.a() == this.j) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                aVar.setTextAppearance(getContext(), R.style.choose_gift_title_text_selected);
                View childAt2 = this.f.getChildAt(this.j);
                if (this.d != null) {
                    removeCallbacks(this.d);
                }
                this.d = new c(this, childAt2);
                post(this.d);
            } else {
                aVar.setTextAppearance(getContext(), R.style.choose_gift_title_text_default);
            }
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.h = onPageChangeListener;
    }
}
