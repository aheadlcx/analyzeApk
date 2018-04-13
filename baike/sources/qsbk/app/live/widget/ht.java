package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ht implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ ProTopRankView b;

    ht(ProTopRankView proTopRankView, Animation animation) {
        this.b = proTopRankView;
        this.a = animation;
    }

    public void onAnimationStart(Animation animation) {
        this.b.setVisibility(0);
        this.b.j = 0;
        this.b.setVisibility(0);
        this.b.d.setVisibility(8);
    }

    public void onAnimationEnd(Animation animation) {
        this.b.startAnimation(this.a);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
