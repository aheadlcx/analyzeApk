package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class aw extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ FanfanleGameView b;

    aw(FanfanleGameView fanfanleGameView, ImageView imageView) {
        this.b = fanfanleGameView;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.N.setImageDrawable(PokerGroup.getPokerDrawable(this.b.V));
        this.a.setVisibility(4);
        this.a.setScaleX(1.0f);
        this.a.setScaleY(1.0f);
        this.a.setTranslationX(0.0f);
    }
}
