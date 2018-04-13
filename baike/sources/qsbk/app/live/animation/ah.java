package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ah extends AnimatorListenerAdapter {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.f.a(this.a.c);
        this.a.f.a(this.a.d);
        this.a.f.a(this.a.b);
        this.a.f.a(this.a.e);
        this.a.f.b.mUserInfoLayout.setVisibility(4);
        this.a.f.b.mUserInfoLayout.setAlpha(1.0f);
        this.a.f.a();
    }
}
