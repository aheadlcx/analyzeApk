package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class w implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ float b;
    final /* synthetic */ long c;
    final /* synthetic */ long d;
    final /* synthetic */ long e;
    final /* synthetic */ float f;
    final /* synthetic */ long g;
    final /* synthetic */ boolean h;
    final /* synthetic */ boolean i;
    final /* synthetic */ EvilAnimation j;

    w(EvilAnimation evilAnimation, ImageView imageView, float f, long j, long j2, long j3, float f2, long j4, boolean z, boolean z2) {
        this.j = evilAnimation;
        this.a = imageView;
        this.b = f;
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = f2;
        this.g = j4;
        this.h = z;
        this.i = z2;
    }

    public void run() {
        List arrayList;
        int i;
        this.a.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{0.0f, this.b});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, View.SCALE_Y, new float[]{0.0f, this.b});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(this.c);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        ObjectAnimator.ofFloat(this.a, View.TRANSLATION_Y, new float[]{0.0f, 0.0f}).setDuration(this.d - this.e);
        ofFloat2 = ObjectAnimator.ofFloat(this.a, View.SCALE_X, new float[]{this.b, this.f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.a, View.SCALE_Y, new float[]{this.b, this.f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(this.g);
        animatorSet2.playTogether(new Animator[]{ofFloat2, ofFloat3});
        new AnimatorSet().playSequentially(new Animator[]{animatorSet, r0, animatorSet2});
        animatorSet = new AnimatorSet();
        animatorSet2 = new AnimatorSet();
        Random random = new Random();
        if (this.h) {
            int i2 = 0;
            arrayList = new ArrayList();
            for (i = 0; ((long) i) < ((this.d - this.e) + this.g) / 500; i++) {
                int nextInt = random.nextInt(60) - 30;
                Animator ofFloat4 = ObjectAnimator.ofFloat(this.a, View.ROTATION, new float[]{(float) i2, (float) (i2 + nextInt)});
                i2 += nextInt;
                ofFloat4.setDuration(500);
                arrayList.add(ofFloat4);
            }
            animatorSet.playSequentially(arrayList);
            animatorSet.setDuration(500);
        }
        if (this.i) {
            float f = 0.0f;
            arrayList = new ArrayList();
            for (i = 0; ((long) i) < ((this.d - this.e) + this.g) / 500; i++) {
                float nextFloat = random.nextFloat();
                ofFloat4 = ObjectAnimator.ofFloat(this.a, View.ALPHA, new float[]{f, f + nextFloat});
                f += nextFloat;
                ofFloat4.setDuration(500);
                arrayList.add(ofFloat4);
            }
            animatorSet2.playSequentially(arrayList);
        }
        AnimatorSet animatorSet3 = new AnimatorSet();
        if (this.h && this.i) {
            animatorSet3.playTogether(new Animator[]{r3, animatorSet, animatorSet2});
        } else if (this.h && !this.i) {
            animatorSet3.playTogether(new Animator[]{r3, animatorSet});
        } else if (!this.i || this.h) {
            animatorSet3.playTogether(new Animator[]{r3});
        } else {
            animatorSet3.playTogether(new Animator[]{r3, animatorSet2});
        }
        animatorSet3.addListener(new x(this));
        animatorSet3.start();
    }
}
