package cn.v6.sixrooms.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.v6.sixrooms.ui.fragment.BaseFragment;

public class VLPagerView extends RelativeLayout {
    public static final int MINE_REWARD = 16;
    VLPagerView$VLPageScrolled a;
    private VLPagerView$VLScrollableViewPager b;
    private VLPagerView$VLPageChangeListener c;

    public VLPagerView(Context context) {
        this(context, null);
    }

    public VLPagerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VLPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new VLPagerView$VLScrollableViewPager(context);
        this.b.setManualResId();
        addView(this.b, new LayoutParams(-1, -1));
        this.b.setOnPageChangeListener(new i(this));
    }

    public int getPagesCount() {
        PagerAdapter adapter = this.b.getAdapter();
        if (adapter == null) {
            return 0;
        }
        return adapter.getCount();
    }

    public ViewPager getViewPager() {
        return this.b;
    }

    public int getPagePosition() {
        return this.b.getCurrentItem();
    }

    public void gotoPage(int i, boolean z) {
        this.b.setCurrentItem(i, z);
    }

    public void setCurrentItem(int i) {
        this.b.setCurrentItem(i);
    }

    public int getCurrentItem() {
        return this.b.getCurrentItem();
    }

    public void setOnPageScrolled(VLPagerView$VLPageScrolled vLPagerView$VLPageScrolled) {
        this.a = vLPagerView$VLPageScrolled;
    }

    public void setPageChangeListener(VLPagerView$VLPageChangeListener vLPagerView$VLPageChangeListener) {
        this.c = vLPagerView$VLPageChangeListener;
    }

    public void setPages(View[] viewArr) {
        this.b.setAdapter(new VLPagerView$c(viewArr));
    }

    public void notifyDataSetChanged() {
        if (this.b.getAdapter() != null) {
            this.b.getAdapter().notifyDataSetChanged();
        }
    }

    public void setPages(VLPagerView$VLPagerDelegate vLPagerView$VLPagerDelegate) {
        this.b.setAdapter(new VLPagerView$d(vLPagerView$VLPagerDelegate));
    }

    public void setFragmentPages(FragmentManager fragmentManager, BaseFragment[] baseFragmentArr) {
        this.b.setAdapter(new VLPagerView$a(fragmentManager, baseFragmentArr));
        this.b.getAdapter().notifyDataSetChanged();
    }

    public void setFragmentPages(FragmentManager fragmentManager, VLPagerView$VLPagerDelegate vLPagerView$VLPagerDelegate) {
        this.b.setAdapter(new VLPagerView$b(fragmentManager, vLPagerView$VLPagerDelegate));
    }

    public void setScrollable(boolean z) {
        this.b.setScrollable(z);
    }

    public void setType(int i) {
        this.b.setType(i);
    }

    public boolean getScrollable() {
        return this.b.getScrollable();
    }

    public void setOffscreenPageLimit(int i) {
        this.b.setOffscreenPageLimit(i);
    }
}
