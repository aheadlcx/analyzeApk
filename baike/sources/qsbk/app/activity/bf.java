package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class bf extends ProgressDialogCallBack {
    final /* synthetic */ ApplyForGroupActivity a;

    bf(ApplyForGroupActivity applyForGroupActivity, Context context, String str) {
        this.a = applyForGroupActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        int optInt = jSONObject.optInt("join_status");
        switch (optInt) {
            case 2:
                ContactListItem contactListItem = new ContactListItem();
                contactListItem.type = 3;
                contactListItem.id = String.valueOf(this.a.b.id);
                contactListItem.icon = this.a.b.icon;
                contactListItem.name = this.a.b.name;
                contactListItem.mLastUpdateTime = System.currentTimeMillis();
                contactListItem.mLastContent = "";
                contactListItem.unreadCount = 0;
                contactListItem.status = 5;
                ContactListItemStore.getContactStore(QsbkApp.currentUser.userId).insert(contactListItem);
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功加入该群!").show();
                break;
            default:
                ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "提交申请成功,请等待群大同意!").show();
                break;
        }
        this.a.a(this.a.b.id, optInt);
        this.a.setResult(optInt);
        this.a.finish();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
    }
}
