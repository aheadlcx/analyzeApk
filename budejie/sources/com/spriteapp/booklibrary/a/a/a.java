package com.spriteapp.booklibrary.a.a;

import android.support.annotation.NonNull;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.SignUtil;
import com.tencent.open.GameAppOperation;
import okhttp3.aa;
import okhttp3.t;

public class a implements t {
    public aa intercept(@NonNull okhttp3.t.a aVar) {
        return aVar.a(aVar.a().e().b("User-Agent", AppUtil.getUserAgent()).b("client-id", HuaXiSDK.getInstance().getClientId()).b("timestamp", String.valueOf(SignUtil.getCurrentTime())).b(GameAppOperation.QQFAV_DATALINE_VERSION, "1.0").b("sign", SignUtil.createSign("1.0")).b(IXAdRequestInfo.SN, AppUtil.getHeaderSnValue()).b(INoCaptchaComponent.token, SharedPreferencesUtil.getInstance().getString("hua_xi_token")).b());
    }
}
