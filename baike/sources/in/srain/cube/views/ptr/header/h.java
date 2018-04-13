package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class h implements AnimationListener {
    final /* synthetic */ c a;
    final /* synthetic */ MaterialProgressDrawable b;

    h(MaterialProgressDrawable materialProgressDrawable, c cVar) {
        this.b = materialProgressDrawable;
        this.a = cVar;
    }

    public void onAnimationStart(Animation animation) {
        this.b.m = 0.0f;
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
        this.a.storeOriginals();
        this.a.goToNextColor();
        this.a.setStartTrim(this.a.getEndTrim());
        this.b.m = (this.b.m + 1.0f) % 5.0f;
    }
}
