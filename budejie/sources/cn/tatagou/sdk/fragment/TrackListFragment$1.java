package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.view.a;

class TrackListFragment$1 extends a {
    final /* synthetic */ TrackListFragment a;

    TrackListFragment$1(TrackListFragment trackListFragment) {
        this.a = trackListFragment;
    }

    public void onStopScroll(boolean z, int i, int i2) {
        super.onStopScroll(z, i, i2);
        if (i == i2) {
            TrackListFragment.a(this.a);
        }
    }
}
