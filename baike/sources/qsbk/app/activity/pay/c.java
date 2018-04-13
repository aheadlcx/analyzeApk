package qsbk.app.activity.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.core.utils.NetworkUtils;

class c extends BroadcastReceiver {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    c(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("errCode", -1);
        this.a.a(intent.getStringExtra("extData"), com.alipay.sdk.app.statistic.c.G);
        if (intExtra == 0) {
            this.a.a(0, "支付成功", this.a.u, "weixin", null);
        } else if (!NetworkUtils.getInstance().isNetworkAvailable()) {
            this.a.a(2, "网络连接失败,请检查相关设置", this.a.u, "weixin", null);
        } else if (intExtra == -1) {
            this.a.a(2, "支付失败", this.a.u, "weixin", null);
        } else if (intExtra == -2) {
            this.a.a(1, "支付取消", this.a.u, "weixin", null);
        }
    }
}
