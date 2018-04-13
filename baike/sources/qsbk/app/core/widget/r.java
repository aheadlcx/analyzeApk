package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;

class r implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ PagerSlidingTabStrip b;

    r(PagerSlidingTabStrip pagerSlidingTabStrip, int i) {
        this.b = pagerSlidingTabStrip;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.g != null) {
            this.b.g.onTabClickListener(this.a);
        }
        this.b.f.setCurrentItem(this.a);
    }
}
