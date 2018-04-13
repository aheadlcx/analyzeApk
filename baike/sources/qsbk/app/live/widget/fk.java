package qsbk.app.live.widget;

import qsbk.app.core.widget.FrameAnimationView.AnimationListener;
import qsbk.app.live.model.LiveEnterMessage;

class fk implements AnimationListener {
    final /* synthetic */ HighLevelUserEnterView a;

    fk(HighLevelUserEnterView highLevelUserEnterView) {
        this.a = highLevelUserEnterView;
    }

    public void onStart() {
    }

    public void onEnd() {
        this.a.isAvailable = true;
        this.a.B.setVisibility(8);
        this.a.C.setVisibility(8);
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
}
