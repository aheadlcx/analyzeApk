package qsbk.app.ticker;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import qsbk.app.R;

public class TickerView extends View {
    private static final Interpolator b = new AccelerateDecelerateInterpolator();
    protected final Paint a = new TextPaint(1);
    private final c c = new c(this.a);
    private final b d = new b(this.c);
    private final ValueAnimator e = ValueAnimator.ofFloat(new float[]{1.0f});
    private final Rect f = new Rect();
    private int g;
    private int h;
    private float i;
    private int j;
    private long k;
    private Interpolator l;
    private int m;
    private boolean n;

    public TickerView(Context context) {
        super(context);
        a(context, null, 0, 0);
    }

    public TickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0, 0);
    }

    public TickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public TickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    static void a(Canvas canvas, int i, Rect rect, float f, float f2) {
        float f3;
        float f4;
        int width = rect.width();
        int height = rect.height();
        if ((i & 16) == 16) {
            f3 = ((((float) height) - f2) / 2.0f) + ((float) rect.top);
        } else {
            f3 = 0.0f;
        }
        if ((i & 1) == 1) {
            f4 = ((float) rect.left) + ((((float) width) - f) / 2.0f);
        } else {
            f4 = 0.0f;
        }
        if ((i & 48) == 48) {
            f3 = 0.0f;
        }
        if ((i & 80) == 80) {
            f3 = ((float) rect.top) + (((float) height) - f2);
        }
        if ((i & GravityCompat.START) == GravityCompat.START) {
            f4 = 0.0f;
        }
        if ((i & GravityCompat.END) == GravityCompat.END) {
            f4 = ((float) rect.left) + (((float) width) - f);
        }
        canvas.translate(f4, f3);
        canvas.clipRect(0.0f, 0.0f, f, f2);
    }

    protected void a(Context context, AttributeSet attributeSet, int i, int i2) {
        int i3 = -16777216;
        float applyDimension = TypedValue.applyDimension(2, 12.0f, context.getResources().getDisplayMetrics());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ticker_TickerView, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        if (resourceId != -1) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, new int[]{16842901, 16842904});
            applyDimension = obtainStyledAttributes2.getDimension(0, applyDimension);
            i3 = obtainStyledAttributes2.getColor(1, -16777216);
            obtainStyledAttributes2.recycle();
        }
        int i4 = obtainStyledAttributes.getInt(3, GravityCompat.START);
        i3 = obtainStyledAttributes.getColor(2, i3);
        applyDimension = (float) ((int) (((double) obtainStyledAttributes.getDimension(1, applyDimension)) + 0.5d));
        this.l = b;
        this.k = (long) obtainStyledAttributes.getInt(4, 350);
        this.n = obtainStyledAttributes.getBoolean(5, false);
        this.m = i4;
        setTextColor(i3);
        setTextSize(applyDimension);
        obtainStyledAttributes.recycle();
        this.e.addUpdateListener(new d(this));
        this.e.addListener(new e(this));
    }

    public void setCharacterList(char[] cArr) {
        Object obj = null;
        for (char c : cArr) {
            if (c == '\u0000') {
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            throw new IllegalArgumentException("Missing TickerUtils#EMPTY_CHAR in character list");
        }
        this.d.a(cArr);
    }

    public void setText(String str) {
        setText(str, this.d.c() > 0.0f);
    }

    public synchronized void setText(String str, boolean z) {
        char[] toCharArray = str == null ? new char[0] : str.toCharArray();
        if (!this.d.b(toCharArray)) {
            this.d.c(toCharArray);
            setContentDescription(str);
            if (z) {
                if (this.e.isRunning()) {
                    this.e.cancel();
                }
                this.e.setDuration(this.k);
                this.e.setInterpolator(this.l);
                this.e.start();
            } else {
                this.d.a(1.0f);
                this.d.a();
                a();
                invalidate();
            }
        }
    }

    public int getTextColor() {
        return this.j;
    }

    public void setTextColor(int i) {
        if (this.j != i) {
            this.j = i;
            this.a.setColor(this.j);
            invalidate();
        }
    }

    public float getTextSize() {
        return this.i;
    }

    public void setTextSize(float f) {
        if (this.i != f) {
            this.i = f;
            this.a.setTextSize(f);
            d();
        }
    }

    public Typeface getTypeface() {
        return this.a.getTypeface();
    }

    public void setTypeface(Typeface typeface) {
        this.a.setTypeface(typeface);
        d();
    }

    public long getAnimationDuration() {
        return this.k;
    }

    public void setAnimationDuration(long j) {
        this.k = j;
    }

    public Interpolator getAnimationInterpolator() {
        return this.l;
    }

    public void setAnimationInterpolator(Interpolator interpolator) {
        this.l = interpolator;
    }

    public int getGravity() {
        return this.m;
    }

    public void setGravity(int i) {
        if (this.m != i) {
            this.m = i;
            invalidate();
        }
    }

    public boolean getAnimateMeasurementChange() {
        return this.n;
    }

    public void setAnimateMeasurementChange(boolean z) {
        this.n = z;
    }

    public void addAnimatorListener(AnimatorListener animatorListener) {
        this.e.addListener(animatorListener);
    }

    public void removeAnimatorListener(AnimatorListener animatorListener) {
        this.e.removeListener(animatorListener);
    }

    private void a() {
        Object obj = 1;
        Object obj2 = this.g != b() ? 1 : null;
        if (this.h == c()) {
            obj = null;
        }
        if (obj2 != null || r1 != null) {
            requestLayout();
        }
    }

    private int b() {
        return (((int) (this.n ? this.d.c() : this.d.b())) + getPaddingLeft()) + getPaddingRight();
    }

    private int c() {
        return (((int) this.c.b()) + getPaddingTop()) + getPaddingBottom();
    }

    private void d() {
        this.c.a();
        a();
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        this.g = b();
        this.h = c();
        switch (mode) {
            case Integer.MIN_VALUE:
                size = Math.min(size, this.g);
                break;
            case 0:
                size = this.g;
                break;
        }
        switch (mode2) {
            case Integer.MIN_VALUE:
                size2 = Math.min(size2, this.h);
                break;
            case 0:
                size2 = this.h;
                break;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        a(canvas);
        canvas.translate(0.0f, this.c.c());
        this.d.a(canvas, this.a);
        canvas.restore();
    }

    private void a(Canvas canvas) {
        a(canvas, this.m, this.f, this.d.c(), this.c.b());
    }
}
