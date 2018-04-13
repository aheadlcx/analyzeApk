package qsbk.app.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class hb implements AnimationListener {
    final /* synthetic */ gz a;

    hb(gz gzVar) {
        this.a = gzVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.a.a.A) {
            this.a.a.n.setMaxLines(1);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
