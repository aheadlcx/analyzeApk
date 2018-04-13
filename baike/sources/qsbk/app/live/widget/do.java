package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class do extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ GameWinDialog b;

    do(GameWinDialog gameWinDialog, ImageView imageView) {
        this.b = gameWinDialog;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.d.removeView(this.a);
    }
}
