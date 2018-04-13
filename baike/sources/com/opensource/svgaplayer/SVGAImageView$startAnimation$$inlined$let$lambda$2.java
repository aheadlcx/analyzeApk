package com.opensource.svgaplayer;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.opensource.svgaplayer.SVGAImageView.FillMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\n"}, d2 = {"com/opensource/svgaplayer/SVGAImageView$startAnimation$1$3", "Landroid/animation/Animator$AnimatorListener;", "(Lcom/opensource/svgaplayer/SVGAImageView$startAnimation$1;)V", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "library_release"}, k = 1, mv = {1, 1, 6})
public final class SVGAImageView$startAnimation$$inlined$let$lambda$2 implements AnimatorListener {
    final /* synthetic */ SVGAImageView a;
    final /* synthetic */ SVGADrawable b;

    SVGAImageView$startAnimation$$inlined$let$lambda$2(SVGAImageView sVGAImageView, SVGADrawable sVGADrawable) {
        this.a = sVGAImageView;
        this.b = sVGADrawable;
    }

    public void onAnimationRepeat(Animator animator) {
        SVGACallback callback = this.a.getCallback();
        if (callback != null) {
            callback.onRepeat();
        }
    }

    public void onAnimationEnd(Animator animator) {
        this.a.a = false;
        this.a.stopAnimation();
        if (!this.a.getClearsAfterStop() && Intrinsics.areEqual(this.a.getFillMode(), FillMode.Backward)) {
            this.b.setCurrentFrame$library_release(0);
        }
        SVGACallback callback = this.a.getCallback();
        if (callback != null) {
            callback.onFinished();
        }
    }

    public void onAnimationCancel(Animator animator) {
        this.a.a = false;
    }

    public void onAnimationStart(Animator animator) {
        this.a.a = true;
    }
}
