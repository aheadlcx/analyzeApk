package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.pay.engine.PayInfoEngine.CallBack;

final class be implements CallBack {
    final /* synthetic */ RechargeActivity a;

    be(RechargeActivity rechargeActivity) {
        this.a = rechargeActivity;
    }

    public final void handleResult(WrapPaySelect wrapPaySelect, String str) {
        this.a.i.setText(str);
    }

    public final void error(int i) {
    }
}
