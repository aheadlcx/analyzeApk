package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import qsbk.app.live.model.LiveEnterMessage;

class fg implements AnimationListener {
    final /* synthetic */ HighLevelUserEnterView a;

    fg(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.B.setVisibility(8);
        this.a.C.setVisibility(8);
        this.a.i.setVisibility(4);
        this.a.j.setVisibility(4);
        this.a.k.setVisibility(4);
        this.a.l.setVisibility(4);
        this.a.m.setVisibility(4);
        this.a.isAvailable = true;
        if (this.a.b != null && this.a.b.size() > 0) {
            LiveEnterMessage liveEnterMessage;
            synchronized (this) {
                liveEnterMessage = (LiveEnterMessage) this.a.b.remove(0);
            }
            if (liveEnterMessage != null) {
                this.a.setViewContentAndStartAnim(liveEnterMessage);
            }
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
