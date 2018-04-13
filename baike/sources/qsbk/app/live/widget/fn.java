package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;

class fn implements AnimationListener {
    final /* synthetic */ Animation a;
    final /* synthetic */ HighLevelUserEnterView b;

    fn(HighLevelUserEnterView highLevelUserEnterView, Animation animation) {
        this.b = highLevelUserEnterView;
        this.a = animation;
    }

    public void onAnimationStart(Animation animation) {
        LayoutParams layoutParams = (LayoutParams) this.b.h.getLayoutParams();
        layoutParams.width = this.b.n.getWidth();
        this.b.h.setLayoutParams(layoutParams);
        this.b.w = 0;
        this.b.B.setVisibility(0);
        this.b.i.setVisibility(0);
        this.b.j.setVisibility(0);
        this.b.k.setVisibility(0);
        this.b.g.setVisibility(8);
    }

    public void onAnimationEnd(Animation animation) {
        this.b.startAnimation(this.a);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
