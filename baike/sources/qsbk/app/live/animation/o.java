package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

class o implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ float b;
    final /* synthetic */ float c;
    final /* synthetic */ long d;
    final /* synthetic */ EvilAnimation e;

    o(EvilAnimation evilAnimation, ImageView imageView, float f, float f2, long j) {
        this.e = evilAnimation;
        this.a = imageView;
        this.b = f;
        this.c = f2;
        this.d = j;
    }

    public void run() {
        this.a.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{this.b, this.c});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, View.SCALE_Y, new float[]{this.b, this.c});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(this.d);
        if (this.c == 0.0f) {
            animatorSet.addListener(new p(this));
        }
        animatorSet.start();
    }
}
