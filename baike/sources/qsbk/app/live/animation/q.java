package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

class q implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ long d;
    final /* synthetic */ float e;
    final /* synthetic */ float f;
    final /* synthetic */ long g;
    final /* synthetic */ float h;
    final /* synthetic */ long i;
    final /* synthetic */ long j;
    final /* synthetic */ EvilAnimation k;

    q(EvilAnimation evilAnimation, ImageView imageView, int i, int i2, long j, float f, float f2, long j2, float f3, long j3, long j4) {
        this.k = evilAnimation;
        this.a = imageView;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = f;
        this.f = f2;
        this.g = j2;
        this.h = f3;
        this.i = j3;
        this.j = j4;
    }

    public void run() {
        this.a.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_X, new float[]{0.0f, (float) this.b});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[]{0.0f, (float) this.c});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(this.d);
        ofFloat = ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{this.e, this.f});
        ofFloat2 = ObjectAnimator.ofFloat(this.a, View.SCALE_Y, new float[]{this.e, this.f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(this.g);
        ofFloat = ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{this.f, this.h});
        ofFloat2 = ObjectAnimator.ofFloat(this.a, View.SCALE_Y, new float[]{this.f, this.h});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setStartDelay(this.i);
        animatorSet3.setDuration(this.j);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        animatorSet4.addListener(new r(this));
        animatorSet4.start();
    }
}
