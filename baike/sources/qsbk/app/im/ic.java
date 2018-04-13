package qsbk.app.im;

import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.JoinedGroupGetter.CallBack;

class ic implements CallBack {
    final /* synthetic */ ib a;

    ic(ib ibVar) {
        this.a = ibVar;
    }

    public void onSuccess(List<GroupInfo> list) {
        a();
    }

    public void onFail(int i, String str) {
        a();
    }

    void a() {
        if (QsbkApp.currentUser != null) {
            this.a.b.a(this.a.a);
        }
    }
}
