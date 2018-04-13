package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.login.interfaces.RegisterCallback;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class bm implements RegisterCallback {
    final /* synthetic */ RegisterActivity a;

    bm(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void getAuthCodeSuccess(String str) {
        LogUtils.e(RegisterActivity.a, "getAuthCode__success:" + str);
        this.a.handleErrorResult("001", str, this.a);
    }

    public final void loginOtherPlace(String str) {
    }

    public final void loginClientSuccess(String str, String str2) {
        LogUtils.e(RegisterActivity.a, "Register__loginClientSuccess:" + str);
        SaveUserInfoUtils.saveEncpass(this.a, str);
        RegisterActivity.a(this.a);
        Intent intent = new Intent();
        intent.putExtra("ticket", str);
        intent.putExtra("op", str2);
        intent.setAction(CustomBroadcast.COOPLOGIN_LOGIN);
        LocalBroadcastManager.getInstance(this.a).sendBroadcast(intent);
        this.a.setResult(-1, intent);
        this.a.finish();
    }

    public final void handleErrorInfo(String str, String str2) {
        LogUtils.e(RegisterActivity.a, "Register__handleErrorInfo:(" + str + ")" + str2);
        RegisterActivity.a(this.a);
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void getTicketSuccess(String str) {
        LogUtils.e(RegisterActivity.a, "Register__getTicketSuccess:" + str);
        this.a.a(R.string.authorization_success_register);
    }

    public final void getTicketError(int i) {
        LogUtils.e(RegisterActivity.a, "Register__getTicketError:" + i);
        RegisterActivity.a(this.a);
        RegisterActivity.b(this.a, i);
    }

    public final void perRegisterSuccess(boolean z) {
        LogUtils.e(RegisterActivity.a, "Register__perRegisterSuccess:" + z);
    }

    public final void perRegisterError(int i) {
        LogUtils.e(RegisterActivity.a, "Register__perRegisterError:" + i);
        RegisterActivity.a(this.a);
        RegisterActivity.b(this.a, i);
    }

    public final void error(int i) {
        LogUtils.e(RegisterActivity.a, "Register__error:" + i);
        RegisterActivity.a(this.a);
        this.a.showErrorToast(i);
    }
}
