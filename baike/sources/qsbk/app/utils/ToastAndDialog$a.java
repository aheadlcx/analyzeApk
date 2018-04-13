package qsbk.app.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

class ToastAndDialog$a implements AnimationListener {
    private View a;
    private TextView b;

    public ToastAndDialog$a(View view, TextView textView) {
        this.a = view;
        this.b = textView;
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setClickable(true);
        ((ViewGroup) this.b.getParent()).removeView(this.b);
        this.b.setVisibility(8);
        this.b = null;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
