package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;

class hu implements AnimationListener {
    final /* synthetic */ AnimationSet a;
    final /* synthetic */ ProTopRankView b;

    hu(ProTopRankView proTopRankView, AnimationSet animationSet) {
        this.b = proTopRankView;
        this.a = animationSet;
    }

    public void onAnimationStart(Animation animation) {
        if (this.b.k == null || !this.b.k.isMessageOverloadOrLowDevice()) {
            ObjectAnimator.ofFloat(this.b.d, View.TRANSLATION_X, new float[]{(float) this.b.i, (float) (this.b.getWidth() - this.b.i)}).setDuration(400);
            Animator ofFloat = ObjectAnimator.ofFloat(this.b.d, View.ALPHA, new float[]{1.0f, 1.0f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(this.b.d, View.ALPHA, new float[]{1.0f, 0.0f});
            ofFloat.setDuration(200);
            ofFloat2.setDuration(200);
            new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{r0, r3});
            animatorSet.setTarget(this.b.d);
            animatorSet.addListener(new hv(this, animatorSet));
            animatorSet.start();
        }
    }

    public void onAnimationEnd(Animation animation) {
        this.b.d.setVisibility(8);
        this.b.startAnimation(this.a);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
