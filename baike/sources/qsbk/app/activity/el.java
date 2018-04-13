package qsbk.app.activity;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;

class el implements HttpCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ CheckInActivity b;

    el(CheckInActivity checkInActivity, String str) {
        this.b = checkInActivity;
        this.a = str;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (TextUtils.equals(this.a, this.b.q)) {
            this.b.o = true;
            this.b.O = this.b.O + 1;
        } else if (TextUtils.equals(this.a, this.b.r)) {
            this.b.p = true;
            this.b.P = this.b.P + 1;
        }
        this.b.H.onUpdate();
    }

    public void onFailure(String str, String str2) {
    }
}
