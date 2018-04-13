package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Item;

class TrackListFragment$3 extends a<CommPojo<Item>> {
    final /* synthetic */ TrackListFragment a;

    TrackListFragment$3(TrackListFragment trackListFragment) {
        this.a = trackListFragment;
    }

    public void onApiDataResult(CommPojo<Item> commPojo, int i) {
        boolean z = false;
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded()) {
            Log.d(TrackListFragment.a(), "onApiDataResult: rcmmParamApiCallback");
            this.a.hideLoading();
            TrackListFragment.b(this.a).refreshFinish(0);
            if (commPojo != null) {
                TrackListFragment.c(this.a).setVisibility(8);
                TrackListFragment.a(this.a, commPojo);
                return;
            }
            TrackListFragment trackListFragment = this.a;
            if (TrackListFragment.d(this.a) != null && TrackListFragment.d(this.a).getCount() > 0) {
                z = true;
            }
            trackListFragment.onDataError(i, z);
        }
    }
}
