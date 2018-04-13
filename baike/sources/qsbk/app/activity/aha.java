package qsbk.app.activity;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog.Builder;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class aha implements HttpCallBack {
    final /* synthetic */ WithdrawSetupActivity a;

    aha(WithdrawSetupActivity withdrawSetupActivity) {
        this.a = withdrawSetupActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.dismissProgressDialog();
        try {
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                ToastAndDialog.makeText(this.a, "绑定提现账户成功").show();
                Intent intent = new Intent();
                intent.putExtra(WithdrawSetupActivity.ALIPAY_NAME, this.a.g);
                this.a.setResult(-1, intent);
                this.a.finish();
                return;
            }
            onFailure(jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) + "", jSONObject.optString("err_msg"));
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(null, "绑定失败，请稍后重试");
        }
    }

    public void onFailure(String str, String str2) {
        this.a.dismissProgressDialog();
        if ("904".equals(str)) {
            Builder builder = new Builder(this.a, R.style.MyDialogStyleNormal);
            builder.setTitle(str2);
            builder.setPositiveButton("重试", new ahb(this));
            builder.setNegativeButton("找回支付密码", new ahc(this));
            builder.show();
            return;
        }
        ToastAndDialog.makeText(this.a, str2).show();
    }
}
