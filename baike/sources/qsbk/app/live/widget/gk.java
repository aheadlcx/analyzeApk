package qsbk.app.live.widget;

import qsbk.app.core.widget.FrameAnimationView.AnimationListener;

class gk implements AnimationListener {
    final /* synthetic */ LevelGiftBoxDialog a;

    gk(LevelGiftBoxDialog levelGiftBoxDialog) {
        this.a = levelGiftBoxDialog;
    }

    public void onStart() {
        this.a.q.setClickable(false);
    }

    public void onEnd() {
        this.a.q.setClickable(true);
        this.a.g.setVisibility(0);
        this.a.l.setVisibility(0);
        this.a.o.setVisibility(0);
        this.a.r.start();
        this.a.s.start();
        this.a.f.play();
        this.a.e.setFramesInAssets("level_gift_box/moreClick");
        this.a.e.setAnimationListener(new gl(this));
    }
}
