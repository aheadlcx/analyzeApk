package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import java.util.Random;

class s implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ long d;
    final /* synthetic */ boolean e;
    final /* synthetic */ int f;
    final /* synthetic */ int g;
    final /* synthetic */ long h;
    final /* synthetic */ EvilAnimation i;

    s(EvilAnimation evilAnimation, View view, int i, int i2, long j, boolean z, int i3, int i4, long j2) {
        this.i = evilAnimation;
        this.a = view;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = z;
        this.f = i3;
        this.g = i4;
        this.h = j2;
    }

    public void run() {
        this.a.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_X, new float[]{0.0f, (float) this.b});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[]{0.0f, (float) this.c});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(this.d);
        AnimatorSet animatorSet2 = new AnimatorSet();
        Random random = new Random();
        AnimatorSet[] animatorSetArr = new AnimatorSet[3];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < 3) {
            int nextInt = random.nextInt(40) - 20;
            int nextInt2 = random.nextInt(40) - 20;
            int nextInt3 = random.nextInt(40) - 20;
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_X, new float[]{(float) (this.b + i3), (float) ((this.b + i3) + nextInt)});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[]{(float) (this.c + i), (float) ((this.c + i) + nextInt2)});
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.a, View.ROTATION, new float[]{(float) i4, (float) (i4 + nextInt3)});
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playTogether(new Animator[]{ofFloat3, ofFloat4, ofFloat5});
            animatorSet3.setDuration(1000);
            animatorSetArr[i2] = animatorSet3;
            int i5 = i3 + nextInt;
            i2++;
            i4 += nextInt3;
            i += nextInt2;
            i3 = i5;
        }
        animatorSet2.playSequentially(new Animator[]{animatorSetArr[0], animatorSetArr[1], animatorSetArr[2]});
        ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{1.0f, 1.0f}).setDuration(3000);
        if (!this.e) {
            i = 0;
            i3 = 0;
        }
        ofFloat2 = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_X, new float[]{(float) (i3 + this.b), (float) this.f});
        ofFloat = ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[]{(float) (i + this.c), (float) this.g});
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet4.playTogether(new Animator[]{ofFloat2, ofFloat});
        animatorSet4.setDuration(this.h);
        AnimatorSet animatorSet5 = new AnimatorSet();
        if (this.e) {
            animatorSet5.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet4});
        } else {
            animatorSet5.playSequentially(new Animator[]{animatorSet, r4, animatorSet4});
        }
        animatorSet5.addListener(new t(this));
        animatorSet5.start();
    }
}
