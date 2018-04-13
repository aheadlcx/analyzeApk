package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;
import qsbk.app.live.animation.leonids.ParticleSystem;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.widget.LargeGiftLayout;

public class ChristmasAnimation extends BaseLargeAnimation {
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private View l;
    private ParticleSystem m;
    private ParticleSystem n;
    private ParticleSystem o;
    private ParticleSystem p;
    private ParticleSystem q;
    private final int r = 50;

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        super.attach(context, largeGiftLayout);
        this.f = (int) (((double) this.d) * 0.5d);
        this.g = this.f;
        this.i = (int) (((double) this.d) * 0.65d);
        this.j = (int) (((double) this.d) * 0.8d);
        this.k = this.j / 7;
        this.l = new View(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.d, 1);
        layoutParams.topMargin = -WindowUtils.dp2Px(10);
        layoutParams.leftMargin = WindowUtils.dp2Px(30);
        layoutParams.rightMargin = WindowUtils.dp2Px(30);
        largeGiftLayout.addView(this.l, -1, layoutParams);
    }

    public long getGiftId() {
        return 43;
    }

    protected void a(View view) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = ((-((int) view.getY())) + view.getTop()) + WindowUtils.dp2Px(80);
        layoutParams.leftMargin = ((-((int) view.getX())) + view.getLeft()) + ((this.d - view.getWidth()) / 2);
        view.setLayoutParams(layoutParams);
        view.setVisibility(4);
    }

    protected void onLoadAnim(LiveGiftMessage liveGiftMessage) {
        Bitmap a = a(R.drawable.christmas_box);
        Bitmap a2 = a(R.drawable.christmas_deer);
        Bitmap a3 = a(R.drawable.christmas_man);
        if (!a(liveGiftMessage, a, a2, a3)) {
            View imageView = new ImageView(this.a);
            if (!e()) {
                a3 = a2;
            }
            imageView.setImageBitmap(a3);
            c((ImageView) imageView);
            View imageView2 = new ImageView(this.a);
            imageView2.setImageBitmap(a);
            b((ImageView) imageView2);
            this.m = new ParticleSystem((Activity) this.a, 400, R.drawable.christmas_snow, 4000).setSpeedModuleAndAngleRange(0.0f, 0.1f, 70, 110).setScaleRange(0.1f, 0.5f).setFadeOut(4000, new AccelerateDecelerateInterpolator()).setAcceleration(2.0E-4f, 90);
            this.n = new ParticleSystem((Activity) this.a, 50, R.drawable.christmas_box_explode_1, 600);
            this.o = new ParticleSystem((Activity) this.a, 50, R.drawable.christmas_box_explode_2, 600);
            this.p = new ParticleSystem((Activity) this.a, 50, R.drawable.christmas_box_explode_3, 600);
            this.q = new ParticleSystem((Activity) this.a, 50, R.drawable.christmas_box_explode_4, 600);
            a(this.n);
            a(this.o);
            a(this.p);
            a(this.q);
            AnimatorSet c = c(imageView2);
            c.addListener(new k(this, imageView2));
            c.start();
            c = d(imageView);
            c.addListener(new l(this, imageView, imageView2));
            a(new m(this), 4200);
            a(new n(this, imageView, c), 1250);
        }
    }

    private void a(ParticleSystem particleSystem) {
        particleSystem.setScaleRange(0.2f, 0.8f);
        particleSystem.setSpeedRange(0.3f, 1.5f);
        particleSystem.setRotationSpeedRange(0.0f, 180.0f);
        particleSystem.setFadeOut(200, new AccelerateInterpolator());
    }

    private void b(ImageView imageView) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.g);
        layoutParams.topMargin = (this.e - this.g) / 2;
        layoutParams.leftMargin = (this.d - this.f) / 2;
        layoutParams.addRule(10, -1);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
    }

    private AnimatorSet c(View view) {
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{(float) ((-(this.e + this.g)) / 2), 0.0f}).setDuration(440);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.5f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.5f, 1.0f});
        animatorSet.setDuration(440);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        new AnimatorSet().playTogether(new Animator[]{r4, animatorSet});
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, -23.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-23.0f, 14.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{14.0f, -17.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-17.0f, 23.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{23.0f, -5.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-5.0f, 25.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{25.0f, 5.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{5.0f, 0.0f}).setDuration((long) 10);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, 23.0f}).setDuration((long) 10);
        float[] fArr = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{23.0f, -15.0f}).setDuration((long) 10);
        float[] fArr2 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{-15.0f, 0.0f}).setDuration((long) 10);
        float[] fArr3 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, -11.0f}).setDuration((long) 10);
        float[] fArr4 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{-11.0f, 20.0f}).setDuration((long) 10);
        float[] fArr5 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{20.0f, -10.0f}).setDuration((long) 10);
        float[] fArr6 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{-10.0f, 5.0f}).setDuration((long) 10);
        float[] fArr7 = new float[2];
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{5.0f, 0.0f}).setDuration((long) 10);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(new Animator[]{r6, r5, r14, ofFloat2, r15, r8, r16, r9, r17, r10, r18, r11, r19, r12, r20, r13, r21});
        animatorSet2.setTarget(view);
        return animatorSet2;
    }

    private void c(ImageView imageView) {
        this.h = e() ? (this.i * 520) / 644 : (this.i * 697) / 643;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h, this.i);
        layoutParams.addRule(13);
        imageView.setLayoutParams(layoutParams);
        b(imageView);
        imageView.setVisibility(8);
    }

    private AnimatorSet d(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 1.0f});
        animatorSet.setDuration(120);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{0.0f, -13.0f}).setDuration(80);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-13.0f, -64.0f}).setDuration(200);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-64.0f, -4.0f}).setDuration(160);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-4.0f, -13.0f}).setDuration(240);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-13.0f, -65.0f}).setDuration(280);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-65.0f, -35.0f}).setDuration(640);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-35.0f, -65.0f}).setDuration(640);
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-65.0f, -35.0f}).setDuration(480);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{0.0f, -460.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-35.0f, -1120.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.0f});
        float[] fArr = new float[2];
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.0f});
        animatorSet2.playTogether(new Animator[]{ofFloat5, ofFloat6});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat3, ofFloat4, animatorSet2});
        animatorSet3.setDuration(320);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playSequentially(new Animator[]{ofFloat, ofFloat2, r5, r6, r7, r8, r9, r10, animatorSet3});
        animatorSet4.setTarget(view);
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playTogether(new Animator[]{animatorSet, animatorSet4});
        animatorSet5.setTarget(view);
        return animatorSet5;
    }

    private boolean e() {
        return this.c % 2 == 0;
    }
}
