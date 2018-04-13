package cn.v6.sixrooms.hall;

import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;

final class y extends SpanSizeLookup {
    final /* synthetic */ HostsNearbyFragment a;

    y(HostsNearbyFragment hostsNearbyFragment) {
        this.a = hostsNearbyFragment;
    }

    public final int getSpanSize(int i) {
        return i == this.a.b.getFooterPosition() ? 2 : 1;
    }
}
