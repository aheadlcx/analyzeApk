package qsbk.app.activity;

import qsbk.app.fragments.IArticleList;
import qsbk.app.widget.PagerSlidingTabStrip.ITabOnClickListener;

class dh implements ITabOnClickListener {
    final /* synthetic */ BaseTabActivity a;

    dh(BaseTabActivity baseTabActivity) {
        this.a = baseTabActivity;
    }

    public void onTabClickListener(int i) {
        if (this.a.b == i && (this.a.e.getItem(this.a.d.getCurrentItem()) instanceof IArticleList)) {
            ((IArticleList) this.a.e.getItem(this.a.d.getCurrentItem())).scrollToTop();
        }
    }
}
