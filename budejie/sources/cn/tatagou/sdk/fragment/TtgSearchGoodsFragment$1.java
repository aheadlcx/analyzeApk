package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.Item;

class TtgSearchGoodsFragment$1 extends a<CommListPojo<Item>> {
    final /* synthetic */ TtgSearchGoodsFragment a;

    TtgSearchGoodsFragment$1(TtgSearchGoodsFragment ttgSearchGoodsFragment) {
        this.a = ttgSearchGoodsFragment;
    }

    public void onApiDataResult(CommListPojo<Item> commListPojo, int i) {
        boolean z = false;
        super.onApiDataResult(commListPojo, i);
        if (this.a.isAdded()) {
            Log.d(TtgSearchGoodsFragment.b(), "onApiDataResult: searchApiCallback");
            this.a.hideLoading();
            TtgSearchGoodsFragment.a(this.a, true);
            if (commListPojo != null) {
                TtgSearchGoodsFragment.a(this.a, commListPojo.getData(), commListPojo.getCode());
                return;
            }
            if (TtgSearchGoodsFragment.a(this.a).getVisibility() == 8) {
                TtgSearchGoodsFragment.a(this.a).setVisibility(0);
                if (TtgSearchGoodsFragment.b(this.a).getVisibility() == 0) {
                    TtgSearchGoodsFragment.b(this.a).setVisibility(8);
                }
            }
            if (TtgSearchGoodsFragment.c(this.a) == 1) {
                this.a.b.clear();
                TtgSearchGoodsFragment.d(this.a).notifyDataSetChanged();
            }
            this.a.mTtgTvTryAgain.setVisibility(0);
            TtgSearchGoodsFragment ttgSearchGoodsFragment = this.a;
            if (!(TtgSearchGoodsFragment.d(this.a) == null || TtgSearchGoodsFragment.d(this.a).getCount() == 0)) {
                z = true;
            }
            TtgSearchGoodsFragment.a(this.a, ttgSearchGoodsFragment.onDataError(i, z), true);
        }
    }
}
