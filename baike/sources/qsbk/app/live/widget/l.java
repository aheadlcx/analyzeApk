package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

class l extends AnimatorListenerAdapter {
    final /* synthetic */ SimpleDraweeView a;
    final /* synthetic */ CatAndDogGameView b;

    l(CatAndDogGameView catAndDogGameView, SimpleDraweeView simpleDraweeView) {
        this.b = catAndDogGameView;
        this.a = simpleDraweeView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.removeView(this.a);
    }
}
