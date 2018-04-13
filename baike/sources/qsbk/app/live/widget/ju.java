package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;

class ju extends AnimatorListenerAdapter {
    final /* synthetic */ YPDXGameView a;

    ju(YPDXGameView yPDXGameView) {
        this.a = yPDXGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.U.setVisibility(8);
        ViewCompat.setTranslationY(this.a.U, 0.0f);
    }
}
