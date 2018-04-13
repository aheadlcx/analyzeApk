package qsbk.app.fragments;

import android.view.ViewTreeObserver.OnPreDrawListener;

class jn implements OnPreDrawListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jn(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public boolean onPreDraw() {
        this.a.mViewPager.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.mViewPager.setOnPageChangeListener(new jo(this));
        if (this.a.g != this.a.f && this.a.g >= 0) {
            this.a.mViewPager.setCurrentItem(this.a.g);
            this.a.r.setSelectedTab(this.a.g);
            this.a.g = -1;
        }
        return true;
    }
}
