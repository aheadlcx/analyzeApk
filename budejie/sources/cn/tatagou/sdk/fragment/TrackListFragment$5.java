package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.util.e;

class TrackListFragment$5 extends e {
    final /* synthetic */ TrackListFragment a;

    TrackListFragment$5(TrackListFragment trackListFragment, long j, long j2) {
        this.a = trackListFragment;
        super(j, j2);
    }

    public void onTick(long j) {
        if (this.a.isAdded()) {
            TrackListFragment.a(this.a, j);
            return;
        }
        TrackListFragment.f(this.a).cancel();
        TrackListFragment.a(this.a, null);
    }

    public void onFinish() {
        if (TrackListFragment.c(this.a) != null) {
            TrackListFragment.c(this.a).setVisibility(8);
        }
    }
}
