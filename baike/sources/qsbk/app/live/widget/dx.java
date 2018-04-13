package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.LinearLayout.LayoutParams;
import qsbk.app.core.utils.WindowUtils;

class dx extends AnimatorListenerAdapter {
    final /* synthetic */ dw a;

    dx(dw dwVar) {
        this.a = dwVar;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.a.a.mAnimator != null) {
            this.a.a.mAnimator.start();
            return;
        }
        LayoutParams layoutParams = (LayoutParams) this.a.b.a.getLayoutParams();
        int dp2Px = WindowUtils.dp2Px(50);
        layoutParams.height = dp2Px;
        layoutParams.width = dp2Px;
        this.a.b.a.setLayoutParams(layoutParams);
    }
}
