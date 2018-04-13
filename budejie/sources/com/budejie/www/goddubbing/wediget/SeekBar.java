package com.budejie.www.goddubbing.wediget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import com.budejie.www.R$styleable;
import com.budejie.www.util.an;

public class SeekBar extends View {
    protected Paint a;
    protected Paint b;
    protected Paint c;
    protected float d;
    protected float e;
    protected float f;
    protected float g;
    protected int h;
    protected ValueAnimator i;
    protected int j;
    private int k;
    private int l;
    private boolean m;
    private a n;
    private int o;
    private int p;

    public interface a {
        void b();
    }

    public SeekBar(Context context) {
        this(context, null);
    }

    public SeekBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeekBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeekBar, i, 0);
        this.k = obtainStyledAttributes.getColor(0, -16711936);
        this.l = obtainStyledAttributes.getColor(1, -7829368);
        this.h = obtainStyledAttributes.getColor(2, SupportMenu.CATEGORY_MASK);
        this.f = obtainStyledAttributes.getDimension(3, (float) b(8));
        this.d = obtainStyledAttributes.getDimension(4, (float) b(100));
        this.e = obtainStyledAttributes.getDimension(5, (float) b(200));
        obtainStyledAttributes.recycle();
        this.g = (float) an.b(context, 1.0f);
        g();
    }

    protected void g() {
        this.a = new Paint();
        this.b = new Paint();
        this.c = new Paint();
        a(this.a, this.k);
        a(this.b, this.l);
        a(this.c, -1);
    }

    protected void a(Paint paint, int i) {
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            size = (int) TypedValue.applyDimension(1, 200.0f, getResources().getDisplayMetrics());
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics());
        }
        setMeasuredDimension(size, size2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m) {
            this.d = 0.0f;
        }
        canvas.drawRect(this.d, 0.0f, this.e, this.f, this.a);
        canvas.drawRect(0.0f, 0.0f, this.d, this.f, this.b);
        canvas.drawRect(this.e, 0.0f, (float) an.x(getContext()), this.f, this.b);
        if (this.o != 0) {
            for (int i = 1; i < this.p; i++) {
                float f = this.d + ((float) (this.o * i));
                canvas.drawRect(f, 0.0f, f + this.g, this.f, this.c);
            }
        }
    }

    private int b(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    public void setStartValue(float f) {
        this.d = f;
    }

    public void setEndValue(float f) {
        this.e = f;
    }

    public void a(int i) {
        if (i >= 0) {
            this.m = true;
            this.i = ValueAnimator.ofInt(new int[]{this.j, an.x(getContext())});
            this.i.setDuration((long) i);
            this.i.addUpdateListener(new SeekBar$1(this));
            this.i.start();
        }
    }

    public void d() {
        this.j = 0;
        this.e = 0.0f;
        invalidate();
    }

    public void e() {
        if (this.i != null) {
            this.i.removeAllUpdateListeners();
        }
    }

    public void a() {
        if (this.i != null) {
            this.i.cancel();
        }
    }

    public void f() {
        this.n = null;
    }

    public void setPlayCallBack(a aVar) {
        this.n = aVar;
    }
}
