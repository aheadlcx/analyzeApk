package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class fv extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ LargeGiftLayout b;

    fv(LargeGiftLayout largeGiftLayout, ImageView imageView) {
        this.b = largeGiftLayout;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.removeAnimView(this.a);
    }
}
