package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine.CallBack;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LogUtils;

final class ak implements CallBack {
    final /* synthetic */ CoopPayAcitvity a;

    ak(CoopPayAcitvity coopPayAcitvity) {
        this.a = coopPayAcitvity;
    }

    public final void handleResult(OrderStatusBean orderStatusBean) {
        if ("1".equals(orderStatusBean.getFlag())) {
            LogUtils.i("CoopPayAcitvity", "获取到充值结果: 成功");
            this.a.l.dismiss();
            CoopPayAcitvity.j(this.a);
            V6Coop.getInstance().rechargeResult(Integer.parseInt(this.a.j.getMoney()), this.a.n, "0");
            return;
        }
        HandleErrorUtils.handleErrorResult(orderStatusBean.getFlag(), orderStatusBean.getContent(), this.a);
    }

    public final void error(int i) {
        HandleErrorUtils.showErrorToast(i);
    }
}
