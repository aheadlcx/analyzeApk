package com.budejie.www.activity.label.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;

public class LabelTabLayout extends LinearLayout implements OnClickListener {
    private Context a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;
    private final int g;
    private int h;
    private final int i;
    private ViewPager j;
    private int k;
    private final int l;
    private final int m;
    private int n;
    private List<TextView> o;
    private a p;
    private boolean q;

    public interface a {
        void a(int i);
    }

    public LabelTabLayout(Context context) {
        this(context, null);
    }

    public LabelTabLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LabelTabLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        this.g = an.x(context);
        this.l = an.a(context, 36);
        this.m = an.a(context, 3);
        this.i = an.a(context, 23);
        this.o = new ArrayList();
        this.q = ai.a(context) == 0;
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.label_tab_layout, null);
        this.b = (TextView) inflate.findViewById(R.id.new_text_view);
        this.c = (TextView) inflate.findViewById(R.id.essence_text_view);
        this.d = (TextView) inflate.findViewById(R.id.hot_text_view);
        this.e = (TextView) inflate.findViewById(R.id.member_text_view);
        this.f = inflate.findViewById(R.id.line_view);
        this.h = this.g / 4;
        this.o.add(this.b);
        this.o.add(this.c);
        this.o.add(this.d);
        this.o.add(this.e);
        addView(inflate);
        b();
        setViewSelected(0);
    }

    public void a(ViewPager viewPager, int i, final OnPageChangeListener onPageChangeListener) {
        this.j = viewPager;
        this.j.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ LabelTabLayout b;

            public void onPageScrolled(int i, float f, int i2) {
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(i, f, i2);
                }
                int a = (int) (((float) this.b.h) * f);
                this.b.f.layout(((this.b.h * i) + this.b.k) + a, this.b.l, (a + ((this.b.h * i) + this.b.k)) + this.b.i, this.b.l + this.b.m);
            }

            public void onPageSelected(int i) {
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(i);
                }
                this.b.n = i;
                this.b.setViewSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(i);
                }
            }
        });
        this.j.setCurrentItem(i);
    }

    private void setViewSelected(int i) {
        if (!com.budejie.www.goddubbing.c.a.a(this.o) && i < this.o.size()) {
            int size = this.o.size();
            for (int i2 = 0; i2 < size; i2++) {
                boolean z;
                TextView textView = (TextView) this.o.get(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                textView.getPaint().setFakeBoldText(z);
                if (this.q) {
                    textView.setTextColor(getResources().getColor(z ? R.color.label_select_text_day_color : R.color.label_unselect_text_day_color));
                } else {
                    textView.setTextColor(getResources().getColor(z ? R.color.label_select_text_night_color : R.color.label_unselect_text_night_color));
                }
                textView.invalidate();
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b != null) {
            this.k = (this.h - this.i) / 2;
            this.f.layout((this.n * this.h) + this.k, this.l, ((this.n * this.h) + this.k) + this.i, this.l + this.m);
        }
    }

    private void b() {
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void setTabListener(a aVar) {
        this.p = aVar;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_text_view:
                if (!a(0)) {
                    this.j.setCurrentItem(0);
                    return;
                }
                return;
            case R.id.essence_text_view:
                if (!a(1)) {
                    this.j.setCurrentItem(1);
                    return;
                }
                return;
            case R.id.hot_text_view:
                if (!a(2)) {
                    this.j.setCurrentItem(2);
                    return;
                }
                return;
            case R.id.member_text_view:
                if (!a(3)) {
                    this.j.setCurrentItem(3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean a(int i) {
        if (i != this.n) {
            return false;
        }
        if (this.p != null) {
            this.p.a(i);
        }
        return true;
    }
}
