package qsbk.app.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.facebook.drawee.drawable.ScalingUtils.InterpolatingScaleType;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.utils.UIHelper;

public class TransitionDraweeView extends SimpleDraweeView {
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f};
    public static final String TAG = TransitionDraweeView.class.getSimpleName();
    private int a = 300;
    private Interpolator b = new AccelerateDecelerateInterpolator();
    private Rect c;
    private Rect d;
    private Rect e;
    private Rect f;
    private SimpleAnimationListener g;
    private SimpleAnimationListener h;
    private ScaleType i = ScaleType.CENTER_CROP;
    private ScaleType j = ScaleType.FIT_CENTER;
    private boolean k;

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

    public TransitionDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public TransitionDraweeView(Context context) {
        super(context);
        a(context);
    }

    public TransitionDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TransitionDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        if (UIHelper.isNightTheme()) {
            setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
        }
    }

    public void setValidRect(Rect rect) {
        this.c = rect;
    }

    public void setPreBounds(Rect rect) {
        this.d = rect;
    }

    public void setPreVisibleBounds(Rect rect) {
        this.f = rect;
    }

    public void setAfterBounds(Rect rect) {
        this.e = rect;
    }

    public void startEnterAnim() {
        Animator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (this.d != null && this.e != null) {
            ofFloat.addUpdateListener(new ez(this, new InterpolatingScaleType(this.i, this.j, this.d, this.e)));
            ofFloat.addListener(new fa(this, ofFloat));
            ofFloat.setDuration((long) this.a);
            ofFloat.start();
        } else if (this.g != null) {
            this.g.onAnimationEnd(ofFloat);
        }
    }

    public void startExitAnim() {
        if (this.d != null && this.e != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new fb(this, new InterpolatingScaleType(this.i, this.j, this.d, this.e)));
            ofFloat.setInterpolator(this.b);
            ofFloat.setDuration((long) this.a);
            ofFloat.addListener(new fc(this, ofFloat));
            ofFloat.start();
        } else if (this.h != null) {
            this.h.onAnimationEnd(null);
        }
    }

    public void setOnEnterAnimListener(SimpleAnimationListener simpleAnimationListener) {
        this.g = simpleAnimationListener;
    }

    public void setOnExitAnimListener(SimpleAnimationListener simpleAnimationListener) {
        this.h = simpleAnimationListener;
    }

    private Rect a(float f) {
        Rect rect = this.f == null ? this.d : this.f;
        int round = Math.round(((float) (this.e.width() - rect.width())) * f);
        int round2 = Math.round(((float) (this.e.height() - rect.height())) * f);
        Rect rect2 = new Rect(rect);
        rect2.inset(-round, -round2);
        rect2.offset(this.e.left, this.e.top);
        return rect2;
    }

    private Rect b(float f) {
        Rect rect = this.f == null ? this.d : this.f;
        int round = Math.round(((float) (this.e.width() - rect.width())) * (1.0f - f));
        int round2 = Math.round(((float) (this.e.height() - rect.height())) * (1.0f - f));
        Rect rect2 = new Rect(rect);
        rect2.inset(-round, -round2);
        if (this.f != null) {
            if (rect2.top < this.f.top && this.f.top - this.d.top > 5) {
                rect2.top = this.f.top;
            }
            if (rect2.bottom > this.f.bottom && this.d.bottom - this.f.bottom > 5) {
                rect2.bottom = this.f.bottom;
            }
        }
        rect2.offset(this.e.left, this.e.top);
        return rect2;
    }

    protected void onDraw(Canvas canvas) {
        if (this.c != null) {
            canvas.clipRect(this.c);
        }
        super.onDraw(canvas);
    }

    public void setPreScaleType(ScaleType scaleType) {
        this.i = scaleType;
    }

    public void setAfterScaleType(ScaleType scaleType) {
        this.j = scaleType;
    }

    public int getAnimDuration() {
        return this.a;
    }

    public void setAnimDuration(int i) {
        this.a = i;
    }

    public void setFadeOut(boolean z) {
        this.k = z;
    }
}
