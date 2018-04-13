package qsbk.app.activity.pay;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Laisee;

class au implements HttpCallBack {
    final /* synthetic */ Laisee a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ LaiseePaymentActivity e;

    au(LaiseePaymentActivity laiseePaymentActivity, Laisee laisee, String str, String str2, String str3) {
        this.e = laiseePaymentActivity;
        this.a = laisee;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
        Log.i(LaiseePaymentActivity.s, jSONObject.toString());
        if (optInt == 0) {
            Intent intent = new Intent();
            intent.putExtra("laisee", this.e.t);
            this.e.setResult(-1, intent);
            this.e.finish();
            return;
        }
        onFailure(null, jSONObject.optString("err_msg"));
    }

    public void onFailure(String str, String str2) {
        if (!this.e.isFinishing()) {
            Builder builder = new Builder(this.e, R.style.MyDialogStyleNormal);
            builder.setTitle(str2);
            builder.setPositiveButton("重试", new av(this, str));
            if ("904".equals(str)) {
                builder.setNegativeButton("找回支付密码", new aw(this));
            }
            builder.show();
            this.e.k();
            this.e.d.setVisibility(0);
            Log.i(LaiseePaymentActivity.s, str2);
        }
    }
}
