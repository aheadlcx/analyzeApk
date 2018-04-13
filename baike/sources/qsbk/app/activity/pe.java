package qsbk.app.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class pe implements AnimationListener {
    final /* synthetic */ ImagesPickerActivity a;

    pe(ImagesPickerActivity imagesPickerActivity) {
        this.a = imagesPickerActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.f.setVisibility(8);
        this.a.g.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
