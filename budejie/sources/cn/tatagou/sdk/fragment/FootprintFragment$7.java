package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.ResultPojo;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;

class FootprintFragment$7 extends a<ResultPojo> {
    final /* synthetic */ FootprintFragment a;

    FootprintFragment$7(FootprintFragment footprintFragment) {
        this.a = footprintFragment;
    }

    public void onApiDataResult(ResultPojo resultPojo, int i) {
        super.onApiDataResult(resultPojo, i);
        if (this.a.isAdded()) {
            Log.d(FootprintFragment.a(), "onApiDataResult: rcmmParamApiCallback");
            if (resultPojo != null && i == 200) {
                FootprintFragment.b(this.a, null);
                FootprintFragment.a(this.a, null);
                FootprintFragment.i(this.a);
            } else if (resultPojo == null || p.isEmpty(resultPojo.getMessage())) {
                l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.del_fail));
            } else {
                l.showToastCenter(this.a.getActivity(), resultPojo.getMessage());
            }
        }
    }
}
