package qsbk.app.widget;

import android.graphics.drawable.Animatable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

class db extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ PersonalInfoView a;

    db(PersonalInfoView personalInfoView) {
        this.a = personalInfoView;
    }

    public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(str, imageInfo, animatable);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setAnimationListener(new dc(this));
        PersonalInfoView.e(this.a).startAnimation(alphaAnimation);
        PersonalInfoView.f(this.a);
    }

    public void onFailure(String str, Throwable th) {
        super.onFailure(str, th);
        this.a.onBigCoverLoadingFailed();
    }
}
