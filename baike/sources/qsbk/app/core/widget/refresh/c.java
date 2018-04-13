package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class c implements AnimationListener {
    final /* synthetic */ a a;
    final /* synthetic */ MaterialProgressDrawable b;

    c(MaterialProgressDrawable materialProgressDrawable, a aVar) {
        this.b = materialProgressDrawable;
        this.a = aVar;
    }

    public void onAnimationStart(Animation animation) {
        this.b.k = 0.0f;
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
        this.a.storeOriginals();
        this.a.goToNextColor();
        this.a.setStartTrim(this.a.getEndTrim());
        if (this.b.a) {
            this.b.a = false;
            animation.setDuration(1332);
            this.a.setShowArrow(false);
            return;
        }
        this.b.k = (this.b.k + 1.0f) % 5.0f;
    }
}
