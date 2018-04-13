package qsbk.app.core.ui.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.core.R;

public abstract class BaseGridPagerFragment extends BaseFragment implements OnClickListener {
    protected ImageView ivBg;
    protected LinearLayout ll_tab_bar;
    protected LinearLayout[] mLlTabs;
    protected BaseFragment[] mTabFragments;
    protected TextView[] mTvTabs;
    protected ViewPager mViewPager;

    public static abstract class BaseTabClickFragment extends BaseFragment {
        public abstract void onTabClick();
    }

    private class a extends FragmentStatePagerAdapter implements OnPageChangeListener {
        final /* synthetic */ BaseGridPagerFragment a;

        public a(BaseGridPagerFragment baseGridPagerFragment, FragmentManager fragmentManager) {
            this.a = baseGridPagerFragment;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (i < 0 || i >= this.a.mTabFragments.length) ? null : this.a.mTabFragments[i];
        }

        public int getCount() {
            return this.a.mTabFragments.length;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            this.a.setPageSelectItem(i);
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    @NonNull
    protected abstract BaseFragment[] getTabFragments();

    @NonNull
    protected abstract String[] getTabStrings();

    protected int getLayoutId() {
        return R.layout.fragment_grid_pager;
    }

    protected void initView() {
        this.mViewPager = (ViewPager) $(R.id.pager);
        this.ll_tab_bar = (LinearLayout) $(R.id.ll_tab_bar);
        this.ivBg = (ImageView) $(R.id.iv_bg);
        this.mTabFragments = getTabFragments();
        this.mLlTabs = new LinearLayout[this.mTabFragments.length];
        this.mTvTabs = new TextView[this.mTabFragments.length];
        String[] tabStrings = getTabStrings();
        for (int i = 0; i < this.mLlTabs.length; i++) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_pager_tab, null);
            this.ll_tab_bar.addView(inflate);
            LinearLayout linearLayout = (LinearLayout) inflate;
            TextView textView = (TextView) linearLayout.getChildAt(0);
            this.mLlTabs[i] = linearLayout;
            this.mTvTabs[i] = textView;
            this.mLlTabs[i].setId(i + 1);
            this.mLlTabs[i].setOnClickListener(this);
            if (i < tabStrings.length) {
                this.mTvTabs[i].setText(tabStrings[i]);
            }
        }
    }

    protected void initData() {
        Object aVar = new a(this, getChildFragmentManager());
        this.mViewPager.setOffscreenPageLimit(2);
        this.mViewPager.setAdapter(aVar);
        this.mViewPager.addOnPageChangeListener(aVar);
        this.mViewPager.setCurrentItem(defaultShowPage());
        this.mLlTabs[defaultShowPage()].setSelected(true);
    }

    protected int defaultShowPage() {
        return 0;
    }

    public void onClick(View view) {
        if (this.mViewPager != null) {
            int currentItem = this.mViewPager.getCurrentItem();
            Fragment fragmentFromViewPagerAdapter = getFragmentFromViewPagerAdapter(currentItem);
            if (fragmentFromViewPagerAdapter != null) {
                int i = 0;
                while (i < this.mLlTabs.length) {
                    if (view.getId() != this.mLlTabs[i].getId()) {
                        i++;
                    } else if (currentItem != i) {
                        this.mViewPager.setCurrentItem(i);
                        return;
                    } else if (fragmentFromViewPagerAdapter instanceof BaseTabClickFragment) {
                        ((BaseTabClickFragment) fragmentFromViewPagerAdapter).onTabClick();
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void setPageSelectItem(int i) {
        for (int i2 = 0; i2 < this.mLlTabs.length; i2++) {
            if (i2 == i) {
                this.mLlTabs[i2].setSelected(true);
            } else {
                this.mLlTabs[i2].setSelected(false);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (int i3 = 0; i3 < this.mViewPager.getAdapter().getCount(); i3++) {
            Fragment fragmentFromViewPagerAdapter = getFragmentFromViewPagerAdapter(i3);
            if (fragmentFromViewPagerAdapter != null) {
                fragmentFromViewPagerAdapter.onActivityResult(i, i2, intent);
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        for (int i = 0; i < this.mViewPager.getAdapter().getCount(); i++) {
            Fragment fragmentFromViewPagerAdapter = getFragmentFromViewPagerAdapter(i);
            if (fragmentFromViewPagerAdapter != null) {
                fragmentFromViewPagerAdapter.onHiddenChanged(z);
            }
        }
    }

    protected Fragment getFragmentFromViewPagerAdapter(int i) {
        return (Fragment) this.mViewPager.getAdapter().instantiateItem(this.mViewPager, i);
    }
}
