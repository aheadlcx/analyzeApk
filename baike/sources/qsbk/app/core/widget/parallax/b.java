package qsbk.app.core.widget.parallax;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class b implements AnimationListener {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    b(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.b.a.P != null && ((float) this.a) >= this.b.a.O) {
            this.b.a.P.onRefresh();
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
