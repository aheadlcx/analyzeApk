package cn.v6.sixrooms.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class VLPagerView$b extends FragmentPagerAdapter {
    private VLPagerView$VLPagerDelegate a;

    VLPagerView$b(FragmentManager fragmentManager, VLPagerView$VLPagerDelegate vLPagerView$VLPagerDelegate) {
        super(fragmentManager);
        this.a = vLPagerView$VLPagerDelegate;
    }

    public final Fragment getItem(int i) {
        return new VLPagerView$VLFragmentPage(i, this.a);
    }

    public final int getCount() {
        return this.a.onGetPageCount();
    }
}
