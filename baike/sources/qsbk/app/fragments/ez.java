package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.qiushibaike.statsdk.StatSDK;

class ez implements OnClickListener {
    final /* synthetic */ LiveTabsFragment a;

    ez(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onClick(View view) {
        if (this.a.C != null) {
            StatSDK.onEvent(view.getContext(), "live_splash_ad_click", String.valueOf(this.a.C.id));
            this.a.C.onActionClick(this.a.getActivity());
        }
    }
}
