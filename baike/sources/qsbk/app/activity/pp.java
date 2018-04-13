package qsbk.app.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class pp implements AnimationListener {
    final /* synthetic */ ImagesPickerForCollectActivity a;

    pp(ImagesPickerForCollectActivity imagesPickerForCollectActivity) {
        this.a = imagesPickerForCollectActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.e.setVisibility(8);
        this.a.f.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
