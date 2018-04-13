package qsbk.app.activity.pay;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastUtil;

class ax implements HttpCallBack {
    final /* synthetic */ a a;
    final /* synthetic */ LaiseePaymentActivity b;

    ax(LaiseePaymentActivity laiseePaymentActivity, a aVar) {
        this.b = laiseePaymentActivity;
        this.a = aVar;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            JSONObject optJSONObject = jSONObject.optJSONObject("laisee");
            this.b.t.id = optJSONObject.optString("id");
            this.b.t.secret = optJSONObject.optString("secret");
            this.a.onLaiseeIdGet();
            return;
        }
        onFailure(null, jSONObject.optString("err_msg"));
    }

    public void onFailure(String str, String str2) {
        ToastUtil.Short(str2);
        this.b.k();
        this.b.finish();
    }
}
