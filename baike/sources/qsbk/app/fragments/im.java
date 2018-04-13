package qsbk.app.fragments;

import android.view.ViewTreeObserver.OnPreDrawListener;

class im implements OnPreDrawListener {
    final /* synthetic */ QiushiListFragment a;

    im(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public boolean onPreDraw() {
        this.a.x.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.x.fullScroll(66);
        this.a.x.postDelayed(new in(this), 200);
        return true;
    }
}
