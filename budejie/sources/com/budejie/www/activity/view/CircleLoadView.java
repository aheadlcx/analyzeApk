package com.budejie.www.activity.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.budejie.www.R;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;

public class CircleLoadView extends View {
    private Paint a;
    private Paint b;
    private RectF c;
    private ValueAnimator d;
    private int e;
    private int f;
    private final float g;
    private int h;
    private int i;
    private Context j;
    private float k;
    private float l;

    public CircleLoadView(Context context) {
        this(context, null);
    }

    public CircleLoadView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleLoadView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = 0.0f;
        this.j = context;
        if (ai.a(context) == 0) {
            this.h = getResources().getColor(R.color.load_view_day_default_color);
            this.i = getResources().getColor(R.color.load_view_day_progress_color);
        } else {
            this.h = getResources().getColor(R.color.load_view_night_default_color);
            this.i = getResources().getColor(R.color.load_view_night_progress_color);
        }
        this.g = (float) an.b(context, 2.0f);
        this.l = (float) an.b(context, 12.0f);
        c();
    }

    private void c() {
        this.a = new Paint();
        this.b = new Paint();
        a(this.a, this.h);
        a(this.b, this.i);
    }

    private void a(Paint paint, int i) {
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStrokeWidth(this.g);
        paint.setStyle(Style.STROKE);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f = getMeasuredWidth();
        this.e = getMeasuredHeight();
        if (this.c == null) {
            this.c = new RectF(((float) (this.f / 2)) - this.l, ((float) (this.e / 2)) - this.l, ((float) (this.f / 2)) + this.l, ((float) (this.e / 2)) + this.l);
        }
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawCircle((float) (this.f / 2), (float) (this.e / 2), this.l, this.a);
        canvas.drawArc(this.c, this.k, 90.0f, false, this.b);
    }

    public void a() {
        this.d = ValueAnimator.ofFloat(new float[]{0.0f, 360.0f});
        this.d.setDuration(1000);
        this.d.setRepeatCount(-1);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ CircleLoadView a;

            {
                this.a = r1;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.a.k = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.a.invalidate();
            }
        });
        this.d.start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.removeAllUpdateListeners();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(an.x(this.j), an.a(this.j, 30));
    }

    public void b() {
        if (this.d != null) {
            this.d.cancel();
            this.d.removeAllUpdateListeners();
        }
    }
}
