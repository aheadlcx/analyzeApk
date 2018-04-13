package qsbk.app.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ToastAndDialog$b implements AnimationListener {
    private final View a;

    public ToastAndDialog$b(View view) {
        this.a = view;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a != null) {
            this.a.setClickable(true);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
