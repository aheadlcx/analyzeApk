package qsbk.app.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.ArrayList;

class ci implements OnPageChangeListener {
    final /* synthetic */ LiveBannerCell a;

    ci(LiveBannerCell liveBannerCell) {
        this.a = liveBannerCell;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.a.getItem().pos = i % this.a.getItem().banners.size();
        LiveBannerCell.a(this.a).setSelectedDot(i % this.a.getItem().banners.size());
        LiveBannerCell.b(this.a);
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            ArrayList arrayList = this.a.getItem().banners;
            int currentItem = LiveBannerCell.c(this.a).getCurrentItem();
            if (currentItem < arrayList.size() * 5000 || currentItem > arrayList.size() * 15000) {
                LiveBannerCell.c(this.a).setCurrentItem((currentItem % arrayList.size()) + (arrayList.size() * 10000), false);
            }
        }
    }
}
