package com.ali.auth.third.login.task;

import android.app.Activity;
import com.ali.auth.third.core.model.KernelMessageConstants;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.task.InitWaitTask;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.login.callback.LogoutCallback;

public class LogoutTask extends InitWaitTask {

    /* renamed from: com.ali.auth.third.login.task.LogoutTask$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ LogoutCallback val$logoutCallback;

        AnonymousClass1(LogoutCallback logoutCallback) {
            this.val$logoutCallback = logoutCallback;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r3 = this;
            r0 = "logout task";
            r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0060 }
            r1.<init>();	 Catch:{ Exception -> 0x0060 }
            r2 = "into logout ";
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0060 }
            r2 = com.ali.auth.third.core.service.impl.CredentialManager.INSTANCE;	 Catch:{ Exception -> 0x0060 }
            r2 = r2.getInternalSession();	 Catch:{ Exception -> 0x0060 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0060 }
            r1 = r1.append(r2);	 Catch:{ Exception -> 0x0060 }
            r1 = r1.toString();	 Catch:{ Exception -> 0x0060 }
            com.ali.auth.third.core.trace.SDKLogger.e(r0, r1);	 Catch:{ Exception -> 0x0060 }
            r0 = com.ali.auth.third.core.service.impl.CredentialManager.INSTANCE;	 Catch:{ Exception -> 0x0060 }
            r0 = r0.getInternalSession();	 Catch:{ Exception -> 0x0060 }
            r0 = r0.user;	 Catch:{ Exception -> 0x0060 }
            r0 = r0.userId;	 Catch:{ Exception -> 0x0060 }
            r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0060 }
            if (r0 != 0) goto L_0x0037;
        L_0x0032:
            r0 = com.ali.auth.third.login.LoginComponent.INSTANCE;	 Catch:{ Exception -> 0x0060 }
            com.ali.auth.third.login.LoginComponent.logout();	 Catch:{ Exception -> 0x0060 }
        L_0x0037:
            r0 = com.ali.auth.third.login.context.LoginContext.credentialService;
            r0 = r0.logout();
            r1 = com.ali.auth.third.core.model.ResultCode.SUCCESS;
            r1 = r1.equals(r0);
            if (r1 == 0) goto L_0x005a;
        L_0x0045:
            r0 = com.ali.auth.third.login.context.LoginContext.rpcService;
            r0.logout();
            r0 = com.ali.auth.third.core.broadcast.LoginAction.NOTIFY_LOGOUT;
            com.ali.auth.third.core.util.CommonUtils.sendBroadcast(r0);
            r0 = com.ali.auth.third.core.context.KernelContext.executorService;
            r1 = new com.ali.auth.third.login.task.LogoutTask$1$1;
            r1.<init>();
            r0.postUITask(r1);
        L_0x0059:
            return;
        L_0x005a:
            r1 = r3.val$logoutCallback;
            com.ali.auth.third.core.util.CommonUtils.onFailure(r1, r0);
            goto L_0x0059;
        L_0x0060:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x008d }
            r0 = com.ali.auth.third.login.context.LoginContext.credentialService;
            r0 = r0.logout();
            r1 = com.ali.auth.third.core.model.ResultCode.SUCCESS;
            r1 = r1.equals(r0);
            if (r1 == 0) goto L_0x0087;
        L_0x0072:
            r0 = com.ali.auth.third.login.context.LoginContext.rpcService;
            r0.logout();
            r0 = com.ali.auth.third.core.broadcast.LoginAction.NOTIFY_LOGOUT;
            com.ali.auth.third.core.util.CommonUtils.sendBroadcast(r0);
            r0 = com.ali.auth.third.core.context.KernelContext.executorService;
            r1 = new com.ali.auth.third.login.task.LogoutTask$1$1;
            r1.<init>();
            r0.postUITask(r1);
            goto L_0x0059;
        L_0x0087:
            r1 = r3.val$logoutCallback;
            com.ali.auth.third.core.util.CommonUtils.onFailure(r1, r0);
            goto L_0x0059;
        L_0x008d:
            r0 = move-exception;
            r1 = com.ali.auth.third.login.context.LoginContext.credentialService;
            r1 = r1.logout();
            r2 = com.ali.auth.third.core.model.ResultCode.SUCCESS;
            r2 = r2.equals(r1);
            if (r2 == 0) goto L_0x00b1;
        L_0x009c:
            r1 = com.ali.auth.third.login.context.LoginContext.rpcService;
            r1.logout();
            r1 = com.ali.auth.third.core.broadcast.LoginAction.NOTIFY_LOGOUT;
            com.ali.auth.third.core.util.CommonUtils.sendBroadcast(r1);
            r1 = com.ali.auth.third.core.context.KernelContext.executorService;
            r2 = new com.ali.auth.third.login.task.LogoutTask$1$1;
            r2.<init>();
            r1.postUITask(r2);
        L_0x00b0:
            throw r0;
        L_0x00b1:
            r2 = r3.val$logoutCallback;
            com.ali.auth.third.core.util.CommonUtils.onFailure(r2, r1);
            goto L_0x00b0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ali.auth.third.login.task.LogoutTask.1.run():void");
        }
    }

    public LogoutTask(Activity activity, LogoutCallback logoutCallback) {
        super(activity, logoutCallback, new AnonymousClass1(logoutCallback), "E_LOGOUT");
    }

    protected void doWhenException(Throwable th) {
        CommonUtils.onFailure(this.failureCallback, ResultCode.create(KernelMessageConstants.GENERIC_SYSTEM_ERROR, new Object[]{th.getMessage()}));
    }
}
