package qsbk.app.activity;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

class df implements Runnable {
    final /* synthetic */ de a;

    df(de deVar) {
        this.a = deVar;
    }

    public void run() {
        Animation animationSet = new AnimationSet(true);
        animationSet.setDuration(400);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        animationSet.addAnimation(new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f));
        animationSet.addAnimation(alphaAnimation);
        animationSet.setAnimationListener(new dg(this));
        this.a.a.k.startAnimation(animationSet);
    }
}
