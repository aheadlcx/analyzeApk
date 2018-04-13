package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class hl implements SimpleCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ NearbyUsersFragment b;

    hl(NearbyUsersFragment nearbyUsersFragment, boolean z) {
        this.b = nearbyUsersFragment;
        this.a = z;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.b.m) {
            this.b.a(jSONObject, this.a);
        }
    }

    public void onFailure(int i, String str) {
        if (!this.b.m) {
            return;
        }
        if (i == -120) {
            this.b.a(this.a, str);
            return;
        }
        if (this.b.t == 1) {
            this.b.b.refreshDone();
            this.b.show_restart();
        } else {
            this.b.b.setLoadMoreEnable(false);
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
