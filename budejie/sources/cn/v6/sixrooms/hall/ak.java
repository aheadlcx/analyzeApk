package cn.v6.sixrooms.hall;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.v6.sixrooms.room.statistic.StatiscProxy;

final class ak implements OnPageChangeListener {
    final /* synthetic */ HotFragment a;

    ak(HotFragment hotFragment) {
        this.a = hotFragment;
    }

    public final void onPageScrolled(int i, float f, int i2) {
    }

    public final void onPageSelected(int i) {
        StatiscProxy.homeClickStatistic(i);
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
