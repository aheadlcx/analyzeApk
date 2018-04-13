package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class jt extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ YPDXGameView b;

    jt(YPDXGameView yPDXGameView, boolean z) {
        this.b = yPDXGameView;
        this.a = z;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.T.setVisibility(0);
        this.b.V.setVisibility(8);
        this.b.U.setVisibility(8);
        if (this.a) {
            this.b.f();
        }
    }
}
