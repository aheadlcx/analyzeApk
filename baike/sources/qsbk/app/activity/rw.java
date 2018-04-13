package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;

class rw implements SimpleCallBack {
    final /* synthetic */ MainActivity a;

    rw(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("level");
        if (QsbkApp.currentUser != null) {
            QsbkApp.currentUser.remixLevel = optInt;
        }
    }

    public void onFailure(int i, String str) {
    }
}
