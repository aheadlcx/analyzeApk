package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.model.LiveEnterFrameUserinfo;

class iw implements Runnable {
    final /* synthetic */ LiveEnterFrameUserinfo a;
    final /* synthetic */ LayoutParams b;
    final /* synthetic */ LiveEnterFrameUserinfo c;
    final /* synthetic */ SuperUserEnterAnimLayout d;

    iw(SuperUserEnterAnimLayout superUserEnterAnimLayout, LiveEnterFrameUserinfo liveEnterFrameUserinfo, LayoutParams layoutParams, LiveEnterFrameUserinfo liveEnterFrameUserinfo2) {
        this.d = superUserEnterAnimLayout;
        this.a = liveEnterFrameUserinfo;
        this.b = layoutParams;
        this.c = liveEnterFrameUserinfo2;
    }

    public void run() {
        if (this.a != null) {
            this.d.m.setVisibility(0);
            LayoutParams layoutParams = (LayoutParams) this.d.m.getLayoutParams();
            layoutParams.gravity = 3;
            layoutParams.width = this.a.width;
            layoutParams.height = this.a.height;
            layoutParams.leftMargin = (int) (this.a.left * ((float) this.b.width));
            layoutParams.topMargin = (int) ((this.a.top * ((float) this.b.height)) + ((float) ((WindowUtils.getScreenExactHeight() - this.b.height) / 2)));
            this.d.m.setLayoutParams(layoutParams);
            this.d.m.setAlpha(((float) this.a.op) / 100.0f);
            long j = (long) ((this.c.index - this.a.index) * 40);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.d.m, View.ALPHA, new float[]{((float) this.a.op) / 100.0f, ((float) this.c.op) / 100.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.d.m, View.X, new float[]{(float) ((int) (this.a.left * ((float) this.b.width))), (float) ((int) (this.c.left * ((float) this.b.width)))});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.d.m, View.Y, new float[]{(float) ((int) ((this.a.top * ((float) this.b.height)) + ((float) ((WindowUtils.getScreenExactHeight() - this.b.height) / 2)))), (float) ((int) ((this.c.top * ((float) this.b.height)) + ((float) ((WindowUtils.getScreenExactHeight() - this.b.height) / 2))))});
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            animatorSet.setDuration(j);
            animatorSet.start();
        }
    }
}
