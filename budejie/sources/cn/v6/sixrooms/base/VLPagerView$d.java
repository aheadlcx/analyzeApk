package cn.v6.sixrooms.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class VLPagerView$d extends PagerAdapter {
    private VLPagerView$VLPagerDelegate a;

    VLPagerView$d(VLPagerView$VLPagerDelegate vLPagerView$VLPagerDelegate) {
        this.a = vLPagerView$VLPagerDelegate;
    }

    public final int getCount() {
        return this.a.onGetPageCount();
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        View onCreatePage = this.a.onCreatePage(viewGroup, i);
        onCreatePage.setLayoutParams(SixRoomsUtils.paramsGroup(-1, -1));
        viewGroup.addView(onCreatePage);
        return onCreatePage;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        this.a.onDestroyPage(viewGroup, i, view);
        viewGroup.removeView(view);
    }
}
