package qsbk.app.fragments;

import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.UIHelper;

class eq implements OnPageChangeListener {
    final /* synthetic */ LiveTabsFragment a;

    eq(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.a.i != i) {
            this.a.a(this.a.i, false);
            this.a.i = i;
            this.a.a(i, true);
        }
        if (this.a.j != null) {
            this.a.j.setSelectedTab(i);
        }
        ActivityCompat.invalidateOptionsMenu(this.a.getActivity());
        this.a.a();
        if (this.a.i >= 2 || QsbkApp.currentUser == null) {
            this.a.o.setVisibility(8);
            this.a.B.setVisibility(8);
        } else {
            this.a.o.setVisibility(0);
            this.a.B.setVisibility(0);
        }
        this.a.A.setImageResource(i == 2 ? UIHelper.getUserRemind() : UIHelper.getLiveRankingMenuIcon());
    }

    public void onPageScrollStateChanged(int i) {
    }
}
