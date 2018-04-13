package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.budejie.www.R;
import com.budejie.www.activity.video.a;
import com.budejie.www.activity.view.TopNavigationTabIndicator.TabPageIndicator.b;
import com.budejie.www.bean.TopNavigation;
import com.budejie.www.widget.FixedViewPager;
import com.budejie.www.widget.curtain.FloatVideoLayout;
import java.util.ArrayList;

public class TypeIndicator extends RadioGroup implements OnPageChangeListener, OnCheckedChangeListener {
    public ArrayList<TopNavigation> a;
    boolean b = true;
    private final int c = 10000;
    private Context d;
    private FixedViewPager e;
    private OnPageChangeListener f;
    private b g;
    private OnClickListener h;
    private ScrollViewCustom i;
    private boolean j = true;
    private boolean k = false;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private Runnable p;
    private OnGlobalLayoutListener q;
    private Animation r;
    private boolean s;
    private int t = 0;
    private Animation u;

    public TypeIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        this.m = a.a((Activity) context);
    }

    public TypeIndicator(Context context) {
        super(context);
        this.d = context;
    }

    public void a(OnClickListener onClickListener) {
        this.h = onClickListener;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setOnClickListener(new TypeIndicator$1(this));
        }
    }

    public void a(FixedViewPager fixedViewPager, ArrayList<TopNavigation> arrayList, ScrollViewCustom scrollViewCustom) {
        if (scrollViewCustom != null) {
            this.i = scrollViewCustom;
            this.i.setHorizontalScrollBarEnabled(false);
        }
        if (this.e != fixedViewPager) {
            this.e = fixedViewPager;
            this.e.setOnPageChangeListener(this);
            if (arrayList != null) {
                this.a = arrayList;
            }
            a();
        }
    }

    public void a() {
        removeAllViews();
        this.n = 0;
        this.o = 0;
        if (this.a != null && this.a.size() != 0) {
            int size = this.a.size();
            if (size > 4) {
                this.l = this.m / 5;
            } else {
                this.l = this.m / size;
            }
            for (int i = 0; i < size; i++) {
                a(i, ((TopNavigation) this.a.get(i)).name);
            }
            a(this.h);
            setOnCheckedChangeListener(this);
            this.k = true;
        }
    }

    private void a(int i) {
        this.o++;
        this.n += i;
        if (this.o == getChildCount()) {
            d();
        }
    }

    private void d() {
        if (this.n < this.m) {
            this.i.b();
            int i = (this.m - this.n) / (this.o * 2);
            for (int i2 = 0; i2 < this.o; i2++) {
                View childAt = getChildAt(i2);
                childAt.setPadding(childAt.getPaddingLeft() + i, childAt.getPaddingTop(), childAt.getPaddingRight() + i, childAt.getPaddingBottom());
            }
            return;
        }
        this.i.c();
    }

    private void a(int i, String str) {
        RadioButton radioButton = (RadioButton) View.inflate(getContext(), R.layout.tab_indicator_item, null);
        radioButton.getViewTreeObserver().addOnGlobalLayoutListener(new TypeIndicator$2(this, radioButton));
        radioButton.setId(i + 10000);
        radioButton.setText(str);
        addView(radioButton);
        LayoutParams layoutParams = (LayoutParams) radioButton.getLayoutParams();
        layoutParams.height = -1;
        layoutParams.width = -2;
        radioButton.setLayoutParams(layoutParams);
        if (i == 0) {
            radioButton.setChecked(true);
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.k = false;
        if (i == 0) {
            this.b = true;
        }
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
        ((RadioButton) getChildAt(i)).setChecked(true);
        if (this.f != null) {
            this.f.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f = onPageChangeListener;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.j) {
            this.j = false;
        } else {
            this.b = false;
        }
        setCurrentItem(i - 10000);
        this.g.a(i - 10000);
    }

    public void setOnTabSelectedListener(b bVar) {
        this.g = bVar;
    }

    public void setCurrentItem(int i) {
        if (this.e != null) {
            this.e.setCurrentItem(i);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                boolean z;
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                ((RadioButton) getChildAt(i2)).setChecked(z);
                if (z) {
                    b(i);
                }
            }
        }
    }

    private void b(int i) {
        View childAt = getChildAt(i);
        if (this.p != null) {
            removeCallbacks(this.p);
        }
        this.i.getViewTreeObserver().removeGlobalOnLayoutListener(this.q);
        this.q = new TypeIndicator$3(this, childAt);
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(this.q);
    }

    public void b() {
        if (!this.s && this.t != 0) {
            if (this.r == null) {
                this.r = AnimationUtils.loadAnimation(getContext(), R.anim.top_tabpageindicator_show_anim);
                this.r.setAnimationListener(new TypeIndicator$4(this));
            }
            setVisibility(0);
            this.t = 0;
            FloatVideoLayout.a(this.d, true);
            startAnimation(this.r);
        }
    }

    public void c() {
        if (!this.s && this.t != 8) {
            if (this.u == null) {
                this.u = AnimationUtils.loadAnimation(getContext(), R.anim.top_tabpageindicator_hide_anim);
                this.u.setAnimationListener(new TypeIndicator$5(this));
            }
            FloatVideoLayout.a(this.d, false);
            startAnimation(this.u);
        }
    }
}
