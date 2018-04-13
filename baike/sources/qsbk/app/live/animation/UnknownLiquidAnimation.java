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

public class UnknownLiquidAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = (int) (((double) this.d) * 0.8d);
        this.g = (int) (((double) this.f) * 0.94d);
        this.h = (int) (((double) this.f) * 0.57d);
        this.i = (int) (((double) this.h) * 1.11d);
        this.j = (int) (((double) this.f) * 0.04d);
        this.k = (int) (((double) this.f) * 0.04d);
        this.l = (int) (((double) this.f) * 0.06d);
        this.m = (int) (((double) this.f) * 0.06d);
        this.n = (int) (((double) this.f) * 0.06d);
        this.o = (int) (((double) this.f) * 0.06d);
    }

    public long getGiftId() {
        return 40;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = ((-((int) view.getY())) + view.getTop()) + WindowUtils.dp2Px(90);
        layoutParams.leftMargin = ((-((int) view.getX())) + view.getLeft()) + ((this.d - view.getWidth()) / 2);
        view.setLayoutParams(layoutParams);
        view.setVisibility(4);
    }

    public void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        if (!a(liveGiftMessage, a(R.drawable.live_anim_unknow_liquid_main), a(R.drawable.live_anim_unknow_liquid_ejection), a(R.drawable.live_anim_unknow_liquid_up), a(R.drawable.live_anim_unknow_liquid_down), a(R.drawable.live_anim_unknow_liquid_rightdown))) {
            ImageView imageView = new ImageView(this.a);
            imageView.setImageBitmap(r2);
            imageView.setVisibility(4);
            d(imageView);
            ImageView imageView2 = new ImageView(this.a);
            imageView2.setImageBitmap(r3);
            imageView2.setVisibility(4);
            e(imageView2);
            ImageView imageView3 = new ImageView(this.a);
            imageView3.setImageBitmap(r7);
            imageView3.setVisibility(4);
            f(imageView3);
            ImageView imageView4 = new ImageView(this.a);
            imageView4.setImageBitmap(r0);
            imageView4.setVisibility(4);
            b(imageView4);
            View imageView5 = new ImageView(this.a);
            imageView5.setImageBitmap(r1);
            c((ImageView) imageView5);
            AnimatorSet e = e(imageView5);
            e.addListener(new ag(this, imageView5, imageView4, imageView, imageView3, imageView2));
            e.start();
        }
    }

    private void b(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.bottomMargin = ((this.e - this.g) * 1) / 2;
        layoutParams.leftMargin = (this.d - this.f) / 2;
        layoutParams.rightMargin = (this.d - this.f) / 2;
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void c(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h, this.i);
        layoutParams.bottomMargin = -this.i;
        layoutParams.leftMargin = (this.d - this.h) / 2;
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void d(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j, this.k);
        layoutParams.bottomMargin = ((this.e + this.g) / 2) - this.k;
        layoutParams.leftMargin = ((this.d - this.f) / 2) + ((int) (((double) this.f) * 0.473d));
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void e(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.l, this.m);
        layoutParams.bottomMargin = (this.e - this.g) / 2;
        layoutParams.leftMargin = ((this.d - this.f) / 2) + ((int) (((double) this.f) * 0.443d));
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private void f(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.n, this.o);
        layoutParams.bottomMargin = ((this.e - this.g) / 2) + ((int) (((double) this.g) * 0.164d));
        layoutParams.leftMargin = ((this.d - this.f) / 2) + ((int) (((double) this.f) * 0.921d));
        layoutParams.addRule(12, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private AnimatorSet c(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, 0.0f});
        animatorSet2.playTogether(new Animator[]{ofFloat});
        animatorSet2.setDuration(1560);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.play(ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}));
        animatorSet3.setDuration(480);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet d(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f});
        animatorSet2.playTogether(new Animator[]{ofFloat});
        animatorSet2.setDuration(1560);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.play(ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}));
        animatorSet3.setDuration(480);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet e(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) (-this.i), (float) (((-(this.e + this.i)) * 1) / 2)});
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(240);
        AnimatorSet animatorSet2 = new AnimatorSet();
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.6f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.6f});
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        animatorSet2.setDuration(240);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{animatorSet, animatorSet2});
        animatorSet3.setTarget(view);
        return animatorSet3;
    }

    private AnimatorSet f(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) (-this.k)}));
        animatorSet2.setDuration(80);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1480);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(480);
        animatorSet.playSequentially(new Animator[]{animatorSet2, r2, r3});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet g(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) this.m});
        animatorSet2.playTogether(new Animator[]{ofFloat});
        animatorSet2.setDuration(80);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) this.m, (float) this.m}));
        animatorSet3.setDuration(440);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) this.m, (float) (((this.e - this.g) / 2) + this.m)}));
        animatorSet4.setDuration(240);
        animatorSet.playSequentially(new Animator[]{animatorSet2, animatorSet3, animatorSet4});
        animatorSet.setTarget(view);
        return animatorSet;
    }

    private AnimatorSet h(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, (float) ((this.n * 4) / 5)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, (float) ((this.o * 4) / 7)});
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(80);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1480);
        ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(480);
        animatorSet.playSequentially(new Animator[]{animatorSet2, r2, ofFloat2});
        animatorSet.setTarget(view);
        return animatorSet;
    }
}
