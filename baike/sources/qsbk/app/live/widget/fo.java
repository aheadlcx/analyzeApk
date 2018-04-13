package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.GiftResSync;

class fo implements AnimationListener {
    final /* synthetic */ LevelData a;
    final /* synthetic */ AnimationSet b;
    final /* synthetic */ HighLevelUserEnterView c;

    fo(HighLevelUserEnterView highLevelUserEnterView, LevelData levelData, AnimationSet animationSet) {
        this.c = highLevelUserEnterView;
        this.a = levelData;
        this.b = animationSet;
    }

    public void onAnimationStart(Animation animation) {
        if (this.c.z == null || !this.c.z.isMessageOverload()) {
            ObjectAnimator.ofFloat(this.c.g, View.TRANSLATION_X, new float[]{(float) this.c.y, (float) (this.c.c.getWidth() - (this.c.y * 4))}).setDuration(400);
            Animator ofFloat = ObjectAnimator.ofFloat(this.c.g, View.ALPHA, new float[]{1.0f, 1.0f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(this.c.g, View.ALPHA, new float[]{1.0f, 0.0f});
            ofFloat.setDuration(200);
            ofFloat2.setDuration(200);
            new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{r0, r3});
            animatorSet.setTarget(this.c.g);
            animatorSet.addListener(new fp(this, animatorSet));
            animatorSet.start();
        }
        if (this.a != null) {
            this.c.l.setVisibility(0);
            this.c.m.setVisibility(0);
            String downloadedGiftResPath = GiftResSync.getDownloadedGiftResPath(this.a.la);
            String downloadedGiftResPath2 = GiftResSync.getDownloadedGiftResPath(this.a.ra);
            if (downloadedGiftResPath != null) {
                AppUtils.getInstance().getImageProvider().loadWebpImage(this.c.l, "file://" + downloadedGiftResPath);
            }
            if (downloadedGiftResPath2 != null) {
                AppUtils.getInstance().getImageProvider().loadWebpImage(this.c.m, "file://" + downloadedGiftResPath2);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        this.c.g.setVisibility(8);
        this.c.startAnimation(this.b);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
