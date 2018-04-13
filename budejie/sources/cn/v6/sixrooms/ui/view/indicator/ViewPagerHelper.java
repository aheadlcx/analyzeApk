package cn.v6.sixrooms.ui.view.indicator;

import android.support.v4.view.ViewPager;
import cn.v6.sixrooms.widgets.phone.indicator.MagicIndicator;

public class ViewPagerHelper {
    public static void bind(MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new b(magicIndicator));
    }
}
