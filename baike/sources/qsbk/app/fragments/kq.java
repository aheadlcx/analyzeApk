package qsbk.app.fragments;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

class kq implements Runnable {
    final /* synthetic */ RandomDoorStartFragment a;

    kq(RandomDoorStartFragment randomDoorStartFragment) {
        this.a = randomDoorStartFragment;
    }

    public void run() {
        this.a.b.clearAnimation();
        Animation rotateAnimation = new RotateAnimation(-0.3f, 1.5f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());
        rotateAnimation.setDuration(200);
        if (this.a.m != null) {
            rotateAnimation.setAnimationListener(new kr(this));
        }
        this.a.b.startAnimation(rotateAnimation);
        this.a.b.setText(this.a.a[this.a.d].getName());
    }
}
