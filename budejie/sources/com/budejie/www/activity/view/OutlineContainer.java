package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.budejie.www.R;
import com.budejie.www.util.an;

public class OutlineContainer extends FrameLayout implements Animatable {
    private Paint a;
    private boolean b = false;
    private long c;
    private float d = 1.0f;
    private final Interpolator e = new OutlineContainer$1(this);
    private final Runnable f = new OutlineContainer$2(this);

    public OutlineContainer(Context context) {
        super(context);
        a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public OutlineContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setStrokeWidth((float) an.a(getResources(), 2));
        this.a.setColor(getResources().getColor(R.color.holo_blue));
        this.a.setStyle(Style.STROKE);
        int a = an.a(getResources(), 10);
        setPadding(a, a, a, a);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int a = an.a(getResources(), 5);
        if (this.a.getColor() != JazzyViewPager.a) {
            this.a.setColor(JazzyViewPager.a);
        }
        this.a.setAlpha((int) (this.d * 255.0f));
        canvas.drawRect(new Rect(a, a, getMeasuredWidth() - a, getMeasuredHeight() - a), this.a);
    }

    public void setOutlineAlpha(float f) {
        this.d = f;
    }

    public boolean isRunning() {
        return this.b;
    }

    public void start() {
        if (!this.b) {
            this.b = true;
            this.c = AnimationUtils.currentAnimationTimeMillis();
            post(this.f);
        }
    }

    public void stop() {
        if (this.b) {
            this.b = false;
        }
    }
}
