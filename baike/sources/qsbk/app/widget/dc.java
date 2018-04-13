package qsbk.app.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class dc implements AnimationListener {
    final /* synthetic */ db a;

    dc(db dbVar) {
        this.a = dbVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        PersonalInfoView.e(this.a.a).setVisibility(8);
    }
}
