package qsbk.app.activity.pay;

import android.support.v7.app.AlertDialog.Builder;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;

class n implements HttpCallBack {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    n(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject.has("record_id")) {
            this.a.a(0, null, null, null, null);
        }
    }

    public void onFailure(String str, String str2) {
        this.a.e();
        this.a.b.setVisibility(0);
        if ("904".equals(str)) {
            Builder builder = new Builder(this.a, R.style.MyDialogStyleNormal);
            builder.setTitle(str2);
            builder.setPositiveButton("重试", new o(this));
            builder.setNegativeButton("找回支付密码", new p(this));
            builder.show();
            return;
        }
        this.a.a(2, str2, null, null, null);
    }
}
