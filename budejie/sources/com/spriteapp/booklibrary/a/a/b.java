package com.spriteapp.booklibrary.a.a;

import android.support.annotation.NonNull;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.spriteapp.booklibrary.enumeration.LoginModeEnum;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import okhttp3.aa;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.y;

public class b implements t {
    public aa intercept(@NonNull a aVar) {
        y a = aVar.a();
        aa a2 = aVar.a(a);
        String sVar = a.a().toString();
        Object obj = (sVar.contains(LoginModeEnum.CHANNEL_LOGIN.getValue()) || sVar.contains(LoginModeEnum.WECHAT_LOGIN.getValue()) || sVar.contains(LoginModeEnum.QQ_LOGIN.getValue()) || sVar.contains(LoginModeEnum.WEIBO_LOGIN.getValue())) ? 1 : null;
        if (obj != null) {
            SharedPreferencesUtil.getInstance().putString("hua_xi_token", a2.a(INoCaptchaComponent.token));
        }
        return a2;
    }
}
