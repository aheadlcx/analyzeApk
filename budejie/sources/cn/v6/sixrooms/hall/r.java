package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;

final class r implements OnClickListener {
    final /* synthetic */ HostsFragment a;

    r(HostsFragment hostsFragment) {
        this.a = hostsFragment;
    }

    public final void onClick(View view) {
        HostsFragment.a(this.a, true);
    }
}
