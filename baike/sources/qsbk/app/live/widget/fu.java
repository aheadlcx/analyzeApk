package qsbk.app.live.widget;

import qsbk.app.core.widget.FrameAnimationView;

class fu implements Runnable {
    final /* synthetic */ FrameAnimationView a;
    final /* synthetic */ LargeGiftLayout b;

    fu(LargeGiftLayout largeGiftLayout, FrameAnimationView frameAnimationView) {
        this.b = largeGiftLayout;
        this.a = frameAnimationView;
    }

    public void run() {
        this.a.play();
    }
}
