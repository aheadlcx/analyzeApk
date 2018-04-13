package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.RcmmParam;

class TtgMainFragment$6 extends a<CommPojo<RcmmParam>> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$6(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void onApiDataResult(CommPojo<RcmmParam> commPojo, int i) {
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded()) {
            Log.d(TtgMainFragment.access$400(), "onApiDataResult: rcmmParamApiCallback");
            TtgMainFragment.access$500(this.a);
            String str = null;
            if (!(commPojo == null || commPojo.getData() == null)) {
                AppHomeData.getInstance().setRcmmParam((RcmmParam) commPojo.getData());
                str = commPojo.getTimestamp();
            }
            TtgMainFragment.access$600(this.a, str);
        }
    }
}
