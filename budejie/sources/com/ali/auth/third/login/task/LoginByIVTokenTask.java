package com.ali.auth.third.login.task;

import android.app.Activity;
import android.text.TextUtils;
import com.ali.auth.third.core.callback.LoginCallback;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.message.MessageUtils;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.task.AbsAsyncTask;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.login.LoginComponent;
import com.ali.auth.third.login.UTConstants;
import com.baidu.mobads.openad.c.b;
import com.google.analytics.tracking.android.HitTypes;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class LoginByIVTokenTask extends AbsAsyncTask<String, Void, Void> {
    private static final String TAG = "login";
    private Activity mActivity;
    private LoginCallback mLoginCallback;

    public LoginByIVTokenTask(Activity activity, LoginCallback loginCallback) {
        this.mActivity = activity;
        this.mLoginCallback = loginCallback;
    }

    protected Void asyncExecute(String... strArr) {
        RpcResponse loginByIVToken;
        if (CommonUtils.isNetworkAvailable()) {
            loginByIVToken = LoginComponent.INSTANCE.loginByIVToken(strArr[0], strArr[1], strArr[2]);
            if (loginByIVToken == null || loginByIVToken.returnValue == null) {
                doWhenResultFail(ResultCode.SYSTEM_EXCEPTION.code, ResultCode.SYSTEM_EXCEPTION.message);
            } else if (loginByIVToken.code == ErrorCode.SERVER_OPERATIONTYPEMISSED) {
                KernelContext.credentialService.refreshWhenLogin((LoginReturnData) loginByIVToken.returnValue);
                doWhenResultOk();
            } else {
                doWhenResultFail(loginByIVToken.code, loginByIVToken.message);
            }
        } else {
            loginByIVToken = new RpcResponse();
            loginByIVToken.code = -1;
            loginByIVToken.message = ResourceUtils.getString("com_taobao_tae_sdk_network_not_available_message");
            doWhenResultFail(loginByIVToken.code, loginByIVToken.message);
        }
        return null;
    }

    protected void doWhenException(Throwable th) {
        Message createMessage = MessageUtils.createMessage(KernelMessageConstants.GENERIC_SYSTEM_ERROR, th.getMessage());
        SDKLogger.log("login", createMessage, th);
        Map hashMap = new HashMap();
        hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, "10010");
        hashMap.put(b.EVENT_MESSAGE, HitTypes.EXCEPTION);
        ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_H5_LOGIN_FAILURE, hashMap);
        doWhenResultFail(createMessage.code, createMessage.message);
    }

    protected void doFinally() {
    }

    private void doWhenResultFail(final int i, final String str) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                Map hashMap = new HashMap();
                hashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, i + "");
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put(b.EVENT_MESSAGE, str);
                }
                ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_H5_LOGIN_FAILURE, hashMap);
                if (LoginByIVTokenTask.this.mLoginCallback != null) {
                    LoginByIVTokenTask.this.mLoginCallback.onFailure(i, str);
                }
            }
        });
    }

    private void doWhenResultOk() {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                if (LoginByIVTokenTask.this.mLoginCallback != null) {
                    LoginByIVTokenTask.this.mLoginCallback.onSuccess(KernelContext.credentialService.getSession());
                }
            }
        });
    }
}
