package qsbk.app.live.widget;

import qsbk.app.core.widget.FrameAnimationView.AnimationListener;
import qsbk.app.live.model.LiveEnterMessage;

class iy implements AnimationListener {
    final /* synthetic */ SuperUserEnterAnimLayout a;

    iy(SuperUserEnterAnimLayout superUserEnterAnimLayout) {
        this.a = superUserEnterAnimLayout;
    }

    public void onStart() {
    }

    public void onEnd() {
        this.a.f = true;
        this.a.d();
        this.a.m.setVisibility(8);
        if (this.a.e != null && this.a.e.size() > 0) {
            LiveEnterMessage liveEnterMessage;
            synchronized (this) {
                liveEnterMessage = (LiveEnterMessage) this.a.e.remove(0);
            }
            if (liveEnterMessage != null) {
                this.a.setViewContentAndStartAnim(liveEnterMessage);
            }
        }
    }
}
