package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.PreferenceUtils;

class fc implements OnClickListener {
    final /* synthetic */ LiveTabsFragment a;

    fc(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onClick(View view) {
        this.a.m.setVisibility(8);
        PreferenceUtils.instance().putBoolean("live_declaration", false);
    }
}
