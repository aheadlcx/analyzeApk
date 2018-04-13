package qsbk.app.widget;

import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;

class co extends SimpleOnPageChangeListener {
    final /* synthetic */ LoopBannerCell a;

    co(LoopBannerCell loopBannerCell) {
        this.a = loopBannerCell;
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        this.a.d.setPos(this.a.c.getIndex(i));
    }
}
