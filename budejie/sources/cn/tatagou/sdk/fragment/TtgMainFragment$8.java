package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.ResultPojo;

class TtgMainFragment$8 extends a<ResultPojo> {
    final /* synthetic */ TtgMainFragment a;

    TtgMainFragment$8(TtgMainFragment ttgMainFragment) {
        this.a = ttgMainFragment;
    }

    public void onApiDataResult(ResultPojo resultPojo, int i) {
        super.onApiDataResult(resultPojo, i);
        Log.d(TtgMainFragment.access$400(), "onApiDataResult: saveUserInfoCallback ：" + i);
        if (200 == i && this.a.isAdded()) {
            cn.tatagou.sdk.b.a.saveStr("ttgSex", Config.getInstance().getSex());
        }
    }
}
