package cn.tatagou.sdk.fragment;

import android.graphics.Color;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.util.c;

class MineFragment$1 extends c {
    final /* synthetic */ MineFragment a;

    MineFragment$1(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public void setTbLogin(int i) {
        super.setTbLogin(i);
        if (!this.a.isAdded()) {
            return;
        }
        if (i == 1) {
            MineFragment.a(this.a).setText(this.a.getString(R.string.taobao_login));
            MineFragment.b(this.a).setText(this.a.getString(R.string.taobao_cancel_login));
            MineFragment.b(this.a).setTextColor(Color.parseColor("#8B8989"));
            MineFragment.c(this.a);
            return;
        }
        MineFragment.a(this.a).setText(this.a.getString(R.string.taobao_logout));
        MineFragment.b(this.a).setTextColor(TtgConfig.getInstance().getThemeColor());
        MineFragment.b(this.a).setText(this.a.getString(R.string.taobao_go_login));
    }
}
