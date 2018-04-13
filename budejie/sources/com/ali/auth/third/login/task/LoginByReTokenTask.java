package com.ali.auth.third.login.task;

import android.app.Activity;
import android.text.TextUtils;
import com.ali.auth.third.core.callback.LoginCallback;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.login.LoginComponent;
import com.ali.auth.third.login.UTConstants;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.login.util.LoginStatus;
import com.baidu.mobads.openad.c.b;
import com.google.analytics.tracking.android.HitTypes;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class LoginByReTokenTask extends AbsLoginByCodeTask {
    private LoginCallback loginCallback;

    public LoginByReTokenTask(Activity activity, LoginCallback loginCallback) {
        super(activity);
        this.loginCallback = loginCallback;
    }

    protected void doWhenResultFail(int i, String str) {
        if (this.loginCallback != null) {
            Map hashMap = new HashMap();
            hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, i + "");
            if (!TextUtils.isEmpty(str)) {
                hashMap.put(b.EVENT_MESSAGE, str);
            }
            ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_AUTO_LOGIN_FAILURE, hashMap);
            if (this.loginCallback != null) {
                this.loginCallback.onFailure(i, str);
            }
        }
    }

    protected void doWhenResultOk() {
        LoginStatus.resetLoginFlag();
        if (this.loginCallback != null) {
            ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_AUTO_LOGIN_SUCCESS, null);
            this.loginCallback.onSuccess(LoginContext.credentialService.getSession());
        }
    }

    protected RpcResponse<LoginReturnData> login(String[] strArr) {
        LoginComponent loginComponent = LoginComponent.INSTANCE;
        return LoginComponent.loginByRefreshToken();
    }

    protected void doWhenException(Throwable th) {
        LoginStatus.resetLoginFlag();
        Map hashMap = new HashMap();
        hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, "10010");
        hashMap.put(b.EVENT_MESSAGE, HitTypes.EXCEPTION);
        ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_AUTO_LOGIN_FAILURE, hashMap);
        CommonUtils.onFailure(this.loginCallback, ResultCode.create(KernelMessageConstants.GENERIC_SYSTEM_ERROR, new Object[]{th.getMessage()}));
    }
}
