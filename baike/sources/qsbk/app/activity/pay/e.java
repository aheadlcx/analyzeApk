package qsbk.app.activity.pay;

import android.support.v4.app.NotificationCompat;
import com.alipay.sdk.app.statistic.c;
import com.facebook.common.util.UriUtil;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastUtil;

class e implements HttpCallBack {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    e(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                this.a.t = jSONObject.optString("charge_id");
                String optString = jSONObject.optString(UriUtil.LOCAL_RESOURCE_SCHEME);
                this.a.u = this.a.a(optString, c.G);
                new Thread(new f(this, optString)).start();
                return;
            }
            this.a.mHandler.post(new h(this, String.format("数据错误，错误码%s", new Object[]{Integer.valueOf(jSONObject.optInt(NotificationCompat.CATEGORY_ERROR))})));
        }
    }

    public void onFailure(String str, String str2) {
        this.a.e();
        ToastUtil.Short(str2);
    }
}
