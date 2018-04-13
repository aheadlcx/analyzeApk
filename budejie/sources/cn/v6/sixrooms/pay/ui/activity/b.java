package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine.CallBack;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.LogUtils;

final class b implements CallBack {
    final /* synthetic */ AlipayActivity a;

    b(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void handleResult(OrderStatusBean orderStatusBean) {
        String flag = orderStatusBean.getFlag();
        if ("1".equals(flag)) {
            LogUtils.i("AlipayActivity", "获取到充值结果: 成功");
            this.a.o = false;
            this.a.l.dismiss();
            AlipayActivity.n(this.a);
            V6Coop.getInstance().rechargeResult(Integer.parseInt(this.a.j.getMoney()), this.a.q, "0");
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
