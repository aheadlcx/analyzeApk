package cn.v6.sixrooms.ui.phone;

import android.text.TextUtils;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.GlobleValue;

final class an implements Runnable {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    an(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void run() {
        if (GlobleValue.getUserBean() == null || TextUtils.isEmpty(GlobleValue.getUserBean().getId())) {
            this.a.b.handleErrorResult(CommonStrs.FLAG_TYPE_NEED_LOGIN, "", this.a.b);
            return;
        }
        this.a.d = GlobleValue.getUserBean().getId();
        this.a.c.sendEmptyMessage(0);
    }
}
