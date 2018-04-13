package qsbk.app.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import qsbk.app.R;

public class ExpandableLayout extends FrameLayout {
    public static final int HORIZONTAL = 0;
    public static final String KEY_EXPANSION = "expansion";
    public static final String KEY_SUPER_STATE = "super_state";
    public static final int VERTICAL = 1;
    private int a;
    private float b;
    private float c;
    private int d;
    private int e;
    private Interpolator f;
    private ValueAnimator g;
    private OnExpansionUpdateListener h;

    static abstract class b implements Interpolator {
        private final float[] a;
        private final float b = (1.0f / ((float) (this.a.length - 1)));

        public b(float[] fArr) {
            this.a = fArr;
        }

        public float getInterpolation(float f) {
            if (f >= 1.0f) {
                return 1.0f;
            }
            if (f <= 0.0f) {
                return 0.0f;
            }
            int min = Math.min((int) (((float) (this.a.length - 1)) * f), this.a.length - 2);
            float f2 = (f - (((float) min) * this.b)) / this.b;
            return ((this.a[min + 1] - this.a[min]) * f2) + this.a[min];
        }
    }

    public static class FastOutSlowInInterpolator extends b {
        private static final float[] a = new float[]{0.0f, 1.0E-4f, 2.0E-4f, 5.0E-4f, 9.0E-4f, 0.0014f, 0.002f, 0.0027f, 0.0036f, 0.0046f, 0.0058f, 0.0071f, 0.0085f, 0.0101f, 0.0118f, 0.0137f, 0.0158f, 0.018f, 0.0205f, 0.0231f, 0.0259f, 0.0289f, 0.0321f, 0.0355f, 0.0391f, 0.043f, 0.0471f, 0.0514f, 0.056f, 0.0608f, 0.066f, 0.0714f, 0.0771f, 0.083f, 0.0893f, 0.0959f, 0.1029f, 0.1101f, 0.1177f, 0.1257f, 0.1339f, 0.1426f, 0.1516f, 0.161f, 0.1707f, 0.1808f, 0.1913f, 0.2021f, 0.2133f, 0.2248f, 0.2366f, 0.2487f, 0.2611f, 0.2738f, 0.2867f, 0.2998f, 0.3131f, 0.3265f, 0.34f, 0.3536f, 0.3673f, 0.381f, 0.3946f, 0.4082f, 0.4217f, 0.4352f, 0.4485f, 0.4616f, 0.4746f, 0.4874f, 0.5f, 0.5124f, 0.5246f, 0.5365f, 0.5482f, 0.5597f, 0.571f, 0.582f, 0.5928f, 0.6033f, 0.6136f, 0.6237f, 0.6335f, 0.6431f, 0.6525f, 0.6616f, 0.6706f, 0.6793f, 0.6878f, 0.6961f, 0.7043f, 0.7122f, 0.7199f, 0.7275f, 0.7349f, 0.7421f, 0.7491f, 0.7559f, 0.7626f, 0.7692f, 0.7756f, 0.7818f, 0.7879f, 0.7938f, 0.7996f, 0.8053f, 0.8108f, 0.8162f, 0.8215f, 0.8266f, 0.8317f, 0.8366f, 0.8414f, 0.8461f, 0.8507f, 0.8551f, 0.8595f, 0.8638f, 0.8679f, 0.872f, 0.876f, 0.8798f, 0.8836f, 0.8873f, 0.8909f, 0.8945f, 0.8979f, 0.9013f, 0.9046f, 0.9078f, 0.9109f, 0.9139f, 0.9169f, 0.9198f, 0.9227f, 0.9254f, 0.9281f, 0.9307f, 0.9333f, 0.9358f, 0.9382f, 0.9406f, 0.9429f, 0.9452f, 0.9474f, 0.9495f, 0.9516f, 0.9536f, 0.9556f, 0.9575f, 0.9594f, 0.9612f, 0.9629f, 0.9646f, 0.9663f, 0.9679f, 0.9695f, 0.971f, 0.9725f, 0.9739f, 0.9753f, 0.9766f, 0.9779f, 0.9791f, 0.9803f, 0.9815f, 0.9826f, 0.9837f, 0.9848f, 0.9858f, 0.9867f, 0.9877f, 0.9885f, 0.9894f, 0.9902f, 0.991f, 0.9917f, 0.9924f, 0.9931f, 0.9937f, 0.9944f, 0.9949f, 0.9955f, 0.996f, 0.9964f, 0.9969f, 0.9973f, 0.9977f, 0.998f, 0.9984f, 0.9986f, 0.9989f, 0.9991f, 0.9993f, 0.9995f, 0.9997f, 0.9998f, 0.9999f, 0.9999f, 1.0f, 1.0f};

        public /* bridge */ /* synthetic */ float getInterpolation(float f) {
            return super.getInterpolation(f);
        }

        public FastOutSlowInInterpolator() {
            super(a);
        }
    }

    public interface OnExpansionUpdateListener {
        void onExpansionUpdate(float f, int i);
    }

    public interface State {
        public static final int COLLAPSED = 0;
        public static final int COLLAPSING = 1;
        public static final int EXPANDED = 3;
        public static final int EXPANDING = 2;
    }

