package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import qsbk.app.core.utils.WindowUtils;

class de extends AnimatorListenerAdapter {
    final /* synthetic */ boolean a;
    final /* synthetic */ LiveBaseActivity b;

    de(LiveBaseActivity liveBaseActivity, boolean z) {
        this.b = liveBaseActivity;
        this.a = z;
    }

    public void onAnimationEnd(Animator animator) {
        this.b.aj.setVisibility(4);
        this.b.aj.hideContent();
        LiveBaseActivity.E(this.b);
        if (this.a) {
            this.b.ak = this.b.aj;
            this.b.ak.reset();
            this.b.aj = null;
            WindowUtils.setTransparentNavigationBar(this.b);
        }
    }
}
