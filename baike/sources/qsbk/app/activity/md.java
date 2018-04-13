package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.ToastAndDialog;

class md implements SimpleCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ ProgressDialog b;
    final /* synthetic */ GroupInfoActivity c;

    md(GroupInfoActivity groupInfoActivity, boolean z, ProgressDialog progressDialog) {
        this.c = groupInfoActivity;
        this.a = z;
        this.b = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.a) {
            ContactListItemStore.getContactStore(QsbkApp.currentUser.userId).delete(String.valueOf(this.c.G), 3);
            GroupNotifier.notify(this.c.G, 3);
        }
        this.c.b.ownerId = 0;
        this.c.b.isOwner = false;
        this.c.H = false;
        GroupMemberManager groupMemberManager = new GroupMemberManager(this.c.G);
        BaseUserInfo member = groupMemberManager.getMember(QsbkApp.currentUser.userId);
        if (member != null) {
            member.isOwner = false;
            member.isAdmin = false;
            groupMemberManager.updateMember(member);
        }
        this.c.m();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功辞职!", Integer.valueOf(0)).show();
        this.b.dismiss();
    }

    public void onFailure(int i, String str) {
        this.b.dismiss();
    }
}
