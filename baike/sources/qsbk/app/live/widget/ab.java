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

class ab implements AnimationListener {
    final /* synthetic */ LevelData a;
    final /* synthetic */ AnimationSet b;
    final /* synthetic */ FamilyCreatorEnterView c;

    ab(FamilyCreatorEnterView familyCreatorEnterView, LevelData levelData, AnimationSet animationSet) {
        this.c = familyCreatorEnterView;
        this.a = levelData;
        this.b = animationSet;
    }

    public void onAnimationStart(Animation animation) {
        if (this.c.o == null || !this.c.o.isMessageOverload()) {
            ObjectAnimator.ofFloat(this.c.e, View.TRANSLATION_X, new float[]{(float) this.c.s, (float) (this.c.a.getWidth() - (this.c.s * 4))}).setDuration(400);
            Animator ofFloat = ObjectAnimator.ofFloat(this.c.e, View.ALPHA, new float[]{1.0f, 1.0f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(this.c.e, View.ALPHA, new float[]{1.0f, 0.0f});
            ofFloat.setDuration(200);
            ofFloat2.setDuration(200);
            new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{r0, r3});
            animatorSet.setTarget(this.c.e);
            animatorSet.addListener(new ac(this, animatorSet));
            animatorSet.start();
        }
        if (this.a != null) {
            this.c.j.setVisibility(0);
            this.c.k.setVisibility(0);
            String downloadedGiftResPath = GiftResSync.getDownloadedGiftResPath(this.a.la);
            String downloadedGiftResPath2 = GiftResSync.getDownloadedGiftResPath(this.a.ra);
            if (downloadedGiftResPath != null) {
                AppUtils.getInstance().getImageProvider().loadWebpImage(this.c.j, "file://" + downloadedGiftResPath);
            }
            if (downloadedGiftResPath2 != null) {
                AppUtils.getInstance().getImageProvider().loadWebpImage(this.c.k, "file://" + downloadedGiftResPath2);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        this.c.e.setVisibility(8);
        this.c.startAnimation(this.b);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
