package qsbk.app.live.widget;

import android.widget.ImageView;
import qsbk.app.core.widget.FrameAnimationView.AnimationListener;

class gh implements AnimationListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ LargeGiftLayout b;

    gh(LargeGiftLayout largeGiftLayout, ImageView imageView) {
        this.b = largeGiftLayout;
        this.a = imageView;
    }

    public void onStart() {
        this.b.a(this.a);
    }

    public void onEnd() {
        this.b.removeAnimView(this.b.l);
        this.b.removeAnimView(this.a);
        this.b.b(this.a);
        this.b.postNextAnim();
    }
}
