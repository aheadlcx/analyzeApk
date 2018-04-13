package qsbk.app.live.animation.leonids;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class c implements AnimatorListener {
    final /* synthetic */ ParticleSystem a;

    c(ParticleSystem particleSystem) {
        this.a = particleSystem;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.a.a();
    }

    public void onAnimationCancel(Animator animator) {
        this.a.a();
    }
}
