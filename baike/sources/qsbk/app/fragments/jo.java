package qsbk.app.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.QiuyouCircleFragment.IPageFocus;

class jo implements OnPageChangeListener {
    final /* synthetic */ jn a;

    jo(jn jnVar) {
        this.a = jnVar;
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.a.a.r != null) {
            this.a.a.r.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.a.a.f != i) {
            Fragment fragment = (Fragment) this.a.a.l.get(this.a.a.f);
            if (fragment instanceof IPageFocus) {
                ((IPageFocus) fragment).setSelected(false);
            }
            fragment = (Fragment) this.a.a.l.get(i);
            if (fragment instanceof IPageFocus) {
                ((IPageFocus) fragment).setSelected(true);
            }
            QsbkApp.isInVideoList = fragment instanceof CircleVideoFragment;
            this.a.a.setHeadVisible();
            this.a.a.f = i;
        }
        if (this.a.a.r != null) {
            this.a.a.r.setSelectedTab(i);
        }
        this.a.a.c();
    }

    public void onPageScrollStateChanged(int i) {
        if (this.a.a.r != null && i == 0) {
            this.a.a.r.onPageScrollStateChanged(this.a.a.mViewPager.getCurrentItem(), i);
        }
    }
}
