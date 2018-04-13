package antistatic.spinnerwheel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import qsbk.app.R;

public abstract class AbstractWheelView extends AbstractWheel {
    private static int y = -1;
    protected int m;
    protected int n;
    protected int o;
    protected int p;
    protected int q;
    protected Drawable r;
    protected Paint s;
    protected Paint t;
    protected Animator u;
    protected Animator v;
    protected Bitmap w;
    protected Bitmap x;
    private final String z;

    protected abstract void a(Canvas canvas);

    protected abstract void j();

    public abstract void setSelectorPaintCoeff(float f);

    public AbstractWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        StringBuilder append = new StringBuilder().append(AbstractWheelView.class.getName()).append(" #");
        int i2 = y + 1;
        y = i2;
        this.z = append.append(i2).toString();
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.AbstractWheelView, i, 0);
        this.m = obtainStyledAttributes.getInt(7, 50);
        this.n = obtainStyledAttributes.getInt(5, 70);
        this.o = obtainStyledAttributes.getInt(4, 70);
        this.p = obtainStyledAttributes.getInt(2, 10);
        this.q = obtainStyledAttributes.getDimensionPixelSize(3, 10);
        this.r = obtainStyledAttributes.getDrawable(6);
        obtainStyledAttributes.recycle();
    }

    @SuppressLint({"NewApi"})
    protected void a(Context context) {
        super.a(context);
        if (VERSION.SDK_INT >= 11) {
            this.u = ObjectAnimator.ofFloat(this, "selectorPaintCoeff", new float[]{1.0f, 0.0f});
            this.v = ObjectAnimator.ofInt(this, "separatorsPaintAlpha", new int[]{this.n, this.o});
        }
        this.t = new Paint();
        this.t.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.t.setAlpha(this.o);
        this.s = new Paint();
        this.s.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
    }

    protected void a(int i, int i2) {
        this.w = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        this.x = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        setSelectorPaintCoeff(0.0f);
    }

    public void setSeparatorsPaintAlpha(int i) {
        this.t.setAlpha(i);
        invalidate();
    }

    public void setSelectionDivider(Drawable drawable) {
        this.r = drawable;
    }

    @SuppressLint({"NewApi"})
    protected void b() {
        if (VERSION.SDK_INT >= 11) {
            this.u.cancel();
            this.v.cancel();
        }
        setSelectorPaintCoeff(1.0f);
        setSeparatorsPaintAlpha(this.n);
    }

    protected void c() {
        super.c();
        a(750);
        b(750);
    }

    protected void d() {
        a(500);
        b(500);
    }

    @SuppressLint({"NewApi"})
    private void a(long j) {
        if (VERSION.SDK_INT >= 11) {
            this.u.setDuration(j);
            this.u.start();
        }
    }

    @SuppressLint({"NewApi"})
    private void b(long j) {
        if (VERSION.SDK_INT >= 11) {
            this.v.setDuration(j);
            this.v.start();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.j != null && this.j.getItemsCount() > 0) {
            if (i()) {
                j();
            }
            f();
            a(canvas);
        }
    }
}
