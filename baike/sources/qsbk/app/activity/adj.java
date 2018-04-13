package qsbk.app.activity;

import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.JoinedGroupGetter.CallBack;
import qsbk.app.utils.ToastAndDialog;

class adj implements CallBack {
    final /* synthetic */ TemporaryNoteActivity a;

    adj(TemporaryNoteActivity temporaryNoteActivity) {
        this.a = temporaryNoteActivity;
    }

    public void onSuccess(List<GroupInfo> list) {
        if (list.size() > 0) {
            for (GroupInfo groupInfo : list) {
                this.a.a.put(groupInfo.id, groupInfo);
            }
            this.a.k();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "没有加入群!", Integer.valueOf(0)).show();
    }

    public void onFail(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
