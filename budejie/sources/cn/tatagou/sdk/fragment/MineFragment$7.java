package cn.tatagou.sdk.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.ResultPojo;
import cn.tatagou.sdk.util.l;

class MineFragment$7 extends a<ResultPojo> {
    final /* synthetic */ MineFragment a;

    MineFragment$7(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public void onApiDataResult(ResultPojo resultPojo, int i) {
        super.onApiDataResult(resultPojo, i);
        if (this.a.isAdded()) {
            Log.d(MineFragment.a(), "onApiDataResult: saveUserInfoApiCallback");
            if (resultPojo == null || 200 != i) {
                l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.set_net_prompt));
                return;
            }
            MineFragment.a(this.a, true);
            Config.getInstance().setSex(MineFragment.f(this.a));
            cn.tatagou.sdk.b.a.saveStr("ttgSex", MineFragment.f(this.a));
            TextView g = MineFragment.g(this.a);
            CharSequence charSequence = TextUtils.isEmpty(Config.getInstance().getSex()) ? "" : "F".equals(Config.getInstance().getSex()) ? "美女" : "M".equals(Config.getInstance().getSex()) ? "帅哥" : "辣妈";
            g.setText(charSequence);
        }
    }
}
