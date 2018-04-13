package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.ToastAndDialog;

class ll implements SimpleCallBack {
    final /* synthetic */ FinishGroupActivity a;

    ll(FinishGroupActivity finishGroupActivity) {
        this.a = finishGroupActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.t.dismiss();
        try {
            GroupInfo groupInfo = new GroupInfo(jSONObject.getJSONObject("tribe_detail"));
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "恭喜您，建群成功！", Integer.valueOf(0)).show();
            GroupInfoActivity.launch(this.a, groupInfo);
            ContactListItem contactListItem = new ContactListItem();
            contactListItem.type = 3;
            contactListItem.id = String.valueOf(groupInfo.id);
            contactListItem.icon = groupInfo.icon;
            contactListItem.name = groupInfo.name;
            contactListItem.mLastUpdateTime = System.currentTimeMillis();
            contactListItem.mLastContent = "";
            contactListItem.unreadCount = 0;
            contactListItem.status = 5;
            ContactListItemStore.getContactStore(QsbkApp.currentUser.userId).insert(contactListItem);
            BaseCreateGroupActivity.notifyToExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), str, Integer.valueOf(1)).show();
        this.a.a(str);
    }
}
