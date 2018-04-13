package qsbk.app.live.widget;

import android.view.View;

class fr implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ LargeGiftLayout b;

    fr(LargeGiftLayout largeGiftLayout, View view) {
        this.b = largeGiftLayout;
        this.a = view;
    }

    public void run() {
        this.b.removeView(this.a);
        this.b.postNextAnim();
    }
}
