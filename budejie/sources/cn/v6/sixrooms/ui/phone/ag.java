package cn.v6.sixrooms.ui.phone;

import android.os.Handler;
import android.os.Message;

final class ag extends Handler {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    ag(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.initView();
                this.a.initData();
                this.a.initListener();
                return;
            default:
                return;
        }
    }
}
