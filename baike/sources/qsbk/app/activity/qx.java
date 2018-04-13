package qsbk.app.activity;

import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import qsbk.app.R;

class qx implements OnTabSelectedListener {
    final /* synthetic */ LaiseeSendActivity a;

    qx(LaiseeSendActivity laiseeSendActivity) {
        this.a = laiseeSendActivity;
    }

    public void onTabSelected(Tab tab) {
        this.a.navigateToFragment(R.id.fragment_container, LaiseeSendActivity.f[tab.getPosition()]);
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }
}
