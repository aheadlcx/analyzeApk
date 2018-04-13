package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

class cl extends AnimatorListenerAdapter {
    final /* synthetic */ SimpleDraweeView a;
    final /* synthetic */ GameView b;

    cl(GameView gameView, SimpleDraweeView simpleDraweeView) {
        this.b = gameView;
        this.a = simpleDraweeView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.removeView(this.a);
    }
}
