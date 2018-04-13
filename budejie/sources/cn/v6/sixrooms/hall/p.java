package cn.v6.sixrooms.hall;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;

final class p extends SpanSizeLookup {
    final /* synthetic */ HostsFragment a;

    p(HostsFragment hostsFragment) {
        this.a = hostsFragment;
    }

    public final int getSpanSize(int i) {
        return i == HostsFragment.a(this.a).getFooterPosition() ? 2 : 1;
    }
}
