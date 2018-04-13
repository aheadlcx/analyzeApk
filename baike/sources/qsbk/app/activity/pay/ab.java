package qsbk.app.activity.pay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alipay.sdk.app.statistic.c;
import qsbk.app.core.utils.NetworkUtils;

class ab extends BroadcastReceiver {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ab(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("errCode", -1);
        this.a.a(intent.getStringExtra("extData"), c.G);
        if (intExtra == 0) {
            this.a.a(-1, "支付成功");
        } else if (!NetworkUtils.getInstance().isNetworkAvailable()) {
            this.a.a(1, "网络连接失败,请检查相关设置");
        } else if (intExtra == -1) {
            this.a.a(1, "支付失败");
        } else if (intExtra == -2) {
            this.a.a(0, "支付取消");
        }
    }
}
