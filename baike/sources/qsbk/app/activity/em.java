package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;

class em implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    em(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.S = jSONObject.optInt("sign_left", -1);
        this.a.q();
        this.a.F.notifyDataSetChanged();
    }

    public void onFailure(String str, String str2) {
    }
}
