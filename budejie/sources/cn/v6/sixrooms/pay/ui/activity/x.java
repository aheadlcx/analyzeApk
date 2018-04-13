package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine.CallBack;

final class x implements CallBack {
    final /* synthetic */ BanklineActivity a;

    x(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void handleResult(String str, OrderBean orderBean) {
        if ("1".equals(str)) {
            this.a.q = orderBean.getOrderid();
            BanklineActivity.a(this.a, orderBean);
            V6Coop.getInstance().rechargeResult(Integer.parseInt(this.a.j.getMoney()), this.a.q, "1");
        } else if ("-4".equals(str)) {
            if (this.a.l != null && this.a.l.isShowing()) {
                this.a.l.dismiss();
            }
            BanklineActivity.k(this.a);
        } else if ("-7".equals(str)) {
            if (this.a.l != null && this.a.l.isShowing()) {
                this.a.l.dismiss();
            }
            BanklineActivity.k(this.a);
        }
    }

    public final void error(int i) {
        if (this.a.l != null && this.a.l.isShowing()) {
            this.a.l.dismiss();
        }
        this.a.o = false;
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }
}
