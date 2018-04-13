package qsbk.app.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.utils.UIHelper;

class ig implements OnPageChangeListener {
    final /* synthetic */ QiushiListFragment a;

    ig(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onPageSelected(int i) {
        this.a.d = i;
        if (this.a.x != null) {
            this.a.x.setSelectedTab(i);
        }
        this.a.e();
        if (this.a.y != null) {
            if (i == 1) {
                this.a.y.setImageResource(UIHelper.getNewSubmitMenuVideoIcon());
            } else {
                this.a.y.setImageResource(UIHelper.getNewSubmitMenuIcon());
            }
        }
        ((MainActivity) this.a.getActivity()).setSelected(i);
        if (this.a.o != i) {
            this.a.a(this.a.o, false);
            Fragment fragment = (Fragment) this.a.n.get(this.a.o);
            if (fragment instanceof IPageableFragment) {
                ((IPageableFragment) fragment).doPause();
                ((IPageableFragment) fragment).doStop();
            }
            this.a.o = i;
            fragment = (Fragment) this.a.n.get(i);
            if (fragment instanceof IPageableFragment) {
                ((IPageableFragment) fragment).doResume();
            }
            this.a.a(fragment);
            NewsFragment.canShowRefreshTips = fragment instanceof NewsFragment;
            QsbkApp.isInVideoList = fragment instanceof VideoFragment;
            this.a.a(i, true);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.a.x != null) {
            this.a.x.onPageScrolled(i, f, i2);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.a.x != null && i == 0) {
            this.a.x.onPageScrollStateChanged(this.a.l.getCurrentItem(), i);
        }
    }
}
