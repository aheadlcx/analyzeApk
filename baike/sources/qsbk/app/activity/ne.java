package qsbk.app.activity;

import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.JoinedGroupGetter.CallBack;
import qsbk.app.utils.ToastAndDialog;

class ne implements CallBack {
    final /* synthetic */ GroupMsgActivity a;

    ne(GroupMsgActivity groupMsgActivity) {
        this.a = groupMsgActivity;
    }

    public void onSuccess(List<GroupInfo> list) {
        if (list.size() > 0) {
            for (GroupInfo groupInfo : list) {
                this.a.l.put(groupInfo.id, groupInfo);
            }
            this.a.j();
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "没有加入群！", Integer.valueOf(0)).show();
    }

    public void onFail(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
