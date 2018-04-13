package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine.CallBack;

final class l implements CallBack {
    final /* synthetic */ AlipayActivity a;

    l(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void handleResult(String str, OrderBean orderBean) {
        if ("1".equals(str)) {
            this.a.q = orderBean.getOrderid();
            AlipayActivity.a(this.a, orderBean);
        } else if ("-4".equals(str)) {
            if (this.a.l != null && this.a.l.isShowing()) {
                this.a.l.dismiss();
            }
            AlipayActivity.j(this.a);
        } else if ("-7".equals(str)) {
            if (this.a.l != null && this.a.l.isShowing()) {
                this.a.l.dismiss();
            }
            AlipayActivity.j(this.a);
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
