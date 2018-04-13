package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.Item;

class FootprintFragment$4 extends a<CommListPojo<Item>> {
    final /* synthetic */ FootprintFragment a;

    FootprintFragment$4(FootprintFragment footprintFragment) {
        this.a = footprintFragment;
    }

    public void onApiDataResult(CommListPojo<Item> commListPojo, int i) {
        super.onApiDataResult(commListPojo, i);
        if (this.a.isAdded()) {
            Log.d(FootprintFragment.a(), "onApiDataResult: getMyPathApiCallback");
            this.a.hideLoading();
            if (commListPojo != null) {
                FootprintFragment.a(this.a, commListPojo.getData(), commListPojo.getMessage(), commListPojo.getCode());
                return;
            }
            FootprintFragment footprintFragment = this.a;
            boolean z = (FootprintFragment.a(this.a) == null || FootprintFragment.a(this.a).getCount() == 0) ? false : true;
            FootprintFragment.a(this.a, footprintFragment.onDataError(i, z), true);
        }
    }
}
