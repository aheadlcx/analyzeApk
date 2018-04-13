package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.core.utils.WindowUtils;

class gm extends AnimatorListenerAdapter {
    final /* synthetic */ LevelGiftBoxDialog a;

    gm(LevelGiftBoxDialog levelGiftBoxDialog) {
        this.a = levelGiftBoxDialog;
    }

    public void onAnimationEnd(Animator animator) {
        LayoutParams layoutParams = (LayoutParams) this.a.p.getLayoutParams();
        layoutParams.height = WindowUtils.dp2Px((((this.a.t.gifts != null ? this.a.t.gifts.size() : 1) / 3) + 1) * 105);
        this.a.p.setLayoutParams(layoutParams);
        this.a.b(0);
    }
}
