package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import qsbk.app.live.model.LiveEnterMessage;

class je extends AnimatorListenerAdapter {
    final /* synthetic */ SuperUserEnterAnimLayout a;

    je(SuperUserEnterAnimLayout superUserEnterAnimLayout) {
        this.a = superUserEnterAnimLayout;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.f = true;
        this.a.setVisibility(8);
        if (this.a.e != null && this.a.e.size() > 0) {
            LiveEnterMessage liveEnterMessage;
            synchronized (this) {
                liveEnterMessage = (LiveEnterMessage) this.a.e.remove(0);
            }
            if (liveEnterMessage != null) {
                this.a.setViewContentAndStartAnim(liveEnterMessage);
            }
        }
    }
}
