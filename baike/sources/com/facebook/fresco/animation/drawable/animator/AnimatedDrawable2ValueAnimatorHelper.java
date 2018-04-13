package com.facebook.fresco.animation.drawable.animator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;

@TargetApi(11)
public class AnimatedDrawable2ValueAnimatorHelper {

    /* renamed from: com.facebook.fresco.animation.drawable.animator.AnimatedDrawable2ValueAnimatorHelper$1 */
    final class AnonymousClass1 implements AnimatorUpdateListener {
        final /* synthetic */ AnimatedDrawable2 val$drawable;

        AnonymousClass1(AnimatedDrawable2 animatedDrawable2) {
            this.val$drawable = animatedDrawable2;
        }

        @TargetApi(11)
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.val$drawable.setLevel(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    public static ValueAnimator createValueAnimator(AnimatedDrawable2 animatedDrawable2, int i) {
        ValueAnimator createValueAnimator = createValueAnimator(animatedDrawable2);
        if (createValueAnimator == null) {
            return null;
        }
        createValueAnimator.setRepeatCount((int) Math.max(((long) i) / animatedDrawable2.getLoopDurationMs(), 1));
        return createValueAnimator;
    }

    public static ValueAnimator createValueAnimator(AnimatedDrawable2 animatedDrawable2) {
        int loopCount = animatedDrawable2.getLoopCount();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{0, (int) animatedDrawable2.getLoopDurationMs()});
        valueAnimator.setDuration(animatedDrawable2.getLoopDurationMs());
        if (loopCount == 0) {
            loopCount = -1;
        }
        valueAnimator.setRepeatCount(loopCount);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setInterpolator(null);
        valueAnimator.addUpdateListener(createAnimatorUpdateListener(animatedDrawable2));
        return valueAnimator;
    }

    public static AnimatorUpdateListener createAnimatorUpdateListener(AnimatedDrawable2 animatedDrawable2) {
        return new AnonymousClass1(animatedDrawable2);
    }

    private AnimatedDrawable2ValueAnimatorHelper() {
    }
}
