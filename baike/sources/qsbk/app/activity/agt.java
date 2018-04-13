package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class agt implements HttpCallBack {
    final /* synthetic */ WithdrawActivity a;

    agt(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.dismissProgress();
        try {
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("withdraw");
                this.a.s = jSONObject2.optString("fee");
                this.a.p = jSONObject2.optString("apply_money");
                this.a.q = jSONObject2.optString("transfer_money");
                this.a.r = 0.0d;
                try {
                    this.a.r = Double.parseDouble(this.a.q);
                } catch (Exception e) {
                }
                if (this.a.r <= 0.0d || TextUtils.isEmpty(this.a.s) || TextUtils.isEmpty(this.a.p)) {
                    onFailure(i + "", "参数错误，请重试");
                    return;
                }
                PayPWDUniversalActivity.launch(this.a, 11, "输入支付密码", "到账金额:", String.format("提现金额：%s元\n 支付宝手续费：%s元", new Object[]{this.a.p, this.a.s}), this.a.r);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            onFailure(null, "参数错误，请重试");
        }
    }

    public void onFailure(String str, String str2) {
        this.a.dismissProgress();
        ToastAndDialog.makeText(this.a, str2).show();
    }
}
