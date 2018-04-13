package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.pay.engine.YeepayCardStatusEngine.CallBack;
import cn.v6.sixrooms.room.RoomActivity;

final class ar implements CallBack {
    final /* synthetic */ MobilePayActivity a;

    ar(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void handleResult(OrderStatusBean orderStatusBean) {
        String flag = orderStatusBean.getFlag();
        String content = orderStatusBean.getContent();
        if ("1".equals(flag)) {
            this.a.k = false;
            this.a.dissSubmitDialog();
            this.a.showSucessDialog();
            V6Coop.getInstance().rechargeResult(Integer.parseInt(this.a.b.getMoney()), this.a.n.getOrderid(), "2");
        } else if (!RoomActivity.VIDEOTYPE_UNKNOWN.equals(flag)) {
            if ("-7".equals(flag)) {
                this.a.k = false;
                this.a.dissSubmitDialog();
                this.a.showErrorDialog();
                return;
            }
            this.a.k = false;
            this.a.dissSubmitDialog();
            this.a.showOtherErrorDialog(content);
        }
    }

    public final void error(int i) {
        this.a.dissSubmitDialog();
        this.a.k = false;
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }
}
