package qsbk.app.core.widget;

import android.view.animation.AlphaAnimation;

class ac implements Runnable {
    final /* synthetic */ VolumeControllerView a;

    ac(VolumeControllerView volumeControllerView) {
        this.a = volumeControllerView;
    }

    public void run() {
        if (this.a.g == null) {
            this.a.g = new AlphaAnimation(1.0f, 0.0f);
            this.a.g.setDuration(1000);
            this.a.g.setFillAfter(true);
        }
        this.a.startAnimation(this.a.g);
        this.a.g.setAnimationListener(new ad(this));
    }
}
