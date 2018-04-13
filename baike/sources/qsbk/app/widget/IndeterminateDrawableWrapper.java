package qsbk.app.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.view.animation.AnimationUtils;

public class IndeterminateDrawableWrapper extends Drawable implements Animatable, Callback, Runnable {
    private int a = 10000;
    private Drawable b;
    private int c = 30;
    private int d = 1500;
    private boolean e;
    private long f;

    public IndeterminateDrawableWrapper(Drawable drawable) {
        setIndeterminateDrawable(drawable);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.b != null) {
            this.b.setBounds(rect);
        }
    }

    public boolean getPadding(Rect rect) {
        boolean padding = super.getPadding(rect);
        if (this.b != null) {
            return this.b.getPadding(rect);
        }
        return padding;
    }

    public int getIntrinsicHeight() {
        int intrinsicHeight = super.getIntrinsicHeight();
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return intrinsicHeight;
    }

    public int getIntrinsicWidth() {
        int intrinsicWidth = super.getIntrinsicWidth();
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return intrinsicWidth;
    }

    protected boolean onLevelChange(int i) {
        super.onLevelChange(i);
        if (i < this.a && !isRunning()) {
            start();
        } else if (i >= this.a) {
            stop();
            return false;
        }
        return true;
    }

    public void start() {
        if (!isRunning()) {
            b();
        }
    }

    public void stop() {
        a();
    }

    public boolean isRunning() {
        return this.e || ((this.b instanceof Animatable) && ((Animatable) this.b).isRunning());
    }

    public void draw(Canvas canvas) {
        if (this.b != null) {
            if (!(this.b instanceof Animatable)) {
                this.b.setLevel((int) (c() * ((float) this.a)));
            }
            this.b.draw(canvas);
            if (!isRunning()) {
                b();
            }
        }
    }

    public void setAlpha(int i) {
        if (this.b != null) {
            this.b.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
        }
    }

    public int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return 0;
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        if (this.b != null) {
            this.b.setCallback(null);
        }
        this.b = drawable;
        drawable.setCallback(this);
    }

    public void setDuration(int i) {
        this.d = i;
    }

    public void setFPS(int i) {
        this.c = i;
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (z) {
            start();
        } else {
            stop();
        }
        return visible;
    }

    void a() {
        if (this.b instanceof Animatable) {
            ((Animatable) this.b).stop();
        } else {
            this.e = false;
            unscheduleSelf(this);
        }
        invalidateSelf();
    }

    void b() {
        if (isVisible()) {
            if (this.b instanceof Animatable) {
                ((Animatable) this.b).start();
            } else {
                this.f = AnimationUtils.currentAnimationTimeMillis();
                this.e = true;
                run();
            }
            invalidateSelf();
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public void run() {
        invalidateSelf();
        scheduleSelf(this, AnimationUtils.currentAnimationTimeMillis() + ((long) (1000 / this.c)));
    }

    private float c() {
        float f = 0.0f;
        if (isRunning()) {
            float f2 = (float) this.d;
            f = ((float) (AnimationUtils.currentAnimationTimeMillis() - this.f)) / f2;
            while (f > 1.0f) {
                f -= 1.0f;
                this.f = (long) (((float) this.f) + f2);
            }
        }
        return f;
    }
}
