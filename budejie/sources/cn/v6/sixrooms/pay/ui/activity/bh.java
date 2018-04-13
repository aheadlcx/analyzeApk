package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.OrderStatusBean;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine.CallBack;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.LogUtils;

final class bh implements CallBack {
    final /* synthetic */ WeixinPayActivity a;

    bh(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void handleResult(OrderStatusBean orderStatusBean) {
        String flag = orderStatusBean.getFlag();
        LogUtils.d("WeixinPayActivity", "timer: " + this.a.t);
        LogUtils.d("WeixinPayActivity", "flag: " + flag);
        if ("1".equals(flag)) {
            LogUtils.i("WeixinPayActivity", "获取到充值结果: 成功");
            this.a.a("更新用户信息...");
            WeixinPayActivity.o(this.a);
            V6Coop.getInstance().rechargeResult(Integer.parseInt(this.a.i.getMoney()), this.a.k, "0");
        } else if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(flag)) {
            if (this.a.t < 3) {
                this.a.w.postDelayed(new bi(this), 5000);
                return;
            }
            this.a.t = 0;
            this.a.b();
            WeixinPayActivity.t(this.a);
        } else if ("-4".equals(flag)) {
            this.a.b();
            this.a.showErrorDialog();
        }
    }

    public final void error(int i) {
        this.a.showToast(this.a.getString(R.string.tip_network_error_str));
    }
}
