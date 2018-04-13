package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class t implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ BlacklistFragment b;

    t(BlacklistFragment blacklistFragment, int i) {
        this.b = blacklistFragment;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.b.i.remove(this.a);
        this.b.h.notifyDataSetChanged();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
