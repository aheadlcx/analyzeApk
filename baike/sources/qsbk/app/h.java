package qsbk.app;

import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.http.SimpleCallBack;

class h implements SimpleCallBack {
    final /* synthetic */ Callback a;
    final /* synthetic */ g b;

    h(g gVar, Callback callback) {
        this.b = gVar;
        this.a = callback;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.a != null) {
            this.a.onSuccess(jSONObject);
        }
    }

    public void onFailure(int i, String str) {
        if (this.a != null) {
            this.a.onFailed(i, str);
        }
    }
}
