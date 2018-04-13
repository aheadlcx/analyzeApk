package cn.xiaochuankeji.tieba.ui.danmaku;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.SystemClock;
import android.text.style.DynamicDrawableSpan;
import android.widget.TextView;

public class a extends DynamicDrawableSpan implements Callback {
    private final TextView a;
    private final AnimationDrawable b;

    public a(TextView textView, AnimationDrawable animationDrawable) {
        this.a = textView;
        this.b = animationDrawable;
        this.b.setCallback(this);
    }

    public Drawable getDrawable() {
        return this.b;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        super.draw(canvas, charSequence, i, i2, f, i3, i4, i5, paint);
    }

    public void invalidateDrawable(Drawable drawable) {
        this.a.invalidate();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.a.postDelayed(runnable, j - SystemClock.uptimeMillis());
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.a.removeCallbacks(runnable);
    }
}
