package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class ChangEAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = (int) (((double) this.d) * 0.8d);
        this.g = (int) (((double) this.f) / 1.33d);
        this.h = (int) (((double) this.d) * 0.5d);
        this.i = (int) (((double) this.d) * 0.5d);
    }

    public long getGiftId() {
        return 31;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = (int) ((((double) this.e) * 0.75d) + (((double) this.g) * 0.25d));
        layoutParams.leftMargin = -this.f;
        view.setVisibility(0);
        view.setLayoutParams(layoutParams);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        if (!a(liveGiftMessage, a(R.drawable.live_moongoddess), a(R.drawable.live_moon))) {
            View imageView = new ImageView(this.a);
            imageView.setBackgroundColor(this.a.getResources().getColor(R.color.black));
            ImageView imageView2 = new ImageView(this.a);
            imageView2.setImageBitmap(r0);
            ImageView imageView3 = new ImageView(this.a);
            imageView3.setImageBitmap(r1);
            b(imageView);
            d(imageView3);
            c(imageView2);
            AnimatorSet d = d(imageView);
            AnimatorSet e = e(imageView2);
            AnimatorSet f = f(imageView3);
            AnimatorSet c = c(this.b.mUserInfoLayout);
            d.addListener(new h(this, imageView));
            e.addListener(new i(this, imageView2));
            f.addListener(new j(this, imageView3));
            d.start();
            e.start();
            f.start();
            c.start();
        }
    }

    private void b(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        imageView.setVisibility(0);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void c(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.topMargin = (int) (((double) this.e) * 0.75d);
        layoutParams.leftMargin = -this.f;
        imageView.setVisibility(0);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void d(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h, this.i);
        layoutParams.topMargin = 0;
        layoutParams.rightMargin = -this.h;
        layoutParams.addRule(11);
        imageView.setVisibility(0);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private AnimatorSet e(ImageView imageView) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        int i = (int) (((double) ((int) (((double) (this.d + this.f)) * 0.5d))) + (((double) (this.d + this.h)) * 0.5d));
        int i2 = (int) (((double) ((int) ((((double) this.e) * 0.25d) + (((double) this.g) * 0.25d)))) + (((double) (this.e - this.h)) * 0.5d));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, (float) r6});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) (-r7)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{1.0f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.2f, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.2f, 1.0f});
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
        animatorSet.setDuration(2500);
        int dp2Px = WindowUtils.dp2Px(10);
        ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-r7), (float) (-(r7 + dp2Px))});
        ofFloat3 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-(r7 + dp2Px)), (float) (-(r7 - dp2Px))});
        ofFloat4 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-(r7 - dp2Px)), (float) (-(r7 + dp2Px))});
        ofFloat5 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-(r7 + dp2Px)), (float) (-(r7 - dp2Px))});
        ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-(r7 - dp2Px)), (float) (-r7)});
        animatorSet2.playSequentially(new Animator[]{ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat});
        animatorSet2.setDuration(600);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{(float) r6, (float) i});
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) (-r7), (float) (-i2)});
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{1.0f, 0.5f});
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, 0.8f});
        ofFloat = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, 0.8f});
        animatorSet3.playTogether(new Animator[]{ofFloat6, ofFloat7, ofFloat8, ofFloat9, ofFloat});
        animatorSet3.setDuration(2000);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        return animatorSet4;
    }

    private AnimatorSet f(ImageView imageView) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        AnimatorSet animatorSet5 = new AnimatorSet();
        int i = (int) (((double) (this.d + this.h)) * 0.5d);
        int i2 = (int) (((double) (this.e - this.h)) * 0.5d);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, 0.0f});
        animatorSet.playTogether(new Animator[]{ofFloat});
        animatorSet.setDuration(1000);
        ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{0.0f, (float) (-i)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{0.0f, (float) i2});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{0.2f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{0.8f, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{0.8f, 1.0f});
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
        animatorSet2.setDuration(1500);
        ofFloat = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{(float) (-i), (float) (-i)});
        animatorSet3.playTogether(new Animator[]{ofFloat});
        animatorSet3.setDuration(3000);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, new float[]{(float) (-i), 0.0f});
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, new float[]{(float) i2, 0.0f});
        ofFloat = ObjectAnimator.ofFloat(imageView, View.ALPHA, new float[]{1.0f, 0.5f});
        ofFloat2 = ObjectAnimator.ofFloat(imageView, View.SCALE_X, new float[]{1.0f, 0.8f});
        ofFloat3 = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, new float[]{1.0f, 0.8f});
        animatorSet4.playTogether(new Animator[]{ofFloat6, ofFloat7, ofFloat, ofFloat2, ofFloat3});
        animatorSet4.setDuration(2000);
        animatorSet5.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, animatorSet4});
        return animatorSet5;
    }

    private AnimatorSet c(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        int width = this.b.mUserInfoLayout.getWidth();
        int i = (int) (((((double) this.e) * 0.75d) - (((double) (this.e - this.i)) * 0.5d)) + (((double) this.g) * 0.25d));
        int height = (this.b.mUserInfoLayout.getHeight() + WindowUtils.dp2Px(10)) + i;
        i = (int) (((double) ((int) ((((double) this.f) + (((double) this.d) * 0.5d)) - (((double) width) * 0.5d)))) + (((double) (this.d + this.h)) * 0.5d));
        int i2 = (int) (((double) height) + (((double) (this.e - this.h)) * 0.5d));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) width});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-height)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.0f});
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5});
        animatorSet.setDuration(2500);
        ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) width, (float) width});
        animatorSet2.playTogether(new Animator[]{ofFloat});
        animatorSet2.setDuration(3000);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) width, (float) i});
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (-height), (float) (-i2)});
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.5f});
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.0f});
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.0f});
        animatorSet3.playTogether(new Animator[]{ofFloat6, ofFloat7, ofFloat8, ofFloat9, ofFloat});
        animatorSet3.setDuration(2000);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        return animatorSet4;
    }

    private AnimatorSet d(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        Animator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f, 0.3f});
        animatorSet.setDuration(2500);
        animatorSet.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.3f, 0.3f});
        animatorSet2.setDuration(3000);
        animatorSet2.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.3f, 0.0f});
        animatorSet3.setDuration(2000);
        animatorSet3.play(ofFloat);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        return animatorSet4;
    }
}
