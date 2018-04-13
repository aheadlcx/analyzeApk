package qsbk.app.core.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class y extends AnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ SwitchButton b;

    y(SwitchButton switchButton, int i) {
        this.b = switchButton;
        this.a = i;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.b.q == null) {
            return;
        }
        if (this.a == this.b.c + this.b.e) {
            this.b.q.off();
        } else if (this.a == (this.b.a - this.b.c) - this.b.e) {
            this.b.q.on();
        }
    }
}
