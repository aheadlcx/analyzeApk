package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.ToastAndDialog;

class mi implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ GroupInfoActivity b;

    mi(GroupInfoActivity groupInfoActivity, ProgressDialog progressDialog) {
        this.b = groupInfoActivity;
        this.a = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        ContactListItemStore.getContactStore(QsbkApp.currentUser.userId).delete(String.valueOf(this.b.G), 3);
        GroupNotifier.notify(this.b.G, 3);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功退出群!", Integer.valueOf(0)).show();
        this.a.dismiss();
    }

    public void onFailure(int i, String str) {
        this.a.dismiss();
    }
}
