package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.pay.bean.OrderBean;
import cn.v6.sixrooms.pay.engine.MakeOrderEngine$CoopRechargeCallBack;
import cn.v6.sixrooms.utils.HandleErrorUtils;

final class ai implements MakeOrderEngine$CoopRechargeCallBack {
    final /* synthetic */ CoopPayAcitvity a;

    ai(CoopPayAcitvity coopPayAcitvity) {
        this.a = coopPayAcitvity;
    }

    public final void handleResult(String str, OrderBean orderBean) {
        if ("001".equals(str)) {
            this.a.n = orderBean.getOrderid();
            SixRoomsUtils.notifyCoopRecharge(orderBean.getOrderid(), orderBean.getPrice());
        }
    }

    public final void error(int i) {
        HandleErrorUtils.showErrorToast(i);
    }

    public final void handleError(String str, String str2) {
        HandleErrorUtils.handleErrorResult(str, str2, this.a);
    }
}
