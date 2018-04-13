package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import qsbk.app.live.widget.BarrageLayout.BarrageItem;

class g extends AnimatorListenerAdapter {
    final /* synthetic */ ViewGroup a;
    final /* synthetic */ BarrageItem b;

    g(BarrageItem barrageItem, ViewGroup viewGroup) {
        this.b = barrageItem;
        this.a = viewGroup;
    }

    public void onAnimationStart(Animator animator) {
        this.b.setVisibility(0);
        this.b.startTime = System.currentTimeMillis();
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setVisibility(4);
        this.b.post(new h(this));
    }
}
