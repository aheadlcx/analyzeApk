package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;

class ec implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    ec(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            this.a.T.fromJson(jSONObject);
            this.a.n();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.n();
    }
}
