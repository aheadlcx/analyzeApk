package qsbk.app.activity;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class aav implements HttpCallBack {
    final /* synthetic */ ReBindPhoneActivity a;

    aav(ReBindPhoneActivity reBindPhoneActivity) {
        this.a = reBindPhoneActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("secret");
            Intent intent = new Intent();
            intent.putExtra("secret", optString);
            this.a.setResult(-1, intent);
            this.a.finish();
        } catch (Exception e) {
            e.printStackTrace();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "数据解析错误", Integer.valueOf(0)).show();
        }
        this.a.i();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.i();
    }
}
