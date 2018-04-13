package qsbk.app.adapter;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class f implements SimpleCallBack {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.a.b != null && this.a.b.k != null) {
            this.a.b.notifyDataSetChanged();
        }
    }

    public void onFailure(int i, String str) {
        if (this.a.b.k != null) {
            ToastAndDialog.makeText(this.a.b.k, str).show();
        }
    }
}
