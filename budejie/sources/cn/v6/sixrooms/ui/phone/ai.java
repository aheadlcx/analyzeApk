package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.AuthCodeEngine.GetAuthCodeCallBack;

final class ai implements GetAuthCodeCallBack {
    final /* synthetic */ ah a;

    ai(ah ahVar) {
        this.a = ahVar;
    }

    public final void success(String str) {
        this.a.a.notifyRecieveCode(str);
    }
}
