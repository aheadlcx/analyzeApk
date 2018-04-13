package qsbk.app.activity.security;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class r implements HttpCallBack {
    final /* synthetic */ PhoneVerifyActivity a;

    r(PhoneVerifyActivity phoneVerifyActivity) {
        this.a = phoneVerifyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        String optString = jSONObject.optString("secret");
        Intent intent = new Intent();
        intent.putExtra("secret", optString);
        this.a.setResult(-1, intent);
        this.a.finish();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeText(this.a, str2).show();
    }
}
