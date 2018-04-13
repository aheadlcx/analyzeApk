package com.alibaba.baichuan.android.trade.adapter.login;

import com.ali.auth.third.core.callback.InitResultCallback;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;

class a implements InitResultCallback {
    final /* synthetic */ AlibcLogin a;

    a(AlibcLogin alibcLogin) {
        this.a = alibcLogin;
    }

    public void onFailure(int i, String str) {
        AlibcLogger.e("AlibcLogin", "MemberSDK init error, code: " + i + " message: " + str);
    }

    public void onSuccess() {
        AlibcLogger.d("AlibcLogin", "MemberSDK init success");
    }
}
