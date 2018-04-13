package qsbk.app.adapter;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class d implements SimpleCallBack {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
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
