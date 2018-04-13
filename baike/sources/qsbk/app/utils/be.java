package qsbk.app.utils;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.UserInfo;

final class be implements SimpleCallBack {
    final /* synthetic */ Context a;

    be(Context context) {
        this.a = context;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (QsbkApp.currentUser != null) {
            UserInfo.updateServerJson(QsbkApp.currentUser, jSONObject);
            boolean z = !QsbkApp.currentUser.hasPhone();
            TipsManager.b(this.a, z);
            if (z) {
                TipsManager.c(this.a);
            }
        }
    }

    public void onFailure(int i, String str) {
    }
}
