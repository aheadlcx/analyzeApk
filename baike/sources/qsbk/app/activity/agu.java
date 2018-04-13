package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class agu implements HttpCallBack {
    final /* synthetic */ WithdrawActivity a;

    agu(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.dismissProgress();
        try {
            if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                ToastAndDialog.makeText(QsbkApp.getInstance(), "提现申请已提交，等待处理").show();
                this.a.finish();
                WalletTradeListActivity.launch(this.a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.dismissProgress();
        if ("904".equals(str)) {
            Builder builder = new Builder(this.a, R.style.MyDialogStyleNormal);
            builder.setTitle(str2);
            if (!(this.a.r <= 0.0d || TextUtils.isEmpty(this.a.s) || TextUtils.isEmpty(this.a.p))) {
                builder.setPositiveButton("重试", new agv(this));
            }
            builder.setNegativeButton("找回支付密码", new agw(this));
            builder.show();
            return;
        }
        ToastAndDialog.makeText(this.a, str2).show();
    }
}
