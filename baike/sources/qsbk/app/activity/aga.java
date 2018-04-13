package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class aga implements HttpCallBack {
    final /* synthetic */ WalletActivity a;

    aga(WalletActivity walletActivity) {
        this.a = walletActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a != null && !this.a.isFinishing() && jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
            this.a.a = jSONObject.optString(PayPWDUniversalActivity.MONEY);
            this.a.b.setText("￥" + this.a.a);
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(this.a, str2).show();
    }
}
