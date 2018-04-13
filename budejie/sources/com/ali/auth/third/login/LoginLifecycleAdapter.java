package com.ali.auth.third.login;

import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.service.CredentialService;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.login.handler.LoginActivityResultHandler;
import com.ali.auth.third.login.util.LoginStatus;
import com.ali.auth.third.ui.support.ActivityResultHandler;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class LoginLifecycleAdapter {
    public static final String TAG = "Member.LoginLifecycleAdapter";

    public static void init() {
        SDKLogger.d(TAG, "LoginLifecycle init ");
        LoginContext.rpcService = (RpcService) KernelContext.getService(RpcService.class, null);
        LoginContext.credentialService = (CredentialService) KernelContext.getService(CredentialService.class, null);
        LoginContext.executorService = (ExecutorService) KernelContext.getService(ExecutorService.class, null);
        LoginContext.storageService = (StorageService) KernelContext.getService(StorageService.class, null);
        LoginActivityResultHandler loginActivityResultHandler = new LoginActivityResultHandler();
        KernelContext.registerService(new Class[]{ActivityResultHandler.class}, loginActivityResultHandler, Collections.singletonMap(ActivityResultHandler.REQUEST_CODE_KEY, String.valueOf(RequestCode.OPEN_H5_LOGIN)));
        KernelContext.registerService(new Class[]{ActivityResultHandler.class}, loginActivityResultHandler, Collections.singletonMap(ActivityResultHandler.REQUEST_CODE_KEY, String.valueOf(RequestCode.OPEN_TAOBAO)));
        KernelContext.registerService(new Class[]{ActivityResultHandler.class}, loginActivityResultHandler, Collections.singletonMap(ActivityResultHandler.REQUEST_CODE_KEY, String.valueOf(RequestCode.OPEN_DOUBLE_CHECK)));
        Map singletonMap = Collections.singletonMap(Constants.ISV_SCOPE_FLAG, Constants.SERVICE_SCOPE_FLAG_VALUE);
        Class[] clsArr = new Class[]{LoginService.class};
        KernelContext.registerService(clsArr, new LoginServiceImpl(), singletonMap);
        LoginStatus.init(KernelContext.getApplicationContext());
    }
}
