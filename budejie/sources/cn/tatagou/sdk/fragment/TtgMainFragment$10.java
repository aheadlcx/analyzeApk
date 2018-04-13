package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.HomeData;

class TtgMainFragment$10 extends a<CommPojo<HomeData>> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$10(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void onApiDataResult(CommPojo<HomeData> commPojo, int i) {
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded()) {
            Log.d(TtgMainFragment.access$400(), "onApiDataResult: homeApiCallback");
            if (commPojo == null || commPojo.getData() == null) {
                TtgMainFragment ttgMainFragment = this.a;
                if (commPojo != null) {
                    i = 20002;
                }
                boolean z = (TtgMainFragment.access$200(this.a) == null || TtgMainFragment.access$200(this.a).getCount() == 0) ? false : true;
                ttgMainFragment.onDataError(i, z);
                return;
            }
            ((HomeData) commPojo.getData()).setTimestamp(commPojo.getTimestamp());
            TtgMainFragment.access$700(this.a, (HomeData) commPojo.getData());
        }
    }
}
