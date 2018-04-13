package qsbk.app.adapter;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ac implements AnimationListener {
    final /* synthetic */ ArticleAdapter a;

    ac(ArticleAdapter articleAdapter) {
        this.a = articleAdapter;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a.b != null) {
            this.a.b.onChangeDate();
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
