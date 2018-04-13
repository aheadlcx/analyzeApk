package com.opensource.svgaplayer;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "onAnimationUpdate"}, k = 3, mv = {1, 1, 6})
final class f implements AnimatorUpdateListener {
    final /* synthetic */ ValueAnimator a;
    final /* synthetic */ SVGAImageView b;
    final /* synthetic */ SVGADrawable c;

    f(ValueAnimator valueAnimator, SVGAImageView sVGAImageView, SVGADrawable sVGADrawable) {
        this.a = valueAnimator;
        this.b = sVGAImageView;
        this.c = sVGADrawable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        SVGADrawable sVGADrawable = this.c;
        Object animatedValue = this.a.getAnimatedValue();
        if (animatedValue == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        sVGADrawable.setCurrentFrame$library_release(((Integer) animatedValue).intValue());
        SVGACallback callback = this.b.getCallback();
        if (callback != null) {
            callback.onStep(this.c.getCurrentFrame(), ((double) (this.c.getCurrentFrame() + 1)) / ((double) this.c.getVideoItem().getFrames()));
        }
    }
}
