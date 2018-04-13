package qsbk.app.utils;

import java.util.ArrayList;
import java.util.List;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.JoinedGroupGetter.CallBack;

final class ak implements CallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CallBack b;

    ak(int i, CallBack callBack) {
        this.a = i;
        this.b = callBack;
    }

    public void onSuccess(List<GroupInfo> list) {
        for (GroupInfo groupInfo : list) {
            if (groupInfo.id == this.a) {
                new ArrayList().add(groupInfo);
                if (this.b != null) {
                    this.b.onSuccess(list);
                    return;
                }
                return;
            }
        }
    }

    public void onFail(int i, String str) {
        if (this.b != null) {
            this.b.onFail(i, str);
        }
    }
}
