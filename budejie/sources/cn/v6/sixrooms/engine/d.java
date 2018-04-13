package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.engine.AuthCodeEngine.GetAuthCodeCallBack;

final class d implements AuthCodeEngine$OnRequestSuccessListener {
    final /* synthetic */ GetAuthCodeCallBack a;
    final /* synthetic */ AuthCodeEngine b;

    d(AuthCodeEngine authCodeEngine, GetAuthCodeCallBack getAuthCodeCallBack) {
        this.b = authCodeEngine;
        this.a = getAuthCodeCallBack;
    }

    public final void onRequestSuccess(String str) {
        this.a.success(str);
    }
}
