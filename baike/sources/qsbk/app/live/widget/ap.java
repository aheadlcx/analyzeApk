package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import qsbk.app.live.R;

class ap implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ ImageView c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ ImageView e;
    final /* synthetic */ int f;
    final /* synthetic */ int g;
    final /* synthetic */ ImageView[] h;
    final /* synthetic */ FanfanleGameView i;

    ap(FanfanleGameView fanfanleGameView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, int i, int i2, ImageView[] imageViewArr) {
        this.i = fanfanleGameView;
        this.a = imageView;
        this.b = imageView2;
        this.c = imageView3;
        this.d = imageView4;
        this.e = imageView5;
        this.f = i;
        this.g = i2;
        this.h = imageViewArr;
    }

    public void run() {
        this.i.aa.setBackgroundColor(this.i.getResources().getColor(R.color.black_40_percent_transparent));
        this.i.aa.setVisibility(0);
        this.a.setBackgroundResource(R.drawable.bg_corner_poke_result);
        this.b.setBackgroundResource(R.drawable.bg_corner_poke_result);
        this.a.setVisibility(4);
        this.b.setVisibility(4);
        this.c.setVisibility(4);
        this.d.setVisibility(4);
        this.e.setVisibility(4);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i.aa, View.ALPHA, new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, View.ALPHA, new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.b, View.ALPHA, new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(270);
        animatorSet.start();
        int i = 0;
        while (i < 5) {
            if (!(i + 1 == this.f || i + 1 == this.g)) {
                this.h[i].setVisibility(0);
                this.h[i].setBackgroundColor(this.i.getResources().getColor(R.color.black_40_percent_transparent));
                ofFloat2 = ObjectAnimator.ofFloat(this.h[i], View.ALPHA, new float[]{0.0f, 1.0f});
                ofFloat2.setDuration(270);
                ofFloat2.start();
            }
            i++;
        }
        this.i.m.postDelayed(new aq(this), 3000);
    }
}
