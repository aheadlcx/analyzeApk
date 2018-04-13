package com.ali.auth.third.core.task;

import android.app.Activity;
import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.callback.FailureCallback;
import com.ali.auth.third.core.callback.InitResultCallback;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.message.MessageUtils;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ReflectionUtils;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;

public class InitWaitTask extends TaskWithDialog<Void, Void, Void> {
    private Class<?> credentialServiceClazz;
    protected FailureCallback failureCallback;
    private Runnable r;
    private String taskName;

    public InitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable, String str) {
        super(activity);
        this.r = runnable;
        this.failureCallback = failureCallback;
        this.taskName = str;
        this.credentialServiceClazz = ReflectionUtils.loadClassQuietly("com.alibaba.sdk.android.session.CredentialService");
    }

    public InitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable) {
        this(activity, failureCallback, runnable, null);
    }

    protected Void asyncExecute(Void... voidArr) {
        Boolean checkInitStatus = KernelContext.checkInitStatus();
        if (checkInitStatus == null) {
            CommonUtils.onFailure(this.failureCallback, MessageUtils.createMessage(10012, new Object[0]));
        } else if (checkInitStatus.booleanValue()) {
            if (this.credentialServiceClazz != null) {
                Object service = KernelContext.serviceRegistry.getService(this.credentialServiceClazz, null);
                if (service != null) {
                    ReflectionUtils.invoke("com.alibaba.sdk.android.session.CredentialService", UserTrackerConstants.P_INIT, null, service, null);
                }
            }
            this.r.run();
        } else {
            MemberSDK.init(KernelContext.context, new InitResultCallback() {
                public void onFailure(int i, String str) {
                    CommonUtils.onFailure(InitWaitTask.this.failureCallback, i, str);
                }

                public void onSuccess() {
                    InitWaitTask.this.r.run();
                }
            });
        }
        return null;
    }

    protected void doWhenException(Throwable th) {
        CommonUtils.toastSystemException();
    }
}
