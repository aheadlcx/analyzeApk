package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
    private static final TimeInterpolator g = new DecelerateInterpolator();
    private static final TimeInterpolator h = new AccelerateInterpolator();
    private int[] i;

    public Explode() {
        this.i = new int[2];
        setPropagation(new CircularPropagation());
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new int[2];
        setPropagation(new CircularPropagation());
    }

    private void b(TransitionValues transitionValues) {
        View view = transitionValues.view;
        view.getLocationOnScreen(this.i);
        int i = this.i[0];
        int i2 = this.i[1];
        transitionValues.values.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        b(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        b(transitionValues);
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        Rect rect = (Rect) transitionValues2.values.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        a((View) viewGroup, rect, this.i);
        return bm.a(view, transitionValues2, rect.left, rect.top, translationX + ((float) this.i[0]), translationY + ((float) this.i[1]), translationX, translationY, g);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        float f;
        float f2;
        Rect rect = (Rect) transitionValues.values.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues.view.getTag(R.id.transition_position);
        if (iArr != null) {
            float f3 = translationX + ((float) (iArr[0] - rect.left));
            f = ((float) (iArr[1] - rect.top)) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
            f2 = f;
            f = f3;
        } else {
            f2 = translationY;
            f = translationX;
        }
        a((View) viewGroup, rect, this.i);
        return bm.a(view, transitionValues, i, i2, translationX, translationY, f + ((float) this.i[0]), f2 + ((float) this.i[1]), h);
    }

    private void a(View view, Rect rect, int[] iArr) {
        int round;
        int height;
        view.getLocationOnScreen(this.i);
        int i = this.i[0];
        int i2 = this.i[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            round = Math.round(view.getTranslationX()) + ((view.getWidth() / 2) + i);
            height = ((view.getHeight() / 2) + i2) + Math.round(view.getTranslationY());
        } else {
            round = epicenter.centerX();
            height = epicenter.centerY();
        }
        float centerX = (float) (rect.centerX() - round);
        float centerY = (float) (rect.centerY() - height);
        if (centerX == 0.0f && centerY == 0.0f) {
            centerX = ((float) (Math.random() * 2.0d)) - 1.0f;
            centerY = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float a = a(centerX, centerY);
        centerX /= a;
        centerY /= a;
        float a2 = a(view, round - i, height - i2);
        iArr[0] = Math.round(centerX * a2);
        iArr[1] = Math.round(a2 * centerY);
    }

    private static float a(View view, int i, int i2) {
        return a((float) Math.max(i, view.getWidth() - i), (float) Math.max(i2, view.getHeight() - i2));
    }

    private static float a(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }
}
