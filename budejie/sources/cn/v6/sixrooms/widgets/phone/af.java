package cn.v6.sixrooms.widgets.phone;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

final class af implements AnimationListener {
    final /* synthetic */ PublicMenuWidgets a;

    af(PublicMenuWidgets publicMenuWidgets) {
        this.a = publicMenuWidgets;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.a.setVisibility(8);
        this.a.b = false;
    }
}
