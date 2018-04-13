package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeClipBounds extends Transition {
    private static final String[] g = new String[]{"android:clipBounds:clip"};

    public String[] getTransitionProperties() {
        return g;
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            Rect clipBounds = ViewCompat.getClipBounds(view);
            transitionValues.values.put("android:clipBounds:clip", clipBounds);
            if (clipBounds == null) {
                transitionValues.values.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        b(transitionValues);
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.values.containsKey("android:clipBounds:clip") || !transitionValues2.values.containsKey("android:clipBounds:clip")) {
            return null;
        }
        int i;
        Rect rect = (Rect) transitionValues.values.get("android:clipBounds:clip");
        Object obj = (Rect) transitionValues2.values.get("android:clipBounds:clip");
        if (obj == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (rect == null && obj == null) {
            return null;
        }
        if (rect == null) {
            rect = (Rect) transitionValues.values.get("android:clipBounds:bounds");
        } else if (obj == null) {
            Rect rect2 = (Rect) transitionValues2.values.get("android:clipBounds:bounds");
        }
        if (rect.equals(obj)) {
            return null;
        }
        ViewCompat.setClipBounds(transitionValues2.view, rect);
        TypeEvaluator axVar = new ax(new Rect());
        Animator ofObject = ObjectAnimator.ofObject(transitionValues2.view, bz.b, axVar, new Rect[]{rect, obj});
        if (i == 0) {
            return ofObject;
        }
        ofObject.addListener(new o(this, transitionValues2.view));
        return ofObject;
    }
}
