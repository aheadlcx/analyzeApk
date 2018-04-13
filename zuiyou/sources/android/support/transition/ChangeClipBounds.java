package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties = new String[]{PROPNAME_CLIP};

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            Rect clipBounds = ViewCompat.getClipBounds(view);
            transitionValues.values.put(PROPNAME_CLIP, clipBounds);
            if (clipBounds == null) {
                transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.values.containsKey(PROPNAME_CLIP) || !transitionValues2.values.containsKey(PROPNAME_CLIP)) {
            return null;
        }
        int i;
        Rect rect = (Rect) transitionValues.values.get(PROPNAME_CLIP);
        Object obj = (Rect) transitionValues2.values.get(PROPNAME_CLIP);
        if (obj == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (rect == null && obj == null) {
            return null;
        }
        if (rect == null) {
            rect = (Rect) transitionValues.values.get(PROPNAME_BOUNDS);
        } else if (obj == null) {
            Rect rect2 = (Rect) transitionValues2.values.get(PROPNAME_BOUNDS);
        }
        if (rect.equals(obj)) {
            return null;
        }
        ViewCompat.setClipBounds(transitionValues2.view, rect);
        TypeEvaluator rectEvaluator = new RectEvaluator(new Rect());
        Animator ofObject = ObjectAnimator.ofObject(transitionValues2.view, ViewUtils.CLIP_BOUNDS, rectEvaluator, new Rect[]{rect, obj});
        if (i == 0) {
            return ofObject;
        }
        final View view = transitionValues2.view;
        ofObject.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ViewCompat.setClipBounds(view, null);
            }
        });
        return ofObject;
    }
}
