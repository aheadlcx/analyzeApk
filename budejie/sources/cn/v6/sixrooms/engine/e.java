package cn.v6.sixrooms.engine;

final class e implements AuthCodeEngine$OnRequestSuccessListener {
    final /* synthetic */ AuthCodeEngine$VerifyAuthCodeCallBack a;
    final /* synthetic */ AuthCodeEngine b;

    e(AuthCodeEngine authCodeEngine, AuthCodeEngine$VerifyAuthCodeCallBack authCodeEngine$VerifyAuthCodeCallBack) {
        this.b = authCodeEngine;
        this.a = authCodeEngine$VerifyAuthCodeCallBack;
    }

    public final void onRequestSuccess(String str) {
        this.a.success(str);
    }
}
