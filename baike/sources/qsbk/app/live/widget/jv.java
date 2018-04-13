package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;

class jv extends AnimatorListenerAdapter {
    final /* synthetic */ YPDXGameView a;

    jv(YPDXGameView yPDXGameView) {
        this.a = yPDXGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.V.setVisibility(8);
        this.a.U.setVisibility(8);
        ViewCompat.setTranslationX(this.a.V, 0.0f);
        ViewCompat.setTranslationY(this.a.V, 0.0f);
        ViewCompat.setRotation(this.a.V, 0.0f);
        ViewCompat.setTranslationY(this.a.U, 0.0f);
    }
}
