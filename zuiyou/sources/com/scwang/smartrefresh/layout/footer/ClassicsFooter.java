package com.scwang.smartrefresh.layout.footer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.d.a;
import com.scwang.smartrefresh.layout.d.a.b;

public class ClassicsFooter extends RelativeLayout implements d {
    public static String b = "上拉加载更多";
    public static String c = "释放立即加载";
    public static String d = "正在加载...";
    public static String e = "正在刷新...";
    public static String f = "加载完成";
    public static String g = "加载失败";
    public static String h = "全部加载完成";
    protected TextView i;
    protected ImageView j;
    protected ImageView k;
    protected b l;
    protected a m;
    protected SpinnerStyle n = SpinnerStyle.Translate;
    protected g o;
    protected int p = 500;
    protected int q = 0;
    protected boolean r = false;
    protected int s = 20;
    protected int t = 20;

    public ClassicsFooter(Context context) {
        super(context);
        a(context, null, 0);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        com.scwang.smartrefresh.layout.f.b bVar = new com.scwang.smartrefresh.layout.f.b();
        this.i = new TextView(context);
        this.i.setId(16908312);
        this.i.setTextColor(-10066330);
        this.i.setText(b);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.i, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(bVar.c(20.0f), bVar.c(20.0f));
        layoutParams.addRule(15);
        layoutParams.addRule(0, 16908312);
        this.j = new ImageView(context);
        addView(this.j, layoutParams);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(layoutParams);
        layoutParams2.addRule(15);
        layoutParams2.addRule(0, 16908312);
        this.k = new ImageView(context);
        this.k.animate().setInterpolator(new LinearInterpolator());
        addView(this.k, layoutParams2);
        if (isInEditMode()) {
            this.j.setVisibility(8);
        } else {
            this.k.setVisibility(8);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.scwang.smartrefresh.layout.a.a.ClassicsFooter);
        layoutParams2.rightMargin = obtainStyledAttributes.getDimensionPixelSize(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlDrawableMarginRight, bVar.c(20.0f));
        layoutParams.rightMargin = layoutParams2.rightMargin;
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableArrowSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableArrowSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableProgressSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableProgressSize, layoutParams2.height);
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(com.scwang.smartrefresh.layout.a.a.ClassicsHeader_srlDrawableSize, layoutParams2.height);
        this.p = obtainStyledAttributes.getInt(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlFinishDuration, this.p);
        this.n = SpinnerStyle.values()[obtainStyledAttributes.getInt(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlClassicsSpinnerStyle, this.n.ordinal())];
        if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlDrawableArrow)) {
            this.j.setImageDrawable(obtainStyledAttributes.getDrawable(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlDrawableArrow));
        } else {
            this.l = new b();
            this.l.a(new int[]{-10066330});
            this.l.a(new String[]{"M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z"});
            this.j.setImageDrawable(this.l);
        }
        if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlDrawableProgress)) {
            this.k.setImageDrawable(obtainStyledAttributes.getDrawable(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlDrawableProgress));
        } else {
            this.m = new a();
            this.m.a(-10066330);
            this.k.setImageDrawable(this.m);
        }
        if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlTextSizeTitle)) {
            this.i.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlTextSizeTitle, com.scwang.smartrefresh.layout.f.b.a(16.0f)));
        } else {
            this.i.setTextSize(16.0f);
        }
        if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlPrimaryColor)) {
            b(obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlPrimaryColor, 0));
        }
        if (obtainStyledAttributes.hasValue(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlAccentColor)) {
            a(obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.ClassicsFooter_srlAccentColor, 0));
        }
        obtainStyledAttributes.recycle();
        int paddingLeft;
        int c;
        int paddingRight;
        int c2;
        if (getPaddingTop() == 0) {
            if (getPaddingBottom() == 0) {
                paddingLeft = getPaddingLeft();
                c = bVar.c(20.0f);
                this.s = c;
                paddingRight = getPaddingRight();
                c2 = bVar.c(20.0f);
                this.t = c2;
                setPadding(paddingLeft, c, paddingRight, c2);
                return;
            }
            paddingLeft = getPaddingLeft();
            c2 = bVar.c(20.0f);
            this.s = c2;
            c = getPaddingRight();
            paddingRight = getPaddingBottom();
            this.t = paddingRight;
            setPadding(paddingLeft, c2, c, paddingRight);
        } else if (getPaddingBottom() == 0) {
            paddingLeft = getPaddingLeft();
            c = getPaddingTop();
            this.s = c;
            paddingRight = getPaddingRight();
            c2 = bVar.c(20.0f);
            this.t = c2;
            setPadding(paddingLeft, c, paddingRight, c2);
        } else {
            this.s = getPaddingTop();
            this.t = getPaddingBottom();
        }
    }

    protected void onMeasure(int i, int i2) {
        if (MeasureSpec.getMode(i2) == 1073741824) {
            setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        } else {
            setPadding(getPaddingLeft(), this.s, getPaddingRight(), this.t);
        }
        super.onMeasure(i, i2);
    }

    public void a(@NonNull g gVar, int i, int i2) {
        this.o = gVar;
        this.o.b(this.q);
    }

    public boolean a() {
        return false;
    }

    public void a(float f, int i, int i2) {
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
        if (!this.r) {
            this.k.setVisibility(0);
            if (this.m != null) {
                this.m.start();
            } else {
                this.k.animate().rotation(36000.0f).setDuration(100000);
            }
        }
    }

    public void b(@NonNull h hVar, int i, int i2) {
    }

    public int a(@NonNull h hVar, boolean z) {
        if (this.r) {
            return 0;
        }
        if (this.m != null) {
            this.m.stop();
        } else {
            this.k.animate().rotation(0.0f).setDuration(300);
        }
        this.k.setVisibility(8);
        if (z) {
            this.i.setText(f);
        } else {
            this.i.setText(g);
        }
        return this.p;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        int i = -1;
        if (this.n == SpinnerStyle.FixedBehind && iArr.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable)) {
                b(iArr[0]);
            }
            if (iArr.length > 1) {
                a(iArr[1]);
                return;
            }
            if (iArr[0] == -1) {
                i = -10066330;
            }
            a(i);
        }
    }

    public boolean a(boolean z) {
        if (this.r != z) {
            this.r = z;
            if (z) {
                this.i.setText(h);
                this.j.setVisibility(8);
            } else {
                this.i.setText(b);
                this.j.setVisibility(0);
            }
            if (this.m != null) {
                this.m.stop();
            } else {
                this.k.animate().rotation(0.0f).setDuration(300);
            }
            this.k.setVisibility(8);
        }
        return true;
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        return this.n;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        if (!this.r) {
            switch (ClassicsFooter$1.a[refreshState2.ordinal()]) {
                case 1:
                    this.j.setVisibility(0);
                    break;
                case 2:
                    break;
                case 3:
                case 4:
                    this.j.setVisibility(8);
                    this.i.setText(d);
                    return;
                case 5:
                    this.i.setText(c);
                    this.j.animate().rotation(0.0f);
                    return;
                case 6:
                    this.i.setText(e);
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                    return;
                default:
                    return;
            }
            this.i.setText(b);
            this.j.animate().rotation(180.0f);
        }
    }

    public ClassicsFooter a(@ColorInt int i) {
        this.i.setTextColor(i);
        if (this.m != null) {
            this.m.a(i);
        }
        if (this.l != null) {
            this.l.a(new int[]{i});
        }
        return this;
    }

    public ClassicsFooter b(@ColorInt int i) {
        this.q = i;
        setBackgroundColor(i);
        if (this.o != null) {
            this.o.b(this.q);
        }
        return this;
    }

    public TextView getTitleText() {
        return this.i;
    }

    public ImageView getProgressView() {
        return this.k;
    }

    public ImageView getArrowView() {
        return this.j;
    }
}
