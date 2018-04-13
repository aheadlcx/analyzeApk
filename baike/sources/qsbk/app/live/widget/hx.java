package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import qsbk.app.live.model.LiveMessage;

class hx implements AnimationListener {
    final /* synthetic */ ProTopRankView a;

    hx(ProTopRankView proTopRankView) {
        this.a = proTopRankView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(8);
        this.a.e = true;
        if (this.a.g != null && this.a.g.size() > 0) {
            LiveMessage liveMessage;
            synchronized (this) {
                liveMessage = (LiveMessage) this.a.g.remove(0);
            }
            if (liveMessage != null) {
                this.a.setViewContentAndStartAnim(liveMessage);
            }
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
