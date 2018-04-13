package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class f implements AnimationListener {
    final /* synthetic */ c a;
    final /* synthetic */ MaterialProgressDrawable b;

    f(MaterialProgressDrawable materialProgressDrawable, c cVar) {
        this.b = materialProgressDrawable;
        this.a = cVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.goToNextColor();
        this.a.storeOriginals();
        this.a.setShowArrow(false);
        this.b.k.startAnimation(this.b.l);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
