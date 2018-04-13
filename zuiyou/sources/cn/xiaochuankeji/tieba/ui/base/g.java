package cn.xiaochuankeji.tieba.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class g extends FragmentPagerAdapter {
    private SparseArray<Fragment> a = new SparseArray(getCount());

    public g(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.a.put(i, fragment);
        return fragment;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
        this.a.remove(i);
    }

    public Fragment a(int i) {
        return (Fragment) this.a.get(i);
    }
}
