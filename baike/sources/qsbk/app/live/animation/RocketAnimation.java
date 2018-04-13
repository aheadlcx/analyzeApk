package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;
import qsbk.app.live.widget.LiveRocketBackground;

public class RocketAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = this.d / 2;
        this.g = (this.f * 7) / 4;
        this.h = this.d / 5;
        this.i = (this.h * 13) / 5;
        this.j = this.d / 4;
        this.k = this.j * 3;
    }

    public long getGiftId() {
        return 16;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.bottomMargin = -view.getHeight();
        layoutParams.leftMargin = (this.d - view.getWidth()) / 2;
        layoutParams.addRule(12, -1);
        view.setVisibility(0);
        view.setLayoutParams(layoutParams);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        Bitmap a = a(R.drawable.live_rocket_big);
        Bitmap a2 = a(R.drawable.live_rocket_fire);
        Bitmap a3 = a(R.drawable.live_rocket_small);
        Bitmap a4 = a(R.drawable.live_rocket_small);
        if (!a(liveGiftMessage, a, a2)) {
            View imageView = new ImageView(this.a);
            imageView.setImageBitmap(a3);
            b(imageView);
            ImageView imageView2 = new ImageView(this.a);
            imageView2.setImageBitmap(a4);
            c(imageView2);
            View liveRocketBackground = new LiveRocketBackground(this.a);
            liveRocketBackground.setLayoutParams(new RelativeLayout.LayoutParams(this.d, this.e));
            liveRocketBackground.show(0);
            b(liveRocketBackground);
            ObjectAnimator.ofFloat(liveRocketBackground, View.ALPHA, new float[]{0.0f, 0.3f}).setDuration(320);
            ObjectAnimator.ofFloat(liveRocketBackground, View.ALPHA, new float[]{0.3f, 0.3f}).setDuration(4040);
            ObjectAnimator.ofFloat(liveRocketBackground, View.ALPHA, new float[]{0.3f, 0.0f}).setDuration(320);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{r2, r3, r7});
            animatorSet.start();
            View imageView3 = new ImageView(this.a);
            imageView3.setImageBitmap(a2);
            d((ImageView) imageView3);
            View imageView4 = new ImageView(this.a);
            imageView4.setImageBitmap(a);
            e((ImageView) imageView4);
            AnimatorSet f = f(this.b.mUserInfoLayout);
            animatorSet = c(imageView4);
            AnimatorSet d = d(imageView3);
            AnimatorSet g = g(imageView);
            AnimatorSet h = h(imageView2);
            animatorSet.addListener(new aa(this, imageView4, imageView3, imageView, imageView2, liveRocketBackground));
            f.start();
            animatorSet.start();
            d.start();
            g.start();
            h.start();
        }
    }

    private void b(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j, this.k);
        layoutParams.bottomMargin = -this.k;
        layoutParams.leftMargin = 0;
        layoutParams.addRule(12, -1);
        layoutParams.addRule(9, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void c(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j, this.k);
        layoutParams.bottomMargin = -this.k;
        layoutParams.rightMargin = 0;
        layoutParams.addRule(12, -1);
        layoutParams.addRule(11, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void d(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h, this.i);
        layoutParams.bottomMargin = -((this.g + (this.i / 2)) + this.b.mUserInfoLayout.getHeight());
        layoutParams.leftMargin = (this.d - this.h) / 2;
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void e(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.bottomMargin = -(this.g + this.b.mUserInfoLayout.getHeight());
        layoutParams.leftMargin = (this.d - this.f) / 2;
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private AnimatorSet c(View view) {
        AnimatorSet e = e(view);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 0.89f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 0.89f});
        animatorSet.setDuration(48);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        AnimatorSet animatorSet2 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.89f, 1.03f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.89f, 1.03f});
        animatorSet2.setDuration(3392);
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        AnimatorSet animatorSet3 = new AnimatorSet();
        ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.03f, 1.17f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.03f, 1.17f});
        animatorSet3.setDuration(1040);
        animatorSet3.playTogether(new Animator[]{ofFloat3, ofFloat4});
        new AnimatorSet().playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{e, r4});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet d(View view) {
        AnimatorSet e = e(view);
        ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 2.68f}).setDuration(1120);
        ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{2.68f, 1.0f}).setDuration(280);
        ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.0f}).setDuration(2000);
        ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 2.68f}).setDuration(200);
        new AnimatorSet().playSequentially(new Animator[]{r1, r2, r3, r4});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{e, r5});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet e(View view) {
        int i = -(((this.b.mUserInfoLayout.getHeight() + this.e) + this.g) + (this.i / 2));
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((i * 4) / 9)}).setDuration(720);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 4) / 9), (float) (i / 3)}).setDuration(3460);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (i / 3), (float) i}).setDuration(820);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r1, r2, r0});
        return animatorSet;
    }

    private AnimatorSet f(View view) {
        int i = -(((this.b.mUserInfoLayout.getHeight() + this.e) + this.g) + (this.i / 2));
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((i * 4) / 9)}).setDuration(720);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 4) / 9), (float) ((i * 11) / 30)}).setDuration(3460);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 11) / 30), (float) i}).setDuration(820);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{r1, r2, r0});
        return animatorSet;
    }

    private AnimatorSet g(View view) {
        int i = -(this.e + this.k);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((i * 3) / 9)}).setDuration(1160);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 3) / 9), (float) ((i * 11) / 30)}).setDuration(3220);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 11) / 30), (float) i}).setDuration(620);
        new AnimatorSet().playSequentially(new Animator[]{r1, r2, r0});
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.7f, 0.7f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.7f, 0.7f});
        animatorSet.setDuration(1440);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        AnimatorSet animatorSet2 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.7f, 0.88f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.7f, 0.88f});
        animatorSet2.setDuration(1400);
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        new AnimatorSet().playSequentially(new Animator[]{animatorSet, animatorSet2});
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{r3, r2});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet h(View view) {
        int i = -(this.e + this.k);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((i * 3) / 9)}).setDuration(1160);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 3) / 9), (float) ((i * 9) / 30)}).setDuration(3220);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((i * 9) / 30), (float) i}).setDuration(620);
        new AnimatorSet().playSequentially(new Animator[]{r1, r2, r0});
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 1.12f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 1.12f});
        animatorSet.setDuration(720);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        AnimatorSet animatorSet2 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.12f, 0.56f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.12f, 0.56f});
        animatorSet2.setDuration(3080);
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        new AnimatorSet().playSequentially(new Animator[]{animatorSet, animatorSet2});
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{r3, r2});
        animatorSet.setTarget(view);
        return animatorSet;
    }
}