    private class a implements AnimatorListener {
        final /* synthetic */ ExpandableLayout a;
        private int b;
        private boolean c;

        public a(ExpandableLayout expandableLayout, int i) {
            this.a = expandableLayout;
            this.b = i;
        }

        public void onAnimationStart(Animator animator) {
            this.a.e = this.b == 0 ? 1 : 2;
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.c) {
                this.a.e = this.b == 0 ? 0 : 3;
                this.a.setExpansion((float) this.b);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.c = true;
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public ExpandableLayout(Context context) {
        this(context, null);
    }

    public ExpandableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 300;
        this.f = new FastOutSlowInInterpolator();
        if (attributeSet != null) {
            int i;
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExpandableLayout);
            this.a = obtainStyledAttributes.getInt(1, 300);
            this.c = obtainStyledAttributes.getBoolean(2, false) ? 1.0f : 0.0f;
            this.d = obtainStyledAttributes.getInt(0, 1);
            this.b = obtainStyledAttributes.getFloat(3, 1.0f);
            obtainStyledAttributes.recycle();
            if (this.c == 0.0f) {
                i = 0;
            } else {
                i = 3;
            }
            this.e = i;
            setParallax(this.b);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Parcelable bundle = new Bundle();
        this.c = isExpanded() ? 1.0f : 0.0f;
        bundle.putFloat(KEY_EXPANSION, this.c);
        bundle.putParcelable(KEY_SUPER_STATE, onSaveInstanceState);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        this.c = bundle.getFloat(KEY_EXPANSION);
        this.e = this.c == 1.0f ? 3 : 0;
        super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i5 = this.d == 0 ? measuredWidth : measuredHeight;
        if (this.c == 0.0f && i5 == 0) {
            i3 = 8;
        } else {
            i3 = 0;
        }
        setVisibility(i3);
        i3 = i5 - Math.round(((float) i5) * this.c);
        if (this.b > 0.0f) {
            float f = this.b * ((float) i3);
            while (i4 < getChildCount()) {
                View childAt = getChildAt(i4);
                if (this.d == 0) {
                    i5 = -1;
                    if (VERSION.SDK_INT >= 17 && getLayoutDirection() == 1) {
                        i5 = 1;
                    }
                    childAt.setTranslationX(((float) i5) * f);
                } else {
                    childAt.setTranslationY(-f);
                }
                i4++;
            }
        }
        if (this.d == 0) {
            setMeasuredDimension(measuredWidth - i3, measuredHeight);
        } else {
            setMeasuredDimension(measuredWidth, measuredHeight - i3);
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (this.g != null) {
            this.g.cancel();
        }
        super.onConfigurationChanged(configuration);
    }

    public int getState() {
        return this.e;
    }

    public boolean isExpanded() {
        return this.e == 2 || this.e == 3;
    }

    public void setExpanded(boolean z) {
        setExpanded(z, true);
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        if (isExpanded()) {
            collapse(z);
        } else {
            expand(z);
        }
    }

    public void expand() {
        expand(true);
    }

    public void expand(boolean z) {
        setExpanded(true, z);
    }

    public void collapse() {
        collapse(true);
    }

    public void collapse(boolean z) {
        setExpanded(false, z);
    }

    public void setExpanded(boolean z, boolean z2) {
        if (z != isExpanded()) {
            int i = z ? 1 : 0;
            if (z2) {
                a(i);
            } else {
                setExpansion((float) i);
            }
        }
    }

    public int getDuration() {
        return this.a;
    }

    public void setDuration(int i) {
        this.a = i;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.f = interpolator;
    }

    public float getExpansion() {
        return this.c;
    }

    public void setExpansion(float f) {
        int i = 0;
        if (this.c != f) {
            float f2 = f - this.c;
            if (f == 0.0f) {
                this.e = 0;
            } else if (f == 1.0f) {
                this.e = 3;
            } else if (f2 < 0.0f) {
                this.e = 1;
            } else if (f2 > 0.0f) {
                this.e = 2;
            }
            if (this.e == 0) {
                i = 8;
            }
            setVisibility(i);
            this.c = f;
            requestLayout();
            if (this.h != null) {
                this.h.onExpansionUpdate(f, this.e);
            }
        }
    }

    public float getParallax() {
        return this.b;
    }

    public void setParallax(float f) {
        this.b = Math.min(1.0f, Math.max(0.0f, f));
    }

    public int getOrientation() {
        return this.d;
    }

    public void setOrientation(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Orientation must be either 0 (horizontal) or 1 (vertical)");
        }
        this.d = i;
    }

    public void setOnExpansionUpdateListener(OnExpansionUpdateListener onExpansionUpdateListener) {
        this.h = onExpansionUpdateListener;
    }

    private void a(int i) {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        this.g = ValueAnimator.ofFloat(new float[]{this.c, (float) i});
        this.g.setInterpolator(this.f);
        this.g.setDuration((long) this.a);
        this.g.addUpdateListener(new bs(this));
        this.g.addListener(new a(this, i));
        this.g.start();
    }
}
