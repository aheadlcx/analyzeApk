package qsbk.app.pay.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alipay.sdk.app.statistic.c;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;

class a extends BroadcastReceiver {
    final /* synthetic */ PayActivity a;

    a(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("errCode", -1);
        String stringExtra = intent.getStringExtra("extData");
        String a = this.a.b(stringExtra, c.G);
        if (intExtra == 0) {
            ToastUtil.Long(R.string.pay_success);
            this.a.d(a);
            this.a.setResult(-1);
            this.a.a(stringExtra);
        } else if (NetworkUtils.getInstance().isNetworkAvailable()) {
            if (intExtra == -1) {
                ToastUtil.Long(R.string.pay_fail);
            } else if (intExtra == -2) {
                ToastUtil.Long(R.string.pay_cancel);
            }
            this.a.e(a);
        } else {
            ToastUtil.Long(R.string.network_not_well);
        }
    }
}
