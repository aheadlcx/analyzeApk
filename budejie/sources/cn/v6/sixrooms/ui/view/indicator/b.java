package cn.v6.sixrooms.ui.view.indicator;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.v6.sixrooms.widgets.phone.indicator.MagicIndicator;

final class b implements OnPageChangeListener {
    final /* synthetic */ MagicIndicator a;

    b(MagicIndicator magicIndicator) {
        this.a = magicIndicator;
    }

    public final void onPageScrolled(int i, float f, int i2) {
        this.a.onPageScrolled(i, f, i2);
    }

    public final void onPageSelected(int i) {
        this.a.onPageSelected(i);
    }

    public final void onPageScrollStateChanged(int i) {
        this.a.onPageScrollStateChanged(i);
    }
}
