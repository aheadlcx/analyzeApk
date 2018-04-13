package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

final class hv implements SimpleCallBack {
    hv() {
    }

    public void onSuccess(JSONObject jSONObject) {
        IMNotifyManager.parseAndSetIMNotification(jSONObject.toString());
    }

    public void onFailure(int i, String str) {
    }
}
