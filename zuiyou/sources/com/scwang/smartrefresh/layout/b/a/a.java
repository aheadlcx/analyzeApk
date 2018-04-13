package com.scwang.smartrefresh.layout.b.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.ColorInt;
import android.view.View;

public class a extends View {
    private int a;
    private Paint b = new Paint();
    private ValueAnimator c;

    public a(Context context) {
        super(context);
        this.b.setAntiAlias(true);
        this.b.setColor(-1);
        this.b.setStyle(Style.FILL);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    public void setFrontColor(@ColorInt int i) {
        this.b.setColor(i);
    }

    public void a() {
        if (this.c == null) {
            int sqrt = (int) Math.sqrt(Math.pow((double) getHeight(), 2.0d) + Math.pow((double) getWidth(), 2.0d));
            this.c = ValueAnimator.ofInt(new int[]{0, sqrt});
            this.c.setDuration(400);
            this.c.addUpdateListener(new AnimatorUpdateListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.a.a = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    this.a.invalidate();
                }
            });
            this.c.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                }
            });
        }
        this.c.start();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.a, this.b);
    }
}
