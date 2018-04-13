package cn.v6.sixrooms.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class VLPagerView$c extends PagerAdapter {
    private View[] a;

    VLPagerView$c(View[] viewArr) {
        this.a = viewArr;
    }

    public final int getCount() {
        return this.a == null ? 0 : this.a.length;
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.a[i];
        viewGroup.addView(view, SixRoomsUtils.paramsGroup(-1, -1));
        return view;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.a[i]);
    }
}
