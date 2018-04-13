package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine.CallBack;
import cn.v6.sixrooms.room.RoomActivity;

final class y implements CallBack {
    final /* synthetic */ BanklineActivity a;

    y(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void handleResult(OrderStatusBean orderStatusBean) {
        String flag = orderStatusBean.getFlag();
        if ("1".equals(flag)) {
            this.a.o = false;
            this.a.l.dismiss();
            BanklineActivity.m(this.a);
        } else if (!RoomActivity.VIDEOTYPE_UNKNOWN.equals(flag) && "-4".equals(flag)) {
            this.a.o = false;
            this.a.l.dismiss();
            this.a.showErrorDialog();
        }
    }

    public final void error(int i) {
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }
}
