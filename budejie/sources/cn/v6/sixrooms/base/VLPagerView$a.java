package cn.v6.sixrooms.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cn.v6.sixrooms.ui.fragment.BaseFragment;

class VLPagerView$a extends FragmentPagerAdapter {
    private BaseFragment[] a;

    VLPagerView$a(FragmentManager fragmentManager, BaseFragment[] baseFragmentArr) {
        super(fragmentManager);
        this.a = baseFragmentArr;
    }

    public final Fragment getItem(int i) {
        return this.a[i];
    }

    public final int getCount() {
        return this.a == null ? 0 : this.a.length;
    }
}
