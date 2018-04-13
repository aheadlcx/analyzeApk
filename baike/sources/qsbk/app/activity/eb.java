package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SplashAdManager.SplashAdItem;

class eb implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    eb(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("sign1");
        if (optJSONObject != null) {
            this.a.Q = new SplashAdItem(optJSONObject);
        }
        optJSONObject = jSONObject.optJSONObject("sign2");
        if (optJSONObject != null) {
            this.a.R = new SplashAdItem(optJSONObject);
        }
        this.a.n();
    }

    public void onFailure(String str, String str2) {
    }
}
