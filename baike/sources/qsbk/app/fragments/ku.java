package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class ku implements SimpleCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ RandomDoorUsersFragment b;

    ku(RandomDoorUsersFragment randomDoorUsersFragment, boolean z) {
        this.b = randomDoorUsersFragment;
        this.a = z;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.b.m) {
            this.b.c();
            this.b.a(jSONObject, this.a);
            this.b.isLoading = false;
            return;
        }
        this.b.isLoading = false;
    }

    public void onFailure(int i, String str) {
        if (this.b.m) {
            this.b.c();
            if (i == -120) {
                this.b.a(null, this.a, str);
                this.b.isLoading = false;
                return;
            } else if (i == RandomDoorUsersFragment.EXHAUNT) {
                this.b.a(str);
                this.b.isLoading = false;
                return;
            } else {
                if (this.b.s == 1) {
                    this.b.b.refreshDone();
                } else {
                    this.b.b.setLoadMoreEnable(false);
                }
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
                this.b.show_restart();
                this.b.isLoading = false;
                return;
            }
        }
        this.b.isLoading = false;
    }
}
