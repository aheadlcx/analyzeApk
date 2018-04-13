package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ct implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ PagerSlidingTabStrip b;

    ct(PagerSlidingTabStrip pagerSlidingTabStrip, int i) {
        this.b = pagerSlidingTabStrip;
        this.a = i;
    }

    public void onClick(View view) {
        PagerSlidingTabStrip.c(this.b).onTabClickListener(this.a);
        PagerSlidingTabStrip.a(this.b).setCurrentItem(this.a);
    }
}
