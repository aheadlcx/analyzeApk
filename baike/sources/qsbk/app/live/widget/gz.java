package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import qsbk.app.core.utils.WindowUtils;

class gz extends AnimatorListenerAdapter {
    final /* synthetic */ LevelUpView a;

    gz(LevelUpView levelUpView) {
        this.a = levelUpView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.a.setVisibility(8);
        this.a.d.setRotation(-180.0f);
        this.a.b.setTranslationY((float) WindowUtils.dp2Px(40));
        this.a.e.setTranslationY((float) WindowUtils.dp2Px(46));
        this.a.g = false;
    }
}
