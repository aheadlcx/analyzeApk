package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.HotSearch;

class TtgSearchGoodsFragment$11 extends a<CommListPojo<HotSearch>> {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$11(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void onApiDataResult(CommListPojo<HotSearch> commListPojo, int i) {
        super.onApiDataResult(commListPojo, i);
        if (this.a.isAdded()) {
            Log.d(TtgSearchGoodsFragment.b(), "onApiDataResult: hotSearchApiCallback");
            TtgSearchGoodsFragment.i(this.a).setAdapter(TtgSearchGoodsFragment.h(this.a));
            if (commListPojo != null) {
                TtgSearchGoodsFragment.a(this.a, commListPojo);
            } else {
                TtgSearchGoodsFragment.b(this.a, false);
                TtgSearchGoodsFragment.j(this.a).setVisibility(8);
            }
            this.a.a();
        }
    }
}
