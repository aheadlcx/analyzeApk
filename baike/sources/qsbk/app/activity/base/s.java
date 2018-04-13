package qsbk.app.activity.base;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import qsbk.app.fragments.QiushiListFragment;
import qsbk.app.utils.OnScrollDirectionListener;

class s extends OnScrollDirectionListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ QiushiListFragment c;
    final /* synthetic */ BaseArticleListViewFragment d;
    private final Animation g = a(0.0f, 1.0f);
    private final Animation h = a(1.0f, 0.0f);
    private final Runnable i = new t(this);
    private final Runnable j = new u(this);

    s(BaseArticleListViewFragment baseArticleListViewFragment, View view, View view2, QiushiListFragment qiushiListFragment) {
        this.d = baseArticleListViewFragment;
        this.a = view;
        this.b = view2;
        this.c = qiushiListFragment;
    }

    private Animation a(float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(200);
        return alphaAnimation;
    }

    public void onScrollUp() {
        this.d.W = 2;
        if (this.b.getVisibility() != 8) {
            this.b.startAnimation(this.h);
            this.b.setVisibility(8);
        }
        if (this.a.getVisibility() != 8) {
            BaseArticleListViewFragment.a.removeCallbacks(this.i);
            BaseArticleListViewFragment.a.removeCallbacks(this.j);
            BaseArticleListViewFragment.a.post(this.j);
        }
    }

    public void onScrollDown() {
        this.d.W = 1;
        if (this.b.getVisibility() != 0 && this.c.canShowActivity()) {
            this.b.startAnimation(this.g);
            this.b.setVisibility(0);
        }
        if (this.a.getVisibility() != 0 && this.c.canShowQiushiNotificationView()) {
            BaseArticleListViewFragment.a.removeCallbacks(this.i);
            BaseArticleListViewFragment.a.removeCallbacks(this.j);
            BaseArticleListViewFragment.a.post(this.i);
        }
    }
}
