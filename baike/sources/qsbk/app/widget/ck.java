package qsbk.app.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.ArrayList;

class ck implements OnPageChangeListener {
    final /* synthetic */ LiveRecommendCell a;

    ck(LiveRecommendCell liveRecommendCell) {
        this.a = liveRecommendCell;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.a.getItem().pos = i % this.a.getItem().lives.size();
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            ArrayList arrayList = this.a.getItem().lives;
            int currentItem = LiveRecommendCell.a(this.a).getCurrentItem();
            if (currentItem < arrayList.size() * 5000 || currentItem > arrayList.size() * 15000) {
                LiveRecommendCell.a(this.a).setCurrentItem((currentItem % arrayList.size()) + (arrayList.size() * 10000), false);
            }
        }
    }
}
