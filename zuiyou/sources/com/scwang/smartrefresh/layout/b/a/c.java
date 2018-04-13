package com.scwang.smartrefresh.layout.b.a;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.scwang.smartrefresh.layout.f.b;
import com.umeng.analytics.a;

public class c extends View {
    private Paint a;
    private Paint b;
    private ValueAnimator c;
    private int d = 0;
    private int e = 270;
    private int f = 0;
    private int g = 0;
    private RectF h = new RectF(0.0f, 0.0f, 0.0f, 0.0f);

    public c(Context context) {
        super(context);
        c();
    }

    private void c() {
        this.a = new Paint();
        this.b = new Paint();
        this.a.setAntiAlias(true);
        this.b.setAntiAlias(true);
        this.a.setColor(-1);
        this.b.setColor(1426063360);
        b bVar = new b();
        this.f = bVar.c(20.0f);
        this.g = bVar.c(7.0f);
        this.a.setStrokeWidth((float) bVar.c(3.0f));
        this.b.setStrokeWidth((float) bVar.c(3.0f));
        this.c = ValueAnimator.ofInt(new int[]{0, a.p});
        this.c.setDuration(720);
        this.c.setRepeatCount(-1);
        this.c.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.c.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.d = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                this.a.postInvalidate();
            }
        });
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.removeAllUpdateListeners();
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), i), resolveSize(getSuggestedMinimumHeight(), i2));
    }

    public void setBackColor(@ColorInt int i) {
        this.b.setColor((ViewCompat.MEASURED_SIZE_MASK & i) | 1426063360);
    }

    public void setFrontColor(@ColorInt int i) {
        this.a.setColor(i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (isInEditMode()) {
            this.e = 0;
            this.d = 270;
        }
        this.a.setStyle(Style.FILL);
        canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) this.f, this.a);
        this.a.setStyle(Style.STROKE);
        canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (this.f + this.g), this.a);
        this.b.setStyle(Style.FILL);
        this.h.set((float) ((width / 2) - this.f), (float) ((height / 2) - this.f), (float) ((width / 2) + this.f), (float) ((height / 2) + this.f));
        canvas.drawArc(this.h, (float) this.e, (float) this.d, true, this.b);
        this.f += this.g;
        this.b.setStyle(Style.STROKE);
        this.h.set((float) ((width / 2) - this.f), (float) ((height / 2) - this.f), (float) ((width / 2) + this.f), (float) ((height / 2) + this.f));
        canvas.drawArc(this.h, (float) this.e, (float) this.d, false, this.b);
        this.f -= this.g;
    }

    public void a() {
        if (this.c != null) {
            this.c.start();
        }
    }

    public void b() {
        if (this.c != null && this.c.isRunning()) {
            this.c.cancel();
        }
    }
}
