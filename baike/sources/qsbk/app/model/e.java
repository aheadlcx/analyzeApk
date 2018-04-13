package qsbk.app.model;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.CircleVoteBuffer;

final class e implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ int b;

    e(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public void onSuccess(JSONObject jSONObject) {
    }

    public void onFailure(int i, String str) {
        CircleVoteBuffer.put(QsbkApp.getInstance(), this.a, this.b);
    }
}
