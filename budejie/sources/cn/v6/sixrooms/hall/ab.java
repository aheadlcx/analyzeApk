package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;

final class ab implements OnClickListener {
    final /* synthetic */ HostsNearbyFragment a;

    ab(HostsNearbyFragment hostsNearbyFragment) {
        this.a = hostsNearbyFragment;
    }

    public final void onClick(View view) {
        this.a.a(true, false);
    }
}
