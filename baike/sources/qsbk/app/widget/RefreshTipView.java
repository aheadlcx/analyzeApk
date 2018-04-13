package qsbk.app.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class RefreshTipView extends TextView {
    public static final long FIRST_REFRESH_INTERVAL = 20000;
    public static final long SECOND_REFRESH_INTERVAL = 180000;
    private static long a = FIRST_REFRESH_INTERVAL;
    private Runnable b = new ej(this);
    private Runnable c = new ek(this);

    public static class SimpleAnimationListener implements AnimatorListener, AnimatorUpdateListener {
        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
        }
    }

    public RefreshTipView(Context context) {
        super(context);
    }

    public RefreshTipView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RefreshTipView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void show() {
        setVisibility(0);
        animate().alpha(1.0f).setDuration(300).setListener(new el(this)).start();
    }

    public void showDelay(long j) {
        a = j;
        removeCallbacks(this.c);
        postDelayed(this.c, j);
    }

    public void hide() {
        animate().alpha(0.0f).setListener(new em(this)).setDuration(300).start();
    }

    public void hideDelay(long j) {
        removeCallbacks(this.b);
        postDelayed(this.b, j);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.b);
        removeCallbacks(this.c);
    }
}
