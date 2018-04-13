package qsbk.app.live.widget;

import qsbk.app.core.widget.FrameAnimationView.AnimationListener;

class gl implements AnimationListener {
    final /* synthetic */ gk a;

    gl(gk gkVar) {
        this.a = gkVar;
    }

    public void onStart() {
        this.a.a.o.setVisibility(8);
        this.a.a.f.setVisibility(8);
        this.a.a.f.stop();
    }

    public void onEnd() {
        this.a.a.o.setVisibility(0);
        this.a.a.f.setVisibility(0);
        this.a.a.f.play();
    }
}
