package cn.tatagou.sdk.util;

import android.util.Log;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.view.IUpdateViewManager;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;

class a$2 implements AlibcLoginCallback {
    final /* synthetic */ c a;

    a$2(c cVar) {
        this.a = cVar;
    }

    public void onSuccess() {
        IUpdateViewManager.getInstance().notifyIUpdateView(TtgInterface.TB_AUTHORIZE, a.getTaoBaoUserInfo());
        if (this.a != null) {
            this.a.setTbLogin(1);
        }
        Log.d(a.a(), "AlibcLogin onSuccess code ");
    }

    public void onFailure(int i, String str) {
        if (TtgSDK.isDebug) {
            Log.d(a.a(), "AlibcLogin nFailure code : " + i + ",msg::" + str);
        } else {
            Log.d(a.a(), "AlibcLogin nFailure code : " + i + ",msg::" + str);
        }
    }
}
