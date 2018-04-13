package cn.v6.sixrooms.hall;

import android.widget.PopupWindow.OnDismissListener;
import cn.v6.sixrooms.R;

final class ad implements OnDismissListener {
    final /* synthetic */ HostsNearbyFragment a;

    ad(HostsNearbyFragment hostsNearbyFragment) {
        this.a = hostsNearbyFragment;
    }

    public final void onDismiss() {
        this.a.h.setImageResource(R.drawable.phone_location_pull_down);
    }
}
