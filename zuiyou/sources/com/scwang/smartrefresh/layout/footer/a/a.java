package com.scwang.smartrefresh.layout.footer.a;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import com.scwang.smartrefresh.layout.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class a extends View {
    private Paint a;
    private int b;
    private int c;
    private float d;
    private float[] e;
    private boolean f;
    private ArrayList<ValueAnimator> g;
    private Map<ValueAnimator, AnimatorUpdateListener> h;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = -1118482;
        this.c = -1615546;
        this.e = new float[]{1.0f, 1.0f, 1.0f};
        this.f = false;
        this.h = new HashMap();
        this.d = (float) b.a(4.0f);
        this.a = new Paint();
        this.a.setColor(-1);
        this.a.setStyle(Style.FILL);
        this.a.setAntiAlias(true);
    }

    protected void onMeasure(int i, int i2) {
        int a = b.a(50.0f);
        setMeasuredDimension(resolveSize(a, i), resolveSize(a, i2));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                ((ValueAnimator) this.g.get(i)).cancel();
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        float min = (((float) Math.min(getWidth(), getHeight())) - (this.d * 2.0f)) / 6.0f;
        float width = ((float) (getWidth() / 2)) - ((min * 2.0f) + this.d);
        float height = (float) (getHeight() / 2);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate((((min * 2.0f) * ((float) i)) + width) + (this.d * ((float) i)), height);
            canvas.scale(this.e[i], this.e[i]);
            canvas.drawCircle(0.0f, 0.0f, min, this.a);
            canvas.restore();
        }
    }

    private boolean c() {
        return this.f;
    }

    private void d() {
        this.g = new ArrayList();
        int[] iArr = new int[]{120, 240, com.umeng.analytics.a.p};
        for (int i = 0; i < 3; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.3f, 1.0f});
            ofFloat.setDuration(750);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay((long) iArr[i]);
            this.h.put(ofFloat, new AnimatorUpdateListener(this) {
                final /* synthetic */ a b;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.b.e[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    this.b.postInvalidate();
                }
            });
            this.g.add(ofFloat);
        }
    }

    public void setIndicatorColor(@ColorInt int i) {
        this.a.setColor(i);
    }

    public void setNormalColor(@ColorInt int i) {
        this.b = i;
    }

    public void setAnimatingColor(@ColorInt int i) {
        this.c = i;
    }

    public void a() {
        if (this.g == null) {
            d();
        }
        if (this.g != null && !c()) {
            for (int i = 0; i < this.g.size(); i++) {
                ValueAnimator valueAnimator = (ValueAnimator) this.g.get(i);
                AnimatorUpdateListener animatorUpdateListener = (AnimatorUpdateListener) this.h.get(valueAnimator);
                if (animatorUpdateListener != null) {
                    valueAnimator.addUpdateListener(animatorUpdateListener);
                }
                valueAnimator.start();
            }
            this.f = true;
            setIndicatorColor(this.c);
        }
    }

    public void b() {
        if (this.g != null && this.f) {
            this.f = false;
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ValueAnimator valueAnimator = (ValueAnimator) it.next();
                if (valueAnimator != null) {
                    valueAnimator.removeAllUpdateListeners();
                    valueAnimator.end();
                }
            }
            this.e = new float[]{1.0f, 1.0f, 1.0f};
        }
        setIndicatorColor(this.b);
    }
}
