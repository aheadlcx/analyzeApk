package cn.v6.sixrooms.hall;

import com.handmark.pulltorefresh.library.view.SixRoomPullToRefreshRecyclerView.OnFooterFuncListener;

final class x implements OnFooterFuncListener {
    final /* synthetic */ HostsNearbyFragment a;

    x(HostsNearbyFragment hostsNearbyFragment) {
        this.a = hostsNearbyFragment;
    }

    public final void onAutoLoadMore() {
        this.a.a(false, false);
    }
}
