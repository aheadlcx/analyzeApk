package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.pay.bean.WrapPaySelect.CommodityItem;
import cn.v6.sixrooms.pay.engine.PayInfoEngine.CallBack;

final class bq implements CallBack {
    final /* synthetic */ WeixinPayActivity a;

    bq(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void handleResult(WrapPaySelect wrapPaySelect, String str) {
        this.a.v = wrapPaySelect.getWxpayapp();
        this.a.i = (CommodityItem) this.a.v.getPayrate().get(0);
        this.a.h.setText(this.a.i.getContent());
    }

    public final void error(int i) {
        this.a.showToast(this.a.getString(R.string.tip_network_error_str));
    }
}
