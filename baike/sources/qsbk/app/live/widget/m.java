package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class m extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ CatAndDogGameView b;

    m(CatAndDogGameView catAndDogGameView, ImageView imageView) {
        this.b = catAndDogGameView;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.removeView(this.a);
    }
}
