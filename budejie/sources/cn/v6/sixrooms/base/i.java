package cn.v6.sixrooms.base;

import android.support.v4.view.ViewPager.OnPageChangeListener;

final class i implements OnPageChangeListener {
    final /* synthetic */ VLPagerView a;

    i(VLPagerView vLPagerView) {
        this.a = vLPagerView;
    }

    public final void onPageSelected(int i) {
        if (VLPagerView.a(this.a) != null) {
            VLPagerView.a(this.a).onPageChanged(i);
        }
    }

    public final void onPageScrolled(int i, float f, int i2) {
        if (this.a.a != null) {
            this.a.a.onPageScrolled(i, f, i2);
        }
    }

    public final void onPageScrollStateChanged(int i) {
    }
}
