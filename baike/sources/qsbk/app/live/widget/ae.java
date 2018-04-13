package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ae implements AnimationListener {
    final /* synthetic */ FamilyCreatorEnterView a;

    ae(FamilyCreatorEnterView familyCreatorEnterView) {
        this.a = familyCreatorEnterView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(4);
        this.a.g.setVisibility(4);
        this.a.h.setVisibility(4);
        this.a.i.setVisibility(4);
        this.a.j.setVisibility(4);
        this.a.k.setVisibility(4);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
