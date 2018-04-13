package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.engine.AuthCodeEngine.GetAuthCodeCallBack;

final class c implements AuthCodeEngine$OnRequestSuccessListener {
    final /* synthetic */ GetAuthCodeCallBack a;
    final /* synthetic */ AuthCodeEngine b;

    c(AuthCodeEngine authCodeEngine, GetAuthCodeCallBack getAuthCodeCallBack) {
        this.b = authCodeEngine;
        this.a = getAuthCodeCallBack;
    }

    public final void onRequestSuccess(String str) {
        this.a.success(str);
    }
}
