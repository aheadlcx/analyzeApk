package qsbk.app.live.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.core.model.LevelData;

class aa implements AnimationListener {
    final /* synthetic */ LevelData a;
    final /* synthetic */ Animation b;
    final /* synthetic */ FamilyCreatorEnterView c;

    aa(FamilyCreatorEnterView familyCreatorEnterView, LevelData levelData, Animation animation) {
        this.c = familyCreatorEnterView;
        this.a = levelData;
        this.b = animation;
    }

    public void onAnimationStart(Animation animation) {
        LayoutParams layoutParams = (LayoutParams) this.c.f.getLayoutParams();
        layoutParams.width = this.c.l.getWidth();
        this.c.f.setLayoutParams(layoutParams);
        this.c.r = 0;
        this.c.setVisibility(0);
        this.c.g.setVisibility(0);
        this.c.h.setVisibility(0);
        this.c.i.setVisibility(0);
        this.c.setLevelBackground(this.a);
        this.c.e.setVisibility(8);
    }

    public void onAnimationEnd(Animation animation) {
        this.c.startAnimation(this.b);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
