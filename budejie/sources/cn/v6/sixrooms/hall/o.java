package cn.v6.sixrooms.hall;

import com.handmark.pulltorefresh.library.view.SixRoomPullToRefreshRecyclerView.OnFooterFuncListener;

final class o implements OnFooterFuncListener {
    final /* synthetic */ HostsFragment a;

    o(HostsFragment hostsFragment) {
        this.a = hostsFragment;
    }

    public final void onAutoLoadMore() {
        HostsFragment.a(this.a, false);
    }
}
