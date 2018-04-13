package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine.CallBack;

final class aq implements CallBack {
    final /* synthetic */ MobilePayActivity a;

    aq(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void handleResult(String str, OrderBean orderBean) {
        if ("1".equals(str)) {
            this.a.n = orderBean;
            MobilePayActivity.a(this.a, orderBean.getOrderid());
        } else if ("-4".equals(str)) {
            this.a.dissSubmitDialog();
            this.a.showErrorDialog();
        } else if ("-7".equals(str)) {
            this.a.dissSubmitDialog();
            this.a.showErrorDialog();
        }
    }

    public final void error(int i) {
        this.a.dissSubmitDialog();
        this.a.k = false;
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }
}
