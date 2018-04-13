package cn.tatagou.sdk.util;

import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.view.IUpdateViewManager;
import com.ali.auth.third.login.callback.LogoutCallback;

class a$1 implements LogoutCallback {
    final /* synthetic */ c a;

    a$1(c cVar) {
        this.a = cVar;
    }

    public void onSuccess() {
        IUpdateViewManager.getInstance().notifyIUpdateView(TtgInterface.TB_AUTHORIZE, null);
        if (this.a != null) {
            this.a.setTbLogin(-1);
        }
    }

    public void onFailure(int i, String str) {
    }
}
