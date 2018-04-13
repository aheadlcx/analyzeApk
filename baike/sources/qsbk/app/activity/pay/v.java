package qsbk.app.activity.pay;

import android.support.v7.app.AlertDialog.Builder;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Payment;

class v implements HttpCallBack {
    final /* synthetic */ Payment a;
    final /* synthetic */ ItemUnivasualBuyActivity b;

    v(ItemUnivasualBuyActivity itemUnivasualBuyActivity, Payment payment) {
        this.b = itemUnivasualBuyActivity;
        this.a = payment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        switch (this.a.mId) {
            case 1:
                try {
                    jSONObject.getInt("balance");
                    jSONObject.getString("charge_id");
                    this.b.a(-1, null);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                try {
                    this.b.b(jSONObject);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    onFailure(str, "数据解析失败");
                    return;
                }
            case 3:
                try {
                    this.b.a(jSONObject);
                    return;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    onFailure(str, "数据解析失败");
                    return;
                }
            default:
                return;
        }
    }

    public void onFailure(String str, String str2) {
        this.b.i();
        this.b.b.setVisibility(0);
        if (this.a == Payment.PAY_WALLET && "904".equals(str)) {
            Builder builder = new Builder(this.b, R.style.MyDialogStyleNormal);
            builder.setTitle(str2);
            builder.setPositiveButton("重试", new w(this));
            builder.setNegativeButton("找回支付密码", new x(this));
            builder.show();
            return;
        }
        this.b.a(1, str2);
    }
}
