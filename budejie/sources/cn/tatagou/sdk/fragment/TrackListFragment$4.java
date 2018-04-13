package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.view.pullview.PullToRefreshLayout;
import cn.tatagou.sdk.view.pullview.c;

class TrackListFragment$4 extends c {
    final /* synthetic */ TrackListFragment a;

    TrackListFragment$4(TrackListFragment trackListFragment) {
        this.a = trackListFragment;
    }

    public void onRefreshNet(PullToRefreshLayout pullToRefreshLayout) {
        super.onRefreshNet(pullToRefreshLayout);
        TrackListFragment.e(this.a);
    }
}
