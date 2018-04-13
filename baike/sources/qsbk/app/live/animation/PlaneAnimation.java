package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.baidu.mobstat.Config;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class PlaneAnimation extends BaseLargeAnimation {
    private int f;
    private int g;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = this.d;
        this.g = (int) (((double) this.d) * 0.6d);
    }

    public long getGiftId() {
        return 15;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = this.e / 4;
        layoutParams.leftMargin = (-(this.f + view.getWidth())) / 2;
        view.setVisibility(0);
        view.setLayoutParams(layoutParams);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        if (!a(liveGiftMessage, a(R.drawable.live_plane_full))) {
            View imageView = new ImageView(this.a);
            imageView.setImageBitmap(r0);
            b(imageView);
            AnimatorSet c = c(imageView);
            AnimatorSet c2 = c(this.b.mUserInfoLayout);
            c.addListener(new z(this, imageView));
            c.start();
            c2.start();
        }
    }

    private void b(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.topMargin = this.e / 4;
        layoutParams.leftMargin = -this.f;
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private AnimatorSet c(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) (((this.d + this.f) * 1) / 2)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((((-this.e) / 8) * 1) / 2)});
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(1000);
        AnimatorSet animatorSet3 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 1) / 2), (float) (((this.d + this.f) * 5) / 8)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((((-this.e) / 8) * 1) / 2), (float) ((((-this.e) / 8) * 5) / 8)});
        animatorSet3.playTogether(new Animator[]{ofFloat2, ofFloat3});
        animatorSet3.setDuration(Config.BPLUS_DELAY_TIME);
        AnimatorSet animatorSet4 = new AnimatorSet();
        ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (((this.d + this.f) * 5) / 8), (float) (this.d + this.f)});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((((-this.e) / 8) * 5) / 8), (float) ((-this.e) / 8)});
        animatorSet4.playTogether(new Animator[]{ofFloat3, ofFloat4});
        animatorSet4.setDuration(1000);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3, animatorSet4});
        animatorSet.setTarget(view);
        return animatorSet;
    }
}
